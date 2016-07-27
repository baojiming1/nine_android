/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：Special.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/11:00.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.bean.Special</p>
 * <p>Description：</p>
 * <pre>
 *     商城专题
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/11:00
 * @version 1.0.0
 */

public class Special implements Parcelable {


    /**
     * id : 2016032900010003
     * title : title  aa dddd
     * imageUrl : http://......aaa.jpg
     * description : 男人的最爱
     * orderNo : 2
     */

    private String id;
    private String title;
    private String imageUrl;
    private String description;
    private Integer orderNo;
    private List<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.imageUrl);
        dest.writeString(this.description);
        dest.writeValue(this.orderNo);
    }

    public Special() {
    }

    protected Special(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.imageUrl = in.readString();
        this.description = in.readString();
        this.orderNo = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Special> CREATOR = new Creator<Special>() {
        public Special createFromParcel(Parcel source) {
            return new Special(source);
        }

        public Special[] newArray(int size) {
            return new Special[size];
        }
    };
}
