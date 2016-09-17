package kz.kase.bot.client;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FixClientsController {
    public static final Logger log = Logger.getLogger(FixClientsController.class);

    public static final int INITIAL_DELAY = 10;
    public static final int PERIOD = 30;

    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure("log4j.xml");

        String rootUser = "01201";
        String[] users = {"01207", "01201", "01323"};
        long initialDelay = INITIAL_DELAY;
        long period = PERIOD;
        int numberOfPools = 10;
        String rootPassword = "onion";

        Map<String, FixClient> clients = new HashMap<>();
        ScheduledExecutorService service = Executors.newScheduledThreadPool(numberOfPools);

        for (String user : users) {
            clients.put(user, new FixClient());
            clients.get(user).startFixApp(user, rootPassword);
            System.out.println("User " + user + " authorized");
            if (user.equals(rootUser)) clients.get(user).subscribeToAll();

            if (user.equals(rootUser)) continue;

            service.scheduleWithFixedDelay(() -> {
                FixClient client = clients.get(user);
                boolean done = client.doSendRandomOrder();
                if (done) log.info(user + " sent order :)");
                else log.info(user + " - sending order was unsuccessful :(");
            }, initialDelay, period, TimeUnit.SECONDS);
        }
    }
}
