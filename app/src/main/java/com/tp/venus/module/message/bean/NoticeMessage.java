/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.bean</p>
 * <p>File：NoticeMessage.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/14:01.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.bean;

import com.tp.venus.module.common.bean.Notice;
import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.module.message.bean.NoticeMessage</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/14:01
 * @version 1.0.0
 */

public class NoticeMessage {

    private Long createTime;
    private String messageId;
    private Notice notice;
    private User user;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
