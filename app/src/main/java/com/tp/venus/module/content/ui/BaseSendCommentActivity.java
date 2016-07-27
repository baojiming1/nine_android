/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.content.ui</p>
 * <p>File：BaseSendCommentActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/26/13:14.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.rx.RxUtils;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.content.bean.Comment;
import com.tp.venus.module.content.event.CommentEvent;
import com.tp.venus.util.InputMethodUtils;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.widget.ClearEditText;
import com.tp.venus.widget.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**<p>Class：com.tp.venus.module.content.ui.BaseSendCommentActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/26/13:14
 * @version 1.0.0
 */

public abstract class BaseSendCommentActivity extends BaseActivity implements RxViewListener.Action {


    @InjectView(R.id.comment_content)
    ClearEditText commentContent;
    @InjectView(R.id.comment_send)
    RippleView commentSend;

    protected Comment parent;
    protected CompositeSubscription mCompositeSubscription;
    protected String toUserId;
    protected String fhint;
    protected String contentId;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewLayout();
        ButterKnife.inject(this);
        contentId = getIntent().getStringExtra(Status.Content.ID);
        toUserId =  getIntent().getStringExtra(Status.User.TOUSERID);
        fhint = ResourcesUtil.getString(this, R.string.addComment);
        RxViewListener.clicks(commentSend, this);
        initView();
    }

    /**
     * 设置布局
     */
    protected abstract void setViewLayout();

    /**
     * 初始化
     */
    protected  abstract  void initView();


    @Override
    public void call(Object o) {
        String content = commentContent.getEditableText().toString();
        if (StringUtil.isEmpty(content)) {
            RxUtils.doSubThead(null, new Action1<Object>() {
                @Override
                public void call(Object o) {
                    commentContent.setShakeAnimation();                 //没有文字时处理
                }
            });
        } else {
            if( StringUtil.isEmpty(contentId) || StringUtil.isEmpty(toUserId)){
                return ;
            }
            String hint = commentContent.getHint().toString();
            if( fhint.equals(hint) ){
                hint = "";
            }
            CommentEvent mCommentEvent = new CommentEvent(Status.Comment.SEND__EVENT, contentId, toUserId, (hint + content), parent);
            mRxBus.send(mCommentEvent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRxBus = getRxBus();
        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(mRxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if( o instanceof CommentEvent) {
                    CommentEvent mCommentEvent = (CommentEvent) o;
                    switch (mCommentEvent.type){
                        case Status.Comment.REPLY_EVENT:
                            parent = mCommentEvent.parent;
                            String reply = "回复" + parent.getSponsor().getNickname() + ":";
                            toUserId = parent.getUserId();
                            commentContent.setHint(reply);
                            InputMethodUtils.showInputMethod(commentContent);
                            break;
                        case Status.Comment.CLEAR_EVENT:
                            commentContent.setText("");
                            commentContent.setHint(fhint);
                            InputMethodUtils.hideInputMethod(commentContent);
                            break;
                        case Status.Comment.SET_TOUSERID:
                            toUserId = mCommentEvent.toUserId;
                            break;
                    }
                }
            }
        }));
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeSubscription.unsubscribe();
        if( mRxBus != null){
            mRxBus = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
