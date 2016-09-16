package kz.kase.bot;


import kz.kase.bot.fix.FixMessReceiver;
import kz.kase.bot.fix.FixParserFactory;
import kz.kase.bot.fix.FixRequest;
import kz.kase.bot.fix.QuickFixClient;
import kz.kase.bot.model.EventBus;
import kz.kase.bot.model.domain.FixUpdate;
import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.InMemoryHazelcastStorage;
import kz.kase.bot.utils.certificate.SSLInfo;
import kz.kase.fix.SecurityRequestResult;
import kz.kase.fix.SubscriptionType;
import kz.kase.fix.core.FixUtils;
import kz.kase.fix.messages.Logout;
import kz.kase.fix.messages.SecurityList;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import quickfix.ConfigError;
import quickfix.FieldConvertError;
import quickfix.SessionSettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.KeyStoreException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TestClient implements EventBus.Listener {
    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    public static final String FIX_CFG = "fix-client-config.cfg";
    public static final String PARAM_SERVER_TYPE = "ServerType";
    public static final String SOCKET_HOST = "SocketConnectHost";
    public static final String SOCKET_PORT = "SocketConnectPort";
    public static final String SOCKET_USE_SSL = "SocketUseSSL";
    public static final String SOCKET_KEY_STORE = "SocketKeyStore";
    public static final String TARGET_COMP_ID = "TargetCompID";
    private boolean useSSL = false;

    private final EventBus eventBus;
    private QuickFixClient fixClient;
    private static SessionSettings settings;
    private boolean authorizationAccepted;
    private long orderStatusRequestRef;
    private QuickFixClient.FixServerType serverType;
    private boolean firstLoad = true;

    public TestClient() throws FileNotFoundException, ConfigError, InterruptedException {
        log.info("TradeClient initializing");
        eventBus = new EventBus();
    }

    public void startFixApp(String login, String pass) throws Exception {
        settings = new SessionSettings(new FileInputStream(FIX_CFG));
        FixParserFactory.init(getFixServerType());

        logClientInfo(login);

        FixUtils.addSession(settings, login);
        fixClient = new QuickFixClient(login, pass,
                eventBus,
                new FixMessReceiver(InMemoryHazelcastStorage.getInstance()),
                settings
        );

        fixClient.start();

        fixClient.awaitLogon();
    }

    @NotNull
    private QuickFixClient.FixServerType getFixServerType() {
        String srvTypeStr = settings.getDefaultProperties()
                .getProperty(PARAM_SERVER_TYPE, QuickFixClient.FixServerType.Transactional.name());
        return QuickFixClient.FixServerType.valueOf(srvTypeStr);
    }

    private void stop() {
        settings.removeSession(fixClient.getSessionID());
        fixClient.logout();
        InMemoryHazelcastStorage.getInstance().disconnectDb();
    }

    public void stopClient() {
/*        service.stopFixClient();
        service.cancel();*/
        stop();
    }

    public void sendAfterReconnect() {
        FixRequest.sendSecurityRequest(fixClient);
        FixRequest.sendPositionRequest(fixClient);
        FixRequest.sendOrderStatusRequest(fixClient, orderStatusRequestRef);
//        sendOrderStatusRequest();
//        sendCalendarRequest();
    }

    private void logClientInfo(String login) throws ConfigError, FieldConvertError, KeyStoreException {
        log.info("   Starting fix client");
        log.info("   Connecting to FixGw");
        log.info("   Server Targ.comp.id: " + settings.getString(TARGET_COMP_ID));
        log.info("   Server host        : " + settings.getString(SOCKET_HOST));
        log.info("   Server port        : " + settings.getString(SOCKET_PORT));
        log.info("   User               : " + login);
        log.info("   Use SSL            : " + useSSL);

        if (useSSL) {
            log.info("   KeyStore   \t: " + settings.getString(SOCKET_KEY_STORE));
            log.info("");
            SSLInfo sslInfo = new SSLInfo();
            sslInfo.load(settings.getString(SOCKET_KEY_STORE),
                    (settings.getString("SocketKeyStorePassword").toCharArray()));
            sslInfo.showInfo();
            log.info("");
        }
    }

    @Override
    public void onEvent(String eventName, Object data) {
        if (QuickFixClient.FIX_SESSION_EVENT.equals(eventName)) {
            if (data instanceof Logout) {
                if (!authorizationAccepted) {
                    stopClient();
                }
            } else if (QuickFixClient.FIX_LOGGED_IN.equals(data)) {
                sendAfterReconnect();
                authorizationAccepted = true;

            } else if (QuickFixClient.WRONG_PASSWORD.equals(data)) {
                authorizationAccepted = false;
                log.error("WRONG_PASSWORD");
            }
        } else if (QuickFixClient.FIX_EVENT.equals(eventName)) {
            switch (eventName) {
                case QuickFixClient.FIX_EVENT:
                    if (data instanceof FixUpdate) {
                        FixUpdate fu = (FixUpdate) data;
                        if (fu.getMessage() instanceof SecurityList) {
                            SecurityList list = (SecurityList) fu.getMessage();
                            if (list.getSecRequestResult() == SecurityRequestResult.VALID_REQUEST) {
                                List<String> symbols = new ArrayList<>();
                                Collection<InstrHolder> instrs =
                                        (Collection<InstrHolder>) fu.getObject();
                                for (InstrHolder instr : instrs) {
                                    symbols.add(instr.getSymbol());
                                }

                                if (!symbols.isEmpty() && firstLoad) {
                                    subscribe(symbols);
                                }
                            }
                        }
                    }
                    break;
                case QuickFixClient.FIX_SESSION_EVENT:
                    if (data.equals(QuickFixClient.FIX_LOGGED_OUT)) {
                        resetFirsInstrs();
                    }
                    break;
            }
        }
    }

    public void subscribe(List<String> symbols) {
        SubscriptionType type = SubscriptionType.SNAPSHOT_AND_UPDATES;

        StringBuilder logBuf = new StringBuilder();
        logBuf.append("\nSending market data request and order status request:\n");
        logBuf.append("\ttype: ").append(type).append("\n");
        log.info(logBuf);
        if (serverType != QuickFixClient.FixServerType.MarketData) {
            FixRequest.sendMarketDataRequest(fixClient, symbols, QuickFixClient.nextRef(), type);
        }
        firstLoad = false;
    }

    public void resetFirsInstrs() {
        firstLoad = true;
    }

    public static void main(String[] args) throws Exception {
        String login = "00908";

        TestClient client = new TestClient();
        client.startFixApp(login, "onion");
        System.out.println("Logon done...");
        if (login.equals("00908")) client.sendAfterReconnect();

//        Thread.sleep(15000);
//        client.stopClient();
//        System.out.println("Stopped");

//        System.exit(0);
    }
}
