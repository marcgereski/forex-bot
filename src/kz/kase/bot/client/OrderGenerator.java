package kz.kase.bot.client;


import kz.kase.bot.model.domain.AccountHolder;
import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.Storage;
import kz.kase.fix.SecStatus;
import kz.kase.fix.Side;
import kz.kase.fix.TradeCondition;
import kz.kase.fix.messages.NewOrderSingle;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class OrderGenerator {
    private final Random random;
    private final Storage storage;

    private final static Logger log = Logger.getLogger(OrderGenerator.class);

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

    public long nextRandomQty(long lot) {
        int f = random.nextInt(7);
        return lot * f;
    }

    public NewOrderSingle nextRandomOrder(String user) {
        List<InstrHolder> ihs = storage.findAll(InstrHolder.class);
        List<InstrHolder> instrs = ihs.stream()
                .filter(i->i.getStatus().equals(SecStatus.Active))
                .collect(Collectors.toList());

        int num = instrs.size();

        if (num == 0) {
            log.info("All Instruments are closed.");
            return null;
        }

        AccountHolder acc = storage.findFirst(AccountHolder.class, a->a.getOwnerUsers().contains(user));
        if (acc == null) {
            log.info("No account found.");
            return null;
        }

        int index = nextRandomIdx(num);
        InstrHolder instr = instrs.get(index);

        double price = nextRandomPrice(instr.getLastPx(), (int) Math.round(instr.getDevLimitLastDealPrc() * 1000));

        NewOrderSingle order = new NewOrderSingle();
        order.setSymbol(instr.getSymbol())
                .setAccount(acc.getName())
                .setSide(nextRandomSide())
                .setPrice(price)
                .setQty(nextRandomQty(instr.getLot()));
        return order;
    }
}
