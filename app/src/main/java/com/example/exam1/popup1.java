package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.util.DisplayMetrics;

import com.google.android.material.tabs.TabLayout;

public class popup1 extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup1);

        DisplayMetrics dm  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int)(height*.6));

        ViewPager viewPager1 = findViewById(R.id.viewPager1);
        fragmentPagerAdapter1 = new ViewPagerAdapter1(getSupportFragmentManager());

        TabLayout tabLayout1 = findViewById(R.id.tab_layout1);
        viewPager1.setAdapter(fragmentPagerAdapter1);
        tabLayout1.setupWithViewPager(viewPager1);

    }
}
