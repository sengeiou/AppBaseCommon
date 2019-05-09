package com.app.project;

import android.os.Bundle;
import android.util.Log;

import com.app.basecommon.base.BaseTabBottomActivity;
import com.app.basecommon.utiles.Logger;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @Auther JiRui
 * @CreateTime 2019/5/8 13:02
 * @Describe
 */
public class Tab extends BaseTabBottomActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setItemTextAppearanceActive(R.style.tab_bottom_normal_text);
        setItemTextAppearanceInactive(R.style.tab_bottom_selecter_text);
        setItemTextColor(R.color.navigation_menu_item_color);

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
}
