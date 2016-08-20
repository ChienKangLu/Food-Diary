package com.example.leo.gift_to_w.animation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.add_article;
import com.example.leo.gift_to_w.choose_place;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.food;
import com.example.leo.gift_to_w.database.place;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leo on 2016/3/1.
 */
public class animation_test extends Fragment {

    public animation_test() {
        // Required empty public constructor
    }

    static View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.animation_test, container, false);
        //RecyclerView mList = (RecyclerView) view.findViewById(R.id.listview);
        ImageView stark=(ImageView)view.findViewById(R.id.stark);

        final ImageView starkFinal=stark;
        final Animation stark_rotation =AnimationUtils.loadAnimation(getActivity(), R.anim.stark_rotation);
        final Animation sacle =AnimationUtils.loadAnimation(getActivity(),R.anim.sacle);
        sacle.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                starkFinal.startAnimation(stark_rotation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        stark_rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), choose_place.class);
                startActivityForResult(intent, 3);
                ActivityAnimator a=new ActivityAnimator();
                a.down_to_top(getActivity());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        stark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView eat_palce=(TextView)view.findViewById(R.id.eat_palce);
                final TextView decide=(TextView)view.findViewById(R.id.decide);
                eat_palce.setVisibility(View.INVISIBLE);
                decide.setVisibility(View.INVISIBLE);
                starkFinal.startAnimation(sacle);
            }
        });



        TextView place_txt1 = (TextView) view.findViewById(R.id.place_txt1);
        TextView place_txt2 = (TextView) view.findViewById(R.id.place_txt2);
        TextView place_txt3 = (TextView) view.findViewById(R.id.place_txt3);
        TextView food_txt1 = (TextView) view.findViewById(R.id.food_txt1);
        TextView food_txt2 = (TextView) view.findViewById(R.id.food_txt2);
        TextView food_txt3 = (TextView) view.findViewById(R.id.food_txt3);
        TextView decide = (TextView) view.findViewById(R.id.decide);
        setMyFont("RobotoTTF/Roboto-Black.ttf",place_txt1);
        setMyFont("RobotoTTF/Roboto-Black.ttf",place_txt2);
        setMyFont("RobotoTTF/Roboto-Black.ttf",place_txt3);
        setMyFont("RobotoTTF/Roboto-Black.ttf",food_txt1);
        setMyFont("RobotoTTF/Roboto-Black.ttf",food_txt2);
        setMyFont("RobotoTTF/Roboto-Black.ttf",food_txt3);
        setMyFont("RobotoTTF/Roboto-Bold.ttf", decide);

        //Toast.makeText(getActivity(), "onCreateView_animation", Toast.LENGTH_SHORT).show();//1 onetimes





        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refreshdata(getActivity());//
        /*
        final TextView place_txt2 = (TextView) view.findViewById(R.id.place_txt2);

        final TextView food_txt2 = (TextView) view.findViewById(R.id.food_txt2);

        final Animation sacle_place =AnimationUtils.loadAnimation(getActivity(),R.anim.sacle);

        final Animation sacle_place2 =AnimationUtils.loadAnimation(getActivity(),R.anim.sacle);

        sacle_place.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                food_txt2.startAnimation(sacle_place2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        place_txt2.startAnimation(sacle_place);
        */
    }
    public static void refreshdata(Context context){

        final TextView place_txt2 = (TextView)view.findViewById(R.id.place_txt2);

        final TextView food_txt2 = (TextView) view.findViewById(R.id.food_txt2);

        //DB ANIMATION 更新數量~~嘻嘻
        DBManager DB;
        DB = new DBManager(context);
        place place = new place(DB);
        String place_size=place.size();
        food food =new food(DB);
        String food_size=food.size();
        DB.closeDatabase();

        place_txt2.setText(place_size);
        food_txt2.setText(food_size);


        final Animation sacle_place =AnimationUtils.loadAnimation(context,R.anim.sacle);

        final Animation sacle_place2 =AnimationUtils.loadAnimation(context,R.anim.sacle);

        sacle_place.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                food_txt2.startAnimation(sacle_place2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        place_txt2.startAnimation(sacle_place);
    }
    @Override
    public void onResume() {
        super.onResume();

//        Toast.makeText(getActivity(), "onResume_animation", Toast.LENGTH_SHORT).show();//3 every times



    }

    @Override
    public void onStart() {
        super.onStart();


//        Toast.makeText(getActivity(), "onStart_animation", Toast.LENGTH_SHORT).show();//2 every times

    }

    public void setMyFont(String font,TextView txt){
        Typeface myTypeface = Typeface.createFromAsset(
                getActivity().getAssets(),
                font);

        txt.setTypeface(myTypeface);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();//滑動到第三頁觸發

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
       }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        //this.menu=menu;
        setHasOptionsMenu(true);
        //inflater.inflate(R.menu.showcard, menu);
    }

    public int myRandom(int X,int Y){
        //X~Y
        int num= (int) (Math.random() * (Y - X + 1)) + X;
        return num;
    }
    public ArrayList<HashMap<String, String>> getselectfood(String id){
        DBManager DB;
        DB = new DBManager(getActivity());
        food food = new food(DB);
        Cursor c = food.select("Select food.food_id,food.food_name,place.place_id,place.place_name from food left join place on food.place_id==place.place_id where food.place_id=" + id);
        //place_id,place_name,count
        ArrayList<HashMap<String, String>> DBdata = DBManager.readCursor(c);
        DB.closeDatabase();
        return DBdata;
    }
    public ArrayList<HashMap<String, String>> getallplace(){
        DBManager DB;
        DB = new DBManager(getActivity());
        place place = new place(DB);
        Cursor c = place.select("select * from place");
        //place_id,place_name,count
        ArrayList<HashMap<String, String>> DBdata = DBManager.readCursor(c);
        DB.closeDatabase();
        return DBdata;
    }
    public String gettitle(String id){//0
        DBManager DB;
        DB = new DBManager(getActivity());
        place place = new place(DB);
        Cursor c = place.select("select * from place where place_id="+id);
        //place_id,place_name,count
        ArrayList<HashMap<String, String>> DBdata = DBManager.readCursor(c);
        DB.closeDatabase();
        return DBdata.get(0).get("place_name");
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(data!=null) {
            if(requestCode==3) {
                Bundle getdata = data.getExtras();
                String idorlast = getdata.getString("idorlast");
                //Toast.makeText(getActivity(), idorlast, Toast.LENGTH_SHORT).show();

                String nowplace="";
                String nowfood="";
                if(!idorlast.equals("the_last")) {

                    //取得全部的食物
                    ArrayList<HashMap<String, String>> DBdata =getselectfood(idorlast);

                    if(DBdata.size()!=0) {
                        //隨機產生
                        int chooseFoodpos = myRandom(0, DBdata.size() - 1);

                        nowplace = DBdata.get(chooseFoodpos).get("place_name");
                        nowfood = DBdata.get(chooseFoodpos).get("food_name");
                        // Toast.makeText(getActivity(), " 計算:"+" 食物名稱:"+nowfood+" 隨機號碼:"+chooseFoodpos+" 資料多寡:"+DBdata.size(), Toast.LENGTH_SHORT).show();
                    }else{
                        nowplace = gettitle(idorlast);
                        nowfood ="這是個蠻荒之地";
                    }
                }else{//可能的錯誤==>>>當一個地點都沒有的時候
                    //取得全部的place
                    ArrayList<HashMap<String, String>> DBdataplace =getallplace();
                    //隨機產生
                    int chooseplace=myRandom(0,DBdataplace.size()-1);

                    //取得全部的食物
                    ArrayList<HashMap<String, String>> DBdatafood =getselectfood(""+DBdataplace.get(chooseplace).get("place_id"));
                    if(DBdatafood.size()!=0) {
                        //隨機產生
                        int chooseFoodpos = myRandom(0, DBdatafood.size() - 1);
                        nowplace = DBdatafood.get(chooseFoodpos).get("place_name");
                        nowfood = DBdatafood.get(chooseFoodpos).get("food_name");
                    }else{
                        nowplace = DBdatafood.get(chooseplace).get("place_id");//gettitle(DBdataplace.get(chooseplace).get("place_id"));
                        nowfood ="這是個蠻荒之地";
                    }

                }
//                Toast.makeText(getActivity(), " 計算:"+" 食物名稱:"+nowfood, Toast.LENGTH_SHORT).show();





                //展開一連串的動畫~哈哈哈哈哈爽~
        ////////////////////////////////////////////////////////////////////////////
        final TextView eat_palce=(TextView)view.findViewById(R.id.eat_palce);
        final TextView decide=(TextView)view.findViewById(R.id.decide);
        decide.setText(nowfood);
        eat_palce.setText(nowplace);
        final Animation fade_in_eat_palce =AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);

        final Animation fade_in_decide =AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        fade_in_decide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                decide.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        fade_in_eat_palce.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                eat_palce.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                decide.startAnimation(fade_in_decide);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation stark_rotation =AnimationUtils.loadAnimation(getActivity(), R.anim.stark_rotation);
        stark_rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                eat_palce.startAnimation(fade_in_eat_palce);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ImageView stark=(ImageView)view.findViewById(R.id.stark);
        final ImageView starkFinal=stark;

        starkFinal.startAnimation(stark_rotation);
        ///////////////////////////////////////////////////////////////////////////////

            }
        }
    }
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
