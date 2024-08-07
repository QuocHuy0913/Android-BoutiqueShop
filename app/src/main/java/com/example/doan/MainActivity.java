package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStateManagerControl;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.doan.fragment.DanhmucFragment;
import com.example.doan.fragment.ThanhvienFragment;
import com.example.doan.fragment.ThongbaoFragment;
import com.example.doan.fragment.TrangchuFragment;
import com.example.doan.fragment.ViewPagerAdapter;
import com.example.doan.fragment.YeuthichFragment;
import com.example.doan.utils.Utilsss;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewpager;
    BottomNavigationView mnmenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = findViewById(R.id.view_pager);
        mnmenu = findViewById(R.id.bottom_navigation);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpager.setAdapter(adapter);


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        mnmenu.getMenu().findItem(R.id.mnTrangchu).setChecked(true);
                        break;
                    case 1:
                        mnmenu.getMenu().findItem(R.id.mnDanhmuc).setChecked(true);
                        break;
                    case 2:
                        mnmenu.getMenu().findItem(R.id.mnYeuthich).setChecked(true);
                        break;
                    case 3:
                        mnmenu.getMenu().findItem(R.id.mnThongbao).setChecked(true);
                        break;
                    case 4:
                        mnmenu.getMenu().findItem(R.id.mnThanhvien).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        mnmenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.mnTrangchu:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.mnDanhmuc:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.mnYeuthich:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.mnThongbao:
                        viewpager.setCurrentItem(3);
                        break;
                    case R.id.mnThanhvien:
                        viewpager.setCurrentItem(4);
                        break;
                }
                    return true;
            }
        });

      /*  ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main");
        actionBar.setDisplayHomeAsUpEnabled(true);
        mnmenu.setOnItemSelectedListener(getListener()); */
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return true;
    }
    /*
    @NonNull
    private NavigationBarView.OnItemSelectedListener getListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fmNew;
                switch (item.getItemId())
                {
                    case R.id.mnTrangchu:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new TrangchuFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnDanhmuc:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new DanhmucFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnYeuthich:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new YeuthichFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnThongbao:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new ThongbaoFragment();
                        loadFragment(fmNew);
                        return true;
                    case R.id.mnThanhvien:
                        getSupportActionBar().setTitle(item.getTitle());
                        fmNew = new ThanhvienFragment();
                        loadFragment(fmNew);
                        return true;
                }
                return true;
            }
        };
    }
    void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.view_pager, fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
    */
}