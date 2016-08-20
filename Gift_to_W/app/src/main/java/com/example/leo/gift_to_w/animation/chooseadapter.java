package com.example.leo.gift_to_w.animation;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.place;
import com.example.leo.gift_to_w.mytools.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class chooseadapter extends ArrayAdapter {
	Context context;
	//ArrayList<String> data;
	DBManager DB;
	public ArrayList<HashMap<String,String>> data;
	public chooseadapter(Context context) {
		super(context,0);
		this.context = context;

		//DB SELECT
		DB = new DBManager(context);
		place place=new place(DB);
		Cursor c=place.select("select place.place_id,place.place_name,COALESCE(NULLIF(other.count,''), 0) as count from place left join (select place.place_id as id,place.place_name as name,count(place.place_id) as count from food Left join place on food.place_id=place.place_id group by place.place_id) as other on place.place_id=other.id");
		//place_id,place_name,count
		data=DBManager.readCursor(c);
		DB.closeDatabase();

		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return data.size()+1;//最後一個要加上去
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row=null;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		if(position!=data.size()) {
			row = inflater.inflate(R.layout.choose_list_item, parent, false);//youtubethumbnailview
			TextView title = (TextView) row.findViewById(R.id.text);
			title.setText(data.get(position).get("place_name"));

			tools.setMyFont("RobotoTTF/Roboto-Bold.ttf", title, context);;
			/*
			TextView count = (TextView) row.findViewById(R.id.count);
			count.setText(data.get(position).get("count"));
			*/
			//row.setTag(0,position);
			row.setTag(data.get(position).get("place_id"));
		}else{
			row = inflater.inflate(R.layout.choose_list_item, parent, false);//youtubethumbnailview
			TextView title = (TextView) row.findViewById(R.id.text);
			title.setText("?");
			//title.setTextSize(10);
			tools.setMyFont("RobotoTTF/Roboto-Bold.ttf", title,context);;
			row.setTag("the_last");
		}
		return row;
	}

}
