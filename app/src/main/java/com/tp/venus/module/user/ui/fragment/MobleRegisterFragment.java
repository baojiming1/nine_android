/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui.fragment</p>
 * <p>File：MobleRegisterFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/24/14:43.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.annotation.ProxyBean;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Url;
import com.tp.venus.module.common.bean.TimeCount;
import com.tp.venus.module.common.presenter.ICodePersenter;
import com.tp.venus.module.common.presenter.impl.CodePersenter;
import com.tp.venus.module.common.ui.WebViewActivity;
import com.tp.venus.module.common.ui.view.ICodeView;
import com.tp.venus.module.home.ui.MainActivity;
import com.tp.venus.module.user.presenter.IRegisterPresenter;
import com.tp.venus.module.user.presenter.impl.RegisterPresenter;
import com.tp.venus.module.user.ui.view.IRegisterView;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.fragment.MobleRegisterFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/2/24/14:43
 */

public class MobleRegisterFragment extends BaseFragment implements IRegisterView, ICodeView {


    public static MobleRegisterFragment newInstance() {
        return new MobleRegisterFragment();
    }


    @InjectView(R.id.mPhone)
    EditText mPhone;
    @InjectView(R.id.mCode)
    EditText mCode;
    @InjectView(R.id.mNickname)
    EditText mNickname;
    @InjectView(R.id.mPassword)
    EditText mPassword;
    @InjectView(R.id.mPasswordAgain)
    EditText mPasswordAgain;
    @InjectView(R.id.mRegister)
    RippleView mRegister;
    @InjectView(R.id.protocolBox)
    CheckBox protocolBox;
    @InjectView(R.id.protocol)
    TextView protocol;
    @InjectView(R.id.send_code)
    RippleView sendCode;

    private IRegisterPresenter mIRegisterPresenter;
    private TimeCount mTimeCount;
    private ICodePersenter mCodePersenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_mobile_register, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mIRegisterPresenter == null) {
            mIRegisterPresenter = new RegisterPresenter(this);
        }
        if (mCodePersenter == null) {
            mCodePersenter = new CodePersenter(this);
        }
        initView();
    }

    private void initView() {
        mRegister.setText(R.string.register);
        RxViewListener.clicks(mRegister, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                if (!protocolBox.isChecked()) {
                    ToastUtil.getInstance().show("请先确认用户协议");
                    return;
                }
                mIRegisterPresenter.register4phone(mPhone.getText().toString(), mCode.getText().toString(),
                        mNickname.getText().toString(), mPassword.getText().toString(), mPasswordAgain.getText().toString());
            }
        });
        RxViewListener.clicks(sendCode, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mCodePersenter.sendCode(mPhone.getText().toString());
            }
        });
        RxViewListener.clicks(protocol, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = getIntentBuilder(WebViewActivity.class).putString(WebViewActivity.URL_KEY, Url.PROTOCOL)
                        .putString(WebViewActivity.TITLE_KEY, "用户协议")
                        .build();
                startActivity(mIntent);
            }
        });

    }


    @Override
    public void onSuccess() {
        if (ProxyBean.isNotNullTask()) {
            ProxyBean.execute();
        }
        startActivity(MainActivity.class);
        finishActivity();
    }

    @Override
    public void sendCodeSuccess() {
        if (mTimeCount == null) {
            mTimeCount = new TimeCount(sendCode);
        }
        mTimeCount.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
