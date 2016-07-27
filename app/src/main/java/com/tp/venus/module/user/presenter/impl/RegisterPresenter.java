/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：RegisterPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/25/11:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.model.ICommonModel;
import com.tp.venus.module.common.model.imp.CommonModel;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.IRegisterPresenter;
import com.tp.venus.module.user.ui.view.IRegisterView;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ValidateUtils;

/**<p>Class：com.tp.venus.module.user.presenter.impl.RegisterPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/25/11:15
 * @version 1.0.0
 */

public class RegisterPresenter extends BasePresenter implements IRegisterPresenter {

    private IRegisterView mRegisterView;
    private IUserModel mUserModel;
    private ICommonModel mICommonModel;

    public RegisterPresenter(IRegisterView mRegisterView) {
        super(mRegisterView);
        this.mRegisterView = mRegisterView;
        this.mUserModel = new UserModel();
        this.mICommonModel = new CommonModel();
    }

    @Override
    public void register4phone(String mPhone, String code, String nickname, String pwd, String aginPwd) {
        if( !validPhone(mPhone) ){
            return ;
        }
        if(StringUtil.isEmpty(code)){
            mRegisterView.showError("验证码不能为空");
            return ;
        }
        if(StringUtil.isEmpty(nickname)){
            mRegisterView.showError("昵称不能为空");
            return ;
        }
        if( !validPwd(pwd, aginPwd) ){
            return ;
        }

        mUserModel.register4phone(mPhone, pwd, ResourcesUtil.getDeviceId(), nickname, code, new RxSubscriber<JsonMessage<User>>(mRegisterView){
            @Override
            public void onSuccess(JsonMessage<User> message) {
                clearUser();
                setCurrentUser(message.getObj());
                mRegisterView.onSuccess();
            }
        });
    }

    @Override
    public void register4Email(String email, String nickname, String pwd, String aginPwd) {
        if( StringUtil.isEmpty(email)){
            mRegisterView.showError("邮箱号不能为空");
        }
        if(StringUtil.isEmpty(nickname)){
            mRegisterView.showError("昵称不能为空");
            return ;
        }
        if( !validPwd(pwd, aginPwd) ){
            return ;
        }
        mUserModel.register4Email(email, nickname, pwd, ResourcesUtil.getDeviceId(), new RxSubscriber<JsonMessage<User>>(mRegisterView) {
            @Override
            public void onSuccess(JsonMessage<User> message) {
                clearUser();
                setCurrentUser(message.getObj());
                mRegisterView.onSuccess();
            }
        });

    }


    @Override
    public void resetPwd(String phone, String code, String pwd, String aginPwd) {
        if( !validPhone(phone) ){
            return ;
        }
        if(StringUtil.isEmpty(code)){
            mRegisterView.showError("验证码不能为空");
            return ;
        }
        if( !validPwd(pwd, aginPwd) ){
            return ;
        }
        mUserModel.resetPwd(phone, code, pwd, new RxSubscriber<JsonMessage>(mRegisterView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mRegisterView.onSuccess();
            }
        });


    }

    //验证码手机号
    private boolean validPhone(String phone){
        if( StringUtil.isEmpty(phone)){
            mRegisterView.showError("手机号码不能为空");
            return false;
        }
        if ( !ValidateUtils.isMobile(phone) ){
            mRegisterView.showError("手机号码格式不正确");
            return false;
        }
        return true;
    }

    //验证密码
    private boolean validPwd(String pwd, String aginPwd){
        if( StringUtil.isEmpty(pwd)){
            mRegisterView.showError("密码不能为空");
            return false;
        }
        if(StringUtil.isEmpty(aginPwd)){
            mRegisterView.showError("确认密码不能为空");
            return false;
        }
        if( !pwd.equals(aginPwd) ){
            mRegisterView.showError("输入的二次密码不一致");
            return false;
        }
        return true;
    }


}
