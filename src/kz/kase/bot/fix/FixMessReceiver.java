package kz.kase.bot.fix;

import kz.kase.bot.model.domain.FixUpdate;
import kz.kase.bot.model.domain.FixUpdateImpl;
import kz.kase.bot.storage.Storage;
import kz.kase.fix.messages.*;

public class FixMessReceiver {

    private FixMessProcessor interpreter;

    public FixMessReceiver(Storage storage) {
        this(storage, true);
    }

    public FixMessReceiver(Storage storage, boolean debug) {
        Storage storage1 = storage;
        boolean debug1 = debug;
        interpreter = new FixMessProcessor(debug, storage);
    }

    public FixUpdate process(Object data) {
        FixUpdate update = null;

        if (data instanceof SecurityList) {
            update = interpreter.processAndStore((SecurityList) data);


        } else if (data instanceof MDIncRefresh) {
            update = interpreter.processAndStore((MDIncRefresh) data);

        } else if (data instanceof MDFullSnapshotRefresh) {
//            update = interpreter.processAndStore((MDFullSnapshotRefresh) data);

        } else if (data instanceof PositionReport) {
            update = interpreter.processAndStore((PositionReport) data);

        } else if (data instanceof ExecutionReport) {
//            update = interpreter.processAndStore((ExecutionReport) data);

//        } else if (data instanceof MMLiabilityList) {
//            update = interpreter.processAndStore((MMLiabilityList) data);

//        } else if (data instanceof MMStat) {
//            update = interpreter.processAndStore((MMStat) data);

//        } else if (data instanceof IndicativeQuoteList) {
//            update = interpreter.processAndStore((IndicativeQuoteList) data);

        } else if (data instanceof SecurityStatus) {
            update = interpreter.processAndStore((SecurityStatus) data);

        } else if (data instanceof UserResponse) {
            update = new FixUpdateImpl((UserResponse) data, null);

        }/* else if (data instanceof CalendarResponse) {
            update = process(data);
        }*/

/*        if (data instanceof DayPositionReport) {
            update = interpreter.processAndStore((DayPositionReport) data);
        }*/

        return update;
    }

}
