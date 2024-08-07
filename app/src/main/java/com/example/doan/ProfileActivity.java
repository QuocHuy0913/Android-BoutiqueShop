package com.example.doan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan.ui.GiaoDienLoginActivity;
import com.example.doan.ui.Utils;
import com.example.doan.model.User;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity {
    Button Logout, XoaTK;
    User user;
    TextView tvName;
    TextView tvDate;
    TextView tvSex;
    SharedPreferences sharedPreferences;
    ImageView imageView;
    private static final int CODE = 404;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvName = findViewById(R.id.TvName);
        tvDate = findViewById(R.id.TvDate);
        tvSex = findViewById(R.id.TvSex);
        imageView = findViewById(R.id.image);
        Logout = findViewById(R.id.idLogout);
        XoaTK = findViewById(R.id.idXoaTK);
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, GiaoDienLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        XoaTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().apply();
                Intent i = new Intent(ProfileActivity.this, GiaoDienLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        String user1 = sharedPreferences.getString(Utils.KEY_USER,null);
        if(user1!=null){
            Gson gson = new Gson();
            user = gson.fromJson(user1,User.class);
            tvName.setText(user.getUserName());
            tvDate.setText(user.getBirthday());
            if(user.getSex()==1){
                tvSex.setText("Nam");
            }else{
                tvSex.setText("Nữ");
            }
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
            Toast.makeText(ProfileActivity.this, "Thêm hình thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ProfileActivity.this, "Thêm hình không thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickOut(View view) {
        finish();
    }
}