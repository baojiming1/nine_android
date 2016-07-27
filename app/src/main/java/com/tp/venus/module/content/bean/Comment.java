package com.tp.venus.module.content.bean;

import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.StringUtil;

/**
 * Created by pc on 2016/1/17.
 */
public class Comment {

    /***
     *  "contentId": 644793689784188900,
     "createTime": 1443073079043,
     "id": 646921498178617300,
     "message": "测试评论",
     "parentId": 0,
     "praise": 0,
     "receiver": {                       //接受者
     "icon": "www.baidu.com",
     "id": 644847264749060100,
     "nickname": "成功"
     },
     "sponsor": {
     "icon": "www.baidu.com",
     "id": 644793689784188900,
     "nickname": "jack"
     }
     */
    private String contentId;
    private String id;
    private Long createTime;
    private String message;
    private String parentId;
    private int  praise;
    private User receiver;              //接受者
    private String toUserId;            //接受者ID
    private User sponsor;               //发起者
    private String userId;              //发起者ID



    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSponsor() {
        return sponsor;
    }

    public void setSponsor(User sponsor) {
        this.sponsor = sponsor;
    }


    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getUserId() {
        if(StringUtil.isNotEmpty(userId)){
            return userId;
        }
        if( this.sponsor != null){
            return this.sponsor.getId();
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
