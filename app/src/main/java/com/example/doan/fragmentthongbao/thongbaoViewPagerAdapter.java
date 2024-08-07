package com.example.doan.fragmentthongbao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class thongbaoViewPagerAdapter extends FragmentStatePagerAdapter {
    public thongbaoViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new tbDanhchobanFragment();
            case 1:
                return new tbCogimoiFragment();
            default:
                return new tbDanhchobanFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Dành cho bạn";
            case 1:
                return "Có gì mới";
            default:
                return "Dành cho bạn";
        }
    }
}
