package com.tmw.tools.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter_more;
import com.tmw.tools.bean.Joke;

/**
 * Created by tmw on 2016/2/19.
 */
public class JokeAdapter extends AbsBaseAdapter_more<Joke>{


    public JokeAdapter(Context context) {
        super(context, R.layout.item_joke_layout1,R.layout.item_joke_layout2);
    }

    @Override
    public int getItemViewType(int position) {

        if (datas.get(position).getPicUrl()==null||datas.get(position).getPicUrl().equals("")){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, Joke data, int position) {

        if (TextUtils.isEmpty(data.getPicUrl())){
            viewHolder.bindTextView(R.id.tv_joke_author,data.getAuthor())
                    .bindTextView(R.id.tv_joke_content,data.getContent());
        }else {
            viewHolder.bindTextView(R.id.tv_joke_author,data.getAuthor())
                    .bindTextView(R.id.tv_joke_content,data.getContent())
                    .bindCacheImageView(R.id.iv_joke,data.getPicUrl());

        }

    }
}
