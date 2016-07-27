/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.bean</p>
 * <p>File：Notice.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/15:56.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.bean;

/**<p>Class：com.tp.venus.module.common.bean.Notice</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/15:56
 * @version 1.0.0
 */

public class Notice {

    /**
     * createTime : 1447921807141
     * message : 测试系统信息
     * noticeId : 667260702381047890
     * showType : 1
     * title : 测试系统信息
     */

    private long createTime;
    private String message;
    private String noticeId;
    private int showType;
    private String title;
    private String url;
    private String icon;

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getMessage() {
        return message;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public int getShowType() {
        return showType;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
