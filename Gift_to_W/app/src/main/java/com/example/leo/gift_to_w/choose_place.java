package com.example.leo.gift_to_w;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.leo.gift_to_w.animation.ActivityAnimator;
import com.example.leo.gift_to_w.animation.chooseadapter;
import com.rengwuxian.materialedittext.MaterialEditText;

public class choose_place extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_place);


        getSupportActionBar().hide();//
        //getSupportActionBar().

        //show status bar
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else {
            View decorView = getWindow().getDecorView();
            // show the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        //getWindow().setStatusBarColor(Color.TRANSPARENT);

        //移除空白大絕招
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
           // url = bundle.getString("url");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView showdata = (ListView) findViewById(R.id.showdata);
        chooseadapter adapter=new chooseadapter(this);

        showdata.setAdapter(adapter);//重整
        showdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                String tag=view.getTag().toString();
                Log.v("idorlast",tag);
                Bundle b = new Bundle();
                b.putString("idorlast",tag);
                /*
                b.putString("lat", lat.getText().toString());
                b.putString("lon", lon.getText().toString());
                */
                intent.putExtras(b);
                choose_place.this.setResult(0, intent);
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.food_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.v("back","good back");

                onBackPressed();

                return true;
            case R.id.ok:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();

        ActivityAnimator a=new ActivityAnimator();
        a.top_to_down(this);
    }
}
