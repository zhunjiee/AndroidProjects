package com.example.broadcasttest2;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String BROADCAST_PERMISSION_DISC = "com.example.broadcasttest.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.example.broadcasttest.my_broadcast";

    private AnotherBroadcastReceiver anotherBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 第二步：在B app中定义注册广播
        // 注册广播接收器
        anotherBroadcastReceiver = new AnotherBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION_DISC);
        registerReceiver(anotherBroadcastReceiver, intentFilter, BROADCAST_PERMISSION_DISC, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(anotherBroadcastReceiver);
    }
}
