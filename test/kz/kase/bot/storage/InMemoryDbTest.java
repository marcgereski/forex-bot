package kz.kase.bot.storage;


import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.dbs.HazelcastDb;
import org.junit.*;

public class InMemoryDbTest {
    private InstrHolder h1, h2;
    private DbSource db;

    @Before
    public void init() {
        h1 = new InstrHolder();
        h1.setSymbol("Hello1");

        h2 = new InstrHolder();
        h2.setSymbol("Hello2");
    }

    @Ignore
    @Test
    public void UnitHazelcastDb() {
        db = new HazelcastDb();
        db.connect();
        db.setInitialData();

        db.put(1L, h1);
        db.put(2L, h2);
        InstrHolder h = db.get(1L, InstrHolder.class);

        Assert.assertEquals(h1.getSymbol(), h.getSymbol());

        db.disconnect();
    }

    @Test
    public void InMemoryDbTest() {
        Storage storage = InMemoryHazelcastStorage.getInstance();

        storage.put(h1, h1.getKey());
        InstrHolder h = storage.get(InstrHolder.class, h1.getKey());
        Assert.assertEquals(h1.getSymbol(), h.getKey());

        InMemoryHazelcastStorage.getInstance().disconnectDb();
    }
}
