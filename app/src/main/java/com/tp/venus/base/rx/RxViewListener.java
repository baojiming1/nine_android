/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base</p>
 * <p>File：RxViewListener.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/14/10:56.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.rx;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**<p>Class：com.tp.venus.base.rx.RxViewListener</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/14/10:56
 * @version 1.0.0
 */

public class RxViewListener {

    /**
     * 点击事件
     * @param view
     * @param mAction
     */
    public static void clicks(View view, Action mAction){
        RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).subscribe(mAction);
    }

    /**
     * 点击事件
     * @param view
     * @return
     */
    public static rx.Observable<Void> clicks(View view){
        return RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS);
    }


    /**
     *
     * @param <T>
     */
    public interface Action<T> extends Action1<T>{

    }



}
