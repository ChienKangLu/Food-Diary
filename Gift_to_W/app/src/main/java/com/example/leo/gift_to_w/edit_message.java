package com.example.leo.gift_to_w;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.gift_to_w.animation.ActivityAnimator;
import com.example.leo.gift_to_w.my_message.icon_adpter;
import com.example.leo.gift_to_w.my_message.message_adpter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class edit_message extends AppCompatActivity{
    String title;
    String pic;
    String message;
    String pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_message);
        //////////////


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("修改一下");

        /////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
           title = bundle.getString("title");
           pic = bundle.getString("pic");
           message = bundle.getString("message");
           pos = bundle.getString("pos");
        }
        RecyclerView mList = (RecyclerView)findViewById(R.id.listview);
        icon_adpter myAdapter = new icon_adpter(""+1,edit_message.this, mList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(edit_message.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);

        if(!title.equals(message_adpter.none)) {
            EditText titleTxt = (EditText) findViewById(R.id.title);
            titleTxt.setText(title);
        }
        if(!message.equals(message_adpter.none)) {
            EditText messageTxt = (EditText) findViewById(R.id.message);
            messageTxt.setText(message);
        }

        if(!pic.equals(message_adpter.none)) {
            myAdapter.setCheck(pic);
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

                EditText title=(EditText)findViewById(R.id.title);
                EditText message=(EditText)findViewById(R.id.message);

/*
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                String myDate= sdf.format(date);
*/
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putString("title", title.getText().toString());
                b.putString("message", message.getText().toString());
               // b.putString("date", myDate);
                b.putString("pic", ""+(icon_adpter.whichone+1));//0 1 2 3 4 5 6-->0表沒有
                b.putString("pos",pos);


                intent.putExtras(b);
                edit_message.this.setResult(1, intent);
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
