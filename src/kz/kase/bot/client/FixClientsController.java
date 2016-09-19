package kz.kase.bot.client;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixClientsController {
    public static final Logger log = Logger.getLogger(FixClientsController.class);
    public static final String CLIENT_PROPERTIES = "client.properties";
    public static final String LOG4J_PROPERTIES = "log4j.xml";
    public static final String ROOT_USER = "root.user";
    public static final String ROOT_PASS = "root.pass";
    public static final String USERS_LIST = "users.list";
    public static final String INITIAL_DEALY = "initial.dealy";
    public static final String PERIOD = "period";
    public static final String NUMBER_OF_THREADS = "number.of.threads";
    public static final String DEFAULT_INITIAL_DELAY = "10";
    public static final String DEFAULT_PERIOD = "30";
    public static final String DEFAULT_NUMBER_OF_THREADS = "10";

    String rootUser;
    String rootPassword;
    List<String> users;
    long initialDelay;
    long period;
    int numberOfPools;
    Map<String, FixClient> clients = new HashMap<>();

    public FixClientsController(String rootUser, String rootPassword, List<String> users,
                                long initialDelay, long period, int numberOfPools) {
        this.rootUser = rootUser;
        this.users = users;
        this.initialDelay = initialDelay;
        this.period = period;
        this.numberOfPools = numberOfPools;
        this.rootPassword = rootPassword;
    }

    public void start() throws Exception {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfPools);

        for (String user : users) {
            clients.put(user, new FixClient());
            clients.get(user).startFixApp(user, rootPassword);
            System.out.println("User " + user + " authorized");
            if (user.equals(rootUser)) clients.get(user).subscribeToAll();

            if (user.equals(rootUser)) continue;

            service.scheduleWithFixedDelay(() -> {
                FixClient client = clients.get(user);
                boolean done = client.doSendRandomOrder(user);
                if (done) log.info(user + " sent order :)");
                else log.info(user + " - sending order was unsuccessful :(");
            }, initialDelay, period, TimeUnit.SECONDS);
        }
    }

    public void addShutDownHook() {
        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Stopping clients...");
                clients.values().forEach(FixClient::stopClient);
                log.info("Stopped");
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
