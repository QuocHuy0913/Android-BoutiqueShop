package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doan.EventBus.TinhTongEvent;
import com.example.doan.adapter.GioHangAdapter;
import com.example.doan.utils.Utilsss;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    TextView giohangtrong, tongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;

    GioHangAdapter adapter;

    double tongtiensp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        initView();
        initControl();
        tinhTongTien();
    }

    private void tinhTongTien() {
        tongtiensp = 0;
        for(int i = 0; i < Utilsss.manggiohang.size(); i++){
            tongtiensp = tongtiensp + (Utilsss.manggiohang.get(i).getGiasp() * Utilsss.manggiohang.get(i).getSoluong());
        }
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(tongtiensp);

        tongtien.setText(str);
    }

    private void initControl() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if(Utilsss.manggiohang.size() == 0){
            giohangtrong.setVisibility(View.VISIBLE);
        }else {
            adapter = new GioHangAdapter(getApplicationContext(), Utilsss.manggiohang);
            recyclerView.setAdapter(adapter);
        }

        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                intent.putExtra("tongtienne", tongtiensp);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        giohangtrong = findViewById(R.id.txtgiohantrong);
        tongtien = findViewById(R.id.txttongtien);
        toolbar = findViewById(R.id.toolbar_giohang);
        recyclerView = findViewById(R.id.recycleviewgiohang);
        btnmuahang = findViewById(R.id.btnmuahang);
    }

    public void clickOut(View view) {
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event){
        if(event != null){
            tinhTongTien();
        }
    }
}