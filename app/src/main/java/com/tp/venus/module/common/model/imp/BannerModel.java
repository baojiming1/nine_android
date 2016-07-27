/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.model.imp</p>
 * <p>File：BannerModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/16:51.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.model.imp;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.api.BannerApi;
import com.tp.venus.module.common.bean.Banner;
import com.tp.venus.module.common.model.IBannerModel;
import com.tp.venus.util.ApiUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.common.model.imp.BannerModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/16:51
 * @version 1.0.0
 */

public class BannerModel extends BaseModel implements IBannerModel {

    @Override
    public void search(@Status.Banner int type, Subscriber<JsonMessage<PageResult<Banner>>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(BannerApi.class).search(type).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
