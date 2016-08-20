package com.example.leo.gift_to_w;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by leo on 2016/3/8.
 */
public class MyOnTouch implements View.OnTouchListener {
    public Context context;
    public View myView;
    private final GestureDetector gestureDetector;
    public MyOnTouch(Context context, View myView) {
        this.context = context;
        this.myView = myView;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            love_animation();

            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            show_message();
            return super.onSingleTapConfirmed(e);
        }
    }
    public void love_animation(){

    }
    public void show_message(){

    }
}
