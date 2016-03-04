package com.tmw.tools.adapter;

import android.content.Context;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter;
import com.tmw.tools.bean.NewHouse;


/**
 * 新房页面的listView的适配器.
 */
public class NewHouseAdapter extends AbsBaseAdapter<NewHouse.DataEntity> {


    public NewHouseAdapter(Context context) {
        super(context, R.layout.item_newhouse_layout);
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, NewHouse.DataEntity data) {

        ViewHolder viewHolder2 = viewHolder.bindCacheImageView(R.id.iv_newhouse,data.getFcover())
                .bindTextView(R.id.tv_newhouse_title,data.getFname())
                .bindTextView(R.id.tv_newhouse_address,data.getFregion())
                .bindTextView(R.id.tv_newhouse_price,data.getPrice_value()+data.getPrice_unit())
                .bindTextView(R.id.tv_newhouse_detailaddress,data.getFaddress());

        switch(data.getBookmark().size()){
            case 1:
                viewHolder2.bindTextView(R.id.tv_newhouse_tag1, data.getBookmark().get(0).getTag());
                break;
            case 2:
                viewHolder2.bindTextView(R.id.tv_newhouse_tag1, data.getBookmark().get(0).getTag())
                        .bindTextView(R.id.tv_newhouse_tag2,data.getBookmark().get(1).getTag());
                break;
            case 3:
                viewHolder2.bindTextView(R.id.tv_newhouse_tag1, data.getBookmark().get(0).getTag())
                        .bindTextView(R.id.tv_newhouse_tag2,data.getBookmark().get(1).getTag())
                        .bindTextView(R.id.tv_newhouse_tag3, data.getBookmark().get(2).getTag());
                break;
        }



    }
}
