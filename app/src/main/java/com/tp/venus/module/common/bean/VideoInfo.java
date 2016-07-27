/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.bean</p>
 * <p>File：VideoInfo.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/7/15:59.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.bean;

/**<p>Class：com.tp.venus.module.common.bean.VideoInfo</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/7/15:59
 * @version 1.0.0
 */

public class VideoInfo {

    private String videoUrl;
    private String videoDesc;
    private String background;
    private Boolean isShow;
    private String videoTitle;


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
