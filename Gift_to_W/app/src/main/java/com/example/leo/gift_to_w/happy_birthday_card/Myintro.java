package com.example.leo.gift_to_w.happy_birthday_card;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.leo.gift_to_w.MainActivity;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.mytools.tools;
import com.github.paolorotolo.appintro.AppIntro2;

/**
 * Created by leo on 2016/3/6.
 */
public class Myintro extends AppIntro2 {
    //String [] article=null;

    @Override
    public void init(Bundle savedInstanceState) {

        //tools.readcard(this);

        addSlide(card_first.newInstance(R.layout.card_first));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_1));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_2));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_3));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_4));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_5));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_6));
        addSlide(SampleSlide.newInstance(R.layout.card_inn, R.raw.card_7));


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
                tools.play(this,R.raw.normal_road);
            }
        }
    }

    public void getStarted(View v) {
        loadMainActivity();
    }

}