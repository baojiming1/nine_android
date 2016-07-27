/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter.impl</p>
 * <p>File：MessagePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/17:13.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.message.model.IMessageModel;
import com.tp.venus.module.message.model.impl.MessageModel;
import com.tp.venus.module.message.presenter.IMessagePresenter;
import com.tp.venus.module.message.ui.view.IMessageView;

/**<p>Class：com.tp.venus.module.message.presenter.impl.MessagePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/17:13
 * @version 1.0.0
 */

public class MessagePresenter extends BasePresenter implements IMessagePresenter {

    protected IMessageModel messageModel;
    protected IMessageView mIMessageView;

    public MessagePresenter(IMessageView mIMessageView) {
        super(mIMessageView);
        this.mIMessageView = mIMessageView;
        this.messageModel = new MessageModel();
    }

    @Override
    public void delete(String messageId) {
        messageModel.delete(messageId, new RxSubscriber<JsonMessage>(mIMessageView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIMessageView.deleteSuccess();
            }
        });
    }
}
