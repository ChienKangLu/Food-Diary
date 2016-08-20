package com.example.leo.gift_to_w;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.leo.gift_to_w.animation.ActivityAnimator;
import com.example.leo.gift_to_w.mytools.tools;
import com.example.leo.gift_to_w.quideshow.Myintro;

import java.io.File;

/**
 * Created by leo on 2016/3/14.
 */
public class gift_main extends AppCompatActivity {
    boolean key=false;
    int answer[]={0,3,2,4};
    int key_1=-1;//0
    int key_2=-1;//3
    int key_3=-1;//2
    int key_4=-1;//4
    boolean key_anim=false;
    boolean key_go=false;
    boolean final_key=true;
    private SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift);



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
            //int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(uiOptions);
        }
        //getWindow().setStatusBarColor(Color.TRANSPARENT);

        //移除空白大絕招
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ImageView topright=(ImageView)findViewById(R.id.topright);
        topright.setOnClickListener(top_right);
        ImageView topleft=(ImageView)findViewById(R.id.topleft);
        topleft.setOnClickListener(top_left);
        ImageView bottonleft=(ImageView)findViewById(R.id.bottonleft);
        bottonleft.setOnClickListener(botton_left);
        ImageView bottonright=(ImageView)findViewById(R.id.bottonright);
        bottonright.setOnClickListener(botton_right);
        ImageView enter=(ImageView)findViewById(R.id.enter);
        enter.setOnClickListener(enter_ls);
        File file = new File("/data/data/com.example.leo.gift_to_w/shared_prefs","LoginInfo.xml");
        if(file.exists()){

            settings = getSharedPreferences("LoginInfo",0);
            String success=settings.getString("goin", "");
            Log.v("ggggg", success);
            if(success.equals("no")){
                enter();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    View.OnClickListener top_left= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView enter=(ImageView)findViewById(R.id.enter);
            enter.setImageResource(android.R.color.transparent);
            key_1++;
            if(key_1==answer[0]) {
                ImageView topleft = (ImageView) findViewById(R.id.topleft);
                topleft.setImageResource(R.drawable.l);
                topleft.setBackgroundResource(R.drawable.circle_gift_word);
            }
        }
    };

    View.OnClickListener top_right= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView enter=(ImageView)findViewById(R.id.enter);
            enter.setImageResource(android.R.color.transparent);
            key_2++;
            if(key_2==answer[1]) {
                ImageView top_right = (ImageView) findViewById(R.id.topright);
                top_right.setImageResource(R.drawable.o);
                top_right.setBackgroundResource(R.drawable.circle_gift_word);
            }
        }
    };

    View.OnClickListener botton_left= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ImageView enter=(ImageView)findViewById(R.id.enter);
            enter.setImageResource(android.R.color.transparent);
            key_3++;
            if(key_3==answer[2]) {
                ImageView bottonleft = (ImageView) findViewById(R.id.bottonleft);
                bottonleft.setImageResource(R.drawable.v);
                bottonleft.setBackgroundResource(R.drawable.circle_gift_word);
            }
        }
    };
    View.OnClickListener botton_right= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageView enter=(ImageView)findViewById(R.id.enter);
            enter.setImageResource(android.R.color.transparent);
            key_4++;
            if(key_4==answer[3]) {
                ImageView bottonright = (ImageView) findViewById(R.id.bottonright);
                bottonright.setImageResource(R.drawable.e);
                bottonright.setBackgroundResource(R.drawable.circle_gift_word);
            }

        }
    };
    View.OnClickListener enter_ls= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(key_anim==true){
                //Toast.makeText(gift_main.this,"good",Toast.LENGTH_LONG).show();
                if(!key_go) {
                    anim_start();
                }else{
                    go();
                }

            }else {
                ImageView topleft = (ImageView) findViewById(R.id.topleft);
                ImageView top_right = (ImageView) findViewById(R.id.topright);
                ImageView bottonleft = (ImageView) findViewById(R.id.bottonleft);
                ImageView bottonright = (ImageView) findViewById(R.id.bottonright);
                if (key_1 == answer[0] && key_2 == answer[1] && key_3 == answer[2] && key_4 == answer[3]&&final_key) {
                    key = true;
                    topleft.setClickable(false);
                    top_right.setClickable(false);
                    bottonleft.setClickable(false);
                    bottonright.setClickable(false);
                } else {

                    topleft.setImageResource(android.R.color.transparent);
                    top_right.setImageResource(android.R.color.transparent);
                    bottonleft.setImageResource(android.R.color.transparent);
                    bottonright.setImageResource(android.R.color.transparent);

                    topleft.setBackgroundResource(R.drawable.gift_text_click);
                    top_right.setBackgroundResource(R.drawable.gift_text_click);
                    bottonleft.setBackgroundResource(R.drawable.gift_text_click);
                    bottonright.setBackgroundResource(R.drawable.gift_text_click);
                    //reset key
                    key_1 = -1;//0
                    key_2 = -1;//3
                    key_3 = -1;//2
                    key_4 = -1;//4


                }

                ImageView enter = (ImageView) findViewById(R.id.enter);
                if (key) {
                    enter.setImageResource(R.drawable.love_red);
                    key_anim = true;
                } else {
                    enter.setImageResource(R.drawable.like_fill);
                }
            }

        }
    };
    public void anim_start(){
        final View line1=findViewById(R.id.line1);
        final View line2=findViewById(R.id.line2);
        final Animation animation_line2 = AnimationUtils.loadAnimation(this, R.anim.slide_gift_down);
        animation_line2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                line2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                key_go=true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Animation animation_line1 = AnimationUtils.loadAnimation(this, R.anim.slide_gift_left);
        animation_line1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                line1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                line2.startAnimation(animation_line2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        line1.startAnimation(animation_line1);
        changeback();
        //go();
    }
    public void changeback(){
        RelativeLayout relativelayout=(RelativeLayout)findViewById(R.id.relativelayout);
        //bottonright.setImageResource(android.R.color.transparent);

        relativelayout.setBackgroundResource(android.R.color.white);
    }
    public void go(){

        Intent intent = new Intent(this, Myintro.class);
        startActivity(intent);
        this.finish();
        //ActivityAnimator a=new ActivityAnimator();
        //a.fadeAnimation(this);
    }
    public void enter(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
        tools.stop();
    }

}
