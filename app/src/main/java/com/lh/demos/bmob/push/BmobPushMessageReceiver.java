package com.lh.demos.bmob.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.bmob.push.PushConstants;

/**
 * Created by leihui on 2019/1/31.
 * BmobPushMessageReceiver
 * TODO 集成：1.3、创建自定义的推送消息接收器，并在清单文件中注册
 */

public class BmobPushMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("lh123", "客户端收到推送内容：" + intent.getStringExtra("msg"));
        }
    }
}
