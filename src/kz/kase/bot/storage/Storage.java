package kz.kase.bot.storage;


import kz.kase.bot.model.domain.Holder;
import kz.kase.bot.storage.predicate.*;


import java.util.List;

public interface Storage {

    <T> T get(Class<T> type, Object id);

    void put(Object obj, Object key);

    void put(Holder holder);

    Object remove(Class type, Object key);

    <T> T findFirst(Class<T> type, Predicate<T> predicate);

    <T> List<T> findAll(Class<T> type, Predicate<T> predicate);

    <T> List<T> findAll(Class<T> type);
}
