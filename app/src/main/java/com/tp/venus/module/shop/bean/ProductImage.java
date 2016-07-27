/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：ProductImage.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/19/14:47.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import com.tp.venus.model.AutoImage;

/**<p>Class：com.tp.venus.module.shop.bean.ProductImage</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/19/14:47
 * @version 1.0.0
 */

public class ProductImage implements AutoImage {


    /**
     * createTime : 1453008464449
     * description : 222
     * id : 1
     * imageUrl : http://7xnrd4.com2.z0.glb.qiniucdn.com/FoVvK87FPcF3l6Ufr_2g1JHZ8uwD
     * isMain : true
     * orderNo : 1
     * productId : 675251346189844480
     * updateTime : 1453008464449
     */

    private Long createTime;
    private String description;
    private String id;
    private String imageUrl;
    private Boolean isMain;
    private Integer orderNo;
    private String productId;
    private Long updateTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
