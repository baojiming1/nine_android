/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter.impl</p>
 * <p>File：FnsPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/17:05.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter.impl;

import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.message.presenter.IFnsPresenter;
import com.tp.venus.module.message.ui.view.IFnsView;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;

/**<p>Class：com.tp.venus.module.message.presenter.impl.FnsPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/17:05
 * @version 1.0.0
 */

public class FnsPresenter extends MessagePresenter implements IFnsPresenter {

    private IFnsView mIFnsView;
    private IUserModel mIUserModel;

    public FnsPresenter(IFnsView mIFnsView) {
        super(mIFnsView);
        this.mIFnsView = mIFnsView;
        this.mIUserModel = new UserModel();
    }

    @Override
    public void attention(String userId, final boolean isAttention) {
        mIUserModel.attention(userId, isAttention, new RxSubscriber<JsonMessage>(mIFnsView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIFnsView.attentionSuccess(isAttention);
            }
        });
    }
}
