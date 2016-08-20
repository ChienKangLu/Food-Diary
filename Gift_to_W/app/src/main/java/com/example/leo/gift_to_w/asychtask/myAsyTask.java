package com.example.leo.gift_to_w.asychtask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.leo.gift_to_w.mytools.tools;
/**
 * Created by leo on 2015/8/9.
 */
public class myAsyTask extends AsyncTask<Void, Void, Void> {

    Context context;
    ListView L;
    public myAsyTask(Context context) {
        this.context=context;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(Void... params) {
        tools.start(context);
        return null;
    }

    @Override
    protected void onPostExecute(Void res) {

    }
}
