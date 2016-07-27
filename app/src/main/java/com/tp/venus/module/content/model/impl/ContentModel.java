/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.model.impl</p>
 * <p>File：ContentModelImpl.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/13/17:35.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.api.ContentApi;
import com.tp.venus.module.content.bean.ContentResult;
import com.tp.venus.module.content.model.IContentModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.home.model.impl.ContentModelImpl</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/13/17:35
 * @version 1.0.0
 */

public class ContentModel extends BaseModel implements IContentModel {
    @Override
    public void praise(String contentId, boolean praise, Subscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, Boolean>  mPraise = new HashMap<String, Boolean>();
        mPraise.put("praise", praise);
        ApiUtil.getInstance().createApi(ContentApi.class).praise(contentId, mPraise).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void contentDetails(String contentId, Subscriber<JsonMessage<ContentResult>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(ContentApi.class).contentDetails(contentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }


    @Override
    public void deleteContent(String contentId, Subscriber<JsonMessage> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(ContentApi.class).delete(contentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }



}
