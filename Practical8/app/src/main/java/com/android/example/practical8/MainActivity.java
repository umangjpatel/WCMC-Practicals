package com.android.example.practical8;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.example_view_pager);
        CircleIndicator indicator = findViewById(R.id.indicator);
        FragmentManager fm = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                String numberMessage = NumbersData.getInstance().getNumbersDataAtIndex(i);
                return ExampleFragment.newInstance(numberMessage);
            }

            @Override
            public int getCount() {
                return NumbersData.getInstance().getDataLength();
            }
        });
        indicator.setViewPager(viewPager);
    }
}
