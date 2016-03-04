package com.tmw.tools.adapter;

import android.content.Context;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter;
import com.tmw.tools.bean.Song;


/**
 * Created by tmw on 2016/2/20.
 */
public class MusicListAapter extends AbsBaseAdapter<Song> {

    public MusicListAapter(Context context) {
        super(context, R.layout.item_song_layout);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, Song data) {

        viewHolder.bindCacheImageView(R.id.iv_songname,data.getThumb())
                .bindTextView(R.id.tv_songname,data.getArtist()+"——"+data.getTitle());

    }
}
