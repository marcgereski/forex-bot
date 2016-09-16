package kz.kase.bot.storage;


import kz.kase.bot.TestClient;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.HashMap;
import java.util.Map;

public class TestClientStuff {
    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure("log4j.xml");

        String[] users = {"01207", "01201", "01323"};
        Map<String, TestClient> clients = new HashMap<>();

        for (String user : users) {
            clients.put(user, new TestClient());
            clients.get(user).startFixApp(user, "onion");
            System.out.println("Logon " + user);
        }

        Thread.sleep(10000);

        for (String login : users) {
            clients.get(login).stopClient();
            System.out.println("Stopped " + login);
        }

        System.exit(0);
    }
}
