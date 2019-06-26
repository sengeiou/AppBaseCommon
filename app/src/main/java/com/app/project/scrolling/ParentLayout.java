package com.app.project.scrolling;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.app.basecommon.utiles.Logger;
import com.app.project.R;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/24 10:29
 * @Describe
 */
public class ParentLayout extends LinearLayout implements NestedScrollingParent {
    public static final String TAG = "Parent";

    private NestedScrollingParentHelper mNestedScrollingParentHelper;

    public ParentLayout(Context context) {
        this(context, null);
    }

    public ParentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    /**
     * 回调开始滑动
     *
     * @param child  该父VIew 的子View
     * @param target 支持嵌套滑动的 VIew
     * @param axes   滑动方向
     * @return 是否支持 嵌套滑动
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {
        mNestedScrollingParentHelper.onStopNestedScroll(target);
    }

    /**
     * 这里 主要处理 dyUnconsumed dxUnconsumed 这两个值对应的数据
     *
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Logger.e("--------onNestedScroll", "dxConsumed:" + dxConsumed + "   dyConsumed:" + dyConsumed);
        Logger.e("--------onNestedScroll", "dxUnconsumed:" + dxUnconsumed + "   dyUnconsumed:" + dyUnconsumed);
    }

    /**
     * 这里 传来了 x y 方向上的滑动距离
     * 并且 先与 子VIew  处理滑动,  并且 consumed  中可以设置相应的 除了的距离
     * 然后 子View  需要更具这感觉, 来处理自己滑动
     *
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     */

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        int scrollY = getScrollY();
        if (scrollY <= 300 && scrollY + dy > 0) {
            consumed[1] = dy;
            scrollBy(0, consumed[1]);
        } else if (!target.canScrollVertically(dy)) {

            if (scrollY + dy > 0) {
                consumed[1] = dy;
                scrollBy(0, consumed[1]);
            }
        }
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mNestedScrollingParentHelper.getNestedScrollAxes();
    }
}
