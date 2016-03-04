package com.tmw.tools.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tmw on 2016/2/21.
 */
public class MusicPlayService extends Service{

    private MediaPlayer mediaPlayer;

    private LocalBroadcastManager localBroadcastManager;
    private ServiceReceiver serviceReceiver;

    private Timer timer;

    private int maxLen;
    private int curLen;
    @Override
    public void onCreate() {
        super.onCreate();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        serviceReceiver = new ServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.progress");
        localBroadcastManager.registerReceiver(serviceReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (mediaPlayer==null){
            String url = intent.getStringExtra("url");
            if (url!=null){
                Log.d("information", "onStartCommand: "+url);
                Uri uri = Uri.parse(url);

               /* mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(this,uri);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                //mediaPlayer = MediaPlayer.create(this,uri);
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(this,uri);
                    mediaPlayer.prepare();

                    mediaPlayer.start();
                    timer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else if(mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
            mediaPlayer.start();
            timer();
        }


        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 定时器方法 -- 用于实时给activity传输播放进度
     */
    private void timer(){
        if(timer == null){
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        if(mediaPlayer != null ){
                            if (mediaPlayer.isPlaying()){
                                //得到歌曲的总时长 和 当前播放进度
                                maxLen = mediaPlayer.getDuration();//得到歌曲的总时长
                                Log.d("hehe", "run: "+maxLen);
                                curLen = mediaPlayer.getCurrentPosition();//得到当前的播放进度

                                Intent intent = new Intent("android.action.intent.timer");
                                intent.putExtra("maxLen", maxLen);
                                intent.putExtra("curLen", curLen);
                                localBroadcastManager.sendBroadcast(intent);
                            } else {
                                timer.cancel();
                                timer = null;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        timer.cancel();
                        timer = null;
                    }
                }
            }, 0, 500);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            //销毁 回收资源
            //curLen = 0;
            mediaPlayer.release();
        }

        if(serviceReceiver != null){
            localBroadcastManager.unregisterReceiver(serviceReceiver);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class ServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.intent.action.progress")){
                int pro = intent.getIntExtra("pro", 0);//得到拖放的进度
                mediaPlayer.seekTo(pro);
            }
        }
    }
}
