package com.example.leo.gift_to_w.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;

import com.example.leo.gift_to_w.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class DBManager {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "gift_database.db"; // 保存的資料庫檔案名
    public static final String PACKAGE_NAME = "com.example.leo.gift_to_w"; //com.example.kkscucc
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;// 在手機裡存放資料庫的位置(/data/data/tw.android/db2.db)

    private SQLiteDatabase database;
    private Context context;

    private int raw_db_name = R.raw.exampleleo2;//本機的資料庫

    public DBManager(Context coNtext) {
        this.context = coNtext;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {

        try {

            if (!(new File(dbfile).exists())) {
                Log.i("have leo2????????", "no");
                // 判斷資料庫檔案是否存在，若不存在則執行導入，否則直接打開資料庫
                InputStream is = this.context.getResources().openRawResource(
                        raw_db_name); // 欲導入的資料庫
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }

            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                    null);
            return db;

        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase() {
        this.database.close();
    }

    public static ArrayList<HashMap<String, String>> readCursor(Cursor c) {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        while (c.moveToNext()) {
            HashMap<String, String> row = new HashMap<>();
            for (int i = 0; i < c.getColumnCount(); i++) {
                row.put(c.getColumnName(i), c.getString(i));
                Log.v("col", c.getColumnName(i));
            }
            data.add(row);
        }
        return data;
    }
    //using
    //建立一個Copy檔案的方法
    public void copyDBtoSDCard() {
        try {
            //取得SD Card路徑
            String tSDCardPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();

            Log.i("CopyDBException","開始");
            //指定SD Card與目標目錄名稱
            File tDataPath = new File(tSDCardPath + "/mydbleo2/");
            //指定實際Database路徑
            String tDBFilePath = tSDCardPath + "/mydbleo2/" + "exampleleo2.db";
            File tFile = new File(tDBFilePath);
            //識別指定的目錄是否存在，false則建立；
            if (tDataPath.exists() == false) {
                tDataPath.mkdirs();
                Log.i("CopyDBException","建立目錄");
            }
            //識別指定的檔案是否存在，false則建立；
            if (tFile.exists() == false) {
                Log.i("CopyDBException","建立檔案");
                //先使用InputStream從raw中把example.db資料庫給讀進來；
                //InputStream tISStream = context.getResources().openRawResource(R.raw.gift_database);
                File innerDB = new File(DB_PATH + "/" + DB_NAME);
                FileInputStream tISStream = new FileInputStream(innerDB);
                //new一個OutputStream物件，並且指定寫入的路徑；
                FileOutputStream tOutStream = new FileOutputStream(tDBFilePath);
                //指定資料庫檔案的size
                byte[] tBuffer = new byte[5120];
                int tCount = 0;
                while ((tCount = tISStream.read(tBuffer)) > 0) {
                    tOutStream.write(tBuffer, 0, tCount);
                    Log.i("CopyDBException", ""+tCount);
                }
                tOutStream.close();
                tISStream.close();
            }

            Log.i("CopyDBException","結束");
        } catch (Exception e) {
            Log.i("CopyDBException", e.getMessage());
        }
    }
    //not use
    public void backupDB() {
        try {
            Log.v("w123", "開始");
            File innerDB = new File(DB_PATH + "/" + DB_NAME);
            if (innerDB.exists()) {
                Log.v("w123", "innerDB 存在");
            } else {
                Log.v("w123", "innerDB 不存在");
            }
//            File saveDB = new File("E:/"+DB_NAME);
//            if(saveDB.exists()){
//                Log.v("w123", "saveDB 存在");
//            }else{
//                Log.v("w123", "saveDB 不存在");
//            }
            FileInputStream inputStream = new FileInputStream(innerDB);
            FileOutputStream outputStream = new FileOutputStream("E:/" + DB_NAME);
            Log.v("w123", "成功找到檔案");
            byte[] buffer = new byte[BUFFER_SIZE];
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, count);
                Log.v("w123", "" + count);
            }
            inputStream.close();
            outputStream.close();

            Log.v("w123", "結束");

        } catch (IOException e) {
            e.printStackTrace();
            Log.v("w123", "出問題了");
        }
    }
    /*
    public static ArrayList<ArrayList<String>> readCursorOne(Cursor c){
        while(c.moveToNext()){
            answer2[i]=c.getString(0);
            id[i]=c.getString(1);
            i++;
        }
    }
    */


}
