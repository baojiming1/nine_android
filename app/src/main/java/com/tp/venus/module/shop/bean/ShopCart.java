/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：ShopCart.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/16:11.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**<p>Class：com.tp.venus.module.shop.bean.ShopCart</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/16:11
 * @version 1.0.0
 */

public class ShopCart implements Parcelable {


    /**
     * buyCount : 1
     * freight : 1.0
     * id : 675251346189844480
     * mainImage : http://7xnrd4.com2.z0.glb.qiniucdn.com/FoVvK87FPcF3l6Ufr_2g1JHZ8uwD
     * originArea : 瑞士
     * price : 2.000000000001E10
     * productId : 675251346189844480
     * realPrice : 150.0
     * selectedSku : 颜色:红色
     * title : 朗姆酒1
     * userId : 100000000000000000
     */

    private Integer buyCount;
    private String freight;
    private String id;
    private String mainImage;
    private String originArea;
    private String price;
    private String productId;
    private String realPrice;
    private String selectedSku;
    private String title;
    private String userId;
    private Boolean onSaleStatus;
    private Boolean delStatus;


    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getOnSaleStatus() {
        return onSaleStatus;
    }

    public void setOnSaleStatus(Boolean onSaleStatus) {
        this.onSaleStatus = onSaleStatus;
    }

    public Boolean getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Boolean delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.buyCount);
        dest.writeString(this.freight);
        dest.writeString(this.id);
        dest.writeString(this.mainImage);
        dest.writeString(this.originArea);
        dest.writeString(this.price);
        dest.writeString(this.productId);
        dest.writeString(this.realPrice);
        dest.writeString(this.selectedSku);
        dest.writeString(this.title);
        dest.writeString(this.userId);
        dest.writeValue(this.onSaleStatus);
        dest.writeValue(this.delStatus);
    }

    public ShopCart() {
    }

    protected ShopCart(Parcel in) {
        this.buyCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.freight = in.readString();
        this.id = in.readString();
        this.mainImage = in.readString();
        this.originArea = in.readString();
        this.price = in.readString();
        this.productId = in.readString();
        this.realPrice = in.readString();
        this.selectedSku = in.readString();
        this.title = in.readString();
        this.userId = in.readString();
        this.onSaleStatus = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.delStatus = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<ShopCart> CREATOR = new Creator<ShopCart>() {
        public ShopCart createFromParcel(Parcel source) {
            return new ShopCart(source);
        }

        public ShopCart[] newArray(int size) {
            return new ShopCart[size];
        }
    };
}
