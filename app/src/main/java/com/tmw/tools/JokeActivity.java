package com.tmw.tools;

import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmw.tools.adapter.JokeAdapter;
import com.tmw.tools.base.BaseActivity;
import com.tmw.tools.bean.Joke;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 笑话页面的Activity
 */
public class JokeActivity extends BaseActivity implements DownUtil.OnDownComplete,AbsListView.OnScrollListener {


    private JokeAdapter adapter;
    private ListView jokeListView;
    private LinearLayout linearLayout;
    private String jokeUrl;
    private int page = 0;
    private boolean isBottom;

    @Override
    protected int contentView() {

        return R.layout.activity_joke;
    }

    @Override
    protected void init() {
        jokeListView = (ListView) findViewById(R.id.lv_joke);

        adapter = new JokeAdapter(this);

        jokeListView.setAdapter(adapter);

        jokeListView.setOnScrollListener(this);


        linearLayout = (LinearLayout) findViewById(R.id.next_page);

    }

    @Override
    protected void loadDatas() {

        jokeUrl = Constant.Url.JOKE_URL+page;

        DownUtil.downJSON(jokeUrl,this);
    }

    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null){

            String jsonString = (String) obj;

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("detail");

                TypeToken<List<Joke>> tt = new TypeToken<List<Joke>>() {};
                List<Joke> jokeList = new Gson().fromJson(jsonArray.toString(), tt.getType());

                adapter.addDatas(jokeList);//这里用add，因为有翻页

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }


    //点击加载更多的回调方法
    public void next(View view) {

        linearLayout.setVisibility(View.GONE);
        page++;
        loadDatas();//页数变更后重新加载

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        //加载更多的按钮默认不可见
        linearLayout.setVisibility(View.GONE);
        //手指滚动时的状态
        if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

        }else if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){//停止滚动的状态
            if(isBottom){
                //让加载更多的按钮可见
                linearLayout.setVisibility(View.VISIBLE);
            }

        }else if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_FLING){//惯性滚动的状态

        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(firstVisibleItem+visibleItemCount==totalItemCount){
            isBottom=true;
        }else{
            isBottom=false;
        }
    }
}
