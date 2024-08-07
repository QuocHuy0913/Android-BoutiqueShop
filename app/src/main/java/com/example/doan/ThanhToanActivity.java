package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.model.User;
import com.example.doan.ui.Utils;
import com.example.doan.utils.Utilsss;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.Locale;

public class ThanhToanActivity extends AppCompatActivity {

    TextView txttongtien, txtname, txtdate;
    EditText edtdiachi;
    AppCompatButton btndathang;

    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        initView();
        initControl();
    }

    private void initControl() {
        double number = getIntent().getDoubleExtra("tongtienne", 0);
        Locale localeUS = new Locale("en", "US");
        NumberFormat currencyUS = NumberFormat.getCurrencyInstance(localeUS);
        String str = currencyUS.format(number);

        txttongtien.setText(str);

        SharedPreferences shareget = getApplicationContext().getSharedPreferences(com.example.doan.ui.Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        String userPref = shareget.getString(Utils.KEY_USER, null);

        User user = gson.fromJson(userPref, User.class);
        txtname.setText(user.getUserName());
        txtdate.setText(user.getBirthday());
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_diachi = edtdiachi.getText().toString().trim();
                if(TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("test", new Gson().toJson(Utilsss.manggiohang));
                }
            }
        });
    }

    private void initView() {
        txttongtien = findViewById(R.id.txttongtien_ne);
        txtname = findViewById(R.id.txtnamee);
        txtdate = findViewById(R.id.txtdatee);
        edtdiachi = findViewById(R.id.edtdiachi);
        btndathang = findViewById(R.id.btndathang);
    }

    public void clickOut(View view) {
        finish();
    }
}