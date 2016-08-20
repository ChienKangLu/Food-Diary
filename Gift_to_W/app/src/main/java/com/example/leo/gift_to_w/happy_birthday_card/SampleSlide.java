package com.example.leo.gift_to_w.happy_birthday_card;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.img_tool.img_compress;
import com.example.leo.gift_to_w.mytools.tools;
import com.example.leo.gift_to_w.quideshow.myshowArticle;

import java.util.ArrayList;

public class SampleSlide extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    private static final String TXT = "TXT";


    public static SampleSlide newInstance(int layoutResId,int str) {


        SampleSlide sampleSlide = new SampleSlide();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        args.putInt(TXT, str);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int layoutResId;
    private int txt;
    private ArrayList<String> data;

    public SampleSlide() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
            txt=getArguments().getInt(TXT);
        }
        data=tools.readcard(getActivity(),txt);

    }
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutResId, container, false);


        LinearLayout title=(LinearLayout)view.findViewById(R.id.title);
        int fontsize[]={20,23};
//        int count=0;
//        TextView now;
//        TextView next;
//        Animation nowAnim;
//        Animation nextAnim;

        for(String txt : data ) {
            if(!txt.equals("memo3_1")) {
                setMytitle(txt, 13, title);


//                count++;
            }else{
                setImg(txt,title);
            }
        }
        return view;
    }
    public void setImg(String name,LinearLayout title){
        int resID = getActivity().getResources().getIdentifier(name, "drawable", getActivity().getPackageName());

        ImageView img = new ImageView(getActivity());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(10, 10, 10, 10);

        img.setImageBitmap(img_compress.decodeSampledBitmapFromResource(getActivity().getResources(), resID, 100, 100));

        title.addView(img, params);
    }
    public TextView setMytitle(String str,int fontsize,LinearLayout title){
        TextView txt=null;
        if(title!=null) {
            txt = new TextView(getActivity());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            params.setMargins(10, 10, 10, 10);
            txt.setText(str);
            txt.setTextSize(fontsize);
            tools.setMyFont("RobotoTTF/Roboto-Black.ttf", txt, getActivity());
            txt.setTextColor(getActivity().getResources().getColor(R.color.dark));


            title.addView(txt, params);
        }
        return txt;
    }

}