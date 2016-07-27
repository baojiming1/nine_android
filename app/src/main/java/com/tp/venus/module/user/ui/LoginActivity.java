/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：LoginActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/8/10:56.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.annotation.ProxyBean;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.module.common.bean.TimeCount;
import com.tp.venus.module.common.presenter.ICodePersenter;
import com.tp.venus.module.common.presenter.impl.CodePersenter;
import com.tp.venus.module.common.ui.view.ICodeView;
import com.tp.venus.module.home.ui.MainActivity;
import com.tp.venus.module.user.presenter.ILoginPresenter;
import com.tp.venus.module.user.presenter.impl.LoginPresenter;
import com.tp.venus.module.user.ui.view.ILoginView;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.LoginActivity</p>
 * <p>Description：</p>
 * <pre>
 *      登录页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/8/10:56
 */

public class LoginActivity extends BaseActivity implements ILoginView, ICodeView {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mAccount)
    EditText mAccount;
    @InjectView(R.id.mLoginBtn)
    RippleView mLoginBtn;
    @InjectView(R.id.passwordLogin)
    TextView passwordLogin;
    @InjectView(R.id.wechat_login)
    LinearLayout wechatLogin;
    @InjectView(R.id.mCode)
    EditText mCode;
    @InjectView(R.id.send_code)
    RippleView sendCode;
    @InjectView(R.id.microblog_login)
    LinearLayout microblogLogin;
    @InjectView(R.id.qq_login)
    LinearLayout qqLogin;


    private ILoginPresenter mILoginPresenter;
    private UMShareAPI mShareAPI;
    private LoginUMAuthListener mLoginUMAuthListener;
    private SHARE_MEDIA platform;

    private ICodePersenter mCodePersenter;
    private TimeCount mTimeCount;


    @Override
    protected void onDestroy() {
        mShareAPI = null;
        mLoginUMAuthListener = null;
        platform = null;
        mILoginPresenter = null;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_fast_login);
        ButterKnife.inject(this);
        if (mILoginPresenter == null) {
            mILoginPresenter = new LoginPresenter(this);
        }
        if (mCodePersenter == null) {
            mCodePersenter = new CodePersenter(this);
        }
        getToolbarBuilder(mToolbar).setTitle(R.string.fast_login).build();
        initView();

    }

    private void initView() {
        MobclickAgent.setDebugMode(AppConfig.IS_DEBUG);
        mShareAPI = UMShareAPI.get(LoginActivity.this);
        mLoginUMAuthListener = new LoginUMAuthListener();
        RxViewListener.clicks(mLoginBtn, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mILoginPresenter.fastLogin(mAccount.getText().toString(), mCode.getText().toString());
            }
        });
        RxViewListener.clicks(passwordLogin, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent intent = new Intent(LoginActivity.this, PasswordLoginActivity.class);
                startActivityForResult(intent, PasswordLoginActivity.LOGIN_SCCESS);
            }
        });
        RxViewListener.clicks(sendCode, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mCodePersenter.sendCode(mAccount.getText().toString());
            }
        });
        RxViewListener.clicks(wechatLogin, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                platform = SHARE_MEDIA.WEIXIN;
                mShareAPI.doOauthVerify(LoginActivity.this, platform, mLoginUMAuthListener);
            }
        });
        RxViewListener.clicks(microblogLogin, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                platform = SHARE_MEDIA.SINA;
                mShareAPI.doOauthVerify(LoginActivity.this, platform, mLoginUMAuthListener);
            }
        });
        RxViewListener.clicks(qqLogin, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                platform = SHARE_MEDIA.QQ;
                mShareAPI.doOauthVerify(LoginActivity.this, platform, mLoginUMAuthListener);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case PasswordLoginActivity.LOGIN_SCCESS:                    //密码登录已经成功
                closeCurrentActivity();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                mShareAPI.onActivityResult(requestCode, resultCode, data);
                mShareAPI.deleteOauth(LoginActivity.this, platform, mLoginUMAuthListener);
                break;
        }
    }

    private void goExecute() {
        String token = SharePreferencesUtils.getString(this, Status.TOKEN);
        if (StringUtil.isEmpty(token)) {
            Intent mIntent = getIntentBuilder(MainActivity.class).putInt(MainActivity.JUMP_FRAGMENT, MainActivity.home).build();
            startActivity(mIntent);
        }
        ProxyBean.clear();
    }


    @Override
    public void loginSuccess() {
        if (ProxyBean.isNotNullTask()) {
            ProxyBean.execute();
        } else {
            goExecute();
        }
        closeCurrentActivity();
    }

    @Override
    public void sendCodeSuccess() {
        if (mTimeCount == null) {
            mTimeCount = new TimeCount(sendCode);
        }
        mTimeCount.start();
    }

    /**
     * 第三方登录毁掉
     */
    private class LoginUMAuthListener implements UMAuthListener {

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            mILoginPresenter.login4other(map, share_media);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            Logger.e(throwable, "授权失败");
            ToastUtil.getInstance().show("授权失败");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            Logger.d("用户取消授权");
        }
    }
}
