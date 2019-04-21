package com.example.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    // 8.0开始，不能在AndroidManifest.xml（所谓的清单）中注册隐式广播接收器了
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Boot Complete", Toast.LENGTH_LONG).show();
    }
}
