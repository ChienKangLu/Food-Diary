package com.example.leo.gift_to_w;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.animation.animation_test;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.place;
import com.example.leo.gift_to_w.innFile.showcard;
import com.example.leo.gift_to_w.mydrawer.listitem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    public static ListView mDrawerList;

    private String[] mPlanetTitles;

    private CharSequence mTitle;

    public static listitem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*重整一次DB,開始前先備份救的不然 GG,最後一次更新要把DBmanager中的每次重置限制加上去*/
        DBManager DB = new DBManager(this);
        DB.openDatabase();
        DB.closeDatabase();


/*
        DBManager DB = new DBManager(this);
        place place=new place(DB);
        Cursor c=place.select("select * from place");
        ArrayList<ArrayList<String>> data=new ArrayList<>();
        while(c.moveToNext()){
            ArrayList<String> row=new ArrayList<>();
            for(int i=0;i<c.getColumnCount();i++){
                row.add(c.getString(i));
            }
            data.add(row);
        }
        DB.closeDatabase();
        String place_id=data.get(0).get(0);
        String place_name=data.get(0).get(1);
        Toast.makeText(this, place_id+"~~"+place_name, Toast.LENGTH_SHORT).show();
*/

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

                public void onDrawerClosed(View view){
                    //getActionBar().setTitle(mTitle);
                    //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
                public void onDrawerOpened(View drawerView) {
                    //getActionBar().setTitle(mDrawerTitle);
                    //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
        };
        drawer.setDrawerListener(toggle);//refreshdata(getActivity());
        toggle.syncState();


        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        //mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        //mPlanetTitles = ;

        adapter=new listitem(this);

        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, mPlanetTitles));//重整
        mDrawerList.setAdapter(adapter);//重整

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

        selectItem(0, "header", appname);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final Menu menu = navigationView.getMenu();
        for (int i = 1; i <= 3; i++) {
            menu.add("台北　　　１");
            //menu.add
        }
        */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ok:
                SharedPreferences settings;
                settings = getSharedPreferences("LoginInfo", 0);
                settings.edit()
                        .putString("goin","yes").commit();
//                DBManager myDB=new DBManager(this);
//                verifyStoragePermissions(this);//// Storage Permissions
//                myDB.copyDBtoSDCard();


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // Storage Permissions start
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    // Storage Permissions end

    String appname="餓了嗎";
    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {//監聽抽屜點擊
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String tag=view.getTag().toString();

            if(!tag.equals("header")&&!tag.equals("the_last")) {
                String title = ((TextView) (view.findViewById(R.id.text))).getText().toString();
                Log.v("last", tag);
                selectItem(position, tag, title);
            }else{
                if(tag.equals("header")) {
                    String title=appname;
                    selectItem(position, tag, title);
                }else if(tag.equals("the_last")){
                    String title = ((TextView) (view.findViewById(R.id.text))).getText().toString();
                    Log.v("last", tag);
                    selectItem(position, tag, title);
                }
            }
        }
    }
    public  void selectItem(int position, String tag, String title) {
        if(tag.equals("header")){//測試動畫頁，記的刪除
            // update the main content by replacing fragments
            Fragment fragment = new animation_test();

            FragmentManager fragmentManager = getSupportFragmentManager();//*

            fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();//*

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(title);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(mDrawerList);
        }else
        if(!tag.equals("the_last")) {
            int pos=Integer.parseInt(tag);
            String dbid=adapter.data.get(pos).get("place_id");
            //Toast.makeText(this, adapter.data.get(dbid).get("place_id"), Toast.LENGTH_SHORT).show();


            // update the main content by replacing fragments
            Fragment fragment = new showcard();
            Bundle args = new Bundle();
            args.putString("dbid", dbid);
            args.putString("title", title);
            fragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();//*

            fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();//*

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            setTitle(title);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(mDrawerList);
        }else{
            mDrawerList.setItemChecked(position, false);
            Intent intent = new Intent();
            intent.setClass(this, add_article.class);
            startActivityForResult(intent,0);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null) {
            if(requestCode==0) {
                Bundle getdata = data.getExtras();
                String articlename = getdata.getString("articlename");
                adapter.add(articlename);
                mDrawerList.setAdapter(adapter);//重整
                animation_test.refreshdata(MainActivity.this);
                //Toast.makeText(this, articlename, Toast.LENGTH_SHORT).show();
            }
       }
    }
    public static void refresh(Context context){

        adapter=new listitem(context);
        mDrawerList.setAdapter(adapter);//重整
    }


}
