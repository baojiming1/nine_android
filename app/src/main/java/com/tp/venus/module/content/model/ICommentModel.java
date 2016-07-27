/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.model</p>
 * <p>File：ICommentModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/14:59.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.bean.Comment;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.content.model.ICommentModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/14:59
 * @version 1.0.0
 */

public interface ICommentModel  {

    /**
     * 保存评论
     * @param contentId
     * @param message
     * @param toUserId
     * @param parentId
     */
     void save(String contentId, String message, String toUserId, String parentId, Subscriber<JsonMessage<Comment>> mProgressSubscriber);

}