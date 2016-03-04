package com.tmw.tools.adapter;

import android.content.Context;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter;
import com.tmw.tools.bean.Weather;

/**
 * Created by tmw on 2016/2/19.
 */
public class WeatherAdapter extends AbsBaseAdapter<Weather.DataEntity.ForecastEntity> {

    public WeatherAdapter(Context context) {

        super(context, R.layout.item_weather_layout);

    }


    @Override
    public void bindDatas(ViewHolder viewHolder, Weather.DataEntity.ForecastEntity data) {

        ViewHolder viewHolder1 = viewHolder.bindTextView(R.id.tv_weather_date, data.getDate().split("日")[0]+"日")
                .bindTextView(R.id.tv_weather_week,data.getDate().split("日")[1])
                .bindTextView(R.id.tv_weather_weather,data.getType())
                .bindTextView(R.id.tv_weather_wind,data.getFengxiang()+","+data.getFengli())
                .bindTextView(R.id.tv_weather_temperature, "温度："+data.getLow().substring(2) + "~" + data.getHigh().substring(2));

        if (data.getType().equals("晴")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_sunny);
        }else if (data.getType().equals("阴")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_yin);
        }else if (data.getType().equals("多云")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_cloud);
        }else if (data.getType().equals("阵雨")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_zhenyu);
        }else if (data.getType().equals("小雨")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_xiaoyu);
        }else if (data.getType().equals("中雨")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_zhongyu);
        }else if (data.getType().equals("大雨")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_dayu);
        }else if (data.getType().equals("暴雨")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_baoyu);
        }else if (data.getType().equals("雷阵雨")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_leizhenyu);
        }else if (data.getType().equals("阵雪")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_zhenxue);
        }else if (data.getType().equals("小雪")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_xiaoxue);
        }else if (data.getType().equals("中雪")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_zhongxue);
        }else if (data.getType().equals("大雪")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_daxue);
        }else if (data.getType().equals("暴雪")){
            viewHolder1.bindImageView(R.id.iv_weather,R.drawable.weather_baoxue);
        }

    }
}
