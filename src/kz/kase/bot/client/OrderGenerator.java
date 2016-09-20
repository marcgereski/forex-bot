package kz.kase.bot.client;


import kz.kase.bot.model.domain.AccountHolder;
import kz.kase.bot.model.domain.InstrHolder;
import kz.kase.bot.storage.Storage;
import kz.kase.fix.OrderType;
import kz.kase.fix.Side;
import kz.kase.fix.TimeInForce;
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

    public double nextRandomPrice(double lastDealPrice, int deviationInMilliPercents, int precision) {
        int d = random.nextInt(2 * deviationInMilliPercents) - deviationInMilliPercents;
        double price = lastDealPrice + lastDealPrice * d / 100000;
        return Math.round(price * 10 * precision) / 10.0 / precision;
    }

    public long nextRandomQty(long lot) {
        int f = random.nextInt(7);
        return lot * f;
    }

    public NewOrderSingle nextRandomOrder(String user) {
        List<InstrHolder> ihs = storage.findAll(InstrHolder.class);
        List<InstrHolder> instrs = ihs.stream()
                .filter(i -> i.getTradeCondition() != null && !TradeCondition.Stopped.equals(i.getTradeCondition()))
                .collect(Collectors.toList());

        int num = instrs.size();

        if (num == 0) {
            log.info("All Instruments are closed.");
            return null;
        }

        AccountHolder acc = storage.findFirst(AccountHolder.class, a -> a.getOwnerUsers().contains(user));
        if (acc == null) {
            log.info("No account found.");
            return null;
        }

        int index = nextRandomIdx(num);
        InstrHolder instr = instrs.get(index);

        Double lastPx = instr.getLastPx();
        Double limit = 0.1; //instr.getDevLimitLastDealPrc();

        if (lastPx == null || limit == null) {
            log.error("LastPrice or DevLimitLastPrice is absent");
            return null;
        }

        double price = nextRandomPrice(lastPx, (int) Math.round(limit * 1000), 1);

        NewOrderSingle order = new NewOrderSingle();
        order.setSymbol(instr.getSymbol())
                .setOrderType(OrderType.LIMIT)
                .setAccount(acc.getName())
                .setSide(nextRandomSide())
                .setPrice(price)
                .setTimeInForce(TimeInForce.AT_THE_CLOSE)
                .setQty(nextRandomQty(instr.getLot()));

        log.info("\n\t" + user + " SENDS LIMIT ORDER."
                + "\n\t\tacc:   " + order.getAccount()
                + "\n\t\tside:  " + order.getSide().name()
                + "\n\t\tprice: " + order.getPrice()
                + "\n\t\tqty:   " + order.getQty());
        return order;
    }
}
