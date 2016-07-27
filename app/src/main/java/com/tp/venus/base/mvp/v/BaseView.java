/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base</p>
 * <p>File：BaseView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/14/11:06.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.v;

import android.content.Context;

/**<p>Class：com.tp.venus.base.mvp.v.BaseView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/14/11:06
 * @version 1.0.0
 */

public interface BaseView  {

    /**
     *
     * @param message
     */
    void showError(String message);

   /* *//**
     * 必须登录
     * @param mBasePresenter
     * @param mOnLoginListener
     *//*
    public void needLogin(BasePresenter mBasePresenter, OnLoginListener mOnLoginListener);
*/
    /**
     * 跳转到登录界面
     */
    void goLoginView();

    /**
     * 获取当前的Context
     * @return
     */
    Context getCurrentContext();


   /* *//**
     * 登录事件
     *//*
    public abstract class OnLoginListener {
        public abstract void  doSuccess();

        public boolean doFail(){

            return false;
        }

    }*/

}
