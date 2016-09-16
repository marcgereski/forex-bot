package kz.kase.bot.storage;

import kz.kase.bot.storage.dbs.HazelcastDb;


public class InMemoryHazelcastStorage extends InMemoryStorage {
    private final static InMemoryHazelcastStorage instance = new InMemoryHazelcastStorage();

    public InMemoryHazelcastStorage() {
        super();
        DbSource source = new HazelcastDb();
        setDbSource(source);
    }

    public static InMemoryHazelcastStorage getInstance() {
        return instance;
    }

    @Override
    public void setDbSource(DbSource source) {
        this.source = source;
    }
}
