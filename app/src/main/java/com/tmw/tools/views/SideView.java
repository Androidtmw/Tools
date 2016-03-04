package com.tmw.tools.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * 侧边栏选择字母的控件
 */
public class SideView extends View {

    private String items[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"
            , "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Paint paint;

    private int textHeight;

    private int index = -1;

    private OnSideClickedListener onSideClickedListener;

    public SideView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }


    public void setOnSideClickedListener(OnSideClickedListener onSideClickedListener) {

        this.onSideClickedListener = onSideClickedListener;
    }

    private void init() {
        paint = new Paint();//画笔对象

        paint.setAntiAlias(true);

        //获取屏幕的宽度和高度
        Resources resources = getContext().getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density1 = dm.density;
        int width3 = dm.widthPixels;
        int height3 = dm.heightPixels;

        paint.setTextSize(width3/24);

        paint.setColor(Color.BLUE);

        paint.setTextAlign(Paint.Align.CENTER);//设置绘画文本居中

        textHeight = (int) (paint.descent() - paint.ascent());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < items.length; i++) {

            if (index == i) {
                paint.setColor(Color.RED);
                canvas.drawText(items[i], getWidth() / 2, textHeight * (i + 1), paint);
                paint.setColor(Color.BLUE);
            } else {
                canvas.drawText(items[i], getWidth() / 2, textHeight * (i + 1), paint);
            }

        }

    }


    /**
     * 测量方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = measureView(widthMeasureSpec, 0);
        int height = measureView(heightMeasureSpec, 1);

        setMeasuredDimension(width, height);
    }

    private int measureView(int measureSpec, int type) {

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                if (type == 0) {
                    int newSize = (int) (paint.measureText(items[0]) + getPaddingLeft() + getPaddingRight());
                    return newSize;
                } else {
                    int newSize = textHeight * items.length + getPaddingTop() + getPaddingBottom();
                    return newSize;
                }
            case MeasureSpec.UNSPECIFIED:
                break;

        }
        return size;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                event.getRawY();//基于屏幕的高度
                int y = (int) event.getY();//基于控件的高度

                changeIndex(y);
               /*index=y/textHeight;
               invalidate();*/
                break;
            case MotionEvent.ACTION_MOVE:
                int z = (int) event.getY();//基于控件的高度
               /*index=z/textHeight;
               invalidate();*/
                changeIndex(z);
                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                invalidate();
                break;

        }
        return true;//将事件处理
    }

    private void changeIndex(int y) {

        int newindex = y / textHeight;

        if (newindex < 0) {
            newindex = 0;
        }
        if (newindex > items.length - 1) {
            newindex = items.length - 1;
        }

        if (onSideClickedListener != null) {
            onSideClickedListener.onSideClicked(newindex, items[newindex]);
        }

        index = newindex;
        invalidate();

    }


    public interface OnSideClickedListener {
        void onSideClicked(int index, String str);
    }
}
