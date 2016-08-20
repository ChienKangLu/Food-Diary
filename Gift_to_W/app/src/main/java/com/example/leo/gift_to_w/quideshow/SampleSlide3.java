package com.example.leo.gift_to_w.quideshow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.mytools.tools;

public class SampleSlide3 extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";


    public static SampleSlide3 newInstance(int layoutResId) {


        SampleSlide3 sampleSlide = new SampleSlide3();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);
        return sampleSlide;

    }

    private int layoutResId;

    public SampleSlide3() {}

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

    @Override
    public void onResume() {
        super.onResume();
        //以下為動畫
        final TextView t1=(TextView)view.findViewById(R.id.t1);
        final TextView t2=(TextView)view.findViewById(R.id.t2);
        final TextView t3=(TextView)view.findViewById(R.id.t3);
        final TextView t4=(TextView)view.findViewById(R.id.t4);
        final TextView t5=(TextView)view.findViewById(R.id.t5);
        final TextView t6=(TextView)view.findViewById(R.id.t6);
        final TextView t7=(TextView)view.findViewById(R.id.t7);
        final TextView t8=(TextView)view.findViewById(R.id.t8);
        final TextView t9=(TextView)view.findViewById(R.id.t9);
        final TextView t10=(TextView)view.findViewById(R.id.t10);
        final TextView t11=(TextView)view.findViewById(R.id.t11);
        final TextView t12=(TextView)view.findViewById(R.id.t12);
        final TextView t13=(TextView)view.findViewById(R.id.t13);
        //動畫
        final Animation animation13 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation13.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t13.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation12 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation12.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t12.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t13.startAnimation(animation13);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation11 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation11.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t11.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t12.startAnimation(animation12);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation10 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation10.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t10.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t11.startAnimation(animation11);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation9 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation9.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t9.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t10.startAnimation(animation10);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation8 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation8.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t8.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t9.startAnimation(animation9);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation7 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation7.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t7.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t8.startAnimation(animation8);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation6 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t6.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t7.startAnimation(animation7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation5 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t5.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t6.startAnimation(animation6);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation4 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t4.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t5.startAnimation(animation5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t3.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t4.startAnimation(animation4);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t3.startAnimation(animation3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                t1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                t2.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        t1.startAnimation(animation);
    }

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