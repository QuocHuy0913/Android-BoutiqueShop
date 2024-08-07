package com.example.doan.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.example.doan.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.example.doan.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText edUserNameC;
    private EditText edPasswordC;
    private EditText edBirthdayC;
    private RadioGroup rbSex;
    private Button btnRegister;

    private SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    private final Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Khai báo share Pre
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        // lấy dữ liệu
        initView();
        initControl();
    }

    private void initView() {
        edUserNameC = findViewById(R.id.edtUserNameRegister);
        edPasswordC = findViewById(R.id.edtPasswordRegister);
        edBirthdayC = findViewById(R.id.edtBirthdayRegister);
        rbSex = findViewById(R.id.rgSex);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void initControl() {
        btnRegister.setOnClickListener(view -> sukienRegister());
    }

    private void sukienRegister() {
        String username = edUserNameC.getText().toString().trim();
        String password = edPasswordC.getText().toString().trim();
        String birthday = edBirthdayC.getText().toString().trim();
        // Nếu sex = 1 là nam, sex = 0 là nữ
        int sex = 1;
        boolean isValid = checkUserName(username) && checkPassword(password);
        if(isValid)
        {
            // Nếu dữ liệu hợp lệ, tạo đối tượng user để lưu vô share perference
            User userNew = new User();
            userNew.setUserName(username);
            userNew.setPassword(password);
            userNew.setBirthday(birthday);
            // Lấy radio button id đang được checked
            int sexSelected = rbSex.getCheckedRadioButtonId();
            if(sexSelected == R.id.rbFemale)
            {
                sex = 0;
            }
            userNew.setSex(sex);
            // Vì user là object nên convert qua String với format là ...
            String userStr = gson.toJson(userNew);
            editor.putString(Utils.KEY_USER, userStr);
            editor.commit();
            // Dùng Toast để show thông báo đăng ký thành công
            Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_LONG).show();
            // finish registerActivity
            finish();
    }


}

    private boolean checkPassword(String password) {
        if(password.isEmpty())
        {
            edUserNameC.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if(password.length() <= 5)
        {
            edUserNameC.setError("Tên đăng nhập phải lớn hơn 5 ký tự");
            return false;
        }
        return true;
    }

    private boolean checkUserName(String userName) {
        if(userName.isEmpty())
        {
            edUserNameC.setError("Vui lòng nhập tên đăng nhập");
            return false;
        }
        if(userName.length() <= 5)
        {
            edUserNameC.setError("Tên đăng nhập phải ít nhất 6 ký tự");
            return false;
        }
        return true;
    }

    public void clickOut(View view) {
        finish();
    }
}
