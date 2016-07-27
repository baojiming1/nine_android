/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter</p>
 * <p>File：IMessagePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/17:12.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.message.presenter.IMessagePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/17:12
 * @version 1.0.0
 */

public interface IMessagePresenter extends IBasePresenter {

    /**
     * 删除信息
     * @param messageId
     */
    @Login
    void delete(String messageId);

}
