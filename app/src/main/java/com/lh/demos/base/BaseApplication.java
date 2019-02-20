package com.lh.demos.base;

import android.app.Application;
import android.util.Log;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by leihui on 2019/1/31.
 * BaseApplication
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initBmobSDK();
        initBmobPush();
    }

    private void initBmobSDK() {
        //初始化BmobSDK
        Bmob.initialize(this, "d0f59510d94467f9df88ccba0ea5a608");
    }

    private void initBmobPush() {
        //TODO 集成：1.4、初始化数据服务SDK、初始化设备信息并启动推送服务
        //使用推送服务时的初始化操作
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.d("lh123", bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                } else {
                    Log.d("lh123", "message:" + e.getMessage());
                }
            }
        });
        //启动推送服务
        BmobPush.startWork(this);
    }
}
