package kz.kase.bot.storage;


import kz.kase.bot.model.domain.Holder;
import kz.kase.bot.storage.predicate.Predicate;

import java.util.ArrayList;
import java.util.List;

abstract class InMemoryStorage implements Storage {
    DbSource source;

    @Override
    public <T> T get(Class<T> type, Object id) {
        return source.get(id, type);
    }

    @Override
    public void put(Object obj, Object key) {
        source.put(key, obj);
    }

    @Override
    public void put(Holder holder) {
        source.put(holder.getKey(), holder);
    }

    @Override
    public Object remove(Class type, Object key) {
        return null;
    }

    @Override
    public <T> T findFirst(Class<T> type, Predicate<T> predicate) {
        List<T> list = new ArrayList<>(source.getAll(type));
        for (T t : list) {
            if (predicate.matches(t)) return t;
        }
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> type, Predicate<T> predicate) {
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> type) {
        return new ArrayList<>(source.getAll(type));
    }

    public abstract void setDbSource(DbSource source);

    public DbSource getSource() {
        return source;
    }

    public void disconnectDb() {
        source.disconnect();
    }

}
