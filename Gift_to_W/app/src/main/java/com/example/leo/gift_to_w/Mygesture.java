package com.example.leo.gift_to_w;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by leo on 2016/3/8.
 */
public class Mygesture  extends GestureDetector.SimpleOnGestureListener{

    View myView;
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("MyGestureDetectorListener", "onSingleTapUp");
        return super.onDoubleTap(e);

    }
    public void getV(View v){
        this.myView=v;
    }
}
