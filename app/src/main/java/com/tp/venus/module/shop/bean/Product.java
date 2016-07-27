/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：Product.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/19/14:29.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.bean.Product</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/19/14:29
 * @version 1.0.0
 */

public class Product {


    /**
     * brandName : 朗姆酒
     * createTime : 1453008464449
     * delStatus : false
     * description : 朗姆酒
     * detail :
     * freight : 1.0
     * id : 675251346189844480
     * mainImage : http://7xnrd4.com2.z0.glb.qiniucdn.com/FoVvK87FPcF3l6Ufr_2g1JHZ8uwD
     * onSaleStatus : true
     * originArea : 瑞士
     * price : 200.0
     * productImages : []
     * productName : 朗姆酒
     * productNo : 20016012255
     * realPrice : 150.0
     * title : 朗姆酒1
     * updateTime : 1453008464449
     * userId : 100000000000000000
     */
    private String brandName;
    private Long createTime;
    private Boolean delStatus;
    private String description;
    private String detail;
    private String freight;
    private String id;
    private String mainImage;
    private Boolean onSaleStatus;
    private String originArea;
    private String price;
    private String productName;
    private String productNo;
    private String realPrice;
    private String title;
    private Long updateTime;
    private String userId;
    private List<ProductImage> productImages;
    private List<ProductSku> productSKUs;

    private Boolean isFavorite;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Boolean delStatus) {
        this.delStatus = delStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Boolean getOnSaleStatus() {
        return onSaleStatus;
    }

    public void setOnSaleStatus(Boolean onSaleStatus) {
        this.onSaleStatus = onSaleStatus;
    }

    public String getOriginArea() {
        return originArea;
    }

    public void setOriginArea(String originArea) {
        this.originArea = originArea;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public List<ProductSku> getProductSKUs() {
        return productSKUs;
    }

    public void setProductSKUs(List<ProductSku> productSKUs) {
        this.productSKUs = productSKUs;
    }

    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

}
