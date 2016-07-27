/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：IRegisterPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/25/11:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.user.presenter.IRegisterPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/25/11:15
 * @version 1.0.0
 */

public interface IRegisterPresenter extends IBasePresenter {

    /**
     * 手机注册
     * @param mPhone
     * @param code
     * @param nickname
     * @param pwd
     */
    void register4phone(String mPhone, String code, String nickname, String pwd, String aginPwd);


    /**
     * 邮箱注册
     * @param email
     * @param nickname
     * @param pwd
     * @param aginPwd
     */
    void register4Email(String email, String nickname, String pwd, String aginPwd);


    /**
     * 重置密码
     * @param phone
     * @param code
     * @param pwd
     * @param aginPwd
     */
    void resetPwd(String phone, String code, String pwd, String aginPwd);


}
