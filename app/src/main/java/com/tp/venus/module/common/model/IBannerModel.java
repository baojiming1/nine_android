/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.model</p>
 * <p>File：IBannerModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/16:50.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.model;

import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.bean.Banner;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.common.model.IBannerModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/16:50
 * @version 1.0.0
 */

public interface IBannerModel {

    /**
     * 搜索Banner
     * @param type
     * @param mProgressSubscriber
     */
    void search(@Status.Banner int type, Subscriber<JsonMessage<PageResult<Banner>>> mProgressSubscriber);

}
