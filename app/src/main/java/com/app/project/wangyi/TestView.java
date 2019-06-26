package com.app.project.wangyi;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/21 12:45
 * @Describe
 */
public class TestView extends View {

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        String test = "askd";
        Rect rect = new Rect();
        Paint paint = new Paint();
        paint.getTextBounds(test,0,test.length(),rect);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float txtHeight = fontMetrics.descent - fontMetrics.ascent;//获取文本的高度
    }


}
