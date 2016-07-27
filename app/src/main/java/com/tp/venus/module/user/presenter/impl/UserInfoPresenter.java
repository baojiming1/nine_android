/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：UserInfoPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/18:57.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.IUserInfoPresenter;
import com.tp.venus.module.user.ui.view.IUserInfoView;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.user.presenter.impl.UserInfoPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/18:57
 * @version 1.0.0
 */

public class UserInfoPresenter extends BasePresenter implements IUserInfoPresenter {

    private IUserInfoView mIUserInfoView;
    private IUserModel mUserModel;

    public UserInfoPresenter(IUserInfoView mBaseView) {
        super(mBaseView);
        this.mIUserInfoView = mBaseView;
        this.mUserModel = new UserModel();
    }

    @Override
    public void editNickName(final String nickname) {
        if(StringUtil.isEmpty(nickname)){
            mIUserInfoView.showError("用户昵称不能为空");
            return ;
        }
        User user = new User();
        user.setNickname(nickname);
        mUserModel.editUserInfo(user, new RxSubscriber<JsonMessage>(mIUserInfoView) {
            @Override
            public void onSuccess(JsonMessage message) {
                User user = getCurrentUser();
                user.setNickname(nickname);
                success(user);
                mIUserInfoView.onSuccess(nickname, IUserInfoView.UserInfoType.NICKNAME);
            }
        });

    }

    @Override
    public void editIcon(final String icon) {
        if(StringUtil.isEmpty(icon)){
            mIUserInfoView.showError("用户头像不能为空");
            return ;
        }
        User user = new User();
        user.setIcon(icon);
        mUserModel.editUserInfo(user, new ProgressSubscriber<JsonMessage>(mIUserInfoView) {
            @Override
            public void onSuccess(JsonMessage message) {
                User user = getCurrentUser();
                user.setIcon(icon);
                success(user);
                mIUserInfoView.onSuccess(icon, IUserInfoView.UserInfoType.ICON);
            }
        });
    }

    @Override
    public void editGender(final short gender, final String sGender) {
        User user = new User();
        user.setGender(gender);
        mUserModel.editUserInfo(user, new ProgressSubscriber<JsonMessage>(mIUserInfoView) {
            @Override
            public void onSuccess(JsonMessage message) {
                User user = getCurrentUser();
                user.setGender(gender);
                success(user);
                mIUserInfoView.onSuccess(sGender, IUserInfoView.UserInfoType.SEX);
            }
        });
    }

    @Override
    public void editAddress(final String address) {
        if(StringUtil.isEmpty(address)){
            mIUserInfoView.showError("用户地址不能为空");
            return ;
        }
        User user = new User();
        user.setAddress(address);
        mUserModel.editUserInfo(user, new ProgressSubscriber<JsonMessage>(mIUserInfoView) {
            @Override
            public void onSuccess(JsonMessage message) {
                User user = getCurrentUser();
                user.setAddress(address);
                success(user);
                mIUserInfoView.onSuccess(address, IUserInfoView.UserInfoType.ADDRESS);
            }
        });
    }


    private void success(User user){
        clearUser();
        setCurrentUserNotRegister(user);
    }

}
