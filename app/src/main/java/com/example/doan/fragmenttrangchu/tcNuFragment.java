package com.example.doan.fragmenttrangchu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;
import java.util.List;

import com.example.doan.Sanpham;
import com.example.doan.my_interface.IClickItemSPListener;
import com.example.doan.adapter.sanphamAdapter;
import com.example.doan.adapter.gioithieuAdapter;
import com.example.doan.adapter.danhmucAdapter;
import com.example.doan.adapter.spAdapter;
import com.example.doan.sanphamDataQuery;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tcNuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tcNuFragment extends Fragment {
    ArrayList<Sanpham> lstSP;
    spAdapter spAdapter;
    RecyclerView rcvSP, rcvGT, rcvDM;
    sanphamAdapter sanphamAdapter;
    gioithieuAdapter gioithieuAdapter;
    danhmucAdapter danhmucAdapter;
    ViewFlipper viewFlipper;
    View mView;
    Context myContext;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tcNuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tcNuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tcNuFragment newInstance(String param1, String param2) {
        tcNuFragment fragment = new tcNuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myContext= context;
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
        mView =  inflater.inflate(R.layout.fragment_tc_nu, container, false);
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

        lstSP = sanphamDataQuery.getAll(getContext(), "Nu");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        sanphamAdapter.setData(lstSP);
        rcvSP.setLayoutManager(gridLayoutManager);
        rcvSP.setAdapter(sanphamAdapter);

        return mView;
    }
    private void onClickGoToDetail(Sanpham sanpham)
    {
        Intent i = new Intent(myContext, DetailSPActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_SP", sanpham);
        i.putExtras(bundle);
        startActivity(i);
    }
    private List<Danhmuc> getListDM() {
        List<Danhmuc> list = new ArrayList<>();
        list.add(new Danhmuc(R.drawable.dm1, "ĐỒ MẶC NGOÀI"));
        list.add(new Danhmuc(R.drawable.dm2, "TRANG PHỤC CHỐNG UV"));
        list.add(new Danhmuc(R.drawable.dm3, "ÁO SƠ MI"));
        list.add(new Danhmuc(R.drawable.dm4, "ÁO THUN"));
        list.add(new Danhmuc(R.drawable.dm5, "QUẦN DÀI"));
        list.add(new Danhmuc(R.drawable.dm6, "QUẦN SHORT"));
        list.add(new Danhmuc(R.drawable.dm7, "ĐẦM"));
        list.add(new Danhmuc(R.drawable.dm8, "ĐỒ MẶC NHÀ"));
        return  list;
    }

    private List<Gioithieu> getListGT() {
        List<Gioithieu> list = new ArrayList<>();
        list.add(new Gioithieu(R.drawable.gt1, "CLICK & COLLECT", "Thoải mái nhận đơn hàng của bạn tại các cửa hàng UNIQLO gần nhất với dịch vụ Click & Collect, hoàn toàn miễn phí giao hàng."));
        list.add(new Gioithieu(R.drawable.gt2, "EXTRA SIZE", "Thêm các lựa chọn kích cỡ phù hợp nhất với bạn, từ XS đến XXL, chỉ có duy nhất tại cửa hàng online."));
        list.add(new Gioithieu(R.drawable.gt3, "COUPON CHO ĐƠN HÀNG ĐẦU TIÊN", "Tải ứng dụng UNIQLO ngay và tận hưởng coupon 150.000VND cho đơn hàng đầu tiên trên app."));
        return list;
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();

        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/rese5d0cb6de1c148813648e018104cb6eafr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res59a76fbafa5d7b35ce75a47b430ffb8dfr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/resdaf2bda73afbb5d493e2c8c4033a5f92fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res22f5cfce6e49c1aa65281790243efa6efr.jpg");
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