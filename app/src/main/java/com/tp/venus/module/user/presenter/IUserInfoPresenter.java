/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：IUserInfoPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/18:56.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.user.presenter.IUserInfoPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/18:56
 * @version 1.0.0
 */

public interface IUserInfoPresenter extends IBasePresenter {

    /**
     *  编辑用户名
     * @param nickname
     */
    @Login
    void editNickName(String nickname);

    /**
     * 编辑用户头像
     * @param icon
     */
    @Login
    void editIcon(String icon);


    /**
     * 编辑用户性别
     * @param gender
     */
    @Login
    void editGender(short gender, String sGender);

    /**
     * 编辑用户地址
     * @param address
     */
    @Login
    void editAddress(String address);
}
