/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.core</p>
 * <p>File：ExceptionHandler.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/7/10:56.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base;

import android.content.Context;

import com.orhanobut.logger.Logger;

/**<p>Class：com.tp.venus.base.ExceptionHandler</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/7/10:56
 * @version 1.0.0
 */

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private ApplicationHandler mContext;

    private ExceptionHandler(Context mContext){
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.mContext = (ApplicationHandler) mContext;
    }

    public static ExceptionHandler getExceptionHandler(Context mContext){
        return new ExceptionHandler(mContext);
    }



    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        ex.printStackTrace();
        Logger.e(ex, "错误");
    }
}
