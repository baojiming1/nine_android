/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：ProductSku.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/19/15:44.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

/**<p>Class：com.tp.venus.module.shop.bean.ProductSku</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/19/15:44
 * @version 1.0.0
 */

public class ProductSku {

    /**
     * createTime : 1453008464449
     * id : 1
     * orderNo : 1
     * productId : 675251346189844480
     * skuName : 颜色
     * skuOptions : 红色,蓝色,白色
     * updateTime : 1453008464449
     */

    private Long createTime;
    private String id;
    private Integer orderNo;
    private String productId;
    private String skuName;
    private String skuOptions;
    private Long updateTime;

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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSkuOptions() {
        return skuOptions;
    }

    public void setSkuOptions(String skuOptions) {
        this.skuOptions = skuOptions;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
