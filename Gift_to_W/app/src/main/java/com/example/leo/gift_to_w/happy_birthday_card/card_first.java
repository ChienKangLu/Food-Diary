package com.example.leo.gift_to_w.happy_birthday_card;

import android.content.Context;
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
import com.example.leo.gift_to_w.img_tool.img_compress;
import com.example.leo.gift_to_w.mytools.tools;

import java.util.ArrayList;

public class card_first extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    private static final String TXT = "TXT";


    public static card_first newInstance(int layoutResId) {


        card_first sampleSlide = new card_first();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int layoutResId;

    public card_first() {}

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
        final ImageView baymax=(ImageView)view.findViewById(R.id.baymax);
        final ImageView hi=(ImageView)view.findViewById(R.id.hi);
        final ImageView title=(ImageView)view.findViewById(R.id.title);
        final Animation animation_title = AnimationUtils.loadAnimation(getActivity(), R.anim.title_rotate);
        animation_title.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                title.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //t2.startAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        final Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.bay_down);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                baymax.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //t2.startAnimation(animation2);
                title.startAnimation(animation_title);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        baymax.startAnimation(animation);
        return view;
    }

}