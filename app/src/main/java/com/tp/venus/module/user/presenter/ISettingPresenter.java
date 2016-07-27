/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：ISettingPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/11:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.user.presenter.ISettingPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/11:38
 * @version 1.0.0
 */

public interface ISettingPresenter extends IBasePresenter {

    /**
     *  退出登录
     */
    @Login
    void loginOut();

}
