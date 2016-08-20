package com.example.leo.gift_to_w.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by leo on 2016/3/4.
 */
//暫時不用place_food，把place放到food
public class place_food {
    public DBManager DB;//從外部接收
    public String food_id="food_id";//integer (pm)
    public String place_id="place_id";//integer (pm)
    public String tablename="place_food";

    public place_food(DBManager DB) {
        this.DB=DB;
    }

    public long insert(int food_id,int place_id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.food_id, food_id);
        values.put(this.place_id, place_id);
        return db.insert(tablename, null, values);
    }
    public boolean delete(int food_id,int place_id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String where = this.food_id + "=" + food_id +"and" + this.place_id + "=" + place_id;
        return db.delete(tablename, where , null) > 0;
    }
/*暫時不會有update,maybe 之後會有
    public int update(String place_name,int id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.place_name, place_name);
        String selection = place_id + "=" + id;
        return db.update(tablename, values, selection, null);
    }*/
    public Cursor select(String sql){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String qq = sql;
        Cursor tCursor = db.rawQuery(qq, null, null);
        return tCursor;
    }

}