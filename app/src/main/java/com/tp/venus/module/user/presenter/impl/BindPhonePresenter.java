/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：BindPhonePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/16:27.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.IBindPhonePresenter;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ValidateUtils;

/**<p>Class：com.tp.venus.module.user.presenter.impl.BindPhonePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/18/16:27
 * @version 1.0.0
 */

public class BindPhonePresenter extends BasePresenter implements IBindPhonePresenter {

    private IUserModel mUserModel;
    private ICommonView mICommonView;

    public BindPhonePresenter(ICommonView mICommonView) {
        super(mICommonView);
        this.mUserModel = new UserModel();
        this.mICommonView = mICommonView;
    }

    //验证码手机号
    private boolean validPhone(String phone){
        if( StringUtil.isEmpty(phone)){
            mICommonView.showError("手机号码不能为空");
            return false;
        }
        if ( !ValidateUtils.isMobile(phone) ){
            mICommonView.showError("手机号码格式不正确");
            return false;
        }
        return true;
    }

    @Override
    public void bindPhone(final String mobile, String code) {
        if( !validPhone(mobile) ){
            return ;
        }
        if( StringUtil.isEmpty(code)){
            mICommonView.showError("验证码不能为空");
            return ;
        }
        mUserModel.bindPhone(mobile, code, new ProgressSubscriber<JsonMessage>(mICommonView) {
            @Override
            public void onSuccess(JsonMessage message) {
                User user = getCurrentUser();
                user.setMobile(mobile);
                clearUser();
                setCurrentUser(user);
                mICommonView.onSuccess();
            }
        });

    }
}
