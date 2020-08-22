package com.tmw.tools;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.tmw.tools.adapter.NewHouseAdapter;
import com.tmw.tools.bean.NewHouse;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;


/**
 * 新房详细页的activity
 */
public class NewHouseActivity extends AppCompatActivity implements DownUtil.OnDownComplete {


    private TextView textView;
    private NewHouseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newhouse);
        init();
        loadDatas();

    }

    private void init() {

        ListView listView = (ListView) findViewById(R.id.lv_newhouse);

        adapter = new NewHouseAdapter(this);

        listView.setAdapter(adapter);

        textView = (TextView) findViewById(R.id.tv_total_newhouse);
    }


    private void loadDatas() {

        Intent intent = getIntent();

        int cityId = intent.getIntExtra(Constant.KEY.CITY_ID,1);

        String url = String.format(Constant.Url.LOOKING_NEWHOUSE,0,cityId);

        DownUtil.downJSON(url,this);
    }

    @Override
    public void onDownSucc(String url, Object obj) {

        String jsonString = (String) obj;

        NewHouse newHouse = new Gson().fromJson(jsonString,NewHouse.class);

        textView.setText("共有"+newHouse.getTotal()+"个楼盘");

        adapter.setDatas(newHouse.getData());

    }
}
