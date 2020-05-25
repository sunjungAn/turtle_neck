package com.example.exam1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter2 extends FragmentPagerAdapter {
    public ViewPagerAdapter2(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return Fragment_stretching2_1.newInstance();
            case 1:
                return Fragment_stretching2_2.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "스트레칭 순서1";
            case 1:
                return "스트레칭 순서2";
            default:
                return null;
        }
    }
}
