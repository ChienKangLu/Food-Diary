package com.example.leo.gift_to_w.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/4.
 */
public class place {
    public DBManager DB;//從外部接收
    public String place_id="place_id";//integer (pm key)
    public String place_name="place_name";//text
    public String tablename="place";

    public place(DBManager DB) {
        this.DB=DB;
    }

    public long insert(String place_name){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.place_name, place_name);
        return db.insert(tablename, null, values);
    }
    public boolean delete(String id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String where = place_id + "=" + id;
        return db.delete(tablename, where , null) > 0;
    }

    public int update(String place_name,int id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.place_name, place_name);
        String selection = place_id + "=" + id;
        return db.update(tablename, values, selection, null);
    }
    public Cursor select(String sql){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String qq = sql;
        Cursor tCursor = db.rawQuery(qq, null, null);
        return tCursor;
    }
    public String size(){
        Cursor c = select("select count(*) as count from place");
        //place_id,place_name,count
        ArrayList<HashMap<String, String>> DBdata = DBManager.readCursor(c);
        DB.closeDatabase();
        return DBdata.get(0).get("count");
    }

}