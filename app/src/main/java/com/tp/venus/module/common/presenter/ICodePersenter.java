/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.presenter</p>
 * <p>File：ICodePersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/16:39.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.common.presenter.ICodePersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/18/16:39
 * @version 1.0.0
 */

public interface ICodePersenter extends IBasePresenter {

    /**
     * 发送验证码
     * @param phone
     */
    void sendCode(String phone);
}
