package com.example.doan.fragmentdanhmuc;

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
import com.example.doan.fragmenttrangchu.tcTresosinhFragment;

public class danhmucViewPagerAdapter extends FragmentStatePagerAdapter {
    public danhmucViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new nuFragment();
            case 1:
                return new namFragment();
            case 2:
                return new treemFragment();
            case 3:
                return new tresosinhFragment();
            default:
                return new nuFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Nữ";
            case 1:
                return "Nam";
            case 2:
                return "Trẻ em";
            case 3:
                return "Trẻ sơ sinh";
            default:
                return "Nữ";
        }
    }
}
