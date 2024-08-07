package com.example.doan.fragmenttrangchu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.doan.fragment.DanhmucFragment;
import com.example.doan.fragment.ThanhvienFragment;
import com.example.doan.fragment.ThongbaoFragment;
import com.example.doan.fragment.TrangchuFragment;
import com.example.doan.fragment.YeuthichFragment;

public class orderViewPagerAdapter extends FragmentStatePagerAdapter {
    public orderViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new tcTrangchuFragment();
            case 1:
                return new tcNuFragment();
            case 2:
                return new tcNamFragment();
            case 3:
                return new tcTreemFragment();
            case 4:
                return new tcTresosinhFragment();
            default:
                return new tcTrangchuFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Trang chủ";
            case 1:
                return "Nữ";
            case 2:
                return "Nam";
            case 3:
                return "Trẻ em";
            case 4:
                return "Trẻ sơ sinh";
            default:
                return "Trang chủ";
        }
    }
}
