package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String BROADCAST_PERMISSION_DISC = "com.example.broadcasttest.MY_BROADCAST";
    private static final String BROADCAST_ACTION_DISC = "com.example.broadcasttest.my_broadcast";

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 动态注册监听网络状态变化的广播
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        networkChangeReceiver = new NetworkChangeReceiver();
//        registerReceiver(networkChangeReceiver, intentFilter);

        // 测试本 App 发送到别的 App 的自定义广播 是否能被自己收到
        intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION_DISC);
        // 设置广播接收时的优先级，数字越大越早接收
        intentFilter.setPriority(100);
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilter, BROADCAST_PERMISSION_DISC, null);


        // 点击按钮，发送自定义广播
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent("com.example.broadcasttest.MY_BROADCAST");
//                intent.putExtra("name", "zhangsan");
//                intent.putExtra("age", 18);
//                intent.setComponent(new ComponentName("com.example.broadcasttest", "com.example.broadcasttest.MyBroadcastReceiver"));
//                // 发送标准广播
////                sendBroadcast(intent);
//                // 发送有序广播
//                sendOrderedBroadcast(intent, null);



                // 第三步：在A app中发送广播
                Intent intent = new Intent();   // Intent就是我们要发送的内容
                // 设置你这个广播的action，只有和这个action一样的接受者才能接受者才能接收广播
                intent.setAction(BROADCAST_ACTION_DISC);
                sendBroadcast(intent, BROADCAST_PERMISSION_DISC);
            }
        });
    }




    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 动态注册的广播接收器一定要取消注册，这点和 iOS 中的 NSNotificationCenter 类似
        unregisterReceiver(networkChangeReceiver);

        unregisterReceiver(myBroadcastReceiver);
    }
}
