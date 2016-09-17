package kz.kase.bot.requests;


import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.InMemoryHazelcastStorage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class Orders {
    private InMemoryHazelcastStorage storage = InMemoryHazelcastStorage.getInstance();
    private Random random = new Random(System.currentTimeMillis());

    @Before
    public void fillHolders() {

        InstrHolder instr = new InstrHolder();
        instr.setSymbol("USDKZT_TOD");
        instr.setAccName("F_063_0000");
        instr.setLastPx(340.0);
        instr.setDevLimitLastDealPrc(1D);
        instr.setMinQty(50000);

        storage.put(instr, instr.getSymbol());


        InstrHolder instr1 = new InstrHolder();
        instr1.setSymbol("EURKZT_TOD");
        instr1.setAccName("F_063_0000");
        instr1.setLastPx(380.0);
        instr1.setDevLimitLastDealPrc(1D);
        instr1.setMinQty(50000);

        storage.put(instr1, instr1.getSymbol());
    }

    @After
    public void stopAll() {
        InMemoryHazelcastStorage.getInstance().disconnectDb();
    }

    @Test
    public void createRandomOrder() {
        List<InstrHolder> instrs = storage.findAll(InstrHolder.class);
        Assert.assertNotNull(instrs);

        int num = instrs.size();
        Assert.assertTrue(num > 0);

        for (int i = 0; i < 10; i++) {
            int index = nextRandomIdx(num);
            InstrHolder instr = instrs.get(index);
            double price = nextRandomPrice(instr.getLastPx(), (int) Math.round(instr.getDevLimitLastDealPrc() * 1000));
            double maxPrice = price + instr.getDevLimitLastDealPrc();
            double minPrice = price - instr.getDevLimitLastDealPrc();
            System.out.println(instr.getSymbol() + ": " + price);
            Assert.assertTrue(price >= minPrice && price <= maxPrice);
        }
    }

    private int nextRandomIdx(int num) {
        return random.nextInt(num);
    }

    private double nextRandomPrice(double lastDealPrice, int deviationInMilliPercent) {
        int d = random.nextInt(2 * deviationInMilliPercent) - deviationInMilliPercent;
        return lastDealPrice + lastDealPrice * d / 100000;
    }
}
