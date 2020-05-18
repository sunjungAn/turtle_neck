package com.example.exam1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    //프래그먼트 교체를 보여주는 처리를 구현한 곳
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragMonday.newInstance();
            case 1:
                return FragTuesday.newInstance();
            case 2:
                return FragWednesday.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    //상단의 탭 레이아웃 인디케이터 쪽에 텍스트를 선언하는 곳
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "스트레칭1";
            case 1:
                return "스트레칭2";
            case 2:
                return "스트레칭3";
            default:
                return null;
        }
    }
}
