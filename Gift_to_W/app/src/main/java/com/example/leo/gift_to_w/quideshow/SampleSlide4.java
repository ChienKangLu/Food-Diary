package com.example.leo.gift_to_w.quideshow;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.leo.gift_to_w.MainActivity;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.mytools.tools;

public class SampleSlide4 extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";


    public static SampleSlide4 newInstance(int layoutResId) {


        SampleSlide4 sampleSlide = new SampleSlide4();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);
        return sampleSlide;

    }

    private int layoutResId;

    public SampleSlide4() {}

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
        TextView title=(TextView)view.findViewById(R.id.title);
        ImageView mybaymax=(ImageView)view.findViewById(R.id.mybamax);
        mybaymax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                tools.stop();

                SharedPreferences settings;
                settings = getActivity().getSharedPreferences("LoginInfo", 0);
                settings.edit()
                        .putString("goin","no").commit();
            }
        });
        tools.setMyFont("myfont/ladyw5.TTC", title, getActivity());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        final TextView title=(TextView)view.findViewById(R.id.title);
        final View line1=(View)view.findViewById(R.id.line1);
        final View line2=(View)view.findViewById(R.id.line2);
        final Animation animation_title = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_sacle_slow);
        animation_title.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                title.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                t2.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Animation animation_line2 = AnimationUtils.loadAnimation(getActivity(), R.anim.title_pull_in_left);
        animation_line2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                line2.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                t2.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Animation animation_line1 = AnimationUtils.loadAnimation(getActivity(), R.anim.title_pull_in_right);
        animation_line1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                line1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                t2.startAnimation(animation2);
                title.startAnimation(animation_title);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        line2.startAnimation(animation_line2);
        line1.startAnimation(animation_line1);
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