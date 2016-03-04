package com.tmw.tools.adapter;

import android.content.Context;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter_more;
import com.tmw.tools.bean.HomeDetail;


/**
 * 首页新闻的详情页的adapter
 */
public class DetailsAdapter extends AbsBaseAdapter_more<HomeDetail> {

    public DetailsAdapter(Context context) {
        super(context, R.layout.item_details_layout, R.layout.item_details_layout2);
    }

    @Override
    public int getItemViewType(int position) {

        return datas.get(position).getType()-1;
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, HomeDetail data, int position) {

        if (data.getType()==1){
            viewHolder.bindTextView(R.id.tv_detail_content,data.getValue());
        }else {
            viewHolder.bindCacheImageView(R.id.iv_detail_content,data.getValue());
        }


    }
}
