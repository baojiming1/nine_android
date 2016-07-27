/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter.impl</p>
 * <p>File：CommentPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/14:30.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.bean.Comment;
import com.tp.venus.module.content.model.ICommentModel;
import com.tp.venus.module.content.model.impl.CommentModel;
import com.tp.venus.module.content.presenter.ICommentPresenter;
import com.tp.venus.module.content.ui.view.ICommentView;

/**<p>Class：com.tp.venus.module.content.presenter.impl.CommentPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/14:30
 * @version 1.0.0
 */

public class CommentPresenter extends BasePresenter implements ICommentPresenter {

    private ICommentView mCommentView;
    private ICommentModel mCommentModel;

    public CommentPresenter(ICommentView mCommentView){
        super(mCommentView);
        this.mCommentView = mCommentView;
        this.mCommentModel = new CommentModel();
    }


    @Override
    public void save(String contentId, String message, String toUserId, final Comment parent) {
        String parentId = parent == null ? null : parent.getId();
        mCommentModel.save(contentId, message, toUserId, parentId, new RxSubscriber<JsonMessage<Comment>>(mCommentView) {
            @Override
            public void onSuccess(JsonMessage message) {
                Comment mComment = (Comment) message.getObj();
                mComment.setSponsor(getCurrentUser());
                if( parent != null){
                    mComment.setReceiver(parent.getReceiver());
                }
                mCommentView.sendComment(mComment);
            }
        });
    }
}
