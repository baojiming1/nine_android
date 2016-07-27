/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：IBindPhonePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/16:26.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;

import java.util.Map;

/**<p>Class：com.tp.venus.module.user.presenter.IBindPhonePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/18/16:26
 * @version 1.0.0
 */

public interface IBindPhonePresenter extends IBasePresenter{


    /**
     *  手机号绑定
     * @param mobile
     * @param code
     */
    void bindPhone(String mobile, String code);
}
