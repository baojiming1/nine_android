/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.bean</p>
 * <p>File：ContentResult.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/11/18:46.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.bean;

import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.StringUtil;

import java.io.Serializable;
import java.util.List;

/**<p>Class：com.tp.venus.module.content.bean.ContentResult</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/11/18:46
 * @version 1.0.0
 */

public class ContentResult implements Serializable {


    /**
     * comment : 0
     * delStatus : false
     * favorites : 0
     * gpsX : 0
     * gpsY : 0
     * id : 683910943020679168
     * isAttention : false
     * isFavorite : false
     * isPraise : false
     * mainImage : http://7xnwo1.media1.z0.glb.clouddn.com/FvWa5woiSH2dw-ZZb00J423X0tbt
     * message : 测试数据有没
     * praise : 2
     * type : 1
     * updateTime : 1451892049923
     * userId : 679173826138865664
     * views : 1
     */

    private int comment;            //评论数量
    private boolean delStatus;      //删除状态
    private int favorites;          //收藏数
    private String gpsX;            //
    private String gpsY;
    private String id;              //帖子主键
    private boolean isAttention;     //关注状态
    private boolean isFavorite;     //收藏状态
    private boolean isPraise;       //点赞状态
    private String mainImage;       //主图
    private String message;         //内容信息
    private int praise;             //点赞数
    private int type;               //类型            // 1:普通文章 2：推荐文章 3.活动文章
    private long updateTime;
    private String userId;          //用户ID
    private int views;              //游览的次数
    private String video;
    private String url;
    private Short status ;              // 帖子的审核状态  0:待审核  1:审核通过  2:审核不通过

    private List<ContentImage> images;      //帖子图片
    private List<User> praisePeople;    //点赞的用户
    private List<Tag> tags;         //标签
    private User user;              //帖子的作者
    private String productUrl;      //产品的详情页
    private String productId;       //产品ID

    public void setComment(int comment) {
        this.comment = comment;
    }

    public void setDelStatus(boolean delStatus) {
        this.delStatus = delStatus;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public void setGpsX(String gpsX) {
        this.gpsX = gpsX;
    }

    public void setGpsY(String gpsY) {
        this.gpsY = gpsY;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsAttention(boolean isAttention) {
        this.isAttention = isAttention;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setIsPraise(boolean isPraise) {
        this.isPraise = isPraise;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setType(int type) {
        this.type = type;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getComment() {
        return comment;
    }

    public boolean isDelStatus() {
        return delStatus;
    }

    public int getFavorites() {
        return favorites;
    }

    public String getGpsX() {
        return gpsX;
    }

    public String getGpsY() {
        return gpsY;
    }

    public String getId() {
        return id;
    }

    public boolean isIsAttention() {
        return isAttention;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    public boolean isIsPraise() {
        return isPraise;
    }

    public String getMainImage() {
        return mainImage;
    }

    public String getMessage() {
        return message;
    }

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getType() {
        return type;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getUserId() {
        if(StringUtil.isNotEmpty(userId)){
            return userId;
        }
        if( user != null && StringUtil.isNotEmpty(user.getId())){
            return  user.getId();
        }
        return userId;
    }

    public int getViews() {
        return views;
    }


    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isPraise() {
        return isPraise;
    }


    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public List<ContentImage> getImages() {
        return images;
    }

    public void setImages(List<ContentImage> images) {
        this.images = images;
    }

    public List<User> getPraisePeople() {
        return praisePeople;
    }

    public void setPraisePeople(List<User> praisePeople) {
        this.praisePeople = praisePeople;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}
