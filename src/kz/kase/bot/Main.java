package kz.kase.bot;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.util.concurrent.ConcurrentMap;

public class Main {

    public static void main(String[] args) {
        DB db = DBMaker.memoryDB().make();
        ConcurrentMap map = db.hashMap("map").make();
        map.put("something", "here");
    }
}
