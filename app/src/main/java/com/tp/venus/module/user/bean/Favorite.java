/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.bean</p>
 * <p>File：Favorite.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/16:57.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.bean;

/**<p>Class：com.tp.venus.module.user.bean.Favorite</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/16:57
 * @version 1.0.0
 */

public class Favorite {


    /**
     * commentCount : 123
     * contentId : 644793689784188900
     * favoriteId : 644793689784200000
     * praiseCount : 12
     * url : www.baidu.com/4
     */

    private int commentCount;               //评论总数
    private String contentId;               //帖子主键
    private String favoriteId;              //收藏主键
    private int praiseCount;                //点赞总数
    private String url;                     //图片地址
    private String video;                   //视频地址

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
