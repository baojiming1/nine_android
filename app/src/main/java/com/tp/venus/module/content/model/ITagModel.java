/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.model</p>
 * <p>File：ITagModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/13:04.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.content.bean.Tag;
import com.tp.venus.module.content.bean.TagResult;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.content.model.ITagModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/13:04
 * @version 1.0.0
 */

public interface ITagModel  {

    /**
     * 搜索热门标签
     */
    void searchHotTag(Subscriber<JsonMessage<PageResult<Tag>>> mProgressSubscriber);

    /**
     * 帖子详情
     * @param tagId
     * @param mProgressSubscriber
     */
    void showTagInfo(String tagId, Subscriber<JsonMessage<TagResult>> mProgressSubscriber);

    /**
     * 关注标签
     * @param tagId
     * @param mProgressSubscriber
     */
    void attention(String tagId, boolean isAttention, Subscriber<JsonMessage> mProgressSubscriber);
}
