package com.tmw.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.tmw.tools.adapter.CityAdapter;
import com.tmw.tools.bean.City;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;
import com.tmw.tools.util.ParseUtil;
import com.tmw.tools.views.LabelView;
import com.tmw.tools.views.SideView;

import java.util.List;

/**
 * 城市选择页面
 */
public class CityActivity extends AppCompatActivity implements DownUtil.OnDownComplete, SideView.OnSideClickedListener, AdapterView.OnItemClickListener {

    private ListView cityListView;

    private SideView sideView;

    private LabelView labelView;

    private List<City> cityList;

    private CityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        initViews();

        initDatas();

    }

    private void initViews() {

        cityListView = (ListView) findViewById(R.id.lv_city);
        adapter = new CityAdapter(this);
        cityListView.setAdapter(adapter);
        cityListView.setOnItemClickListener(this);

        sideView = (SideView) findViewById(R.id.sideView);
        sideView.setOnSideClickedListener(this);


        labelView = (LabelView) findViewById(R.id.labelView);

    }

    private void initDatas() {

        DownUtil.downJSON(Constant.Url.CHOICE_CITY, this);
    }


    @Override
    public void onDownSucc(String url, Object obj) {


        if (url == Constant.Url.CHOICE_CITY) {

            String jsonString = (String) obj;

            //Log.d("json", "onDownSucc: " + jsonString);

            cityList = ParseUtil.parseJson(jsonString);

            adapter.setDatas(cityList);

        }

    }

    @Override
    public void onSideClicked(int index, String str) {
        labelView.setText(str);

        int letterIndex = adapter.getIndexByLabel(str);

        cityListView.smoothScrollToPositionFromTop(letterIndex,0);//第二个参数代表Y轴的偏移量
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String cityName = cityList.get(position).getCityname();
        int cityId = cityList.get(position).getCityid();

        Intent intent = getIntent();

        intent.putExtra("cityID",cityId);
        intent.putExtra("cityName",cityName);

        setResult(Constant.KEY.RESULT_CODE, intent);

        this.finish();

    }
}
