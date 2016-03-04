package com.tmw.tools.adapter;

import android.content.Context;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter_more;
import com.tmw.tools.bean.City;


/**
 * 城市选择页面的ListView的适配器
 */
public class CityAdapter extends AbsBaseAdapter_more<City> {


    public CityAdapter(Context context) {
        super(context, R.layout.item_cityletter_layout,R.layout.item_city_layout);
    }


    @Override
    public int getItemViewType(int position) {

        return datas.get(position).getType();//类型来区别布局
    }

    @Override
    public void bindDatas(ViewHolder viewHolder, City data, int position) {

        viewHolder.bindTextView(R.id.city_name,data.getCityname());

    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position)==1?true:false;
    }


    /**
     * 通过选中的字母来获取对应listView中字母的下标
     * @param label
     * @return
     */
    public int getIndexByLabel(String label){

        for (int i = 0;i<datas.size();i++){
            if (datas.get(i).equalsLabel(label)){
                return i;
            }
        }

        return -1;
    }

}
