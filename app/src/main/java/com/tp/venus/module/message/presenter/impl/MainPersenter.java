/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter.impl</p>
 * <p>File：MainPersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/20/10:49.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.home.ui.view.IMainView;
import com.tp.venus.module.message.presenter.IMainPersenter;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.message.presenter.impl.MainPersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/20/10:49
 * @version 1.0.0
 */

public class MainPersenter extends BasePresenter implements IMainPersenter {

    private IMainView mIMainView;
    private IUserModel userModel;

    public MainPersenter(IMainView mIMainView) {
        super(mIMainView);
        this.mIMainView = mIMainView;
        this.userModel = new UserModel();
    }

    @Override
    public void refreshToken() {
        String token = getToken();
        if(StringUtil.isEmpty(token)){
            mIMainView.onError();
            return ;
        }
        userModel.refreshToken( new RxSubscriber<JsonMessage<User>>(mIMainView) {
            @Override
            public void onSuccess(JsonMessage<User> message) {
                clearUser();
                setCurrentUser(message.getObj());
                mIMainView.onSuccess(message.getObj());
            }

            @Override
            public boolean onAuthenticationFail(JsonMessage jsonMessage) {
                clearUser();
                mIMainView.onError();
                return true;
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mIMainView.onError();
            }

            @Override
            public void onFail(JsonMessage jsonMessage) {
                //super.onFail(jsonMessage);
                mIMainView.onError();
            }
        });
    }
}
