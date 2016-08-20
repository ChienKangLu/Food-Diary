package com.example.leo.gift_to_w.quideshow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.leo.gift_to_w.MainActivity;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.asychtask.myAsyTask;
import com.example.leo.gift_to_w.mytools.tools;
import com.github.paolorotolo.appintro.AppIntro2;

/**
 * Created by leo on 2016/3/6.
 */
public class Myintro extends AppIntro2 {
    @Override
    public void init(Bundle savedInstanceState) {

        addSlide(SampleSlide4.newInstance(R.layout.intro_myapp4));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s1_2,"不知道要吃啥?","讓史塔克告訴你"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s2_2,"想去哪吃?","讓史奴比告訴你"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s3_2,"一鍵告訴你今天要吃啥","史塔克會轉圈呦"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s4_2,"怕忘記去過哪裡嗎?","記錄我們走過的點點滴滴"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s5_2,"手寫怕麻煩?","一鍵新增專屬回憶"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s6_2,"把吃過的美食像卡片一般","全部收藏，永久保存"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s9_2,"曾經想吃，卻不知怎麼走嗎?","一鍵點擊引導你"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s7_2,"記錄我們在一起的美分每秒","把你心中的千言萬語全部寫下來吧"));
        addSlide(SampleSlide.newInstance(R.layout.intro_myapp1,R.drawable.s8_2,"慧縈專屬情緒貼圖","是不是很可愛丫"));
        addSlide(SampleSlide3.newInstance(R.layout.intro_myapp3));
        addSlide(SampleSlide2.newInstance(R.layout.intro_myapp2));

        getSupportActionBar().hide();


//        FrameLayout indicator_container=(FrameLayout)pager.findViewById(R.id.indicator_container);
//        indicator_container.setBackgroundColor(Color.parseColor("#ff0000"));
//        indicator_container
        //setIndicatorColor(Color.parseColor("#ff0000"), Color.parseColor("#00ff00"));
        //setIndicatorColor(1,2);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //if(!tools.played) {
//            tools.start(this);
//        tools.play(this);
       // }
//        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show();

//        FrameLayout indicator_container=(FrameLayout)pager.findViewById(R.id.indicator_container);
    }

    private void loadMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
        tools.stop();

        SharedPreferences settings;
        settings = getSharedPreferences("LoginInfo", 0);
        settings.edit()
                .putString("goin","no").commit();

    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {
        if(pager.getCurrentItem()==0){
            setProgressButtonEnabled(false);
        }else{
            setProgressButtonEnabled(true);
        }

        if(pager.getCurrentItem()==1){
            if(!tools.played){
                tools.play(this,R.raw.tryeverything);
            }
        }
        if(pager.getCurrentItem()==11){
            SampleSlide2.letter.startAnimation(SampleSlide2.animation_letter);
        }
        //Toast.makeText(this,"slide"+pager.getCurrentItem(),Toast.LENGTH_LONG).show();
    }

    public void getStarted(View v) {
        loadMainActivity();
    }

}