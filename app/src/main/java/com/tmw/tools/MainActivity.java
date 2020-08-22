package com.tmw.tools;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.tmw.tools.base.BaseActivity;
import com.tmw.tools.fragment.FunctionFragment;
import com.tmw.tools.fragment.HomeFragment;
import com.tmw.tools.fragment.MineFragment;
import com.tmw.tools.util.Constant;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    private String username;

    @Override
    protected int contentView() {

        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        radioGroup = findViewById(R.id.rg_tab);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        username = intent.getStringExtra(Constant.KEY.UN);//接收登录页面传过来的用户名
        radioGroup.getChildAt(0).performClick();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_function) {
            FunctionFragment functionFragment = new FunctionFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY.UN, username);
            functionFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, functionFragment)
                    .commit();

        } else if (checkedId == R.id.rb_house) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new HomeFragment())
                    .commit();

        } else if (checkedId == R.id.rb_mine) {

            MineFragment mineFragment = new MineFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.KEY.UN, username);

            mineFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, mineFragment)
                    .commit();

        }

    }
}
