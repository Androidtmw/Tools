package com.tmw.tools.fragment;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.tmw.tools.CityActivity;
import com.tmw.tools.HomeDetailsActivity;
import com.tmw.tools.NewHouseActivity;
import com.tmw.tools.R;
import com.tmw.tools.adapter.FirstPageAdapter;
import com.tmw.tools.adapter.GridViewAdapter;
import com.tmw.tools.base.BaseFragment;
import com.tmw.tools.bean.GradViewItem;
import com.tmw.tools.bean.HomeNews;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;
import com.tmw.tools.util.ParseUtil;
import com.tmw.tools.util.ShareUtil;
import com.tmw.tools.views.HeadView;
import com.tmw.tools.views.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页Fragment
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, DownUtil.OnDownComplete, AdapterView.OnItemClickListener {

    private Button button;
    private ListView listView;
    private MyGridView gridView;
    private HeadView headView;

    private String urlString;
    private List<HomeNews> list;
    private FirstPageAdapter adapter;
    private String homeUrl;

    private int reqnum = 20;
    private int pageflag = 0;
    private int buttonmore = 0;
    private int cityId = 1;
    private String cityName;


    @Override
    protected int contentResid() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {

        button = (Button) view.findViewById(R.id.button_city);

        button.setOnClickListener(this);

        //初始化时读取共享参数
        String name = ShareUtil.getString(Constant.KEY.CITY_NAME);
        int id = ShareUtil.getInt(Constant.KEY.CITY_ID);

        if (name != null) {
            button.setText(name);
        }

        if (id != -1) {
            cityId = id;
        }

        //初始化控件
        listView = (ListView) view.findViewById(R.id.lv_home);

        adapter = new FirstPageAdapter(getActivity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);


        headView = new HeadView(getActivity(),getActivity().getSupportFragmentManager());


        gridView = new MyGridView(getActivity());
        gridView.setNumColumns(4);
        gridView.setGravity(Gravity.CENTER);

        gridView.setOnItemClickListener(this);

        listView.addHeaderView(headView);
        listView.addHeaderView(gridView);

    }

    @Override
    protected void loadDatas() {

        list = new ArrayList<HomeNews>();

        //首页资讯的url
        urlString = String.format(Constant.Url.FIRST_PAGE_LISTVIEW, reqnum, pageflag, buttonmore, cityId);

        DownUtil.downJSON(urlString, this);//下载数据


        //首页新闻的URL
        homeUrl = String.format(Constant.Url.FIRST_NEWS, cityId);

        headView.setUrl(homeUrl);


        //GridView的数据
        List<GradViewItem> gradViewItems = new ArrayList<>();

        gradViewItems.add(new GradViewItem("新房", R.drawable.ic_xinfang_pressed));
        gradViewItems.add(new GradViewItem("二手房", R.drawable.ic_ershou_pressed));
        gradViewItems.add(new GradViewItem("租房", R.drawable.ic_zufang_pressed));
        gradViewItems.add(new GradViewItem("资讯", R.drawable.ic_zixun_pressed));
        gradViewItems.add(new GradViewItem("优惠", R.drawable.ic_youhui_pressed));
        gradViewItems.add(new GradViewItem("贷款计算", R.drawable.ic_calculator_pressed));
        gradViewItems.add(new GradViewItem("开盘", R.drawable.ic_kaipan_pressed));
        gradViewItems.add(new GradViewItem("更多", R.drawable.ic_more_pressed));

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), gradViewItems);

        gridView.setAdapter(gridViewAdapter);


    }

    //获取 cityActivity的返回值，并重新加载数据
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.KEY.REQUEST_CODE && resultCode == Constant.KEY.RESULT_CODE) {

            cityName = data.getStringExtra(Constant.KEY.CITY_NAME);
            cityId = data.getIntExtra(Constant.KEY.CITY_ID, 0);
            button.setText(cityName);
            loadDatas();

            //写入共享参数
            ShareUtil.putString(cityName, Constant.KEY.CITY_NAME);
            ShareUtil.putInt(cityId, Constant.KEY.CITY_ID);
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), CityActivity.class);

        startActivityForResult(intent, Constant.KEY.REQUEST_CODE);

    }


    //下载完成后，解析数据
    @Override
    public void onDownSucc(String url, Object obj) {

        if (url.equals(urlString)) {

            String jsonString = (String) obj;

            list = ParseUtil.parseNews(jsonString);

            adapter.setDatas(list);

        }
    }

    //ListView和GridView的点击回调方法
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (parent.getId() == R.id.lv_home) {

            Intent intent = new Intent(getActivity(), HomeDetailsActivity.class);

            String newsId = list.get(position-2).getId();

            intent.putExtra("NEWSID", newsId);

            startActivity(intent);

        } else {

            switch (position) {
                case 0:
                    Intent intent = new Intent(getActivity(), NewHouseActivity.class);

                    intent.putExtra(Constant.KEY.CITY_ID, cityId);

                    startActivity(intent);

                    break;
            }

        }

    }

}
