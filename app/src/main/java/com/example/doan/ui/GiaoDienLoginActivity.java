package com.example.doan.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.doan.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GiaoDienLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giao_dien_login);
    }

    public void Login(View view) {
        Intent i = new Intent(GiaoDienLoginActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void Register(View view) {
        Intent i = new Intent(GiaoDienLoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }
}