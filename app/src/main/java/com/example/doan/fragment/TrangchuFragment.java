package com.example.doan.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.doan.GioHangActivity;
import com.example.doan.ProfileActivity;
import com.example.doan.R;
import com.example.doan.SearchActivity;
import com.example.doan.model.User;
import com.example.doan.ui.Utils;
import com.example.doan.utils.Utilsss;
import com.google.android.material.tabs.TabLayout;
import com.example.doan.fragmenttrangchu.orderViewPagerAdapter;
import com.google.gson.Gson;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangchuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangchuFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    View mView;
    NotificationBadge badge;
    FrameLayout frameLayout;

    TextView tvUserNameC;
    private final Gson gson = new Gson();

    ImageView imgsearch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TrangchuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangchuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangchuFragment newInstance(String param1, String param2) {
        TrangchuFragment fragment = new TrangchuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_trangchu, container, false);
        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager = mView.findViewById(R.id.order_viewpager);
        badge = mView.findViewById(R.id.menu_sl);
        frameLayout = mView.findViewById(R.id.framegiohang);

        imgsearch = mView.findViewById(R.id.imgsearch);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        if(Utilsss.manggiohang == null)
        {
            Utilsss.manggiohang = new ArrayList<>();
        }else
        {
            int totalItem = 0;
            for(int i = 0; i < Utilsss.manggiohang.size(); i++){
                totalItem = totalItem + Utilsss.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        if(Utilsss.mangyeuthich == null)
        {
            Utilsss.mangyeuthich = new ArrayList<>();
        }

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giohang = new Intent(getContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });

        orderViewPagerAdapter adapter = new orderViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        tvUserNameC = mView.findViewById(R.id.tvUserName_fm_trangchu);
        SharedPreferences shareget = getActivity().getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        String userPref = shareget.getString(Utils.KEY_USER, null);

        User user = gson.fromJson(userPref, User.class);
        if(user == null)
        {
            tvUserNameC.setText("Hello");
        }
        else
        {
            String info = user.getUserName();
            tvUserNameC.setText(info);
        }
        tvUserNameC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                startActivity(i);
            }
        });

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        int totalItem = 0;
        for(int i = 0; i < Utilsss.manggiohang.size(); i++){
            totalItem = totalItem + Utilsss.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }
}