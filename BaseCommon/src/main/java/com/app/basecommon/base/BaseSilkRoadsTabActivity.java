package com.app.basecommon.base;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.app.basecommon.R;
import com.app.basecommon.utiles.UIUtils;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.internal.BaselineLayout;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public abstract class BaseSilkRoadsTabActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "BaseTabBottomActivity";

    protected BottomNavigationView mNavigation;

    protected ViewPager mVpContent;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    protected DrawerLayout mDrawerLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_silk_roads_tab;
    }

    @Override
    public void initView() {
        mIvLeft = findViewById(R.id.iv_left);
        mIvRight = findViewById(R.id.iv_rigth);

        mVpContent = findViewById(R.id.vp_content);
        mNavigation = findViewById(R.id.bnv_navigation);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.setScrimColor(getResources().getColor(R.color.c_66000000));
    }

    @Override
    public void initData() {

        mNavigation.setOnNavigationItemSelectedListener(this);

        mNavigation.inflateMenu(getMenu());
        mNavigation.setItemIconSize(0);
        setOffest(45);
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

        mVpContent.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
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
     *
     * @param item
     */
    protected void setCurrentTabItem(int item) {
        mNavigation.getMenu().getItem(item).setChecked(true);
    }

    /**
     * 禁止item水平平移动画效果
     *
     * @param itemHorizontalTranslationEnabled
     */
    protected void setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled) {
        mNavigation.setItemHorizontalTranslationEnabled(itemHorizontalTranslationEnabled);
    }

    /**
     * 设置图标下面的文字显示，该属性对应的值有auto、labeled、selected、unlabeled
     *
     * @param labelVisibilityMode
     * @Describe auto   当item小于等于3是，显示文字，item大于3个默认不显示，选中显示文字
     * labeled  始终显示文字
     * selected  选中时显示
     * unlabeled 选中时显示
     */
    protected void setLabelVisibilityMode(int labelVisibilityMode) {
        mNavigation.setLabelVisibilityMode(labelVisibilityMode);
    }

    protected void setItemTextAppearanceActive(@StyleRes int textAppearanceRes) {
        mNavigation.setItemTextAppearanceActive(textAppearanceRes);
    }

    protected void setItemTextAppearanceInactive(@StyleRes int textAppearanceRes) {
        mNavigation.setItemTextAppearanceInactive(textAppearanceRes);
    }

    @SuppressLint("RestrictedApi")
    protected void setItemTextColor(@ColorRes int styleRes) {
        ColorStateList colorStateList = getResources().getColorStateList(styleRes);
        mNavigation.setItemTextColor(colorStateList);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(0);
        itemView.setChecked(true);

    }

    /**
     * 设置指定位置的左上角角标
     *
     * @param position
     * @param itemLayout
     */
    protected void setPositionMenuView(int position, @LayoutRes int itemLayout) {

        if (position >= getItemFragmentSize() || position < 0) {
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
     * 设置底部图标的大小
     * @param width
     * @param height
     */
    public void adjustNavigationIcoSize(int width,int height) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            layoutParams.width = UIUtils.getInstance().getWidth(width);
            layoutParams.height = UIUtils.getInstance().getHeight(height);
            iconView.setLayoutParams(layoutParams);
        }
    }

    /**
     *
     * @param offest
     */
    public void setOffest(int offest){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mNavigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.height = 0;
            iconView.setLayoutParams(layoutParams);

            FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) menuView.getLayoutParams();
            layoutParams1.bottomMargin = UIUtils.getInstance().getWidth(offest);
            menuView.setLayoutParams(layoutParams1);
        }
    }

    public void setOnIvLeftClick(View.OnClickListener onIvLeftClick) {
        mIvLeft.setOnClickListener(onIvLeftClick);
    }

    public void setOnIvRightlick(View.OnClickListener onIvRightClick) {
        mIvRight.setOnClickListener(onIvRightClick);
    }

    public void setIvLeftImage(@DrawableRes int resId) {
        mIvLeft.setImageResource(resId);
    }

    public void setIvRightImage(@DrawableRes int resId) {
        mIvRight.setImageResource(resId);
    }

    /**
     * 获取长度
     *
     * @return
     */
    protected abstract int getMenu();

    /**
     * 获取长度
     *
     * @return
     */
    protected abstract int getItemFragmentSize();

    /**
     * 获取需要显示的fragment
     *
     * @param position
     * @return
     */
    protected abstract Fragment getItemFragment(int position);

    /**
     * 根据vp的更改，获取需要显示的tab导航
     *
     * @param position
     * @return
     */
    protected abstract int getSelectedItemId(int position);

    protected abstract int getCurrentItem(int itemId);

    protected abstract void onTabDestroy();

}
