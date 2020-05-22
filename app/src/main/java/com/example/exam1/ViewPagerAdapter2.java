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
                return Frag_str1_1.newInstance();
            case 1:
                return Frag_str1_2.newInstance();
            case 2:
                return Frag_str1_3.newInstance();
            case 3:
                return Frag_str1_4.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    //상단의 탭 레이아웃 인디케이터 쪽에 텍스트를 선언하는 곳
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "스트레칭 순서1";
            case 1:
                return "스트레칭 순서2";
            case 2:
                return "스트레칭 순서3";
            case 3:
                return "스트레칭 순서4";
            default:
                return null;
        }
    }
}
