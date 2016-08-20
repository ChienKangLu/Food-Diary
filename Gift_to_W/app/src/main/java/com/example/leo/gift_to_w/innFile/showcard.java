package com.example.leo.gift_to_w.innFile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.leo.gift_to_w.MainActivity;
import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.add_food;
import com.example.leo.gift_to_w.animation.animation_test;
import com.example.leo.gift_to_w.card.cardadpter;

/**
 * Created by leo on 2016/3/1.
 */
public class showcard extends Fragment {

    public showcard() {
        // Required empty public constructor
    }

    View view;
    cardadpter myAdapter;
    String dbid = "";//初值
    String title="";
    RecyclerView mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.showcard, container, false);
        mList = (RecyclerView) view.findViewById(R.id.listview);

        Bundle b = getArguments();
        if(b!=null){
            dbid=b.getString("dbid");
            title=b.getString("title");
            //Toast.makeText(getActivity(), ""+dbid, Toast.LENGTH_SHORT).show();
        }
//        myAdapter = new cardadpter(dbid,getActivity(), mList);
//
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mList.setLayoutManager(layoutManager);
//        mList.setAdapter(myAdapter);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);


        final String finalDbid = dbid;//copy dbid because need final
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {

                //add_food
                Intent intent = new Intent();


                Bundle bundle = new Bundle();
                bundle.putString("dbid", finalDbid);
                intent.putExtras(bundle);

                intent.setClass(getActivity(), add_food.class);
                startActivityForResult(intent,1);

                //myAdapter.additem("品田",1,2,1,"0");//good


                //  myAdapter.additem(myDataset2);
                // myAdapter.notifyDataSetChanged();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }


    @Override
    public void onResume() {
        super.onResume();
        myAdapter = new cardadpter(dbid,getActivity(), mList);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);
//        Toast.makeText(getActivity(),"onResume showcard",Toast.LENGTH_LONG).show();

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if(resultCode==0) {
                if (data != null) {
                    Bundle getdata = data.getExtras();
                    String name = getdata.getString("foodname");//名稱
                    String lat = getdata.getString("lat"); //經度
                    String lon = getdata.getString("lon");//緯度
                    int time = 0;//次數
                    String love = "0";//是不是很喜歡

                    // Toast.makeText(getActivity(), "得到data2", Toast.LENGTH_SHORT).show();

                    myAdapter.additem(dbid, name, lat, lon, time, love);//String name,double lat,double lon,int time,String love
                }
            }
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        //this.menu=menu;
        menu.clear();
        inflater.inflate(R.menu.showcard, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                myAdapter.removeAll(view, title, dbid);


//                MainActivity.mDrawerList.getAdapter().getView(0,null,null).performClick();
                Log.v("qwer","delete");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
