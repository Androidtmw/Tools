package com.tmw.tools.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tmw.tools.R;

/**
 * 组合导航控件
 */
public class NavView extends FrameLayout{

    private static final String TAG = "print";
    private LinearLayout llChecked, llUnChecked;
    private int checkedRes, unCheckedRes, count;

    private LinearLayout.LayoutParams checkedLP, unCheckedLP;

    private ImageView imageView;//被选中的图片

    private int index;//当前被选中的下标

    public NavView(Context context) {
        super(context);
        init();
    }

    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.nav_layout, this, true);
        llChecked = (LinearLayout) findViewById(R.id.ll_checked);
        llUnChecked = (LinearLayout) findViewById(R.id.ll_unchecked);

        checkedLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        unCheckedLP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadView();
    }

    /**
     * 解析自定义属性
     * @param attrs
     */
    private void getAttrs(AttributeSet attrs){
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.navview);
        checkedRes = typedArray.getResourceId(R.styleable.navview_checkedimg, 0);
        unCheckedRes = typedArray.getResourceId(R.styleable.navview_uncheckedimg, 0);
        count = typedArray.getInteger(R.styleable.navview_count, 0);
        typedArray.recycle();
    }

    /**
     * 加载控件
     */
    private void loadView(){
        if(count > 0){
            llUnChecked.removeAllViews();
            llChecked.removeAllViews();
            for(int i = 0; i < count; i++){
                ImageView unchecked = new ImageView(getContext());
                unchecked.setImageResource(unCheckedRes);
                unchecked.setLayoutParams(unCheckedLP);
                unchecked.setPadding(5, 0, 5, 0);
                llUnChecked.addView(unchecked);
            }

            imageView = new ImageView(getContext());
            imageView.setImageResource(checkedRes);
            imageView.setLayoutParams(checkedLP);
            imageView.setPadding(5, 0, 5, 0);
            llChecked.addView(imageView);
        }
    }

    /**
     * 结合ViewPager使用的方法
     * @param position
     * @param pyl
     */
    public void setNavAddress(int position, float pyl){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.leftMargin = (int) (imageView.getWidth() * (position + pyl));
        imageView.setLayoutParams(layoutParams);

        index = position;
    }

    /**
     * 设置图标的数量
     * @param count
     */
    public void setCount(int count){
        this.count = count;
        loadView();
    }

    //返回图标的数量
    public int getCount(){

        return this.count;
    }


    /**
     * 选中上一个
     */
    public void above(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.leftMargin = (imageView.getWidth() * ((index-- + 1)));
        imageView.setLayoutParams(layoutParams);
    }

    /**
     * 选中下一个
     */
    public void next(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.leftMargin = (imageView.getWidth() * ((index++ + 1)));
        imageView.setLayoutParams(layoutParams);
    }
}
