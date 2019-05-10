package com.app.basecommon.base;

import com.app.basecommon.bean.BaseEntityBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/10 13:40
 * @Describe
 */
public abstract class BaseRvAdapter <T extends BaseEntityBean,K extends BaseRvViewHolder> extends BaseMultiItemQuickAdapter<T, K> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseRvAdapter(List<T> data) {
        super(data);
        addItemTypeView();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

                @Override
                public int getSpanSize(int position) {
                    return onAttachedToRv(position);
                }

            });
        }
    }

    /**
     * 设置item的排列方式
     *
     * @param position
     * @describe://默认显示一列
     */
    protected int onAttachedToRv(int position) {
        return 1;//默认显示一列
    }

    /**
     * 添加item布局
     */
    protected abstract void addItemTypeView();
}
