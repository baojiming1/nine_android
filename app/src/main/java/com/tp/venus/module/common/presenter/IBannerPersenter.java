/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.presenter</p>
 * <p>File：IBannerPersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/16:45.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.config.Status;

/**<p>Class：com.tp.venus.module.common.presenter.IBannerPersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/16:45
 * @version 1.0.0
 */

public interface IBannerPersenter extends IBasePresenter {

    /**
     *  获取banner
     * @param type
     */
    void searchBanner(@Status.Banner int type);

}
