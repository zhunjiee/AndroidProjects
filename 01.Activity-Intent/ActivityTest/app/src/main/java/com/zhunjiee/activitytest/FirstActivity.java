package com.zhunjiee.activitytest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast 提示信息
//                Toast.makeText(FirstActivity.this, "You Click Button 1", Toast.LENGTH_SHORT).show();

                // 显示Intent
//                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
//                startActivity(intent);

                // 隐式Intent
//                Intent intent = new Intent("com.zhunjiee.activitytest.ACTION_START");
//                intent.addCategory("com.zhunjiee.activitytest.MY_CATEGORY");
//                startActivity(intent);

//                启动活动的最佳写法
                SecondActivity.actionStart(FirstActivity.this, "data1", "data2");

                // 销毁一个活动，相当于点击了back按钮
//                finish();
            }
        });
    }

    // 在活动中使用menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "You Click Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "You Click Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
