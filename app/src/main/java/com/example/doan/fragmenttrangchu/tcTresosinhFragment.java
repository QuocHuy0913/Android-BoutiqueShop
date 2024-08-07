package com.example.doan.fragmenttrangchu;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.doan.Danhmuc;
import com.example.doan.DetailSPActivity;
import com.example.doan.Gioithieu;
import com.example.doan.R;
import com.example.doan.Sanpham;
import com.example.doan.adapter.danhmucAdapter;
import com.example.doan.adapter.gioithieuAdapter;
import com.example.doan.my_interface.IClickItemSPListener;
import com.example.doan.adapter.sanphamAdapter;
import com.example.doan.sanphamDataQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tcTresosinhFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tcTresosinhFragment extends Fragment {
    ArrayList<Sanpham> lstSP;
    RecyclerView rcvSP, rcvGT, rcvDM;
    com.example.doan.adapter.sanphamAdapter sanphamAdapter;
    com.example.doan.adapter.gioithieuAdapter gioithieuAdapter;
    com.example.doan.adapter.danhmucAdapter danhmucAdapter;
    ViewFlipper viewFlipper;
    View mView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tcTresosinhFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tcTresosinhFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tcTresosinhFragment newInstance(String param1, String param2) {
        tcTresosinhFragment fragment = new tcTresosinhFragment();
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
        mView =  inflater.inflate(R.layout.fragment_tc_tresosinh, container, false);
        viewFlipper = mView.findViewById(R.id.viewflipper);
        ActionViewFlipper();

        rcvGT = mView.findViewById(R.id.rcv_gioithieu);
        rcvSP = mView.findViewById(R.id.rcv_sanpham);
        rcvDM = mView.findViewById(R.id.rcv_danhmuc);

        sanphamAdapter = new sanphamAdapter(lstSP, new IClickItemSPListener() {
            @Override
            public void onClickItemSP(Sanpham sanpham) {
                onClickGoToDetail(sanpham);
            }
        });
        gioithieuAdapter = new gioithieuAdapter(getContext());
        danhmucAdapter = new danhmucAdapter(getContext());

        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 4);
        rcvDM.setLayoutManager(gridLayoutManager2);
        danhmucAdapter.setData(getListDM());
        rcvDM.setAdapter(danhmucAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvGT.setLayoutManager(linearLayoutManager);
        gioithieuAdapter.setData(getListGT());
        rcvGT.setAdapter(gioithieuAdapter);

        lstSP = sanphamDataQuery.getAll(getContext(), "Tresosinh");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        sanphamAdapter.setData(lstSP);
        rcvSP.setLayoutManager(gridLayoutManager);
        rcvSP.setAdapter(sanphamAdapter);
        return mView;
    }
    private void onClickGoToDetail(Sanpham sanpham)
    {
        Intent i = new Intent(getContext(), DetailSPActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_SP", sanpham);
        i.putExtras(bundle);
        startActivity(i);
    }
    private List<Danhmuc> getListDM() {
        List<Danhmuc> list = new ArrayList<>();
        list.add(new Danhmuc(R.drawable.dm25, "CHỐNG UV"));
        list.add(new Danhmuc(R.drawable.dm26, "ÁO"));
        list.add(new Danhmuc(R.drawable.dm27, "ĐẦM"));
        list.add(new Danhmuc(R.drawable.dm28, "QUẦN DÀI & LEGGING"));
        list.add(new Danhmuc(R.drawable.dm29, "BODYSUIT"));
        list.add(new Danhmuc(R.drawable.dm30, "ĐỒ LIỀN MẢNH"));
        list.add(new Danhmuc(R.drawable.dm31, "PYJAMA"));
        list.add(new Danhmuc(R.drawable.dm32, "ĐỒ MẶC TRONG"));
        return  list;
    }

    private List<Gioithieu> getListGT() {
        List<Gioithieu> list = new ArrayList<>();
        list.add(new Gioithieu(R.drawable.gt12, "LIFEWEAR MAGAZINE", "Khám phá ấn phẩm thứ tám của tạp chí lifeWear từ UNIQLO"));
        list.add(new Gioithieu(R.drawable.gt13, "BỘ SƯU TẬP LIFEWEAR XUÂN/HÈ 2023", "Mỗi mùa là cơ hội để ngắm nhìn mọi thứ theo một cách mới mẻ. Trang phục đầy sắc màu với chất lượng cao."));
        list.add(new Gioithieu(R.drawable.gt14, "UNIQLO", "Trang phục đơn giản với chất lượng tuyệt vời. Đây là những gì mà sản phẩm của UNIQLO hướng đến."));
        return list;
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();

        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/rescaf8b682fc159b8cb3c0c42fd3b19e94fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/rese0655785eaaf5d81ac8ee4aa98214a6afr.jpg");
        mangquangcao.add("https://image.uniqlo.com/UQ/ST3/jp/imagesother/uvcut/23ss/img/kids/hero-1-desktop.jpg?1673513922602");
        mangquangcao.add("https://image.uniqlo.com/UQ/ST3/jp/imagesother/uvcut/23ss/img/kids/hero-3-desktop.jpg?1673513922602");
        for(int i = 0; i < mangquangcao.size(); i++)
        {
            ImageView imageView = new ImageView(getContext().getApplicationContext());
            Glide.with(getContext().getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
}