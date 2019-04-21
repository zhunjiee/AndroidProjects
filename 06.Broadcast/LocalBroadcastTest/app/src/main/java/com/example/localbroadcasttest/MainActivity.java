package com.example.localbroadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String LOCAL_BROADCAST_STR = "com.example.localbroadcasttest.LOCAL_BROADCAST";

    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 1. 获取 localBroadcastManager 实例
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        // 发送本地广播
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LOCAL_BROADCAST_STR);
                // 发送本地广播
                localBroadcastManager.sendBroadcast(intent);
            }
        });



        // 2. 创建 intentFilter
        intentFilter = new IntentFilter();
        intentFilter.addAction(LOCAL_BROADCAST_STR);
        // 创建 localReceiver 本地接收器
        localReceiver = new LocalReceiver();
        // localBroadcastManager 注册本地广播监听器，
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
