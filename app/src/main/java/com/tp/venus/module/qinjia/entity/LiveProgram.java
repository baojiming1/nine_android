/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.entity</p>
 * <p>File：LiveProgram.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:07.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**<p>Class：com.tp.venus.module.qinjia.entity.LiveProgram</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/18:07
 * @version 1.0.0
 */

public class LiveProgram implements Parcelable {
    private String programId;
    private String roomId;
    private String shareUrl;
    private String anchorPwd;
    private String userPwd;
    private String title;
    private String adImage;
    private String video;
    private Integer baseCount;
    private Integer type;                   //节目类型 1:直播  2：重播  0 全部


    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getAnchorPwd() {
        return anchorPwd;
    }

    public void setAnchorPwd(String anchorPwd) {
        this.anchorPwd = anchorPwd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getBaseCount() {
        return baseCount;
    }

    public void setBaseCount(Integer baseCount) {
        this.baseCount = baseCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.programId);
        dest.writeString(this.roomId);
        dest.writeString(this.shareUrl);
        dest.writeString(this.anchorPwd);
        dest.writeString(this.userPwd);
        dest.writeString(this.title);
        dest.writeString(this.adImage);
        dest.writeString(this.video);
        dest.writeValue(this.baseCount);
        dest.writeValue(this.type);
    }

    public LiveProgram() {
    }

    protected LiveProgram(Parcel in) {
        this.programId = in.readString();
        this.roomId = in.readString();
        this.shareUrl = in.readString();
        this.anchorPwd = in.readString();
        this.userPwd = in.readString();
        this.title = in.readString();
        this.adImage = in.readString();
        this.video = in.readString();
        this.baseCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<LiveProgram> CREATOR = new Parcelable.Creator<LiveProgram>() {
        @Override
        public LiveProgram createFromParcel(Parcel source) {
            return new LiveProgram(source);
        }

        @Override
        public LiveProgram[] newArray(int size) {
            return new LiveProgram[size];
        }
    };
}
