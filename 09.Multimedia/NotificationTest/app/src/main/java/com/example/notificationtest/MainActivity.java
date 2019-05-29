package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                Intent intent = new Intent(this, NotificationActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                // 获取通知管理器
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // 创建通知
                Notification notification = new NotificationCompat.Builder(this, "channel_id")
                        .setContentTitle("This is content title")   // 设置通知标题
//                        .setContentText("This is content text") // 设置通知内容,带省略号
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))    // 能显示全部的通知内容
//                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))  // 设置显示大图, 和 bigText 只能显示一个
                        .setWhen(System.currentTimeMillis())    // 通知显示的时间
                        .setSmallIcon(R.mipmap.ic_launcher) // 状态栏图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))   // 通知栏图标
                        .setContentIntent(pi)   // 设置点击通知跳转到具体的详情页
                        .setAutoCancel(true)
//                        .setSound(Uri.fromFile(new File("./system/media/audio/ringtones/Luna.ogg")))    // 设置声音
//                        .setVibrate(new long[] {0, 1000, 1000, 1000})   // 设置震动
//                        .setLights(Color.GREEN, 1000, 1000) // 设置 LED 灯
                        .setDefaults(NotificationCompat.DEFAULT_ALL)    // 上面三项都使用系统的默认设置
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                // 8.0 开始一定要设置NotificationChannel,否则通知无效
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
                    channel.setBypassDnd(true);    //设置绕过免打扰模式
                    channel.canBypassDnd();       //检测是否绕过免打扰模式
                    channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);//设置在锁屏界面上显示这条通知
                    channel.setDescription("description of this notification");
                    channel.setLightColor(Color.GREEN);
                    channel.setName("name of this notification");
                    channel.setShowBadge(true);
                    channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    channel.enableVibration(true);
                    manager.createNotificationChannel(channel);
                }
                // 显示通知
                manager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
