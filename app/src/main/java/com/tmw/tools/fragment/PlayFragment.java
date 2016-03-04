package com.tmw.tools.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmw.tools.R;
import com.tmw.tools.base.BaseFragment;
import com.tmw.tools.bean.SongPlay;
import com.tmw.tools.service.MusicPlayService;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;
import com.tmw.tools.util.ShareUtil;
import com.tmw.tools.views.CacheImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tmw on 2016/2/20.
 */
public class PlayFragment extends BaseFragment implements DownUtil.OnDownComplete, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private CacheImageView cacheImageView;
    private TextView textView;
    private TextView timeTextView;
    private SeekBar seekBar;
    private String url;
    private Button playButton;
    private Button stopButton;
    private SongPlay songPlay;
    private String songLink;
    private String progressPause;//记录暂停时的进度
    private String musicTime;//歌曲总时长

    private LocalBroadcastManager localBroadcastManager;
    private MyReceiver myReceiver;

    @Override
    protected int contentResid() {
        return R.layout.fragment_play;
    }

    @Override
    protected void init(View view) {

        cacheImageView = (CacheImageView) view.findViewById(R.id.iv_playmusic);

        textView = (TextView) view.findViewById(R.id.tv_playmusic);
        timeTextView = (TextView) view.findViewById(R.id.tv_playtime);

        playButton = (Button) view.findViewById(R.id.button_play);
        playButton.setOnClickListener(this);

        stopButton = (Button) view.findViewById(R.id.button_stop);
        stopButton.setOnClickListener(this);

        seekBar = (SeekBar) view.findViewById(R.id.sb_seekbar);
        seekBar.setOnSeekBarChangeListener(this);

        //注册本地光播
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        myReceiver = new MyReceiver();
        IntentFilter intent = new IntentFilter();
        intent.addAction("android.action.intent.timer");
        localBroadcastManager.registerReceiver(myReceiver, intent);

        //初始化时读取共享参数

        String musicUrl = ShareUtil.getString("musicUrl");
        String musicName = ShareUtil.getString("musicName");
        musicTime = ShareUtil.getString("musicTime");
        songLink = ShareUtil.getString("songLink");
        progressPause = ShareUtil.getString("progressPause");

        int max = ShareUtil.getInt("max");
        int current = ShareUtil.getInt("current");

        //设置进度条保存的进度
        if (max!=-1&&current!=-1){
            seekBar.setMax(max);
            seekBar.setProgress(current);
        }

        if (musicUrl!=null){
            cacheImageView.setUrl(musicUrl);
        }
        if (musicName!=null){
            textView.setText(musicName);
        }
        if (musicTime!=null&&progressPause!=null){
            timeTextView.setText(progressPause + "/" + musicTime);
        }else if (musicTime!=null){
            timeTextView.setText("00:00/"+musicTime);
        }

    }


    @Override
    protected void getDatas(Bundle arguments) {
        int x = arguments.getInt("songId");//接收acvitity传过来的的歌曲Id

        if (x!=0){

            stop();

            url = String.format(Constant.Url.SONG_URL,x);
            DownUtil.downJSON(url, this);
        }
    }

    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null){
            String jsonString = (String) obj;

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                //Log.d("json对象", "onDownSucc: "+jsonObject1.toString());
                JSONArray jsonArray = jsonObject1.getJSONArray("songList");

                TypeToken<List<SongPlay>> tt= new TypeToken<List<SongPlay>>() {};

                List<SongPlay> list = new Gson().fromJson(jsonArray.toString(), tt.getType());

                songPlay = list.get(0);

                cacheImageView.setUrl(songPlay.getSongPicRadio());
                textView.setText(songPlay.getArtistName() + "——" + songPlay.getSongName());
                seekBar.setProgress(0);
                //int time = songPlay.getTime();
                songLink = songPlay.getSongLink();

                //将时间格式化
                /*Date date = new Date(time*1000);
                SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                String format1 = format.format(date);
                timeTextView.setText(format1);*/

                //将歌曲信息写入共享参数
                ShareUtil.putString(songPlay.getSongPicRadio(), "musicUrl");
                ShareUtil.putString(songPlay.getArtistName() + "——" + songPlay.getSongName(), "musicName");
                //ShareUtil.putString(format1,"musicTime");
                ShareUtil.putString(songLink,"songLink");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * button的点击回调方法
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.button_play){
            Intent intent = new Intent(getActivity(), MusicPlayService.class);
            if (songLink!=null) {
                intent.putExtra("url", songLink);
            }
            getActivity().startService(intent);
            ShareUtil.putString(progressPause,"progressPause");
        }else {
            //点击停止播放时的操作
            stop();
        }

    }


    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.action.intent.timer")){
                int max = intent.getIntExtra("maxLen", 0);
                int cur = intent.getIntExtra("curLen", 0);

                seekBar.setMax(max);
                seekBar.setProgress(cur);
                if(max==cur){
                    cur = 0;
                }
                seekBar.setProgress(0);
                setTimerString(max, cur);
                stop();//判断当播放结束时停止
            }
        }
    }


    /**
     * 设置时间字符串
     * @param max
     * @param cur
     */
    private void setTimerString(int max, int cur){
        Date dateMax = new Date(max);
        Date dateCur = new Date(cur);

        SimpleDateFormat simpeDateFormat = new SimpleDateFormat("mm:ss");
        String maxStr = simpeDateFormat.format(dateMax);
        String curStr = simpeDateFormat.format(dateCur);
        timeTextView.setText(curStr + "/" + maxStr);
        progressPause = curStr;

        musicTime = maxStr;
        ShareUtil.putString(musicTime,"musicTime");
        ShareUtil.putInt(cur, "current");
        ShareUtil.putInt(max,"max");

    }
    //停止方法
    private void stop(){
        //timeTextView.setText("00:00/"+musicTime);//时间归零

        getActivity().stopService(new Intent(getActivity(), MusicPlayService.class));
        seekBar.setMax(100);
        seekBar.setProgress(0);//进度归零
        ShareUtil.putString(null, "progressPause");
        ShareUtil.putString(null, "musicTime");
        ShareUtil.putInt(-1, "current");
        timeTextView.setText("");

    }




    /**
     * SeekBar的监听方法
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        //拖动过程中，一直回调该方法
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //当你拖动前，触发该方法
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //当你拖动停止时，触发该方法
        int pro = seekBar.getProgress();//得到当前SeekBar的进度
        Intent intent = new Intent("android.intent.action.progress");
        intent.putExtra("pro", pro);
        localBroadcastManager.sendBroadcast(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(myReceiver);
    }
}
