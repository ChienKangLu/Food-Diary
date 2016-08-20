package com.example.leo.gift_to_w.quideshow;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;

/**
 * Created by leo on 2016/3/15.
 */
public class myshowArticle{
    final Animation anim;
    final myshowArticle anim_next;
    final TextView now;
    final TextView next;
    final Context context;
    public myshowArticle(Context contextG, TextView nowG,TextView nextG ,myshowArticle anim_nextG){
        this.now=nowG;
        this.next=nextG;
        this.context=contextG;
        this.anim = AnimationUtils.loadAnimation(context, R.anim.fade_in_sacle_slow);
        this.anim_next=anim_nextG;
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                now.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                try{
                next.startAnimation(anim_next.anim);
                }catch (Exception e){

                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void start(){
        anim.start();
    }

}
