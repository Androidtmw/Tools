package com.tmw.tools;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.widget.ImageView;

import com.tmw.tools.base.BaseActivity;



/**
 * 欢迎页
 */
public class WelcomeActivity extends BaseActivity{
    @Override
    protected int contentView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {

        ImageView imageView = (ImageView) findViewById(R.id.iv_welcome);

        //将ImageView的背景强转为帧动画
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        animationDrawable.start();

        Handler handler = new Handler();

        //利用Handler延时跳转Activity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        },2600);

    }
}
