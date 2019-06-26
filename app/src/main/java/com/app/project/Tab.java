package com.app.project;

import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.app.basecommon.base.BaseSilkRoadsTabActivity;
import com.app.basecommon.utiles.Logger;
import com.app.basecommon.utiles.StatusBarUtil;
import com.app.basecommon.utiles.UIUtils;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/8 13:02
 * @Describe
 */
public class Tab extends BaseSilkRoadsTabActivity implements DrawerLayout.DrawerListener {

    @Override
    public void initIntent() {
    }

    @Override
    public void initData() {
        super.initData();
        setItemTextAppearanceActive(R.style.tab_bottom_selecter_text);
        setItemTextAppearanceInactive(R.style.tab_bottom_normal_text);
        setItemTextColor(R.color.navigation_menu_item_color);

        View viewById = findViewById(R.id.container);

        StatusBarUtil.setTranslucentForImageView(this,0,viewById);
        StatusBarUtil.setLightMode(this);

        setIvLeftImage(R.mipmap.iv_drawlayout);
        mDrawerLayout.addDrawerListener(this);
        setOnIvLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void getData() {

    }

    private enum TabFragment {
        practice(R.id.navigation_home, Test1Fragment.class),
        styles(R.id.navigation_dashboard, Test2Fragment.class),
        using(R.id.navigation_notifications, Test3Fragment.class),
        mine(R.id.navigation_wode, Test4Fragment.class),
        ;

        private Fragment fragment;
        private final int menuId;
        private final Class<? extends Fragment> clazz;

        TabFragment(@IdRes int menuId, Class<? extends Fragment> clazz) {
            this.menuId = menuId;
            this.clazz = clazz;
        }

        @NonNull
        public Fragment fragment() {
            if (fragment == null) {
                try {
                    fragment = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    fragment = new Fragment();
                }
            }
            return fragment;
        }

        public static TabFragment from(int itemId) {
            for (TabFragment fragment : values()) {
                if (fragment.menuId == itemId) {
                    return fragment;
                }
            }
            return styles;
        }

        public static void onTabDestroy() {
            for (TabFragment fragment : values()) {
                fragment.fragment = null;
            }
        }
    }

    @Override
    protected int getMenu() {
        return R.menu.navigation;
    }

    @Override
    protected int getItemFragmentSize() {
        return TabFragment.values().length;
    }

    @Override
    protected Fragment getItemFragment(int position) {
        return TabFragment.values()[position].fragment();
    }

    @Override
    protected int getSelectedItemId(int position) {
        return TabFragment.values()[position].menuId;
    }

    @Override
    protected int getCurrentItem(int itemId) {
        return TabFragment.from(itemId).ordinal();
    }

    @Override
    protected void onTabDestroy() {
        TabFragment.onTabDestroy();
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        drawerView.setAlpha(slideOffset);
    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

}
