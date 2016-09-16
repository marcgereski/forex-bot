package kz.kase.bot.fix;


import kz.kase.bot.fix.QuickFixClient.FixServerType;

public class FixParserFactory {

    private static FixParser fixParser;

    public static void init(FixServerType type) {
        fixParser = createParserByType(type);
    }

    private static FixParser createParserByType(FixServerType type) {
        if (type == FixServerType.MarketData) {
            QuickFixClient.setMD(true);
            throw new IllegalStateException("MarketData Parsing is not Available!");
        } else if (type == FixServerType.Transactional) {
            return new FixParserTR();
        }
        return null;
    }

    public static FixParser getInstance() {
        if (fixParser == null)
            throw new IllegalStateException("FixParser has not been initialized!");
        return fixParser;
    }
}
