package com.example.doan.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doan.MainActivity;
import com.example.doan.R;
import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.doan.model.User;

public class LoginActivity extends AppCompatActivity {
    EditText edNhapten, edNhapPassword;
    Button btLogin, btRegister;
    private final Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Ánh xạ
        initView();
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        // Tạo sự kiện
        initControl();

    }

    private void initControl() {
        btLogin.setOnClickListener(view -> checkUserLogin());
        btRegister.setOnClickListener(funRegister());
    }

    private View.OnClickListener funRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }

    private void checkUserLogin() {
        String userPref = sharedPreferences.getString(Utils.KEY_USER, null);
        User user = gson.fromJson(userPref, User.class);
        // user == null có nghĩa là chưa có user đăng ký
        if(user == null)
        {
            return;
        }
        boolean isValid = edNhapten.getText().toString().trim().equals(user.getUserName()) && edNhapPassword.getText().toString().trim().equals(user.getPassword());
        if(isValid)
        {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Utils.KEY_USER_PROFILE, user);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        edNhapten = findViewById(R.id.edNhapten);
        edNhapPassword = findViewById(R.id.edNhapPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btSign_up);
    }

    @NonNull
    private View.OnClickListener nhanvaoRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }

    @NonNull
    private View.OnClickListener nhanvaoLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edNhapten.getText().toString().trim();
                String password = edNhapPassword.getText().toString().trim();

                if (checkUserName(username) && checkPasword(password))
                {
                    Intent i = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(i);
                }

            }
        };
    }

    boolean checkUserName(String username)
    {
        if(username.isEmpty())
        {
            edNhapten.setError("Vui lòng nhập");
            return false;
        }
        return true;
    }

    boolean checkPasword(String password)
    {
        if(password.isEmpty())
        {
            edNhapPassword.setError("Vui lòng nhập");
            return false;
        }
        return true;
    }

}