package com.app.basecommon.base;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.app.basecommon.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public abstract class BaseTabBottomActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "BaseTabBottomActivity";

    protected BottomNavigationView mNavigation;

    protected ViewPager mVpContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab);

        mVpContent = findViewById(R.id.vp_content);
        mNavigation = findViewById(R.id.bnv_navigation);
        mNavigation.setOnNavigationItemSelectedListener(this);

        mNavigation.inflateMenu(getMenu());
        mVpContent.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return getItemFragmentSize();
            }
            @Override
            public Fragment getItem(int position) {
                return getItemFragment(position);
            }
        });
        mVpContent.setOffscreenPageLimit(getItemFragmentSize());

        mVpContent.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                mNavigation.setSelectedItemId(getSelectedItemId(position));
            }
        });

        mNavigation.setItemIconTintList(null);
        setItemHorizontalTranslationEnabled(false);

        setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        mNavigation.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mVpContent.setCurrentItem(getCurrentItem(menuItem.getItemId()));
        return true;
    }

    /**
     * 设置当前需要显示的tab
     * @param item
     */
    protected void setCurrentTabItem(int item){
        mNavigation.getMenu().getItem(item).setChecked(true);
    }

    /**
     * 禁止item水平平移动画效果
     * @param itemHorizontalTranslationEnabled
     */
    protected void setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled){
        mNavigation.setItemHorizontalTranslationEnabled(itemHorizontalTranslationEnabled);
    }

    /**
     *  设置图标下面的文字显示，该属性对应的值有auto、labeled、selected、unlabeled
     * @param labelVisibilityMode
     * @Describe
     * auto   当item小于等于3是，显示文字，item大于3个默认不显示，选中显示文字
     * labeled  始终显示文字
     * selected  选中时显示
     * unlabeled 选中时显示
     */
    protected void setLabelVisibilityMode(int labelVisibilityMode){
        mNavigation.setLabelVisibilityMode(labelVisibilityMode);
    }

    protected void setItemTextAppearanceActive(@StyleRes int textAppearanceRes){
        mNavigation.setItemTextAppearanceActive(textAppearanceRes);
    }

    protected void setItemTextAppearanceInactive(@StyleRes int textAppearanceRes){
        mNavigation.setItemTextAppearanceInactive(textAppearanceRes);
    }

    @SuppressLint("RestrictedApi")
    protected void setItemTextColor(@ColorRes int styleRes){
        ColorStateList colorStateList = getResources().getColorStateList(styleRes);
        mNavigation.setItemTextColor(colorStateList);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(0);
        itemView.setChecked(true);

    }

    /**
     * 设置指定位置的左上角角标
     * @param position
     * @param itemLayout
     */
    protected void setPositionMenuView(int position, @LayoutRes int itemLayout){

        if (position >= getItemFragmentSize() || position < 0){
            throw new IndexOutOfBoundsException(TAG + "底部数组位置越界！");
        }
        //获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);

        //这里就是获取所添加的每一个Tab，
        View tabView = menuView.getChildAt(position);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tabView;

        //加载我们的角标View，新创建的一个布局
        View itemBadge = LayoutInflater.from(this).inflate(itemLayout, menuView, false);

        //添加到Tab上
        itemView.addView(itemBadge);
    }

    /**
     * 获取长度
     * @return
     */
    protected abstract int getMenu();

    /**
     * 获取长度
     * @return
     */
    protected abstract int getItemFragmentSize();

    /**
     * 获取需要显示的fragment
     * @param position
     * @return
     */
    protected abstract Fragment getItemFragment(int position);

    /**
     * 根据vp的更改，获取需要显示的tab导航
     * @param position
     * @return
     */
    protected abstract int getSelectedItemId(int position);

    protected abstract int getCurrentItem(int itemId);

    protected abstract void onTabDestroy();

}
