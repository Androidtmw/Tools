package com.tmw.tools.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tmw.tools.R;
import com.tmw.tools.base.BaseFragment;
import com.tmw.tools.bean.Advertisement;
import com.tmw.tools.views.CacheImageView;

/**
 * 头部的Fragment.
 */
public class HeadFragment extends BaseFragment{

    private CacheImageView imageView;
    private TextView textView;


    /**
     * 静态工厂，返回一个HeadFragment对象
     * @param advertisement
     * @return
     */
    public static HeadFragment getInstance(Advertisement advertisement){

        HeadFragment headFragment = new HeadFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",advertisement);

        headFragment.setArguments(bundle);
        return headFragment;

    }


    @Override
    protected int contentResid() {
        return R.layout.fragment_headview;
    }

    @Override
    protected void init(View view) {

        imageView = (CacheImageView) view.findViewById(R.id.iv_headview);

        textView = (TextView) view.findViewById(R.id.tv_headview);
    }

    @Override
    protected void getDatas(Bundle arguments) {

        Advertisement advertisement = (Advertisement) arguments.getSerializable("key");

        imageView.setUrl(advertisement.getPicurl());
        textView.setText(advertisement.getTitle());

    }
}
