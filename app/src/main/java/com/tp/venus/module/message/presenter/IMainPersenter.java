/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter</p>
 * <p>File：IMainPersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/20/10:48.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.message.presenter.IMainPersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/20/10:48
 * @version 1.0.0
 */

public interface IMainPersenter extends IBasePresenter {

    /**
     * 刷新Token
     */
    void refreshToken();

}
