/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui.fragment</p>
 * <p>File：MobileForgetFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/24/16:19.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.tp.venus.R;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.common.bean.TimeCount;
import com.tp.venus.module.common.presenter.ICodePersenter;
import com.tp.venus.module.common.presenter.impl.CodePersenter;
import com.tp.venus.module.common.ui.view.ICodeView;
import com.tp.venus.module.user.presenter.IRegisterPresenter;
import com.tp.venus.module.user.presenter.impl.RegisterPresenter;
import com.tp.venus.module.user.ui.ForgetPasswdActivity;
import com.tp.venus.module.user.ui.view.IRegisterView;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.fragment.MobileForgetFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/2/24/16:19
 */

public class MobileForgetFragment extends BaseFragment implements IRegisterView, ICodeView {

    private int type;
    private String mobile;

    public static MobileForgetFragment newInstance(String mobile, int type){
        MobileForgetFragment mMobileForgetFragment = new MobileForgetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Status.TYPE_FIELD, type);
        bundle.putString(ForgetPasswdActivity.MOBILE, mobile);
        mMobileForgetFragment.setArguments(bundle);
        return mMobileForgetFragment;
    }


    @InjectView(R.id.mPhone)
    EditText mPhone;
    @InjectView(R.id.mCode)
    EditText mCode;
    @InjectView(R.id.mPassword)
    EditText mPassword;
    @InjectView(R.id.mPasswordAgain)
    EditText mPasswordAgain;
    @InjectView(R.id.mForgetPasswdBtn)
    RippleView mForgetPasswdBtn;
    @InjectView(R.id.send_code)
    RippleView sendCode;

    private IRegisterPresenter mIRegisterPresenter;
    private TimeCount mTimeCount;
    private ICodePersenter mCodePersenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_activity_forgetpasswd, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mIRegisterPresenter == null) {
            mIRegisterPresenter = new RegisterPresenter(this);
        }
        if( mCodePersenter == null){
            mCodePersenter = new CodePersenter(this);
        }
        initView();
    }

    private void initView() {
        RxViewListener.clicks(mForgetPasswdBtn, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIRegisterPresenter.resetPwd(mPhone.getText().toString(),
                        mCode.getText().toString(),mPassword.getText().toString(),
                        mPasswordAgain.getText().toString()
                );
            }
        });
        RxViewListener.clicks(sendCode, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mCodePersenter.sendCode(mPhone.getText().toString());
            }
        });
        type = getArguments().getInt(Status.TYPE_FIELD, 0);
        mobile = getArguments().getString(ForgetPasswdActivity.MOBILE);
        if( type == 0 ){
            mForgetPasswdBtn.setText("立即找回");
        } else {
            mForgetPasswdBtn.setText("立即设置");
        }
        if(StringUtil.isNotEmpty(mobile)){
            mPhone.setText(mobile + "");
            mPhone.setFocusable(false);
            mPhone.setEnabled(false);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onSuccess() {
        ToastUtil.getInstance().show("设置密码成功");
        finishActivity();
    }

    @Override
    public void sendCodeSuccess() {
        if( mTimeCount == null){
            mTimeCount = new TimeCount(sendCode);
        }
        mTimeCount.start();
    }

}
