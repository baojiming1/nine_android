/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp.p</p>
 * <p>File：IBasePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/15:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.p;

import android.content.Context;

import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.base.mvp.p.IBasePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/15:24
 * @version 1.0.0
 */

public interface IBasePresenter {

    /**
     * 获取当前用户
     * @return
     */
    User getCurrentUser();

    /**
     * 设置当前用户
     * @param user
     * @return
     */
    User setCurrentUser(User user);

    /**
     * 清空当前用户
     */
    void clearUser();


    /**
     * 跳转到登录界面
     */
    void goLoginView();

    /**
     * 获取当前的Context
     */
    Context getCurrentContext();

   // boolean isLogin();
}
