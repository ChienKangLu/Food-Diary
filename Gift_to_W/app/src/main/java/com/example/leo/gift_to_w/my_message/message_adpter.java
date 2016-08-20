package com.example.leo.gift_to_w.my_message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.MainActivity;
import com.example.leo.gift_to_w.MyOnTouch;
import com.example.leo.gift_to_w.MyOnTouch2;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.custom_listener.DoubleClickListener;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.food;
import com.example.leo.gift_to_w.database.message;
import com.example.leo.gift_to_w.database.place;
import com.example.leo.gift_to_w.img_tool.img_compress;
import com.example.leo.gift_to_w.mytools.tools;
import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/1.
 */
public class message_adpter extends RecyclerView.Adapter<message_adpter.ViewHolder> {
        public final static String none="#@none@#";
        private RecyclerView mList;
        DBManager DB;
        public ArrayList<HashMap<String,String>> data;//接資料庫data
        public ArrayList<Item> myItem;
        Context context;
        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView pic;
            TextView article;
            TextView datetime;
            ImageView dots;
            View card;
            public ViewHolder(View v) {
                super(v);
                title = (TextView) v.findViewById(R.id.title);
                pic=(ImageView)v.findViewById(R.id.pic);
                article=(TextView) v.findViewById(R.id.article);
                datetime=(TextView) v.findViewById(R.id.datetime);
                dots=(ImageView) v.findViewById(R.id.dots);
                card=v;
            }
        }

        public static class Item{
            String id;
            String title;
            String pic_name;
            String article;
            String datetime;

            public Item(String id, String title, String pic_name, String article, String datetime) {
                this.id = id;
                this.title = title;
                this.pic_name = pic_name;
                this.article = article;
                this.datetime = datetime;
            }
        }
        public message_adpter(String dbid, Context context, RecyclerView mList) {
            myItem=new ArrayList<>();
            //DB第一次SELECET資料
            DB = new DBManager(context);
            message message=new message(DB);
            Cursor c=message.select("Select * from message where food_id="+dbid);
            //place_id,place_name,count
            data=DBManager.readCursor(c);
            DB.closeDatabase();
            Log.v("fff",""+data.size()+"~~"+dbid);
            for(int i=0;i<data.size();i++){
                myItem.add(new Item(data.get(i).get("message_id"),data.get(i).get("message_title"),data.get(i).get("message_pic"),data.get(i).get("message_content"),data.get(i).get("message_date_time")));
                //int yy=R.drawable.w2;
                //Log.v("RRR",""+yy);
            }


            this.context=context;
            this.mList=mList;
        }

        @Override
        public message_adpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_card, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            if(!myItem.get(position).title.equals(none)) {
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(myItem.get(position).title);
                tools.setMyFont("RobotoTTF/Roboto-Black.ttf", holder.title, context);
            }else{
                holder.title.setVisibility(View.GONE);
            }

            if(!myItem.get(position).pic_name.equals(none)) {
                holder.pic.setVisibility(View.VISIBLE);
                int resID = context.getResources().getIdentifier(myItem.get(position).pic_name, "drawable", context.getPackageName());
                holder.pic.setImageBitmap(img_compress.decodeSampledBitmapFromResource(context.getResources(), resID, 100, 100));
            }else{
                holder.pic.setVisibility(View.GONE);
            }

            if(!myItem.get(position).article.equals(none)) {
                holder.article.setVisibility(View.VISIBLE);
                holder.article.setText(myItem.get(position).article);
            }else{
                holder.article.setVisibility(View.GONE);
            }

            holder.datetime.setText(myItem.get(position).datetime);

            holder.dots.setTag(position);//myItem.get(position).id
            holder.dots.setOnClickListener(chooseMode);
        }

        View.OnClickListener chooseMode= new View.OnClickListener() {//選項
            @Override
            public void onClick(View v) {
                final FrameLayout anim_place=(FrameLayout)((Activity) context).findViewById(R.id.anim_place);
                final View nowv=v;
                //動畫
                Animation animation =AnimationUtils.loadAnimation(context, R.anim.slide_in_up);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        anim_place.setVisibility(View.VISIBLE);
                        anim_place.setTag(nowv.getTag().toString());
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //anim_place.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                anim_place.startAnimation(animation);


            }

        };
        @Override
        public int getItemCount() {
            return myItem.size();//myItem.size()
        }

        public void additem(String food_id,String title,String messageS,String date,String pic){
            if(title.equals("")){
                title=none;
            }
            if(messageS.equals("")){
                messageS=none;
            }
            if(pic.equals("0")){
                pic=none;
            }


            myItem.add(new Item("", title, pic, messageS, date));
            notifyItemInserted(getItemCount() - 1);
            mList.scrollToPosition(getItemCount() - 1);

            DB = new DBManager(context);
            message message=new message(DB);

            long id=message.insert(food_id,title,pic,messageS,date);
            myItem.get(getItemCount() - 1).id = "" + id;
            DB.closeDatabase();

        }

        public void remove(int id ,View v){
            final int pos=id;
            Snackbar.make(v, "是否要刪除 ?", Snackbar.LENGTH_SHORT).setAction("是", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "成功刪除", Toast.LENGTH_SHORT).show();

                    DBManager DB = new DBManager(context);
                    message message=new message(DB);
                    message.delete(myItem.get(pos).id);
                    DB.closeDatabase();

                    myItem.remove(pos);

                    notifyItemRemoved(pos);//動畫上的消除
                    notifyItemRangeChanged(pos, getItemCount());//告知資料量改變

                }
            }).show();
        }
        public void edititem(String food_id,String title,String messageS,String pic,String pos){
            int posNow=Integer.parseInt(pos);
            if(title.equals("")){
                title=none;
            }
            if(messageS.equals("")){
                messageS=none;
            }
            if(pic.equals("0")){
                pic=none;
            }


            //myItem.add(new Item("", title, pic, messageS, date));
            myItem.get(posNow).title=title;
            myItem.get(posNow).pic_name=pic;
            myItem.get(posNow).article=messageS;
            notifyDataSetChanged();

            DB = new DBManager(context);
            message message=new message(DB);
            message.update(myItem.get(posNow).id,title,pic,messageS);
            DB.closeDatabase();

        }

}








            /*holder.card.setOnTouchListener(new MyOnTouch2(context,holder.card){
                @Override
                public void edit() {
                    super.edit();
                    View v=super.myView;
                    Snackbar.make(v, "編輯", Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void delete() {
                    super.delete();
                    View v=super.myView;
                    Snackbar.make(v, "是否要刪除 ?", Snackbar.LENGTH_SHORT).setAction("是", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(context, "成功刪除", Toast.LENGTH_SHORT).show();
                            //DB DELETE
                        }
                    }).show();
                }
            });*/