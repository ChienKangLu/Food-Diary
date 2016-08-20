package com.example.leo.gift_to_w.mytools;

import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.example.leo.gift_to_w.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by leo on 2016/3/6.
 */
public class tools {
    public static MediaPlayer mediaPlayer;
    public static boolean played=false;
    public static void setMyFont(String font,TextView txt,Context context){
        Typeface myTypeface = Typeface.createFromAsset(
                context.getAssets(),
                font);

        txt.setTypeface(myTypeface);
    }
    public static void start(Context context){
        MediaPlayer mediaPlayer=MediaPlayer.create(context, R.raw.animal);
        mediaPlayer.start();
    }
    public static ArrayList<String> readcard(Context context,int txt){
        final Context contxtnow=context;
        String uri = "android.resource://" + context.getPackageName() + "/"+txt;//txt-->R.raw.card
        //final Uri uri = getIntent() != null ? getIntent().getData() : null;
        InputStream inputStream = null;
        String str = "";
        StringBuffer buf = new StringBuffer();

        try {
            inputStream = context.getContentResolver().openInputStream(Uri.parse(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayList<String> data=new ArrayList<>();
        if (inputStream!=null){
            try {
                while((str = reader.readLine())!=null){
                    buf.append(str+"\n");
                    Log.v("mycardhappy", str);
                    data.add(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //txt.setText(buf.toString());
        }
        return data;

    }
    public static void play(Context context,int mid) {
        played=true;
        final Context contxtnow=context;
        String uri = "android.resource://" + context.getPackageName() + "/"+mid;//R.raw.tryeverything;
//        String path = "android.resource://" + context.getPackageName() + "/" + R.raw.animal;
        //String path = "animal.m4a";
//        File file = new File(path);
//        if (file.exists() && file.length() > 0) {
            try {
                mediaPlayer = new MediaPlayer();
                // 设置指定的流媒体地址

                mediaPlayer.setDataSource(context,
                        Uri.parse(uri));
                // 设置音频流的类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                // 通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 装载完毕 开始播放流媒体
                        mediaPlayer.start();
                        //Toast.makeText(contxtnow, "开始播放", Toast.LENGTH_LONG).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(contxtnow, "播放失败", Toast.LENGTH_LONG).show();
            }
//        } else {
//            Toast.makeText(contxtnow, "文件不存在", Toast.LENGTH_LONG).show();
//        }

    }
    /**
     * 停止播放
     */
    public static void stop() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            played=false;
            //btn_play.setEnabled(true);
            //Toast.makeText(this, "停止播放", 0).show();
        }

    }
}
