/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：Classes.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/16:21.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**<p>Class：com.tp.venus.module.shop.bean.Classes</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/16:21
 * @version 1.0.0
 */

public class Classes implements Parcelable {


    /**
     * id : 2016032900010003
     * className : title  aa dddd
     * classNo : 2016032900010003
     * parentNo : 2016032900010003
     * classLevel : 2
     * imageUrl : http://......aaa.jpg
     * orderNo : 2
     */

    private String id;
    private String className;
    private String classNo;
    private String parentNo;
    private Integer classLevel;
    private String imageUrl;
    private Integer orderNo;
    private int listType;    //1-分类列表 ; 2-产品列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getParentNo() {
        return parentNo;
    }

    public void setParentNo(String parentNo) {
        this.parentNo = parentNo;
    }

    public Integer getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(Integer classLevel) {
        this.classLevel = classLevel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public int getListType() {
        return listType;
    }

    public void setListType(int listType) {
        this.listType = listType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.className);
        dest.writeString(this.classNo);
        dest.writeString(this.parentNo);
        dest.writeValue(this.classLevel);
        dest.writeString(this.imageUrl);
        dest.writeValue(this.orderNo);
        dest.writeInt(this.listType);
    }

    public Classes() {
    }

    protected Classes(Parcel in) {
        this.id = in.readString();
        this.className = in.readString();
        this.classNo = in.readString();
        this.parentNo = in.readString();
        this.classLevel = (Integer) in.readValue(Integer.class.getClassLoader());
        this.imageUrl = in.readString();
        this.orderNo = (Integer) in.readValue(Integer.class.getClassLoader());
        this.listType = in.readInt();
    }

    public static final Creator<Classes> CREATOR = new Creator<Classes>() {
        @Override
        public Classes createFromParcel(Parcel source) {
            return new Classes(source);
        }

        @Override
        public Classes[] newArray(int size) {
            return new Classes[size];
        }
    };
}
