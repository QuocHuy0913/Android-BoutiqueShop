package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.adapter.yeuthichAdapter;
import com.example.doan.custom_textview.RobotoBlackTV;
import com.example.doan.my_interface.IImageClickListenner;
import com.example.doan.utils.Utilsss;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailSPActivity extends AppCompatActivity {
    TextView tvNameSP, tvGiaSP, tvMotaSP;
    Button btnthem;
    ImageButton btnthemyeuthich;
    ImageView imgSP;
    ListView listView;
    String[] listData, list_soluong;
    ArrayAdapter<String> adapter, adapter_soluong_spinnner;

    Spinner soluong_spinner;
    Context context;

    Sanpham sanpham;

    NotificationBadge badge;

    boolean flag2 = true;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_spactivity);

        initView();
        initData();
        initControl();
    }

    private void initControl() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themGioHang();
            }
        });

        btnthemyeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themYeuThich();
                if(flag2 == true){
                    btnthemyeuthich.setImageResource(R.drawable.baseline_favorite_24);
                    flag2 = false;
                }
                else {
                    btnthemyeuthich.setImageResource(R.drawable.baseline_favorite_border_24);
                    flag2 = true;
                }
            }
        });
    }

    private void themYeuThich() {
        if(Utilsss.mangyeuthich.size() > 0){
            boolean flag = false;
            for(int i = 0; i < Utilsss.mangyeuthich.size(); i++){
                if(Utilsss.mangyeuthich.get(i).getIdsp() == sanpham.getIdPro()) {
                    double gia = sanpham.getPrice();
                    Utilsss.mangyeuthich.get(i).setGiasp(gia);
                    flag = true;
                }
            }
            if(flag == false){
                double gia = sanpham.getPrice();
                Yeuthich yeuthich = new Yeuthich();
                yeuthich.setGiasp(gia);
                yeuthich.setIdsp(sanpham.getIdPro());
                yeuthich.setTensp(sanpham.getName());
                yeuthich.setHinhsp(sanpham.getResourceImage());
                Utilsss.mangyeuthich.add(yeuthich);
            }
        }else {
            double gia = sanpham.getPrice();
            Yeuthich yeuthich = new Yeuthich();
            yeuthich.setGiasp(gia);
            yeuthich.setIdsp(sanpham.getIdPro());
            yeuthich.setTensp(sanpham.getName());
            yeuthich.setHinhsp(sanpham.getResourceImage());
            Utilsss.mangyeuthich.add(yeuthich);
        }
    }

    private void themGioHang() {
        if(Utilsss.manggiohang.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(soluong_spinner.getSelectedItem().toString());
            for(int i = 0; i < Utilsss.manggiohang.size(); i++){
                if(Utilsss.manggiohang.get(i).getIdsp() == sanpham.getIdPro()){
                    Utilsss.manggiohang.get(i).setSoluong(soluong + Utilsss.manggiohang.get(i).getSoluong());
                    double gia = sanpham.getPrice() * Utilsss.manggiohang.get(i).getSoluong();
                    Utilsss.manggiohang.get(i).setGiasp(gia);
                    flag = true;
                }
            }
            if(flag == false){
                double gia = sanpham.getPrice() * soluong;
                Giohang giohang = new Giohang();
                giohang.setGiasp(gia);
                giohang.setSoluong(soluong);
                giohang.setIdsp(sanpham.getIdPro());
                giohang.setTensp(sanpham.getName());
                giohang.setHinhsp(sanpham.getResourceImage());
                Utilsss.manggiohang.add(giohang);
            }
        }else {
            int soluong = Integer.parseInt(soluong_spinner.getSelectedItem().toString());
            double gia = sanpham.getPrice() * soluong;
            Giohang giohang = new Giohang();
            giohang.setGiasp(gia);
            giohang.setSoluong(soluong);
            giohang.setIdsp(sanpham.getIdPro());
            giohang.setTensp(sanpham.getName());
            giohang.setHinhsp(sanpham.getResourceImage());
            Utilsss.manggiohang.add(giohang);
        }
        int totalItem = 0;
        for(int i = 0; i < Utilsss.manggiohang.size(); i++){
            totalItem = totalItem + Utilsss.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }
        sanpham = (Sanpham) bundle.get("object_SP");
        double number = sanpham.getPrice();
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(number);


        tvNameSP.setText(sanpham.getName());
        tvGiaSP.setText(str);
        tvMotaSP.setText(sanpham.getDescription());
        imgSP.setImageBitmap(Utils.convertToBitmapFromAssets(this, sanpham.resourceImage));

        listData = new String[]{"TÌM KIẾM THEO CỬA HÀNG","MÔ TẢ", "TỔNG QUAN", "CHẤT LIỆU", "ĐÁNH GIÁ", "XEM BẢNG KÍCH THƯỚC", "CHÍNH SÁCH HOÀN TRẢ"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        list_soluong = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        adapter_soluong_spinnner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list_soluong);
        soluong_spinner.setAdapter(adapter_soluong_spinnner);
    }

    private void initView() {
        tvNameSP = findViewById(R.id.tv_name_SP);
        tvGiaSP = findViewById(R.id.tv_price_SP);
        tvMotaSP = findViewById(R.id.tv_description_SP);
        imgSP = findViewById(R.id.img_SPnew);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        btnthemyeuthich = findViewById(R.id.ibtnthemvaoyeuthich);


        listView = findViewById(R.id.lv_Detail);

        soluong_spinner = findViewById(R.id.soluong_spinner);

        badge = findViewById(R.id.menu_sl);
        if(Utilsss.manggiohang != null){
            int totalItem = 0;
            for(int i = 0; i < Utilsss.manggiohang.size(); i++){
                totalItem = totalItem + Utilsss.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }

        FrameLayout framLayoutgiohang = findViewById(R.id.framegiohang);
        framLayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });
    }

    public void clickOut(View view) {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utilsss.manggiohang != null){
            int totalItem = 0;
            for(int i = 0; i < Utilsss.manggiohang.size(); i++){
                totalItem = totalItem + Utilsss.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
}