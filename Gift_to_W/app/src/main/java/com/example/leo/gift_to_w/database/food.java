package com.example.leo.gift_to_w.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/4.
 */
public class food{
    public DBManager DB;//從外部接收
    public String food_id="food_id";//integer (pm key)
    public String place_id="place_id";//integer(forin key)
    public String time="time";//integer
    public String food_name="food_name";//text
    public String lat="lat";//text
    public String lon="lon";//text
    public String love="love";//text(0,1)
    public String delete="[delete]";//text(0,1)
    public String tablename="food";


    public food(DBManager DB) {
        this.DB=DB;
    }
    public long insert(String food_name,String lat,String lon,String love,String delete,int time,int place_id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.food_name, food_name);
        values.put(this.place_id, place_id);
        values.put(this.time, time);
        values.put(this.lat, lat);
        values.put(this.lon, lon);
        values.put(this.love, love);
        values.put(this.delete, delete);
        return db.insert(tablename, null, values);
    }
    public boolean delete(String id){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String where = food_id + "=" + id;
        return db.delete(tablename, where , null) > 0;
    }

    public boolean delete_place(String place_id){//刪除地點,因此要刪除該地點的全部食物
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String where = this.place_id + "=" + place_id;
        return db.delete(tablename, where , null) > 0;
    }

    public int update_love(String love,int id){
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.love, love);
        String selection = this.food_id + "=" + id;
        return db.update(tablename, values, selection, null);
    }
    public int update_delete(String delete,int id){
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.delete, delete);
        String selection = this.food_id + "=" + id;
        return db.update(tablename, values, selection, null);
    }

    public int update_time(String time,int id){
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.time, time);
        String selection = this.food_id + "=" + id;
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
        Cursor c = select("select count(*) as count from food");
        //place_id,place_name,count
        ArrayList<HashMap<String, String>> DBdata = DBManager.readCursor(c);
        DB.closeDatabase();
        return DBdata.get(0).get("count");
    }

}