/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.model</p>
 * <p>File：Banner.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/12/11:48.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.bean;

import com.tp.venus.model.AutoImage;

/**<p>Class：com.tp.venus.module.common.bean.Banner</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/12/11:48
 * @version 1.0.0
 */

public class Banner implements AutoImage {


    /**
     * id : 1
     * icon : http://www.qbt365.com/./55.jpg
     * createTime : 1362212133223
     * title : title
     * updateTime : 1362212133223
     * foreignkey : 1
     * description : desc
     * orderField : 1
     * showType : 1
     * type : 1
     * url : http://www.qbt365.com/./55.jpg
     * isShow : false
     */
    private String id;
    private String icon;
    private Long createTime;
    private String title;
    private Long updateTime;
    private String foreignkey;
    private String description;
    private Integer orderField;
    private Integer showType;          //1:手机端 2：WEB
    private Integer type;           //    1.首页广告   2.活动专题
    private Boolean isShow;             // 是否显示
    private String url;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getForeignkey() {
        return foreignkey;
    }

    public void setForeignkey(String foreignkey) {
        this.foreignkey = foreignkey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderField() {
        return orderField;
    }

    public void setOrderField(Integer orderField) {
        this.orderField = orderField;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImageUrl() {
        return this.icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }
}
