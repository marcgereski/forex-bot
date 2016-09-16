package kz.kase.bot.fix;

import kz.kase.fix.SecurityListRequestType;
import kz.kase.fix.SubscriptionType;
import kz.kase.fix.messages.MarketDataRequest;
import kz.kase.fix.messages.OrderStatusRequest;
import kz.kase.fix.messages.PositionRequest;
import kz.kase.fix.messages.SecurityListRequest;
import org.apache.log4j.Logger;

import java.util.List;


public class FixRequest {

    private static final Logger log = Logger.getLogger("");

    public static void sendSecurityRequest(QuickFixClient fixClient) {
        long ref = QuickFixClient.nextRef();
        SecurityListRequestType type = SecurityListRequestType.ALL_SECURITIES;
        StringBuilder logBuf = new StringBuilder();

        logBuf.append("\nSending security list request:\n");
        logBuf.append("\tref: ").append(ref).append("\n");
        logBuf.append("\ttype: ").append(type).append("\n");
        log.info(logBuf);

        fixClient.sendMessage(new SecurityListRequest()
                .setRef(ref)
                .setType(type));
    }

    public static void sendPositionRequest(QuickFixClient fixClient) {
        log.info("Sending position request");
        long ref = QuickFixClient.nextRef();
        PositionRequest pq = new PositionRequest().setRef(ref);
        fixClient.sendMessage(pq);
    }

    public static void sendOrderStatusRequest(QuickFixClient fixClient, long orderStatusRequestRef) {
        orderStatusRequestRef = QuickFixClient.nextRef();
        OrderStatusRequest orderStReq = new OrderStatusRequest();
        orderStReq.setRef(orderStatusRequestRef);
        fixClient.sendMessage(orderStReq);
    }

    public static void sendMarketDataRequest(QuickFixClient fixClient, List<String> symbols, long ref, SubscriptionType type) {
        MarketDataRequest req = new MarketDataRequest(false)
                .setRef(ref)
                .setSubscriptionType(type);
        if (!QuickFixClient.isMD()) {
            for (String symbol : symbols) {
                req.addInstrument(symbol);
            }
        } else {
            req.addInstrument("ALL");
        }
        fixClient.sendMessage(req);
    }
}
