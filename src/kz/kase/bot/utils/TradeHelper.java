package kz.kase.bot.utils;


public class TradeHelper {
    public static double getDeviation(int precision) {
        int i = precision, order = 1;
        while (i-- > 0) {
            order *= 10;
        }
        return 1/order;
    }
}
