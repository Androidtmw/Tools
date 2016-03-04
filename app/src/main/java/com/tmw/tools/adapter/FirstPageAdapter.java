package com.tmw.tools.adapter;

import android.content.Context;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter_more;
import com.tmw.tools.bean.HomeNews;


/**
 * Created by tmw on 2016/2/16.
 */
public class FirstPageAdapter extends AbsBaseAdapter_more<HomeNews> {


    public FirstPageAdapter(Context context) {
        super(context, R.layout.item_home_layout, R.layout.item_home2_layout);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, HomeNews data, int position) {

        if (data.getType()==0){
            viewHolder.bindTextView(R.id.tv_home_title,data.getTitle())
                    .bindTextView(R.id.tv_home_summary,data.getSummary())
                    .bindCacheImageView(R.id.iv_homemsg,data.getThumbnail());
        }else {
            viewHolder.bindTextView(R.id.tv_home_title,data.getTitle())
            .bindCacheImageView(R.id.iv_homemore,data.getGroupthumbnail());
        }


    }
}
