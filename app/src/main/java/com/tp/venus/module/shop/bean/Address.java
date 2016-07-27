/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：Address.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/23/15:08.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**<p>Class：com.tp.venus.module.shop.bean.Address</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/23/15:08
 * @version 1.0.0
 */

public class Address implements Parcelable {


    /**
     * recipientName : 董恒
     * recipientID : 310222656151546465655
     * mobile : 13548851265
     * province : 上海市
     * city : 静安区
     * county : 大华镇
     * detail : 高平路100 号9号楼3楼
     * isDefault : false
     */
    private String id;
    private String recipientName;
    private String recipientID;
    private String mobile;
    private String province;
    private String city;
    private String county;
    private String detail;
    private Boolean isDefault;

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
    /**
     *  获取详细地址
     * @return
     */
    public String getDetailAddress(){
       // return this.province + this.city + this.county + this.detail;
        return this.detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.recipientName);
        dest.writeString(this.recipientID);
        dest.writeString(this.mobile);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.county);
        dest.writeString(this.detail);
        dest.writeValue(this.isDefault);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.id = in.readString();
        this.recipientName = in.readString();
        this.recipientID = in.readString();
        this.mobile = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.county = in.readString();
        this.detail = in.readString();
        this.isDefault = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
