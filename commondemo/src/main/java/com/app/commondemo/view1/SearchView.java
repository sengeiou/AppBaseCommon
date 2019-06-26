package com.app.commondemo.view1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * @Auther JiRui
 * @CreateTime 2019/5/28 9:53
 * @Describe
 */
public class SearchView extends View {

    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }


    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;
    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(500,500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        mCenterX = width / 2;
        mCenterY = height / 2;
    }
}
