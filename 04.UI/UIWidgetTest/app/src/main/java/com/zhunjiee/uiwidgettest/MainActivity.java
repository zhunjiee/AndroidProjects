package com.zhunjiee.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.FileReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);

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

                // 控制 progressBar 的显示与隐藏
//                if (progressBar.getVisibility() == View.GONE) {
//                    progressBar.setVisibility(View.VISIBLE);
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                }
                int progress = progressBar.getProgress();
                progress = progress + 10;
                progressBar.setProgress(progress);

                // AlertDialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("This is a Dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "You press OK");
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "You press Cancel");
                    }
                });
                dialog.show();

                // ProgressDialog
                // 8.0已废弃
//                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//                progressDialog.setTitle("This is ProgressDialog");
//                progressDialog.setMessage("Loading...");
//                progressDialog.setCancelable(true);
//                progressDialog.show();
                break;

                default:
                    break;
        }
    }
}
