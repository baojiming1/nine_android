/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.content.event</p>
 * <p>File：CommentEvent.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/12/14:20.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.event;

import com.tp.venus.config.Status;
import com.tp.venus.module.content.bean.Comment;

import rx.functions.Action1;

/**<p>Class：com.tp.venus.module.content.event.CommentEvent</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/12/14:20
 * @version 1.0.0
 */

public class CommentEvent implements Action1 {

    public int type;                    //事件类型
    public String contentId;                //内容ID
    public String toUserId;                 //接受者ID
    public String  message;                 //信息内容
    public Comment parent;                   //父级ID

    public CommentEvent(){

    }

    public  CommentEvent(int type, String contentId, String toUserId, String message, Comment parent) {
        this.type = type;
        this.contentId = contentId;
        this.toUserId = toUserId;
        this.message = message;
        this.parent = parent;
    }

    @Override
    public void call(Object o) {
       if( o instanceof CommentEvent){
           CommentEvent mCommentEvent = (CommentEvent) o;
       }
    }

    public void execute(CommentEvent mCommentEvent){
        switch (mCommentEvent.type){
            case Status.Comment.REPLY_EVENT:
                break;
            case Status.Comment.CLEAR_EVENT:
                break;
            case Status.Comment.SET_TOUSERID:
                break;
        }
    }

}
