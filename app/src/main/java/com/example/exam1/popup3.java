package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class popup3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup3);

        DisplayMetrics dm  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int)(height*.6));

        ViewPager viewPager3 = findViewById(R.id.viewPager3);
        fragmentPagerAdapter3 = new ViewPagerAdapter3(getSupportFragmentManager());

        TabLayout tabLayout3= findViewById(R.id.tab_layout3);
        viewPager3.setAdapter(fragmentPagerAdapter3);
        tabLayout3.setupWithViewPager(viewPager3);
    }
}
