/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：SettingPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/11:39.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.ISettingPresenter;
import com.tp.venus.module.user.ui.view.ISettingView;

/**<p>Class：com.tp.venus.module.user.presenter.impl.SettingPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/11:39
 * @version 1.0.0
 */

public class SettingPresenter extends BasePresenter implements ISettingPresenter {

    private ISettingView mISettingView;
    private IUserModel userModel;

    public SettingPresenter(ISettingView mISettingView) {
        super(mISettingView);
        this.mISettingView = mISettingView;
        this.userModel = new UserModel();
    }

    @Override
    public void loginOut() {
        userModel.loginOut(new ProgressSubscriber<JsonMessage>(mISettingView) {
            @Override
            public void onSuccess(JsonMessage message) {
                clearUser();
                mISettingView.onSuccess();
            }
        });
    }
}
