package com.example.leo.gift_to_w.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/4.
 */
public class message {
    public DBManager DB;//從外部接收
    public String message_id="message_id";//integer (pm key)
    public String food_id="food_id";//integer
    public String message_title="message_title";//text
    public String message_pic="message_pic";//text
    public String message_content="message_content";//text
    public String message_date_time="message_date_time";//DATETIME
    public String tablename="message";


    public message(DBManager DB) {
        this.DB=DB;
    }
    public long insert(String food_id,String message_title,String message_pic,String message_content,String message_date_time){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(this.food_id, food_id);
        values.put(this.message_title, message_title);
        values.put(this.message_pic, message_pic);
        values.put(this.message_content, message_content);
        values.put(this.message_date_time, message_date_time);
        return db.insert(tablename, null, values);
    }
    public boolean delete(String id){//base id=message_id
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String where = message_id + "=" + id;
        return db.delete(tablename, where , null) > 0;
    }
    public boolean deleteAll(String id){//id=food_id
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String where = food_id + "=" + id;
        return db.delete(tablename, where , null) > 0;
    }
    public void deletefromPlace(String id){//id=place_id
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        db.execSQL("delete from message where message_id in(select message.message_id from message left join food on message.food_id=food.food_id where place_id="+id+")");
    }

    public void update(String id,String title,String pic,String message){//id=message_id
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        ContentValues values = new ContentValues();
        values.put(message_title, title);
        values.put(message_pic, pic);
        values.put(message_content, message);
        String selection = message_id + "=" + id;
        db.update(tablename, values, selection, null);

    }
    public Cursor select(String sql){//base
        DB.openDatabase();
        SQLiteDatabase db = DB.getDatabase();
        String qq = sql;
        Cursor tCursor = db.rawQuery(qq, null, null);
        return tCursor;
    }

}