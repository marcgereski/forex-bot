package kz.kase.bot.model;

public interface BusMessage {

    public final static String INSTRUMENTS_TABLE_INIT = "instruments-table-update";
    public final static String ORDERS_TABLE_INIT = "orders-table-init";
    public final static String DEALS_TABLE_INIT = "deals-table-init";
    public final static String POSITIONS_TABLE_INIT = "positions-table-init";
    public final static String INCOMING_DEAL = "incoming-deal";
    public final static String INCOMING_ARC_DEAL = "incoming-arc-deal";
    public final static String DAY_POSITIONS_TABLE_INIT = "day_positions-table-init";

}
