/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.bean</p>
 * <p>File：BadgeCount.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/14:53.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.bean;

/**<p>Class：com.tp.venus.module.message.bean.BadgeCount</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/14:53
 * @version 1.0.0
 */

public class BadgeCount {

    private int friendsCount ;
    private int commentCount ;
    private int fnsCount ;
    private int praiseCount ;
    private int noticeCount ;

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFnsCount() {
        return fnsCount;
    }

    public void setFnsCount(int fnsCount) {
        this.fnsCount = fnsCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public int getNoticeCount() {
        return noticeCount;
    }

    public void setNoticeCount(int noticeCount) {
        this.noticeCount = noticeCount;
    }
}
