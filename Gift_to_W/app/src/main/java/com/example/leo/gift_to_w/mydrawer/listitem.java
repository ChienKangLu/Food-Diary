package com.example.leo.gift_to_w.mydrawer;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;
import com.example.leo.gift_to_w.database.DBManager;
import com.example.leo.gift_to_w.database.place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listitem extends ArrayAdapter {
	Context context;
	//ArrayList<String> data;
	DBManager DB;
	public ArrayList<HashMap<String,String>> data;
	public listitem(Context context) {
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
	int size=0;
	public int getCount() {
		size=data.size()+1+1;
		return size;//最後一個要加上去  7-->9
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row=null;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		if(position==0){
			row = inflater.inflate(R.layout.drawer_list_item_header, parent, false);//youtubethumbnailview
			row.setTag("header");
		}else
		if(position>0 &&position<size-1) {//0開頭 0 1 2 3 4 5 6 7
			row = inflater.inflate(R.layout.drawer_list_item, parent, false);//youtubethumbnailview
			TextView title = (TextView) row.findViewById(R.id.text);
			title.setText(data.get(position-1).get("place_name"));
			TextView count = (TextView) row.findViewById(R.id.count);
			count.setText(data.get(position-1).get("count"));
			//row.setTag(0,position);
			row.setTag(position-1);
		}else{
			row = inflater.inflate(R.layout.drawer_list_item2, parent, false);//youtubethumbnailview
			row.setTag("the_last");
		}
		return row;
	}
	public void add(String title){

		//資料庫
		DB = new DBManager(context);
		place place=new place(DB);
		long id=place.insert(title);
		DB.closeDatabase();

		//本地資料
		HashMap<String,String> row=new HashMap<>();

		row.put("place_name",title);
		row.put("place_id",""+id);
		row.put("count","0");
		data.add(row);


		//data.add(title);
		//DB INSERT
		//重新讀取GETVIEW
	}


}
