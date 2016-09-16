package kz.kase.bot.storage;


public interface DbSource {
    void connect();
    void disconnect();
    void setInitialData();
    <K, V> void put(K key, V value);
    <K, C> void remove(K key, Class<C> type);
    <K, V> void add(K key, V value);
    <K, C> C get(K key, Class<C> type);
}
