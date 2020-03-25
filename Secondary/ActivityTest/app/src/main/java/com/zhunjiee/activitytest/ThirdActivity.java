package com.zhunjiee.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Log.d(TAG, "Task id is : " + getTaskId());
    }
}
