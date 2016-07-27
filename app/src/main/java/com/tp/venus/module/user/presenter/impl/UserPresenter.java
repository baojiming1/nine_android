/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：UserPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/11:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.IUserPresenter;
import com.tp.venus.module.user.ui.view.IUserView;

/**<p>Class：com.tp.venus.module.user.presenter.impl.UserPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/11:22
 * @version 1.0.0
 */

public class UserPresenter extends BasePresenter implements IUserPresenter {

    private IUserView mIUserView;
    private IUserModel mIUserModel;

    public UserPresenter(IUserView mIUserView){
        super(mIUserView);
        this.mIUserView = mIUserView;
        this.mIUserModel = new UserModel();
    }


    @Override
    public void showUserInfo() {
        showUserInfo(null);
    }

    @Override
    public void showUserInfo(String userId) {
        mIUserModel.userInfo(userId, new RxSubscriber<JsonMessage<User>>(mIUserView) {
            @Override
            public void onSuccess(JsonMessage jsonMessage) {
                User user = (User) jsonMessage.getObj();
                mIUserView.showUserInfo(user);
            }
        });
    }

    @Override
    public void attention(String userId, final Boolean isAttention) {
        mIUserModel.attention(userId, isAttention, new RxSubscriber<JsonMessage>(mIUserView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIUserView.attentionSuccess(isAttention);
            }
        });
    }

}
