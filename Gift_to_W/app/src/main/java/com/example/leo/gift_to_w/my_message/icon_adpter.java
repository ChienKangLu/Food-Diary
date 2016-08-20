package com.example.leo.gift_to_w.my_message;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.img_tool.img_compress;
import com.example.leo.gift_to_w.mytools.tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/1.
 */
public class icon_adpter extends RecyclerView.Adapter<icon_adpter.ViewHolder> {

        private RecyclerView mList;
        public ArrayList<Item> myItem;
        Context context;
        public static int whichone=-1;
        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView pic;
            ImageView check;
            View card;
            public ViewHolder(View v) {
                super(v);
                pic=(ImageView)v.findViewById(R.id.pic);
                check=(ImageView)v.findViewById(R.id.check);
                card=v;
            }
        }

        public static class Item{
            String pic_name;
            //boolean checked=false;
            public Item(String pic_name) {

                this.pic_name = pic_name;
            }
        }
        public icon_adpter(String dbid, Context context, RecyclerView mList) {
            myItem=new ArrayList<>();
            for(int i=1;i<15;i++){
                myItem.add(new Item("w"+i));
            }
            whichone=-1;

            this.context=context;
            this.mList=mList;
        }

        @Override
        public icon_adpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.icon, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            int resID = context.getResources().getIdentifier(myItem.get(position).pic_name, "drawable", context.getPackageName());
            Log.v("RRR", "" + resID);
            holder.pic.setImageBitmap(img_compress.decodeSampledBitmapFromResource(context.getResources(), resID, 100, 100));
            if(position==whichone){
                holder.check.setVisibility(View.VISIBLE);
            }else{
                holder.check.setVisibility(View.INVISIBLE);
            }

            holder.pic.setTag(""+position);
            holder.pic.setOnClickListener(add);
        }

        @Override
        public int getItemCount() {
            return myItem.size();//myItem.size()
        }

        View.OnClickListener add=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"good",Toast.LENGTH_LONG).show();

                int position=Integer.parseInt(v.getTag().toString());
                if(position==whichone){//原來是有選取的
                    whichone=-1;
                    notifyDataSetChanged();
                }else{
                    whichone=position;
                    notifyDataSetChanged();
                }

            }
        };
        public void setCheck(String picname){//w1 w2 w3 w4 w5 ....
            String pos=picname.replace("w","");
            whichone=Integer.parseInt(pos)-1;
            notifyDataSetChanged();
        }
    }







