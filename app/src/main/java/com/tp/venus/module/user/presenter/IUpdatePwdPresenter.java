/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：IUpdatePwdPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/12:37.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.user.presenter.IUpdatePwdPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/12:37
 * @version 1.0.0
 */

public interface IUpdatePwdPresenter extends IBasePresenter {

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     * @param againPwd
     */
    @Login
    void updatePwd(String oldPwd, String newPwd, String againPwd);
}
