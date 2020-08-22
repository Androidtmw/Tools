package com.tmw.tools;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tmw.tools.adapter.WeatherAdapter;
import com.tmw.tools.base.BaseActivity;
import com.tmw.tools.bean.Weather;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;
import com.tmw.tools.util.ShareUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by tmw on 2016/2/19.
 */
public class WeatherActivity extends BaseActivity implements DownUtil.OnDownComplete, View.OnClickListener {

    private ListView listView;
    private Button button;
    private WeatherAdapter adapter;
    private String defaultCity = "北京";
    private String str;
    private TextView textView;

    @Override
    protected int contentView() {
        return R.layout.activity_weather;
    }

    @Override
    protected void init() {
        listView = (ListView) findViewById(R.id.lv_weather);
        adapter = new WeatherAdapter(this);
        listView.setAdapter(adapter);


        button = (Button) findViewById(R.id.button_city_choose);
        button.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.tv_weather_tips);


        //初始化时读取共享参数
        String city = ShareUtil.getString("defaultCity");

        if (city != null) {
            defaultCity = city;
            button.setText(city);
        }


    }

    @Override
    protected void loadData() {
        try {
            //将中文转化为utf编码
            String defaultCityCode = URLEncoder.encode(defaultCity, "UTF-8");

            button.setText(defaultCity);
            //准备一个Url字符串
            str = "http://wthrcdn.etouch.cn/weather_mini?city="+defaultCityCode;

            DownUtil.downJSON(str,this);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null){
            String jsonString = (String) obj;

            Weather weather = new Gson().fromJson(jsonString, Weather.class);

            textView.setText("  小贴士："+weather.getData().getGanmao());

            List<Weather.DataEntity.ForecastEntity> forecasts = weather.getData().getForecast();

            adapter.setDatas(forecasts);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.KEY.REQUEST_CODE && resultCode == Constant.KEY.RESULT_CODE) {

            defaultCity = data.getStringExtra(Constant.KEY.CITY_NAME);
            loadData();

            ShareUtil.putString(defaultCity, "defaultCity");
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, CityActivity.class);

        startActivityForResult(intent, Constant.KEY.REQUEST_CODE);

    }
}
