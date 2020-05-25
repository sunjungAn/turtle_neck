package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class popup2 extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup2);

        DisplayMetrics dm  = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int)(height*.6));

        ViewPager viewPager2 = findViewById(R.id.viewPager2);
        fragmentPagerAdapter2 = new ViewPagerAdapter2(getSupportFragmentManager());

        TabLayout tabLayout2 = findViewById(R.id.tab_layout2);
        viewPager2.setAdapter(fragmentPagerAdapter2);
        tabLayout2.setupWithViewPager(viewPager2);
    }
}
