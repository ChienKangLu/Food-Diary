package com.example.leo.gift_to_w.my_message;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.add_food;
import com.example.leo.gift_to_w.add_message;
import com.example.leo.gift_to_w.animation.ActivityAnimator;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.message;
import com.example.leo.gift_to_w.edit_message;
import com.rengwuxian.materialedittext.MaterialEditText;

public class message_show extends AppCompatActivity{
    String dbid;//食物的id
    message_adpter myAdapter;
    FrameLayout anim_place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.message_show);
        //////////////


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("記錄此刻的心情");

        /////////
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
           dbid = bundle.getString("dbid");
            //Toast.makeText(this,dbid,Toast.LENGTH_SHORT).show();
        }

        RecyclerView mList = (RecyclerView)findViewById(R.id.listview);
        myAdapter = new message_adpter(""+dbid,message_show.this, mList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(message_show.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);


        final String finalDbid = dbid;//copy dbid because need final
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                //add_food
                Intent intent = new Intent();


                Bundle bundle = new Bundle();
                bundle.putString("dbid", finalDbid);
                intent.putExtras(bundle);

                intent.setClass(message_show.this, add_message.class);
                startActivityForResult(intent, 4);

                //myAdapter.additem("品田",1,2,1,"0");//good


                //  myAdapter.additem(myDataset2);
                // myAdapter.notifyDataSetChanged();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        LinearLayout hidearea=(LinearLayout)findViewById(R.id.hidearea);
        anim_place=(FrameLayout)findViewById(R.id.anim_place);
        hidearea.setOnClickListener(hide);
        TextView edit =(TextView)findViewById(R.id.edit);
        edit.setOnClickListener(editaction);
        TextView delete =(TextView)findViewById(R.id.delete);
        delete.setOnClickListener(deleteaction);
        TextView cancel =(TextView)findViewById(R.id.cancel);
        cancel.setOnClickListener(hide);

    }
    View.OnClickListener editaction= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(message_show.this,id, Toast.LENGTH_LONG).show();
            String pos=anim_place.getTag().toString();
            hidecore();
            //edit_food
            Intent intent = new Intent();


            Bundle bundle = new Bundle();
//            bundle.putString("dbid", finalDbid);
            bundle.putString("title",myAdapter.myItem.get(Integer.parseInt(pos)).title);
            bundle.putString("message",myAdapter.myItem.get(Integer.parseInt(pos)).article);
            bundle.putString("pic",myAdapter.myItem.get(Integer.parseInt(pos)).pic_name);
            bundle.putString("pos",""+pos);
            intent.putExtras(bundle);

            intent.setClass(message_show.this, edit_message.class);
            startActivityForResult(intent, 4);
        }
    };
    View.OnClickListener deleteaction= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String pos=anim_place.getTag().toString();
            myAdapter.remove(Integer.parseInt(pos),v);
            hidecore();
//            Toast.makeText(message_show.this, anim_place.getTag().toString(), Toast.LENGTH_LONG).show();
        }
    };
    View.OnClickListener hide=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hidecore();
        }
    };
    public void hidecore(){
        Animation animation = AnimationUtils.loadAnimation(message_show.this, R.anim.slide_down);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //anim_place.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                anim_place.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim_place.startAnimation(animation);
    }
    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.food_menu, menu);
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

                onBackPressed();
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4) {
            if(resultCode==0) {
                if (data != null) {
                    Bundle getdata = data.getExtras();
                    String title=getdata.getString("title");//判斷空白
                    String message=getdata.getString("message");//判斷空白
                    String date=getdata.getString("date");//系統產生
                    String pic=getdata.getString("pic");//判斷-1
                    String food_id=dbid;

/*
                    if(title.equals("")){//空的

                    }

                    if(message.equals("")){//空的

                    }*/

                    if(!pic.equals("0")){//不為空的

                        pic="w"+pic;

                    }else{//空的

                    }

                    Log.v("mydate", "~"+title);
                    Log.v("mydate", "~"+message);
                    Log.v("mydate", "~"+date);
                    Log.v("mydate", "~"+pic);
                    myAdapter.additem(food_id,title,message,date,pic);

                   }
            }else if(resultCode==1){
                Bundle getdata = data.getExtras();
                String title=getdata.getString("title");//判斷空白
                String message=getdata.getString("message");//判斷空白
                String pic=getdata.getString("pic");//判斷-1
                String pos=getdata.getString("pos");//判斷-1
                String food_id=dbid;

                if(!pic.equals("0")){//不為空的
                    pic="w"+pic;
                }

                Log.v("mydate", "~"+title);
                Log.v("mydate", "~"+message);
                Log.v("mydate", "~"+pic);
                myAdapter.edititem(food_id,title,message,pic,pos);
            }
        }

    }
}
