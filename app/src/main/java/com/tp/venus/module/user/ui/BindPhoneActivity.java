/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：BindPhoneActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/8/17:29.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.module.common.bean.TimeCount;
import com.tp.venus.module.common.presenter.ICodePersenter;
import com.tp.venus.module.common.presenter.impl.CodePersenter;
import com.tp.venus.module.common.ui.view.ICodeView;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.user.presenter.IBindPhonePresenter;
import com.tp.venus.module.user.presenter.impl.BindPhonePresenter;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.BindPhoneActivity</p>
 * <p>Description：</p>
 * <pre>
 *    绑定手机号页面
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/8/17:29
 */

public class BindPhoneActivity extends BaseActivity implements ICommonView, ICodeView {

    public static final String DATA_KEY = "data";
    public static final String SOURCE_KEY = "source";

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mPhone)
    EditText mPhone;
    @InjectView(R.id.mCode)
    EditText mCode;
    @InjectView(R.id.mBindBtn)
    RippleView mBindBtn;
    @InjectView(R.id.send_code)
    RippleView sendCode;


    private IBindPhonePresenter mBindPhonePresenter;
    private ICodePersenter mCodePersenter;
    private TimeCount mTimeCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_bindphone);
        ButterKnife.inject(this);
        getToolbarBuilder(mToolbar).setTitle(R.string.bindPhone).build();
        init();
    }

    private void init() {
        if (mBindPhonePresenter == null) {
            mBindPhonePresenter = new BindPhonePresenter(this);
        }
        if( mCodePersenter == null){
            mCodePersenter = new CodePersenter(this);
        }

        RxViewListener.clicks(sendCode, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mCodePersenter.sendCode(mPhone.getText().toString() );
            }
        });
        RxViewListener.clicks(mBindBtn, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mBindPhonePresenter.bindPhone(mPhone.getText().toString(), mCode.getText().toString());
            }
        });
    }



    @Override
    public void onSuccess() {
        ToastUtil.getInstance().show("绑定成功");
        finishActivity();
    }

    @Override
    public void onError() {

    }

    @Override
    public void sendCodeSuccess() {
        if( mTimeCount == null){
            mTimeCount = new TimeCount(sendCode);
        }
        mTimeCount.start();
    }
}
