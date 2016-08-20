package com.example.leo.gift_to_w.quideshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.happy_birthday_card.*;
import com.example.leo.gift_to_w.mytools.tools;

import java.util.ArrayList;

public class SampleSlide2 extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";


    public static SampleSlide2 newInstance(int layoutResId) {


        SampleSlide2 sampleSlide = new SampleSlide2();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);
        return sampleSlide;

    }

    private int layoutResId;

    public SampleSlide2() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
        }

    }
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutResId, container, false);


        return view;
    }
    public static ImageView letter;
    public static Animation animation_letter;
    int move=100;
    int margin=30;

    @Override
    public void onResume() {
        super.onResume();
        letter=(ImageView)view.findViewById(R.id.letter);
        animation_letter = AnimationUtils.loadAnimation(getActivity(), R.anim.letter_rotate);


        animation_letter.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //letter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                letter.clearAnimation();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.ABOVE, R.id.center);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                params.bottomMargin = (move + margin + margin);
                letter.setLayoutParams(params);
                letter.requestLayout();
                letter.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //letter.startAnimation(animation_letter);

        letter.setOnClickListener(open);
    }
    View.OnClickListener open=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final int margin=30;
            final ImageView letter=(ImageView)view.findViewById(R.id.letter);
            final ImageView letter_inn=(ImageView)view.findViewById(R.id.letter_inn);

            final Animation animation_letter_inn_float=AnimationUtils.loadAnimation(getActivity(), R.anim.letter_inn_float);

            final Animation animation_letter_up = AnimationUtils.loadAnimation(getActivity(), R.anim.letter_up);
            animation_letter_up.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    letter_inn.clearAnimation();
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ABOVE, R.id.center);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                    params.bottomMargin = (move + margin + margin);
                    letter_inn.setLayoutParams(params);
                    letter_inn.requestLayout();
                    //letter_inn.clearAnimation();
                    letter_inn.startAnimation(animation_letter_inn_float);
                    letter_inn.setOnClickListener(card);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


            final Animation animation_letter_down = AnimationUtils.loadAnimation(getActivity(), R.anim.letter_down);
            animation_letter_down.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    letter.clearAnimation();
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.ABOVE, R.id.center);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
                    params.bottomMargin = (margin+margin);
                    letter.setLayoutParams(params);
                    letter.requestLayout();
                    letter.clearAnimation();
                    letter.setClickable(false);
                    letter.setImageResource(R.drawable.letter_open);
                    letter_inn.setVisibility(View.VISIBLE);
                    letter_inn.startAnimation(animation_letter_up);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            letter.startAnimation(animation_letter_down);

        }
    };
    View.OnClickListener card=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), com.example.leo.gift_to_w.happy_birthday_card.Myintro.class);
            startActivity(intent);
            getActivity().finish();
            tools.stop();
            //Toast.makeText(getActivity(),"打開卡片嚕",Toast.LENGTH_LONG).show();
        }
    };



    public void setMytitle(String str,int fontsize,LinearLayout title){
        TextView txt=new TextView(getActivity());

        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(10,10,10,10);
        txt.setText(str);
        txt.setTextSize(fontsize);
        tools.setMyFont("RobotoTTF/Roboto-Black.ttf", txt, getActivity());
        txt.setTextColor(getActivity().getResources().getColor(R.color.dark));


        title.addView(txt,params);
    }

}