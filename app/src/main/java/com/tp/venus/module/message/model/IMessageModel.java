/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.model</p>
 * <p>File：IMessageModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/14:51.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.message.bean.BadgeCount;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.message.model.IMessageModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/14:51
 * @version 1.0.0
 */

public interface IMessageModel {

    /**
     * 获取未读信息总数
     * @param mProgressSubscriber
     */
    void getMessageCount(Subscriber<JsonMessage<BadgeCount>> mProgressSubscriber);

    /**
     * 消息删除
     * @param messageId
     */
    void delete(String messageId, Subscriber<JsonMessage> mProgressSubscriber);

}
