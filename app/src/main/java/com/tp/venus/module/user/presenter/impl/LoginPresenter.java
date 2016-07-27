/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：LoginPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/21/18:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.ILoginPresenter;
import com.tp.venus.module.user.ui.view.ILoginView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ValidateUtils;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**<p>Class：com.tp.venus.module.user.presenter.impl.LoginPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/21/18:23
 * @version 1.0.0
 */

public class LoginPresenter extends BasePresenter implements ILoginPresenter{

    private IUserModel mIUserModel;
    private ILoginView mILoginView;

    public LoginPresenter(ILoginView mILoginView) {
        super(mILoginView);
        this.mILoginView = mILoginView;
        this.mIUserModel = new UserModel();
    }

    @Override
    public void login( String account,  String pwd) {
        int type = Status.User.MOBILE;
        if( !ValidateUtils.isMobile(account) ){  //手机号登陆
            mILoginView.showError("请输入您的手机号进行登录");
            return ;
        }
        clearUser();
        mIUserModel.login(account, pwd, type, new RxSubscriber<JsonMessage<User>>(mILoginView) {
            @Override
            public void onSuccess(JsonMessage<User> message) {
                clearUser();
                setCurrentUser(message.getObj());
                mILoginView.loginSuccess();
            }
        });



    }

    @Override
    public void fastLogin(String account, String code) {
        if( !ValidateUtils.isMobile(account) ){  //手机号登陆
            mILoginView.showError("请输入您的手机号进行登录");
            return ;
        }
        if( StringUtil.isEmpty(code)){
            mILoginView.showError("验证码为空");
            return ;
        }
        mIUserModel.fastLogin(account, code, new ProgressSubscriber<JsonMessage<User>>(mILoginView) {
            @Override
            public void onSuccess(JsonMessage<User> message) {
                clearUser();
                setCurrentUser(message.getObj());
                mILoginView.loginSuccess();
            }
        });


    }

    @Override
    public void login4other(final Map<String, String> map, SHARE_MEDIA mSHARE_MEDIA) {
        if(CollectionUtils.isMapEmpty(map)){
            return ;
        }
        //1.QQ登录 2.微信登录 3.微博
        String openid = "";
        Short souce = 1;
        switch (mSHARE_MEDIA){
            case QQ:
                openid = map.get("openid");
                souce = 1;
                break;
            case WEIXIN:
                openid = map.get("openid");
                souce = 2;
                break;
            case SINA:
                openid = map.get("uid");
                souce = 3;
                break;
        }
        if(StringUtil.isEmpty(openid)){
            mILoginView.showError("授权失败");
            return ;
        }
        mIUserModel.login4other(openid, souce, new RxSubscriber<JsonMessage<User>>(mILoginView) {
            @Override
            public void onSuccess(JsonMessage message) {
                User user = (User) message.getObj();
                clearUser();
                setCurrentUser(user);
                mILoginView.loginSuccess();
            }
        });
    }


}
