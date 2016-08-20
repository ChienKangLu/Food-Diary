package com.example.leo.gift_to_w;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.leo.gift_to_w.animation.ActivityAnimator;
import com.rengwuxian.materialedittext.MaterialEditText;

public class add_article extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_article);
        //////////////


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("新增地點");

        /////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
           // url = bundle.getString("url");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

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

                MaterialEditText articlename=(MaterialEditText)findViewById(R.id.articlename);
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("articlename", articlename.getText().toString());
                /*
                b.putString("lat", lat.getText().toString());
                b.putString("lon", lon.getText().toString());
                */
                intent.putExtras(b);
                add_article.this.setResult(0, intent);
                finish();
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
