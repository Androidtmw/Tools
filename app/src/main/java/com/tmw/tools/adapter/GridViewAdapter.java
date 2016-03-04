package com.tmw.tools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.tmw.tools.R;
import com.tmw.tools.bean.GradViewItem;

import java.util.List;

/**
 * Created by tmw on 2016/2/16.
 */
public class GridViewAdapter extends BaseAdapter{

    private Context context;

    private List<GradViewItem> list;

    public GridViewAdapter(Context context,List<GradViewItem> list){
        this.context = context;

        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder=null;

        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_layout,null,false);

            TextView textView = (TextView) convertView.findViewById(R.id.tv_gradview);

            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_gradview);

            viewHolder = new ViewHolder(imageView,textView);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.getImageView().setImageResource(list.get(position).getResId());
        viewHolder.getTextView().setText(list.get(position).getName());


        return convertView;
    }


    private class ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public ViewHolder() {
        }

        public ViewHolder(ImageView imageView, TextView textView) {
            this.imageView = imageView;
            this.textView = textView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }
    }
}
