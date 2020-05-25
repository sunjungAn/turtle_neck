package com.example.exam1;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter1 extends FragmentPagerAdapter {
    public ViewPagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragment_stretching1_1.newInstance();
            case 1:
                return Fragment_stretching1_2.newInstance();
            case 2:
                return Fragment_stretching1_3.newInstance();
            case 3:
                return Fragment_stretching1_4.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "스트레칭 1";
            case 1:
                return "스트레칭 2";
            case 2:
                return "스트레칭 3";
            case 3:
                return "스트레칭 4";
            default:
                return null;
        }
    }
}