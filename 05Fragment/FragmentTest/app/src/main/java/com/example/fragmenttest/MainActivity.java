package com.example.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
//        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
//                replaceFragment(new AnotherRightFragment());
        }
    }

    // 切换碎片的方法
//    private void replaceFragment(Fragment fragment) {
//        // 获取 fragmentManager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        // 开启事务
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        // 替换碎片
//        transaction.replace(R.id.right_layout, fragment);
//        // 模拟返回栈
//        transaction.addToBackStack(null);
//        // 提交事务
//        transaction.commit();
//    }
}
