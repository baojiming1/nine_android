/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：OrderDetail.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/5/15:02.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

/**<p>Class：com.tp.venus.module.shop.bean.OrderDetail</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/5/15:02
 * @version 1.0.0
 */

public class OrderDetail {


    /**
     * brandName : 朗姆酒
     * count : 1
     * createTime : 1459934368424
     * id : 717642871670308864
     * mainImage : http://7xnrd42g1JHZ8uwD
     * orderId : 717642871640948736
     * orderNo : 201604060519282830000005768
     * price : 200.0
     * productId : 675251346189844480
     * productName : 朗姆酒
     * realPrice : 150.0
     * selectedSku : 颜色:红色
     * title : 朗姆酒
     * updateTime : 1459934368424
     * userId : 100000000000000000
     */

    private String brandName;
    private Integer buyCount;
    private Long createTime;
    private String id;
    private String mainImage;
    private String orderId;
    private String orderNo;
    private String price;
    private String productId;
    private String productName;
    private String realPrice;
    private String selectedSku;
    private String title;
    private Long updateTime;
    private String userId;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getSelectedSku() {
        return selectedSku;
    }

    public void setSelectedSku(String selectedSku) {
        this.selectedSku = selectedSku;
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
}
