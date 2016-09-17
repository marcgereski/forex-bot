package kz.kase.bot.client;


import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.Storage;
import kz.kase.fix.Side;
import kz.kase.fix.messages.NewOrderSingle;

import java.util.List;
import java.util.Random;

public class OrderGenerator {
    private final Random random;
    private final Storage storage;

    public OrderGenerator(Storage storage) {
        this.random = new Random(System.currentTimeMillis());
        this.storage = storage;
    }

    public int nextRandomIdx(int num) {
        return random.nextInt(num);
    }

    public Side nextRandomSide() {
        int n = random.nextInt(2);
        if (n == 0) return Side.BUY;
        else return Side.SELL;
    }

    public double nextRandomPrice(double lastDealPrice, int deviationInMilliPercents) {
        int d = random.nextInt(2 * deviationInMilliPercents) - deviationInMilliPercents;
        return lastDealPrice + lastDealPrice * d / 100000;
    }

    public long nextRandomQty(long minQty) {
        int f = random.nextInt(7);
        return minQty * f;
    }

    public NewOrderSingle nextRandomOrder() {
        List<InstrHolder> instrs = storage.findAll(InstrHolder.class);
        int num = instrs.size();
        int index = nextRandomIdx(num);
        InstrHolder instr = instrs.get(index);
        double price = nextRandomPrice(instr.getLastPx(), (int) Math.round(instr.getDevLimitLastDealPrc() * 1000));

        NewOrderSingle order = new NewOrderSingle();
        order.setSymbol(instr.getSymbol())
                .setAccount("")
                .setSide(nextRandomSide())
                .setPrice(price)
                .setQty(nextRandomQty(instr.getMinQty()));
        return order;
    }
}
