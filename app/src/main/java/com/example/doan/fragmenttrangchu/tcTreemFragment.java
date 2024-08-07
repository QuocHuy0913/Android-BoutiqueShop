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
 * Use the {@link tcTreemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tcTreemFragment extends Fragment {
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

    public tcTreemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tcTreemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tcTreemFragment newInstance(String param1, String param2) {
        tcTreemFragment fragment = new tcTreemFragment();
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
        mView =  inflater.inflate(R.layout.fragment_tc_treem, container, false);
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

        lstSP = sanphamDataQuery.getAll(getContext(), "Treem");
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
        list.add(new Danhmuc(R.drawable.dm17, "CHỐNG UV"));
        list.add(new Danhmuc(R.drawable.dm18, "QUẦN ÁO NỈ"));
        list.add(new Danhmuc(R.drawable.dm19, "ÁO SƠ MI & ÁO KIỂU"));
        list.add(new Danhmuc(R.drawable.dm20, "ÁO THUN"));
        list.add(new Danhmuc(R.drawable.dm21, "QUẦN DÀI"));
        list.add(new Danhmuc(R.drawable.dm22, "QUẦN SHORT"));
        list.add(new Danhmuc(R.drawable.dm24, "ĐẦM"));
        list.add(new Danhmuc(R.drawable.dm23, "ĐỒ MẶC NHÀ"));
        return  list;
    }

    private List<Gioithieu> getListGT() {
        List<Gioithieu> list = new ArrayList<>();
        list.add(new Gioithieu(R.drawable.gt8, "CHỐNG NẮNG TỨC THÌ", "Giải pháp chống nắng giúp ngăn chặn 90% tia UV để bảo vệ làn da của bạn. Bộ sưu tập sản phẩm của chúng tôi phù hợp cho mọi phong cách và hoàn cảnh."));
        list.add(new Gioithieu(R.drawable.gt9, "TRANG PHỤC THỂ THAO ĐA NĂNG", "Bước tiến mới trong năm với một bộ trang phục mới. Khám phá lựa chọn hàng đầu của chúng tôi về trang phục thể thao cho mùa Xuân/Hè này."));
        list.add(new Gioithieu(R.drawable.gt10, "BỘ SƯU TẬP ÁO THUN", "Hãy chọn chiếc áo thun hoàn hảo cho riêng bạn và sẵn sàng bắt đầu một mùa hè đầy hứng khởi."));
        list.add(new Gioithieu(R.drawable.gt11, "UNIFORM BLUE", "Những phong cách hiện đại, sự kết hợp hàng loạt các màu xanh tuyệt đẹp và màu trắng nhẹ nhàng."));
        return list;
    }


    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();

        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res56b91e5fd3bb56cc1e4d49f848cedb47fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res2b944ae655ac8ee760015f5e1706e4d4fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res36375a1857cc850dbbdce3868af4969dfr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res7b0b06f35c5df40adb2d389ad3d0448efr.jpg");
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