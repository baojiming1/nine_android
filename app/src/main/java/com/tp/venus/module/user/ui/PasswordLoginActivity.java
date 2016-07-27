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
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.annotation.ProxyBean;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.home.ui.MainActivity;
import com.tp.venus.module.user.presenter.ILoginPresenter;
import com.tp.venus.module.user.presenter.impl.LoginPresenter;
import com.tp.venus.module.user.ui.view.ILoginView;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StringUtil;
import com.tp.venus.widget.RippleView;

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

public class PasswordLoginActivity extends BaseActivity implements ILoginView {

    public static final int LOGIN_SCCESS = 10012;

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mAccount)
    EditText mAccount;
    @InjectView(R.id.mPassword)
    EditText mPassword;
    @InjectView(R.id.mLoginBtn)
    RippleView mLoginBtn;
    @InjectView(R.id.mForget)
    TextView mForget;
    @InjectView(R.id.mToolbarRightTitle)
    TextView mToolbarRightTitle;


    private ILoginPresenter mILoginPresenter;


    @Override
    protected void onDestroy() {
        mILoginPresenter = null;
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_pwd_login);
        ButterKnife.inject(this);
        if (mILoginPresenter == null) {
            mILoginPresenter = new LoginPresenter(this);
        }
        getToolbarBuilder(mToolbar).setTitle(R.string.password_login).build();
        initView();

    }

    private void initView() {
        RxViewListener.clicks(mLoginBtn, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mILoginPresenter.login(mAccount.getText().toString(), mPassword.getText().toString());
            }
        });
        RxViewListener.clicks(mToolbarRightTitle, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(RegisterActivity.class);
            }
        });
        RxViewListener.clicks(mForget, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(ForgetPasswdActivity.class);
            }
        });
    }


    @Override
    public void loginSuccess() {
        setResult(LOGIN_SCCESS,  new Intent());
        if ( ProxyBean.isNotNullTask() ) {
            ProxyBean.execute();
        } else {
            goExecute();
        }
        closeCurrentActivity();
    }


    private void goExecute() {
        String token = SharePreferencesUtils.getString(this, Status.TOKEN);
        if (StringUtil.isEmpty(token)) {
            Intent mIntent = getIntentBuilder(MainActivity.class).putInt(MainActivity.JUMP_FRAGMENT, MainActivity.home).build();
            startActivity(mIntent);
        }
        ProxyBean.clear();
    }
}
