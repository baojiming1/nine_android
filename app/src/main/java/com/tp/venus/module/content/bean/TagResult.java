/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.bean</p>
 * <p>File：TagResult.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/18:00.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.bean;

import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.module.content.bean.TagResult</p>
 * <p>Description：</p>
 * <pre>
 *      标签结果
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/18:00
 * @version 1.0.0
 */

public class TagResult {

    private Tag tag;
    private User user;						//占领者信息
    private Integer contentCount;			//帖子数
    private Integer attentionCount;		//关注数
    private Boolean isAttention;		//是否已经关注

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public Boolean getAttention() {
        return isAttention;
    }

    public void setAttention(Boolean attention) {
        isAttention = attention;
    }
}
