package com.app.project.scrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.widget.NestedScrollView;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/24 10:28
 * @Describe
 */
public class ChildLayout extends NestedScrollView implements NestedScrollingChild {

    public static final String TAG = "Child";


    public ChildLayout(Context context) {
        this(context,null);
    }

    public ChildLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ChildLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
