package com.tmw.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmw.tools.adapter.DetailsAdapter;
import com.tmw.tools.bean.HomeDetail;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页新闻的详情页的activity
 */
public class HomeDetailsActivity extends AppCompatActivity implements DownUtil.OnDownComplete, View.OnClickListener {


    private TextView detailTitle;
    private TextView detailSource;
    private TextView detailTime;
    private DetailsAdapter adapter;

    private List<HomeDetail> detailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_details);

        init();

        loadDatas();

    }

    private void init() {

        ListView detailListView = (ListView) findViewById(R.id.lv_detail);


        detailTitle = (TextView) findViewById(R.id.tv_detail_title);

        detailSource = (TextView) findViewById(R.id.tv_detail_source);

        detailTime = (TextView) findViewById(R.id.tv_detail_time);


        adapter = new DetailsAdapter(this);

        detailListView.setAdapter(adapter);

        ImageButton backButton = (ImageButton) findViewById(R.id.button_detail_back);

        backButton.setOnClickListener(this);
    }


    private void loadDatas() {

        detailList = new ArrayList<>();

        Intent intent = getIntent();

        String newsId = intent.getStringExtra("NEWSID");

        String detailUrl = String.format(Constant.Url.NEWS_DETAIL,newsId);

        DownUtil.downJSON(detailUrl,this);


    }

    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null){

            String jsonString = (String) obj;

            try {
                JSONObject jsonObject = new JSONObject(jsonString);

                String title = jsonObject.getString("title");
                String source = jsonObject.getString("source");
                String time = jsonObject.getString("time");

                detailTitle.setText(title);
                detailSource.setText(source);
                detailTime.setText(time);

                JSONArray jsonArray = jsonObject.getJSONArray("content");

                TypeToken<List<HomeDetail>> tt = new TypeToken<List<HomeDetail>>() {};

                detailList = new Gson().fromJson(jsonArray.toString(), tt.getType());

                adapter.setDatas(detailList);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
