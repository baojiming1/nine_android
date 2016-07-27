/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：UserPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/11:21.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.user.presenter.UserPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/11:21
 * @version 1.0.0
 */

public interface IUserPresenter extends IBasePresenter {

    /**
     * 显示用户信息
     */
    @Login
    void showUserInfo();

    /**
     * 显示他人用户信息
     * @param userId
     */
    void showUserInfo(String userId);

    /**
     * 用户关注/取消关注
     * @param userId
     * @param isAttention
     */
    @Login
    void attention(String userId, Boolean isAttention);
}
