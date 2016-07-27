/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.model.impl</p>
 * <p>File：TagModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/13:05.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.content.api.TagApi;
import com.tp.venus.module.content.bean.Tag;
import com.tp.venus.module.content.bean.TagResult;
import com.tp.venus.module.content.model.ITagModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.content.model.impl.TagModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/13:05
 * @version 1.0.0
 */

public class TagModel extends BaseModel implements ITagModel {

    @Override
    public void showTagInfo(String tagId, Subscriber<JsonMessage<TagResult>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(TagApi.class).showTagInfo(tagId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void attention(String tagId, boolean isAttention, Subscriber<JsonMessage> mProgressSubscriber) {
        Map<String, Object> params = new HashMap<>();
        params.put("isAttention", isAttention);
        params.put("id", tagId);
        params.put("type", Status.Attention.TAG);
        ApiUtil.getInstance().createApi(TagApi.class).attention(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }

    @Override
    public void searchHotTag(Subscriber<JsonMessage<PageResult<Tag>>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(TagApi.class).searchHotTag()
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
