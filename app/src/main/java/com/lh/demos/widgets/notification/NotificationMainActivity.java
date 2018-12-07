package com.lh.demos.widgets.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lh.demos.R;

import java.io.File;

public class NotificationMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_main);
        initData();
        initView();
    }

    private void initData() {
        mContext = this;
    }

    private void initView() {
        Button btnGeneral = findViewById(R.id.btn_general_notification);
        Button btnFolder = findViewById(R.id.btn_folder_notification);
        Button btnFloat = findViewById(R.id.btn_float_notification);
        btnGeneral.setOnClickListener(this);
        btnFolder.setOnClickListener(this);
        btnFloat.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_general_notification:
                onGeneralNotification();
                break;
            case R.id.btn_folder_notification:
                onFolderNotification();
                break;
            case R.id.btn_float_notification:
                onFloatNotification();
                break;
        }
    }

    private void onGeneralNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            //打开一个网页
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.baidu.com"));
            //打开一个Activity
            Intent intent = new Intent(mContext, NotificationJumpActivity.class);
            Intent[] intents = new Intent[] { intent };
            PendingIntent pendingIntent = PendingIntent.getActivities(mContext, 0, intents, 0);

            Notification notification = new NotificationCompat.Builder(mContext, "123")
                    .setContentTitle("通知标题")
                    .setContentText("通知内容！！！")
                    .setSmallIcon(R.drawable.logo_mini)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo))
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    //点后用通知图标自动取消
                    .setAutoCancel(true)
                    //设置声音
                    //.setSound(Uri.fromFile(new File("/system/media/audio/notifications/Bottle.ogg")))
                    //设置震动
                    //.setVibrate(new long[] {0, 1000, 1000, 1000})
                    //设置呼吸灯
                    //.setLights(Color.GREEN, 1000, 1000)
                    //设置默认的声音，震动，呼吸灯
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .build();
            notificationManager.notify(1, notification);
        }
    }

    private void onFolderNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            //打开一个Activity
            Intent intent = new Intent(mContext, NotificationJumpActivity.class);
            Intent[] intents = new Intent[] { intent };
            PendingIntent pendingIntent = PendingIntent.getActivities(mContext, 0, intents, 0);

            //长文字
            String strContent = "我是非常长的通知内容，我是非常长的通知内容，我是非常长的通知内容，我是非常长的通知内容，我是非常长的通知内容！！！";
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat
                    .BigTextStyle()
                    .bigText(strContent)
                    .setBigContentTitle("我是通知标题")
                    .setSummaryText("我是Summary!!!");
            //大图片
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat
                    .BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.dog));

            Notification notification = new NotificationCompat.Builder(mContext, "123")
                    .setContentTitle("通知标题")
                    .setContentText("通知内容！！！")
                    .setSmallIcon(R.drawable.logo_mini)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.logo))
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    //点后用通知图标自动取消
                    .setAutoCancel(true)
                    //设置默认的声音，震动，呼吸灯
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    //设置长文字，大图片
                    .setStyle(bigTextStyle)
                    //设置通知的重要程度
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .build();
            notificationManager.notify(1, notification);
        }
    }

    private void onFloatNotification() {

    }
}
