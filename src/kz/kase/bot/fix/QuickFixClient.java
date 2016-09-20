package kz.kase.bot.fix;


import kz.kase.bot.client.OrderGenerator;
import kz.kase.bot.model.EventBus;
import kz.kase.bot.model.domain.FixUpdate;
import kz.kase.fix.EncryptMethod;
import kz.kase.fix.factory.KaseFixMessageFactory;
import kz.kase.fix.messages.Logon;
import kz.kase.fix.messages.Logout;
import org.apache.log4j.Logger;
import quickfix.*;
import quickfix.logging.LogFactory;
import quickfix.logging.NextLogFactory;
import quickfix.store.FileStoreFactory;
import quickfix.store.MessageStoreFactory;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class QuickFixClient implements Application {

    public static final String FIX_EVENT = "FIX_EVENT";
    public static final String FIX_SESSION_EVENT = "FIX_SESSION_EVENT";
    public static final String FIX_LOGGED_IN = "LOGGED_IN";
    public static final String FIX_LOGGED_OUT = "LOGGED_OUT";
    public static final String RECONNECT_ATTEMPTS = "ReconnectAttempts";
    public static final String WRONG_PASSWORD = "Wrong password";
    public static final String WRONG_USER = "Wrong user";
    public static final String ALREADY_ONLINE = "Already online";
    public static final int MAX_RAND_INT = 1000000;
    public SessionID sessionID;

    public static enum FixServerType {
        Transactional, MarketData
    }

    private final CountDownLatch logonLatch = new CountDownLatch(1);

    private static Random refRand = new Random();
    private SocketInitiator initiator;

    private final String login, password;
    private String memberName;
    private final EventBus eventBus;
    private Session session;
    private final FixMessReceiver fixMessReceiver;
    private long reconnectQnt;
    private long reconnectAttempts = 3;

    private static boolean isMD = false;

    public static boolean isMD() {
        return isMD;
    }

    public static void setMD(boolean stat) {
        isMD = stat;
    }

    private final static Logger log = Logger.getLogger(OrderGenerator.class);

    public QuickFixClient(String login, String password,
                          EventBus eventBus,
                          FixMessReceiver fixListener,
                          SessionSettings settings)
            throws ConfigError, FileNotFoundException {

        this.login = login;
        this.password = password;
        this.eventBus = eventBus;
        this.fixMessReceiver = fixListener;

        try {
            reconnectAttempts = settings.getLong(RECONNECT_ATTEMPTS);
        } catch (ConfigError | FieldConvertError configError) {
            configError.printStackTrace();
        }

        initSocket(settings);

        System.out.println("initiator created");

    }

    @Override
    public void onCreate(SessionID sessionID) {
    }

    @Override
    public void onLogon(SessionID sessionID) {
        log.info("FixApplication.onLogon: " + sessionID);
        if (Session.doesSessionExist(sessionID)) {
            session = Session.lookupSession(sessionID);
        }
        logonLatch.countDown();
        this.sessionID = sessionID;
        eventBus.fireOnEvent(FIX_SESSION_EVENT, FIX_LOGGED_IN);
    }

    @Override
    public void onLogout(SessionID sessionID) {
        log.info("FixApplication.onLogout: " + sessionID);
        this.sessionID = sessionID;
        eventBus.fireOnEvent(FIX_SESSION_EVENT, FIX_LOGGED_OUT);
    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {
        if (message instanceof Logon) {
            Logon logon = (Logon) message;
            logon.setPassword(password);
            logon.setUsername(login);
            logon.setEncryptMethod(EncryptMethod.NONE_OTHER);
            logon.setResetSeqNum(true);
            logon.setText("9999");
        }
    }

    @Override
    public void toApp(Message message, SessionID sessionID) throws DoNotSend {
        log.info("TO_APP " + message.toString());
    }

    private boolean reconnecting = false;

    @Override
    public void fromAdmin(Message message, SessionID sessionID) throws IncorrectDataFormat, IncorrectTagValue, RejectLogon {
        if (message instanceof Logout) {
            Logout logout = (Logout) message;

            switch (logout.getText()) {
                case WRONG_PASSWORD:
                    log.error("Wrong password");
                    eventBus.fireOnEvent(FIX_SESSION_EVENT, logout);
                    System.exit(0);
                case WRONG_USER:
                    log.error("Wrong user");
                    eventBus.fireOnEvent(FIX_SESSION_EVENT, logout);
                    System.exit(0);
                case ALREADY_ONLINE:
                    log.error("Already online");
                    eventBus.fireOnEvent(FIX_SESSION_EVENT, logout);
                    break;
                default:
                    reconnectQnt++;
                    reconnecting = true;
                    if (reconnectQnt > reconnectAttempts) {
                        System.out.println("logout");
                        eventBus.fireOnEvent(FIX_SESSION_EVENT, logout);
                        reconnectQnt = 0;
                    }
            }
        }

        if (message instanceof Logon) {
            Logon logon = (Logon) message;
            if (logon.hasMemberName()) {
                memberName = logon.getMemberName();
            }
            if (reconnecting) {

//                TradeClient.getInstance().sendAfterReconnect();
                //todo open when stoke will come
//                TradeClient.getInstance().sendMMLiabilityRequest();
//                TradeClient.getInstance().sendIndiQuoteRequest();
                reconnecting = false;
            }
            reconnectQnt = 0;
        }
//        eventBus.fireOnEvent(FIX_EVENT, message);
    }

    @Override
    public void fromApp(Message message, SessionID sessionID) throws IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        try {
            FixUpdate fu = fixMessReceiver.process(message);
            eventBus.fireOnEvent(FIX_EVENT, fu);
        } catch (Throwable e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private void initSocket(SessionSettings settings) {
        MessageStoreFactory storeFactory = new FileStoreFactory(settings);
        LogFactory nextFixLog = new NextLogFactory(settings);
        MessageFactory mesFactory = new KaseFixMessageFactory();

        try {
            initiator = new SocketInitiator(this, storeFactory, settings, nextFixLog, mesFactory);
        } catch (ConfigError configError) {
            configError.printStackTrace();
        }
    }

    public void awaitLogon() throws InterruptedException {
        logonLatch.await();
    }

    public void sendMessage(Message message) {
        if (session != null /*&& validate(message, session)*/) {
            session.send(message);
        }
    }

    public void start() throws ConfigError {
        initiator.start();
    }

    public void logout() {
        log.info("Logging out");
        for (SessionID sessionId : initiator.getSessions()) {
            Session session = Session.lookupSession(sessionId);
            if (session != null) {
                session.logout("user requested");
                log.info("Session " + sessionId + " stopped");
            }
        }

        initiator.stop(true);
//        initiator.getInitiators().clear();

        log.info("Socket initiator stopped");
//        TradeClient.getInstance().getEventBus().removeListener(this);
    }

    public SessionID getSessionID() {
        return sessionID;
    }

    public synchronized static long nextRef() {
        return refRand.nextInt(MAX_RAND_INT);
    }
}
