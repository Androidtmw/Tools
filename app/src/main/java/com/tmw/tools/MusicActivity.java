package com.tmw.tools;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tmw.tools.bean.PublicChannel;
import com.tmw.tools.bean.SingerChannel;
import com.tmw.tools.fragment.ChannelFragment;
import com.tmw.tools.fragment.MusicListFragment;
import com.tmw.tools.fragment.PlayFragment;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.DownUtil;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements DownUtil.OnDownComplete, View.OnClickListener, ViewPager.OnPageChangeListener, ChannelFragment.OnChannelItemClickListener, MusicListFragment.OnSongItemClickListener {


    private HorizontalScrollView scrollView;
    private ViewPager viewPager;
    private View cursorView;
    private String titles[];
    private ArrayList<SingerChannel> publicList;
    private ArrayList<SingerChannel> singerList;
    private LinearLayout titleLayout;
    private List<Integer> viewWidths=new ArrayList<>();
    private String urlMusic;
    private int songId = 0;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music);


        initViews();

        initDatas();
    }


    private void initViews() {

       scrollView = (HorizontalScrollView) findViewById(R.id.sv_music);

        titleLayout= (LinearLayout) findViewById(R.id.ll_title);

        viewPager= (ViewPager) findViewById(R.id.vp_channel);
       // viewPager.setOnPageChangeListener(MainActivity.this);

        cursorView=findViewById(R.id.view_item);
    }

    private void initDatas() {

        titles=new String[4];

        DownUtil.downJSON(Constant.Url.DIANTAI_URL,this);

    }

    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null) {

            String jsonString = (String) obj;

            try {
                JSONObject jsonObject = new JSONObject(jsonString);

                JSONArray jsonArray = jsonObject.getJSONArray("result");

                JSONObject publicObject = (JSONObject) jsonArray.get(0);

                // Log.d("hehe", "onDownSucc: "+publicObject.toString());

                titles[0] = publicObject.getString("title");

                JSONArray array = publicObject.getJSONArray("channellist");

                TypeToken<List<PublicChannel>> tt = new TypeToken<List<PublicChannel>>() {
                };
                publicList = new Gson().fromJson(array.toString(),
                        tt.getType());

                JSONObject publicObject2 = (JSONObject) jsonArray.get(1);

                titles[1] = publicObject2.getString("title");

                JSONArray array2 = publicObject2.getJSONArray("channellist");

                TypeToken<List<SingerChannel>> tt2 = new TypeToken<List<SingerChannel>>() {};
                singerList = new Gson().fromJson(array2.toString(), tt2.getType());

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

                titles[2] = "歌曲列表";
                titles[3] = "播放页面";

                for (int i = 0; i < titles.length; i++) {
                    TextView tv = new TextView(this);
                    tv.setText(titles[i]);
                    tv.setGravity(Gravity.CENTER);
                    tv.setPadding(40, 0, 40, 0);
                    tv.setLayoutParams(layoutParams);
                    tv.setOnClickListener(MusicActivity.this);
                    tv.setTag(i);
                    titleLayout.addView(tv);

                    tv.measure(0, 0);//调用系统测量

                    viewWidths.add(tv.getMeasuredWidth());

                }


                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) cursorView.getLayoutParams();

                params.width = viewWidths.get(0);

                cursorView.setLayoutParams(params);

                adapter = new ViewPagerAdapter(getSupportFragmentManager());

                viewPager.setAdapter(adapter);

                viewPager.addOnPageChangeListener(MusicActivity.this);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //每个标题的点击事件回调方法
    @Override
    public void onClick(View v) {

        int index= (int) v.getTag();//通过标签获取当前是哪个标题

        viewPager.setCurrentItem(index);//将viewPager跳到指定页面

    }

    //回调方法
    @Override
    public void OnChannelItemClick(String url) {
        urlMusic = url;
        viewPager.setCurrentItem(2);
    }

    @Override
    public void OnSongItemClick(int id) {

        songId = id;

        //viewPager.removeView(viewPager.getChildAt(3));
        viewPager.setCurrentItem(1);

        viewPager.setCurrentItem(3);
    }


    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            ChannelFragment fragment=new ChannelFragment();
            fragment.setOnChannelItemClickListener(MusicActivity.this);
            Bundle bundle=new Bundle();
            if (position==0){
                bundle.putSerializable("json",publicList);
                bundle.putInt("key", 1);//增加标识来区别
                fragment.setArguments(bundle);
                return fragment;
            }else if (position==1){
                bundle.putSerializable("json",singerList);
                bundle.putInt("key", 2);
                fragment.setArguments(bundle);
                return fragment;
            }else if (position==2){

                MusicListFragment musicListFragment = new MusicListFragment();
                bundle.putString("url", urlMusic);

                //Log.d("接口回调传过来的url", urlMusic);
                musicListFragment.setArguments(bundle);
                musicListFragment.setOnSongItemClickListener(MusicActivity.this);
                return  musicListFragment;
            }else if (position == 3){

                PlayFragment playFragment = new PlayFragment();

                bundle.putInt("songId",songId);
                playFragment.setArguments(bundle);
                songId = 0;//传递之后将songId清零，以免下次启动Fragment时再将数据传递过去
                return playFragment;
            }
            return null;
        }

        @Override
        public int getCount() {

            return titles.length;
        }

    }


    //ViewPager的滑动监听事件
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) cursorView.getLayoutParams();

        //计算光标的移动位置
        int width = 0;
        for(int i = 0; i < position; i++){
            width += viewWidths.get(i);
        }
        width += viewWidths.get(position) * positionOffset;
        params.leftMargin = width;

        //计算光标的长度
        if(position < viewWidths.size() - 1){
            params.width = (int) (viewWidths.get(position) + (viewWidths.get(position + 1) - viewWidths.get(position)) * positionOffset);
        } else {
            params.width = viewWidths.get(position);
        }
        cursorView.setLayoutParams(params);//设置滑动光标的位置

        scrollView.scrollTo(params.leftMargin - 50, 0);

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
