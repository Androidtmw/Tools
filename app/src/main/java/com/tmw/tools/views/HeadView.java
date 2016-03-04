package com.tmw.tools.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.tmw.tools.R;
import com.tmw.tools.bean.Advertisement;
import com.tmw.tools.fragment.HeadFragment;
import com.tmw.tools.util.DownUtil;
import com.tmw.tools.util.ParseUtil;

import java.util.List;

/**
 * Created by tmw on 2016/2/18.
 */
public class HeadView extends FrameLayout implements DownUtil.OnDownComplete, ViewPager.OnPageChangeListener {

    private FragmentManager manager;
    private ViewPager viewPager;
    private NavView navView;

    public HeadView(Context context,FragmentManager manager) {
        super(context);
        this.manager = manager;
        init();
    }

    private void init() {

        //加载布局
        LayoutInflater.from(getContext()).inflate(R.layout.headview_layout,this,true);

        viewPager = (ViewPager) findViewById(R.id.vp_headview);

        navView = (NavView) findViewById(R.id.nv_headview);

        viewPager.addOnPageChangeListener(this);
    }

    /**
     * 提供给外部的方法，用来设置链接，内部下载数据
     * @param url
     */
    public void setUrl(String url){

        DownUtil.downJSON(url,this);

    }


    @Override
    public void onDownSucc(String url, Object obj) {

        if (obj!=null){
            String jsonString = (String) obj;

            List<Advertisement> advertisements = ParseUtil.parseAdvertisement(jsonString);

            navView.setCount(advertisements.size());

            HeadViewPagerAdapter adapter = new HeadViewPagerAdapter(manager,advertisements);

            viewPager.setAdapter(adapter);

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (navView.getCount()>0){
            navView.setNavAddress(position,positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class HeadViewPagerAdapter extends FragmentStatePagerAdapter{

        private List<Advertisement> advertisements;


        public HeadViewPagerAdapter(FragmentManager fm,List<Advertisement> advertisements) {
            super(fm);
            this.advertisements = advertisements;
        }

        @Override
        public Fragment getItem(int position) {
            return HeadFragment.getInstance(advertisements.get(position));
        }

        @Override
        public int getCount() {
            return advertisements.size();
        }
    }
}
