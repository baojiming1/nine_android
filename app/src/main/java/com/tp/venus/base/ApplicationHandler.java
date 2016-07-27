/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base</p>
 * <p>File：ApplicationHandler.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/7/11:21.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.gotye.live.core.GLCore;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Constant;
import com.tp.venus.config.Status;
import com.tp.venus.util.PermissionUtil;
import com.tp.venus.util.StringUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.PlatformConfig;
import com.youzan.sdk.YouzanSDK;

import cn.jpush.android.api.JPushInterface;
import io.vov.vitamio.Vitamio;

/**
 * <p>Class：com.tp.venus.base.ApplicationHandler</p>
 * <p>Description：</p>
 * <pre>
 *   //TODO 需要整理
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/7/11:21
 */

public class ApplicationHandler extends Application {

    protected static ApplicationHandler mInstance;
    //系统默认的UncaughtException处理类
  /*  private Thread.UncaughtExceptionHandler mDefaultHandler;
    private ExceptionHandler mExceptionHandler;

    public void init(){
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        mExceptionHandler = ExceptionHandler.getExceptionHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(mExceptionHandler);
    }*/


    public ApplicationHandler() {
    }

    public synchronized static ApplicationHandler getApp() {
        if( mInstance == null ){
            mInstance = new ApplicationHandler();
        }
        return mInstance;
    }


    @Override
    public void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = PermissionUtil.getProcessName(this,
                android.os.Process.myPid());
        mInstance = this;
        initLog();
        initUMeng();
        if(StringUtil.isNotEmpty(processName) &&
                processName.equals(Constant.WEB_VIEW_PROESS)){     //webview进程
            Logger.d(":webview --- processName-->" + processName);
            return ;
        }
     /* //  Logger.e(":lecloud --- processName-->" + processName);
        if (getApplicationInfo().packageName.equals(Constant.LETV_PROESS)) {
            //TODO CrashHandler是一个抓取崩溃log的工具类（可选）
            //CrashHandler.getInstance(this);

            return ;
        }*/
        initQinJia();
       // initLeTv();
        initJPush();
        initYouzan();
        initVitamio();

       /* Bugtags.start("5370f04554388fa76458c9bd4a605311", this, Bugtags.BTGInvocationEventBubble);*/
    }

    private void initQinJia(){
        GLCore.registerApp(this, "467ff220-f653-11e5-8fee-5254009b7711", "a21b1c1d7f534982ad74b9671ae63700", "sanzhu");
    }

    /**
     * 乐视视频初始化
     */
    private void initLeTv(){
      /*  LeCloudProxy.init(getApplicationContext());
        LeCloudPlayerConfig.getInstance().setDeveloperMode(true).setIsApp();*/
    }

    private void initVitamio(){
        Vitamio.isInitialized(this);
    }

    /**
     * 初始化极光推送
     */
    private void initJPush(){
        if(AppConfig.IS_DEBUG){
            JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        }
        JPushInterface.init(this);     		// 初始化 JPush
    }

    /**
     * 初始化有赞组件
     */
    private void initYouzan(){
        YouzanSDK.init(this, Status.YouZan.uat);
    }

    /**
     * 初始化友盟组件
     */
    private void initUMeng(){
        if( AppConfig.IS_DEBUG){
            MobclickAgent.setDebugMode(true);
        }
        MobclickAgent.openActivityDurationTrack(false);
      //  PlatformConfig.setWeixin("wxd807109ec58edf28", "0a5e297963518f05d2d1f458a6e47407");
        //微信 appid appsecret
        PlatformConfig.setWeixin(Status.UMeng.WEIXIN_AppID, Status.UMeng.WEIXIN_AppSecret);

        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo("3723377647","7c0377fca486e3b8df8c102fe6dd1a98");

        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("1105187144", "Noi74Xd7MoiEIIjk");

    }

    /**
     * 日志初始化
     */
    private void initLog() {
        if( AppConfig.IS_DEBUG ){
            Logger
                    .init(AppConfig.LOG_TAG)               // default tag : PRETTYLOGGER or use just init()
                    .setMethodCount(3)            // default 2
                    .hideThreadInfo()             // default it is shown
                    .setLogLevel(LogLevel.FULL);
        }
    }
}
