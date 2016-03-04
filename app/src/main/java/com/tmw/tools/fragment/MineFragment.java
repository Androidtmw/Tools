package com.tmw.tools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tmw.tools.ChangepwActivity;
import com.tmw.tools.LoginActivity;
import com.tmw.tools.R;
import com.tmw.tools.base.BaseFragment;
import com.tmw.tools.util.Constant;
import com.tmw.tools.util.ShareUtil;

/**
 * Created by tmw on 2016/2/19.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    private Button exitButton;

    private Button logoffButton;

    private Button changeButton;

    private String username;

    @Override
    protected int contentResid() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(View view) {

        logoffButton = (Button) view.findViewById(R.id.button_logoff);
        logoffButton.setOnClickListener(this);
        exitButton = (Button) view.findViewById(R.id.button_exit);
        exitButton.setOnClickListener(this);

        changeButton = (Button) view.findViewById(R.id.button_changepassword);
        changeButton.setOnClickListener(this);
    }

    @Override
    protected void loadDatas() {

        Bundle arguments = getArguments();
        username = arguments.getString(Constant.KEY.UN);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_logoff){
            //注销时清除保留的记录
            ShareUtil.putString(null, Constant.KEY.PWD);
            ShareUtil.putBoolean("isRemember",false);
            ShareUtil.putBoolean("isAuto",false);

            startActivity(new Intent(getActivity(), LoginActivity.class));

            getActivity().finish();
        }else if (v.getId() == R.id.button_exit){
            getActivity().finish();
        }else {
            Intent intent = new Intent(getActivity(), ChangepwActivity.class);
            intent.putExtra(Constant.KEY.UN, username);
            startActivity(intent);
        }

    }
}
