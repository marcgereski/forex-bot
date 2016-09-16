package kz.kase.bot.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventBus {

    private final List<Listener> listeners =
            new CopyOnWriteArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(EventBus.class);

    public EventBus() {
        log.info("Event bus initialing");
    }

    public void addListener(Listener l) {
        log.debug("Adding a listener " + l.getClass().getSimpleName());
        listeners.add(l);
    }

    public void removeListener(Listener l) {
        if (listeners.contains(l)) {
            log.debug("Removing a listener " + l.getClass().getSimpleName());
            listeners.remove(l);
        }
    }

    public void fireOnEvent(final String eventName) {
        fireOnEvent(eventName, null);
    }

    public void fireOnEvent(final String eventName, final Object data) {
        log.debug("Get event :\n" + eventName + "\n" + data);
        for (Listener l : listeners) {
            try {
                l.onEvent(eventName, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static interface Listener {
        void onEvent(String eventName, Object data);
    }

    public List<Listener> getListeners() {
        return listeners;
    }


    //    private static final EventBus instance = new EventBus();
//
//    public static EventBus getInstance() {
//        return instance;
//    }
}
