package kz.kase.bot.storage.dbs;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.DbSource;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


public class HazelcastDb implements DbSource {
    private final HazelcastInstance hazelcast;

    public HazelcastDb() {
        this.hazelcast = Hazelcast.newHazelcastInstance(null);
    }

    @Override
    public void connect() {
    }

    @Override
    public void disconnect() {
        hazelcast.shutdown();
    }

    @Override
    public void setInitialData() {
    }

    @Override
    public <K, V> void put(K key, V value) {
        IMap<K, V> map = hazelcast.getMap(value.getClass().getSimpleName());
        map.put(key, value);
    }

    @Override
    public <K, C> void remove(K key, Class<C> type) {
        IMap<K, Object> map = hazelcast.getMap(type.getSimpleName());
        map.remove(key);
    }

    @Override
    public <K, V> void add(K key, V value) {
        IMap<K, V> map = hazelcast.getMap(value.getClass().getSimpleName());
        map.putIfAbsent(key, value);
    }

    @Override
    public <K, C> C get(K key, Class<C> type) {
        IMap<K, C> map = hazelcast.getMap(type.getSimpleName());
        return map.get(key);
    }

    @Override
    public <C> Collection<C> getAll(Class<C> type) {
        IMap<Object, C> map = hazelcast.getMap(type.getSimpleName());
        return map.values();
    }
}
