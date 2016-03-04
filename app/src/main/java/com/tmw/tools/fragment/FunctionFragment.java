package com.tmw.tools.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.tmw.tools.CalculatorActivity;
import com.tmw.tools.CalendarActivity;
import com.tmw.tools.JokeActivity;
import com.tmw.tools.MusicActivity;
import com.tmw.tools.R;
import com.tmw.tools.WeatherActivity;
import com.tmw.tools.adapter.FunctionAdapter;
import com.tmw.tools.base.BaseFragment;
import com.tmw.tools.bean.Function;
import com.tmw.tools.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能页的Fragment
 */
public class FunctionFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView funListView;
    private String funs[] = {"计算器","日历","天气预报","幽默笑话","在线音乐","出行导航"};
    private int resId[] = {R.drawable.fun_calculator,R.drawable.fun_calendar,R.drawable.fun_weather
    ,R.drawable.fun_joke,R.drawable.fun_music,R.drawable.fun_bus};

    private List<Function> functionList;
    private TextView unTextView;


    @Override
    protected int contentResid() {

        return R.layout.fragment_function;
    }

    @Override
    protected void init(View view) {

        funListView = (ListView) view.findViewById(R.id.lv_function);
        funListView.setOnItemClickListener(this);

        unTextView = (TextView) view.findViewById(R.id.tv_welcome);
    }

    @Override
    protected void loadDatas() {

        Bundle arguments = getArguments();
        String username = arguments.getString(Constant.KEY.UN);
        unTextView.setText("欢迎你："+username);

        functionList = new ArrayList<>();

        for (int i =0;i<resId.length;i++){

            Function function = new Function(resId[i],funs[i]);

            functionList.add(function);

        }

        FunctionAdapter adapter = new FunctionAdapter(getActivity(),functionList);

        funListView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == 0) {
            Intent intent = new Intent(getActivity(), CalculatorActivity.class);
            startActivity(intent);
        }else if (position == 1){
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivity(intent);
        }else if (position == 2){
            Intent intent = new Intent(getActivity(), WeatherActivity.class);
            startActivity(intent);
        }else if (position == 3){
            Intent intent = new Intent(getActivity(), JokeActivity.class);
            startActivity(intent);
        }else if (position == 4){
            Intent intent = new Intent(getActivity(), MusicActivity.class);
            startActivity(intent);
        }

    }
}
