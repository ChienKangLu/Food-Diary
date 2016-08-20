package com.example.leo.gift_to_w.card;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.MainActivity;
import com.example.leo.gift_to_w.MyOnTouch;
import com.example.leo.gift_to_w.Mygesture;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.adapter.date_list;
import com.example.leo.gift_to_w.animation.ActivityAnimator;
import com.example.leo.gift_to_w.custom_listener.DoubleClickListener;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.food;
import com.example.leo.gift_to_w.database.message;
import com.example.leo.gift_to_w.database.place;
import com.example.leo.gift_to_w.my_message.message_show;
import com.example.leo.gift_to_w.mydrawer.listitem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/1.
 */
public class cardadpter  extends RecyclerView.Adapter<cardadpter.ViewHolder> {

        private Status mystatus;
        private RecyclerView mList;
        //insert,delete寫在cardadpter
        DBManager DB;
        public ArrayList<HashMap<String,String>> data;
        Context context;
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ImageView dining;
            public ImageView love;
            public ImageView map;
            public ImageView animlove;
            public ImageView trash;
            public View card;
            public TextView times;
            public TextView message;

            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.info_text);
                dining=(ImageView)v.findViewById(R.id.dining);
                love=(ImageView)v.findViewById(R.id.love);
                map=(ImageView)v.findViewById(R.id.map);
                animlove=(ImageView)v.findViewById(R.id.animlove);
                trash=(ImageView)v.findViewById(R.id.trash);
                times=(TextView)v.findViewById(R.id.times);
                message=(TextView)v.findViewById(R.id.message);
                card=v;

            }
        }

        public static class Status{
            public  static class posItem{
                String lat;
                String lon;
                posItem(String lat,String lon){
                    this.lat=lat;
                    this.lon=lon;
                }
            }
            public ArrayList<String> id;
            public ArrayList<String> name;
            public ArrayList<Integer> time;
            public ArrayList<String> love;
            public ArrayList<posItem> pos;
            public ArrayList<String> message;

            public  Status(){
                this.id=new ArrayList<>();
                this.name=new ArrayList<>();
                this.time=new ArrayList<>();
                this.love=new ArrayList<>();
                this.pos=new ArrayList<>();
                this.message=new ArrayList<>();
            }

        }
        public cardadpter(String dbid,Context context,RecyclerView mList) {
            mystatus=new Status();
            //DB第一次SELECET資料
            DB = new DBManager(context);
            food food=new food(DB);
            Cursor c=food.select("select food.*,COALESCE(NULLIF(t.count,''), 0) as count from food left join (select food_id,count(*) as count from message group by food_id) as t on food.food_id=t.food_id where place_id="+dbid);
            //place_id,place_name,count
            data=DBManager.readCursor(c);
            DB.closeDatabase();


            for(int i=0;i<data.size();i++){
                mystatus.id.add(data.get(i).get("food_id"));
                mystatus.name.add(data.get(i).get("food_name"));
                mystatus.time.add(Integer.parseInt(data.get(i).get("time")));
                mystatus.love.add(data.get(i).get("love"));
                mystatus.pos.add(new Status.posItem(data.get(i).get("lat"), data.get(i).get("lon")));//25.038065, 121.503669
                mystatus.message.add(data.get(i).get("count"));
            }
            this.context=context;
            this.mList=mList;
        }

        @Override
        public cardadpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.mTextView.setText(mystatus.name.get(position));//mData[position]

            holder.dining.setTag(position);
            holder.dining.setOnClickListener(eat_again);

            holder.love.setTag(position);
            holder.love.setOnClickListener(eat_like);
            if(mystatus.love.get(position).equals("0")){
                holder.love.setImageResource(R.drawable.like);//改為喜歡
            }else{
                holder.love.setImageResource(R.drawable.love_red);//改為不喜歡
            }
            holder.map.setTag(position);
            holder.map.setOnClickListener(gomap);

            holder.card.setTag(position);

            holder.card.setOnTouchListener(new MyOnTouch(context,holder.card){//在這裡可以更改雙擊的項目holder.card * 2
                @Override
                public void love_animation() {
                    super.love_animation();
                    View v=super.myView;
                    love_Anim(v);

                }

                @Override
                public void show_message() {
                    super.show_message();
                    View v=super.myView;
                    go_message(v);
                }
            });

           // holder.card.setOnClickListener(go_message);

            holder.animlove.setVisibility(View.INVISIBLE);

            holder.trash.setTag(position);
            holder.trash.setOnClickListener(remove);

            holder.times.setText(mystatus.time.get(position).toString());

            holder.message.setText(mystatus.message.get(position));

        }

        @Override
        public int getItemCount() {
            return mystatus.name.size();
        }
        private View.OnClickListener eat_again = new View.OnClickListener() {
            public void onClick(View v) {
               // View parent=(View)v.getParent().getParent();
               // TextView changettime=(TextView)parent.findViewById(R.id.times);
                int position=Integer.parseInt(v.getTag().toString());


                //DB UPDATE 次數+1
                DB = new DBManager(context);
                food food=new food(DB);
                int n=food.update_time(""+(mystatus.time.get(position)+1),Integer.parseInt(mystatus.id.get(position)));
                Log.v("updatee",""+n);
                DB.closeDatabase();


                mystatus.time.set(position,mystatus.time.get(position)+1);
                notifyDataSetChanged();

                Snackbar.make(v, "我又來吃了一次   "+mystatus.name.get(position), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        };
        private View.OnClickListener eat_like = new View.OnClickListener() {
            public void onClick(View v) {
                int position=Integer.parseInt(v.getTag().toString());
                DB = new DBManager(context);
                food food=new food(DB);

                if(mystatus.love.get(position).equals("0")){

                    mystatus.love.set(position,"1");
                    notifyDataSetChanged();
                    //((ImageView)v).setImageResource(R.drawable.love_red);//改為喜歡

                    Snackbar.make(v, "我最愛吃  "+mystatus.name.get(position), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    //DB UPDATE LOVE
                    int n=food.update_love("" + 1, Integer.parseInt(mystatus.id.get(position)));
                    Log.v("updatee", "" + n);
                    DB.closeDatabase();


                }else{

                    mystatus.love.set(position, "0");
                    notifyDataSetChanged();

                    //((ImageView)v).setImageResource(R.drawable.like);//改為不喜歡

                    Snackbar.make(v, "沒有非常愛   "+mystatus.name.get(position), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //DB UPDATE LOVE
                    int n=food.update_love("" + 0, Integer.parseInt(mystatus.id.get(position)));
                    Log.v("updatee", "" + n);
                    DB.closeDatabase();
                }
            }
        };
        private View.OnClickListener gomap = new View.OnClickListener() {
            public void onClick(View v) {
                int position=Integer.parseInt(v.getTag().toString());
                String lat=mystatus.pos.get(position).lat;
                String lon=mystatus.pos.get(position).lon;
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lat+","+lon);//google.navigation:q=latitude,longitude
                //google.navigation:q=Taronga+Zoo,+Sydney+Australia  25.038065, 121.503669
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);

            }
        };
        public void go_message(View v){
            int position=Integer.parseInt(v.getTag().toString());
            //add_food
            Intent intent = new Intent();


            Bundle bundle = new Bundle();
            bundle.putString("dbid", mystatus.id.get(position));
            intent.putExtras(bundle);

            intent.setClass(context, message_show.class);

            ((Activity)context).startActivityForResult(intent, 4);
            ActivityAnimator a=new ActivityAnimator();
            a.myPullRightPushLeft((Activity) context);
        }
        public void love_Anim(View v){
            /*
                Snackbar.make(v,"double click", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            int position=Integer.parseInt(v.getTag().toString());
            //實際更改圖示
            //ImageView love=(ImageView)v.findViewById(R.id.love);
            //love.setImageResource(R.drawable.love_red);//改為喜歡
            mystatus.love.set(position, "1");
            //DB UPDATE LOVE

            DB = new DBManager(context);
            food food=new food(DB);
            int n=food.update_love("" + 1, Integer.parseInt(mystatus.id.get(position)));
            Log.v("updatee", "" + n);
            DB.closeDatabase();


            //以下為動畫
            final ImageView now=(ImageView)v.findViewById(R.id.animlove);
            //動畫
            Animation animation =AnimationUtils.loadAnimation(context, R.anim.fade_in_sacle);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    now.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    now.setVisibility(View.INVISIBLE);

                    notifyDataSetChanged();

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            now.startAnimation(animation);


        }

        private View.OnClickListener remove = new View.OnClickListener() {
            public void onClick(View v) {
                final int position=Integer.parseInt(v.getTag().toString());
                Snackbar.make(v, "是否要刪除 "+mystatus.name.get(position)+" ?", Snackbar.LENGTH_SHORT).setAction("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "成功刪除 "+mystatus.name.get(position), Toast.LENGTH_SHORT).show();
                        //DB DELETE
                        DB = new DBManager(context);
                        food food=new food(DB);
                        boolean b=food.delete(mystatus.id.get(position));
                        //food.update_delete(""+1,Integer.parseInt(mystatus.id.get(position)));


                        message message=new message(DB);
                        boolean b2=message.deleteAll(mystatus.id.get(position));

                        DB.closeDatabase();



                        //refresh 抽屜
                        MainActivity.refresh(context);

                        mystatus.name.remove(position);
                        mystatus.love.remove(position);
                        mystatus.time.remove(position);
                        mystatus.pos.remove(position);
                        mystatus.id.remove(position);
                        notifyItemRemoved(position);//動畫上的消除
                        notifyItemRangeChanged(position, getItemCount());//告知資料量改變
                        //notifyDataSetChanged();
                        //////////////////////////////////home


                    }
                }).show();
            }
        };

        public void additem(String dbid,String name,String lat,String lon,int time,String love){

            mystatus.name.add(name);
            mystatus.love.add(love);
            mystatus.time.add(time);
            mystatus.pos.add(new Status.posItem(lat, lon));

            notifyItemInserted(getItemCount() - 1);  // contacts.size() - 1 is the last element position
            mList.scrollToPosition(getItemCount() - 1);
            //notifyItemRangeChanged(position, getItemCount());//告知資料量改變
            //scrollToPosition(mAdapter.getItemCount() - 1); // update based on adapter

            DB = new DBManager(context);
            food food=new food(DB);
            long id=food.insert(name,lat,lon,""+0,""+0,0,Integer.parseInt(dbid));

            mystatus.id.add("" + id);
            DB.closeDatabase();

            //refresh 抽屜
            MainActivity.refresh(context);
        }
        public void removeAll(View v,String title, final String place_id){
            final String titleFinal=title;
            Snackbar.make(v, "是否要刪除 "+titleFinal+" ?", Snackbar.LENGTH_SHORT).setAction("是", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "成功刪除 " + titleFinal, Toast.LENGTH_SHORT).show();
                    //DB DELETE

                    DB = new DBManager(context);

                    message message = new message(DB);
                    message.deletefromPlace(place_id);
                    DB.closeDatabase();


                    place place = new place(DB);
                    boolean b1 = place.delete(place_id);
                    DB.closeDatabase();


                    food food = new food(DB);
                    boolean b2 = food.delete_place(place_id);
                    DB.closeDatabase();

                    //refresh 抽屜
                    MainActivity.refresh(context);


                    //模擬點擊
                    MainActivity.mDrawerList.requestFocusFromTouch();
                    MainActivity.mDrawerList.setSelection(0);

                    MainActivity.mDrawerList.performItemClick(
                            MainActivity.mDrawerList.getAdapter().getView(0, null, null), 0, 0);

                    /*
                    int oldsize = mystatus.name.size();
                    for (int i = oldsize-1; i >=0 ; i--) {
                        mystatus.name.remove(i);
                        mystatus.love.remove(i);
                        mystatus.time.remove(i);
                        mystatus.pos.remove(i);
                        mystatus.id.remove(i);
                    }
                    //notifyItemRemoved(position);//動畫上的消除
                    //notifyItemRangeChanged(position, getItemCount());//告知資料量改變
                    notifyDataSetChanged();
                    */
                }
            }).show();
        }


    }

