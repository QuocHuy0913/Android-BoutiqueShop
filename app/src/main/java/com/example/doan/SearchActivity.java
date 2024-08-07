package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.doan.adapter.sanphamAdapter;
import com.example.doan.my_interface.IClickItemSPListener;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    sanphamAdapter sanphamAdapter;
    ArrayList<Sanpham> lstSP;
    EditText edtsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        lstSP = new ArrayList<>();
        edtsearch = findViewById(R.id.edtsearch);

        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    lstSP.clear();
                    sanphamAdapter = new sanphamAdapter(lstSP, new IClickItemSPListener() {
                        @Override
                        public void onClickItemSP(Sanpham sanpham) {
                            onClickGoToDetail(sanpham);
                        }
                    });
                    recyclerView.setAdapter(sanphamAdapter);
                    sanphamAdapter.setData(lstSP);
                }else {
                    getDataSearch(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        recyclerView = findViewById(R.id.recycleviewsearch);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        sanphamAdapter = new sanphamAdapter(lstSP, new IClickItemSPListener() {
            @Override
            public void onClickItemSP(Sanpham sanpham) {
                onClickGoToDetail(sanpham);
            }
        });
    }

    private void getDataSearch(String s) {
        lstSP.clear();
        lstSP = sanphamDataQuery.getAll1(getApplicationContext(), s);
        sanphamAdapter = new sanphamAdapter(lstSP, new IClickItemSPListener() {
            @Override
            public void onClickItemSP(Sanpham sanpham) {
                onClickGoToDetail(sanpham);
            }
        });
        recyclerView.setAdapter(sanphamAdapter);
        sanphamAdapter.setData(lstSP);
    }

    private void onClickGoToDetail(Sanpham sanpham) {
        Intent i = new Intent(getApplicationContext(), DetailSPActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_SP", sanpham);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void clickOut(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        lstSP.clear();
        super.onDestroy();
    }
}