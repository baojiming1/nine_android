/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：ToastUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/12/10:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.tp.venus.base.ApplicationHandler;

/**<p>Class：com.tp.venus.util.ToastUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/12/10:36
 * @version 1.0.0
 */

public class ToastUtil {

    private static ToastUtil instance = new ToastUtil();
    private Toast mToast;

    public static  ToastUtil getInstance(){
        return instance;
    }

    private ToastUtil(){
        mToast = Toast.makeText(ApplicationHandler.getApp().getApplicationContext(), StringUtil.EMPTY, Toast.LENGTH_SHORT);
    }

    /**
     *  显示信息
     * @param message
     */
    public  void show(String message){
        mToast.setText(message);
        mToast.show();
    }

    public void show(@StringRes  int message){
        mToast.setText(message);
        mToast.show();
    }



}
