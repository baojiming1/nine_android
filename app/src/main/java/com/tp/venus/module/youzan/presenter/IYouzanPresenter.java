/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.youzan.presenter</p>
 * <p>File：IYouzanPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/19/11:02.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.youzan.presenter.IYouzanPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/19/11:02
 * @version 1.0.0
 */

public interface IYouzanPresenter extends IBasePresenter {

    /**
     * 打开有赞商城
     */
    @Login
    void openYouzan(String url);
}
