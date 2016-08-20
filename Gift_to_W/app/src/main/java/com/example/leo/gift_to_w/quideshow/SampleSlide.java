package com.example.leo.gift_to_w.quideshow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.mytools.tools;

import java.util.ArrayList;

public class SampleSlide extends Fragment {

    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    private static final String IMG_ID = "img_id";
    private static final String TXT = "TXT";


    public static SampleSlide newInstance(int layoutResId,int pic_id,String... str) {


        ArrayList<String> txt=new ArrayList<>();
        for (String data:str) {
            txt.add(data);
        }
        SampleSlide sampleSlide = new SampleSlide();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        args.putInt(IMG_ID, pic_id);
        args.putStringArrayList(TXT,txt);
        sampleSlide.setArguments(args);

        return sampleSlide;
    }

    private int layoutResId;
    private int pic_id;
    private ArrayList<String> txt=new ArrayList<>();

    public SampleSlide() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = getArguments().getInt(ARG_LAYOUT_RES_ID);
            pic_id=getArguments().getInt(IMG_ID);
            txt=getArguments().getStringArrayList(TXT);
        }

    }
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(layoutResId, container, false);

        ImageView img=(ImageView)view.findViewById(R.id.img);
        img.setImageResource(pic_id);

        LinearLayout title=(LinearLayout)view.findViewById(R.id.title);
        int fontsize[]={20,23};
        int count=0;
        for(String data : txt ) {
            setMytitle(data,fontsize[count] ,title);
            count++;
        }
        return view;
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