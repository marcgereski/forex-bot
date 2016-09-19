package kz.kase.bot;

import kz.kase.bot.client.FixClientsController;
import org.apache.log4j.xml.DOMConfigurator;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentMap;

import static kz.kase.bot.client.FixClientsController.*;

public class Main {

    public static void main(String[] args) throws Exception {
        DOMConfigurator.configure(LOG4J_PROPERTIES);

        Properties props = new Properties();
        Reader reader = new FileReader(new File(CLIENT_PROPERTIES));
        props.load(reader);

        String rootUser = props.getProperty(ROOT_USER);
        String rootPassword = props.getProperty(ROOT_PASS);
        List<String> users = Arrays.asList(props.getProperty(USERS_LIST).replaceAll(" ", "").split(","));
        long initialDelay = Integer.parseInt(props.getProperty(INITIAL_DEALY, DEFAULT_INITIAL_DELAY));
        long period = Integer.parseInt(props.getProperty(PERIOD, DEFAULT_PERIOD));
        int numberOfPools = Integer.parseInt(props.getProperty(NUMBER_OF_THREADS, DEFAULT_NUMBER_OF_THREADS));

        FixClientsController controller = new FixClientsController(rootUser, rootPassword, users, initialDelay, period, numberOfPools);
        controller.start();
    }
}
