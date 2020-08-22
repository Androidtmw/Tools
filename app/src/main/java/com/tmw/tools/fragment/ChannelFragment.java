package com.tmw.tools.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import androidx.fragment.app.Fragment;

import com.tmw.tools.R;
import com.tmw.tools.base.AbsBaseAdapter;
import com.tmw.tools.bean.PublicChannel;
import com.tmw.tools.bean.SingerChannel;

import java.util.ArrayList;

/**
 * Created by tmw on 2016/1/25.
 */
public class ChannelFragment extends Fragment implements AdapterView.OnItemClickListener {


    private GridView gridView;
    private ArrayList<PublicChannel> publicChannels;
    private ArrayList<SingerChannel> SingerChannels;

    private OnChannelItemClickListener onChannelItemClickListener;


    public interface OnChannelItemClickListener {
        void OnChannelItemClick(String url);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_channel,null);

        gridView= (GridView) view.findViewById(R.id.gv_channel);

        gridView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle=getArguments();

        int x=bundle.getInt("key");
        //Log.d("key", ": "+x);
        if (x==1){
            publicChannels = (ArrayList<PublicChannel>) bundle.getSerializable("json");

            AbsBaseAdapter<PublicChannel> adapter=new AbsBaseAdapter<PublicChannel>(getActivity(),R.layout.channel_item_layout) {
                @Override
                public void bindDatas(ViewHolder viewHolder, PublicChannel data) {
                    viewHolder.bindTextView(R.id.tv_channel,data.getName());
                    viewHolder.bindCacheImageView(R.id.iv_channel,data.getThumb());

                }
            };

            gridView.setAdapter(adapter);
            adapter.setDatas(publicChannels);
        }else if (x==2){
            SingerChannels = (ArrayList<SingerChannel>) bundle.getSerializable("json");

            //Log.d("data", list.get(0).getAvatar());

            AbsBaseAdapter<SingerChannel> adapter=new AbsBaseAdapter<SingerChannel>(getActivity(),R.layout.channel_item_layout) {

                @Override
                public void bindDatas(ViewHolder viewHolder, SingerChannel data) {
                    viewHolder.bindTextView(R.id.tv_channel,data.getName());
                    viewHolder.bindCacheImageView(R.id.iv_channel,data.getAvatar());
                }
            };

            gridView.setAdapter(adapter);
            adapter.setDatas(SingerChannels);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (publicChannels!=null){

            String channelName = publicChannels.get(position).getCh_name();

            //Log.d("id", channelName);

            onChannelItemClickListener.OnChannelItemClick(channelName);
        }else {


        }

    }



    public void setOnChannelItemClickListener(OnChannelItemClickListener onChannelItemClickListener) {
        // TODO Auto-generated method stub
        this.onChannelItemClickListener = onChannelItemClickListener;
    }




}
