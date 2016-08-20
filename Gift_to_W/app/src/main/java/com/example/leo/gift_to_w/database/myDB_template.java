package com.example.leo.gift_to_w.database;

import android.database.Cursor;

/**
 * Created by leo on 2016/3/4.
 */

//哈哈本來想說試試~邏輯有點複雜~嘻嘻~下次再試試
public interface  myDB_template {
    long insert(String...data);//回傳最新的id
    Cursor select(String sql);//回傳資料row
    boolean delete(String where);//回傳是否成功
    int update();//回傳影響幾筆資料
}
