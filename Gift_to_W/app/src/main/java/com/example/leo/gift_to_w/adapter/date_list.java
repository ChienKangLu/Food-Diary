package com.example.leo.gift_to_w.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;

public class date_list extends ArrayAdapter {
	Context context;

	String[] date={"2015/12/13 12:12:34","2015/12/13 12:12:34","2015/12/13 12:12:34"};
	public date_list(Context context) {
		super(context,0);
		this.context = context;

		// TODO Auto-generated constructor stub
	}

	public int getCount() {
		return date.length;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row=null;
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();

		row = inflater.inflate(R.layout.date_list_item, parent, false);//youtubethumbnailview
		TextView title = (TextView) row.findViewById(R.id.text);
		title.setText(date[position]);
		return row;
	}


}
