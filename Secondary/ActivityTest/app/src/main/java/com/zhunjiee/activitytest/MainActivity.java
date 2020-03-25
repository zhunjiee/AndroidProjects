package com.zhunjiee.activitytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Task id is : " + getTaskId());
        // Show Toast
        Button button1 = findViewById(R.id.button_toast);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You Clicked Button1", Toast.LENGTH_SHORT).show();
            }
        });


        // 显式Intent
        Button button2 = findViewById(R.id.button_intent);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        // 隐式Intent
        Button button3 = findViewById(R.id.button_intent_hide);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.activitytest.ACTION_START");
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);
            }
        });

        // 打开网页
        Button button4 = findViewById(R.id.button_open_web);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        // 打电话
        Button button5 = findViewById(R.id.button_dial);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        // 数据传值 - 顺传
        Button button6 = findViewById(R.id.button_send_data);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "Hello SecondActivity";
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivity(intent);
            }
        });

        // 数据传值 - 逆传
        Button button7 = findViewById(R.id.button_send_data_2);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    String resultData = data.getStringExtra("data_return");
                    Log.d(TAG, resultData);
                }
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(MainActivity.this, "You Clicked Add Menu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(MainActivity.this, "You Clicked Remove Menu", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
