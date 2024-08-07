package com.example.doan.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doan.fragmenttrangchu.tcTrangchuFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new TrangchuFragment();
            case 1:
                return new DanhmucFragment();
            case 2:
                return new YeuthichFragment();
            case 3:
                return new ThongbaoFragment();
            case 4:
                return new ThanhvienFragment();
            default:
                return new TrangchuFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
