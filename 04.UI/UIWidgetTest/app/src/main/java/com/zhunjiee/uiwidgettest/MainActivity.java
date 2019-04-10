package com.zhunjiee.uiwidgettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);

        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Click Button", Toast.LENGTH_SHORT).show();
//            }
//        });
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String inputText = editText.getText().toString();
                if (inputText.length() != 0) {
                    Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                }
                // 切换图片
                imageView.setImageResource(R.drawable.android_2);
                break;
                default:
                    break;
        }
    }
}
