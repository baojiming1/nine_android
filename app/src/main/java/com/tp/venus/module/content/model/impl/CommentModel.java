/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.model.impl</p>
 * <p>File：CommentModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/15:01.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.api.CommentApi;
import com.tp.venus.module.content.bean.Comment;
import com.tp.venus.module.content.model.ICommentModel;
import com.tp.venus.util.ApiUtil;
import com.tp.venus.util.StringUtil;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.content.model.impl.CommentModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/15:01
 * @version 1.0.0
 */

public class CommentModel extends BaseModel implements ICommentModel {


    @Override
    public void save(String contentId, String message, String toUserId, String parentId,  Subscriber<JsonMessage<Comment>> mProgressSubscriber) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("contentId", contentId);
        params.put("message", message);
        params.put("toUserId", toUserId);
        if(StringUtil.isNotEmpty(parentId)){
            params.put("parentId", parentId);
        }
        ApiUtil.getInstance().createApi(CommentApi.class).save(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
