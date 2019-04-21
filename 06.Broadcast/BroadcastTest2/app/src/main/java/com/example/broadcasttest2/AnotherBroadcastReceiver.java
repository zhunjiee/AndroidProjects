package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AnotherBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "AnotherBroadcastReceive";
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in AnotherBroadcastReceiver", Toast.LENGTH_LONG).show();
        Log.d(TAG, "received in AnotherBroadcastReceiver");
    }
}
