/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.model.impl</p>
 * <p>File：MessageModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/14:52.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.message.api.MessageApi;
import com.tp.venus.module.message.bean.BadgeCount;
import com.tp.venus.module.message.model.IMessageModel;
import com.tp.venus.util.ApiUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.message.model.impl.MessageModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/14:52
 * @version 1.0.0
 */

public class MessageModel extends BaseModel implements IMessageModel {
    @Override
    public void getMessageCount(Subscriber<JsonMessage<BadgeCount>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(MessageApi.class).getBadgeCount().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void delete(String messageId, Subscriber<JsonMessage> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(MessageApi.class).delete(messageId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
