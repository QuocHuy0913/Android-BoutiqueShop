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
 * Use the {@link tcNamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tcNamFragment extends Fragment {
    ArrayList<Sanpham> lstSP;
    RecyclerView rcvSP, rcvGT, rcvDM;
    sanphamAdapter sanphamAdapter;
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

    public tcNamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tcNamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tcNamFragment newInstance(String param1, String param2) {
        tcNamFragment fragment = new tcNamFragment();
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
        mView =  inflater.inflate(R.layout.fragment_tc_nam, container, false);
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

        lstSP = sanphamDataQuery.getAll(getContext(), "Nam");
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
        list.add(new Danhmuc(R.drawable.dm9, "ĐỒ MẶC NGOÀI"));
        list.add(new Danhmuc(R.drawable.dm10, "ÁO SƠ MI"));
        list.add(new Danhmuc(R.drawable.dm11, "ÁO THUN"));
        list.add(new Danhmuc(R.drawable.dm12, "ÁO POLO"));
        list.add(new Danhmuc(R.drawable.dm13, "TRANG PHỤC CHỐNG UV"));
        list.add(new Danhmuc(R.drawable.dm14, "QUẦN DÀI"));
        list.add(new Danhmuc(R.drawable.dm15, "QUẦN SHORT"));
        list.add(new Danhmuc(R.drawable.dm16, "ĐỒ MẶC NHÀ"));
        return  list;
    }

    private List<Gioithieu> getListGT() {
        List<Gioithieu> list = new ArrayList<>();
        list.add(new Gioithieu(R.drawable.gt4, "SPLATOON 3", "Bộ sưu tập áo thun đặc biệt với những thiết kế lấy cảm hứng từ trò chơi bắn súng Splatoon nổi tiếng của Nintendo."));
        list.add(new Gioithieu(R.drawable.gt5, "ATTACK ON TITAN", "Bộ Anime truyền hình \"Attack on Titan\" The Final Season. Một bộ sưu tập tuyệt vời phù hợp với phần kết của câu chuyện hỗn loạn này."));
        list.add(new Gioithieu(R.drawable.gt6, "PLAYSTATION™ | UT", "Bộ sưu tập UT với các thiết kế nguyên bản mang tính biểu tượng của PlayStation!"));
        list.add(new Gioithieu(R.drawable.gt7, "SHIN JAPAN HEROES UNIVERSE", "Những chiếc áo thun UT độc đáo với hình ảnh \"Shin Godzilla\", \"Shin Ultraman\" và cả \"Shin Kamen Rider\" rất nổi tiếng hiện nay đã có mặt."));
        return list;
    }
    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();

        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/res956f8b50352738c62284eca297c41af7fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/resf675a5fb93d5e20892bdb818f8ec6adafr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/resebcf772071148ad402b14380207383c4fr.jpg");
        mangquangcao.add("https://im.uniqlo.com/global-cms/spa/rese3ee18ead2ad739e7a3463a510d117e8fr.jpg");
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