/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.model</p>
 * <p>File：IContentModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/13/17:31.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.bean.ContentResult;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.content.model.IContentModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/13/17:31
 * @version 1.0.0
 */

public interface IContentModel {

    /**
     * 点赞
     * @param contentId
     * @param praise
     * @param mProgressSubscriber
     */
    void praise(String contentId, boolean praise, Subscriber<JsonMessage> mProgressSubscriber);

    /**
     * 帖子详情
     * @param contentId
     */
    void contentDetails(String contentId, Subscriber<JsonMessage<ContentResult>> mProgressSubscriber);

    /**
     * 删除帖子
     * @param contentId
     * @param mProgressSubscriber
     */
    void deleteContent(String contentId, Subscriber<JsonMessage> mProgressSubscriber);

}
