package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);

        rememberPass = findViewById(R.id.remember_pass);
        // 将保存的账号密码取出设置到文本框中
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean isRemember = sharedPref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = sharedPref.getString("account", "");
            String password = sharedPref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(isRemember);
        }

        loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击了登录按钮进行登录操作
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if (account.equals("admin") && password.equals("123456")) {
                    // 跳转到主页
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                    editor = sharedPref.edit();
                    // 保存账号密码
                    if (rememberPass.isChecked()) {
                        // 如果勾选了保存密码则将密码保存起来
                        editor.putString("account", account);
                        editor.putString("password", password);
                        editor.putBoolean("remember_password", true);
                    } else {
                        // 否则将之前保存的账号密码清除
                        editor.clear();
                    }
                    editor.apply();

                    finish();   // 退出当前活动
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
