package kz.kase.bot.storage.dbs;


import kz.kase.bot.model.domain.InstrHolder;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.serializer.SerializerLong;

import java.util.concurrent.ConcurrentMap;

public class MapDb {
    private final DB db;
    private static final MapDb mapDb = new MapDb();

    public MapDb() {
        db = DBMaker.memoryDB().make();
    }

    public static MapDb getInstance() {
        return mapDb;
    }

    public <T> void createTable(T clazz) {
//        db.hashMap(T.getName()).create();
        db.hashMap("InstrHolder")
                .keySerializer(SerializerLong.LONG)
                .create();
    }

    public <T> ConcurrentMap getTable(T clazz) {
        return db.get("InstrHolder");
    }

    public static void main(String[] args) {
        InstrHolder h= new InstrHolder();
        h.setSymbol("Hello2");

        DB db = DBMaker.memoryDB().make();
        db.hashMap("InstrHolder").create();
        ConcurrentMap map = db.get("InstrHolder");
        map.put(1L, h);
        Object o = map.get(1L);


    }
}
