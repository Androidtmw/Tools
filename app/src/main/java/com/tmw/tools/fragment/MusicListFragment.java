package com.tmw.tools.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmw.tools.R;
import com.tmw.tools.adapter.MusicListAapter;
import com.tmw.tools.base.BaseFragment;
import com.tmw.tools.bean.Song;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;
import com.tmw.tools.util.ShareUtil;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by tmw on 2016/2/20.
 */
public class MusicListFragment extends BaseFragment implements DownUtil.OnDownComplete, AdapterView.OnItemClickListener {

    private ListView listView;
    private MusicListAapter adapter;
    private List<Song> songs;
    private String url;//某一频道下音乐列表的链接




    private OnSongItemClickListener onSongItemClickListener;


    public interface OnSongItemClickListener {
        void OnSongItemClick(int id);
    }

    public void setOnSongItemClickListener(OnSongItemClickListener onSongItemClickListener) {
        this.onSongItemClickListener = onSongItemClickListener;
    }


    @Override
    protected int contentResid() {
        return R.layout.fragment_musiclist;
    }

    @Override
    protected void init(View view) {
        listView = (ListView) view.findViewById(R.id.lv_music);
        adapter = new MusicListAapter(getActivity());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void loadDatas() {

        Bundle bundle = getArguments();

        url = bundle.getString("url");
        if (url==null){
            //表示没有选择频道，则从本地读取
            url = ShareUtil.getString("musicList");
        }
        if (url!=null){
            String urlString = String.format(Constant.Url.MUSIC_LIST_URL,url);
            //Log.d("需要下载歌曲列表的url", urlString);
            DownUtil.downJSON(urlString,this);
            ShareUtil.putString(url,"musicList");
        }
    }

    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null){

            String jsonString = (String) obj;

            //Log.d("json", "onDownSucc: "+jsonString);

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");

                JSONArray jsonArray = jsonObject1.getJSONArray("songlist");

               TypeToken<List<Song>> tt = new TypeToken<List<Song>>(){};

                songs = new Gson().fromJson(jsonArray.toString(), tt.getType());

                //Log.d("歌曲集合", songs.toString());
                songs.remove(songs.size()-1);

                adapter.setDatas(songs);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (songs!=null){

            String songid = songs.get(position).getSongid();
            if (songid!=null) {
                int idOfSong = Integer.valueOf(songid);

                onSongItemClickListener.OnSongItemClick(idOfSong);
            }
        }

    }
}
