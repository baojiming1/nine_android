/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：UpdatePwdActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/12:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.user.presenter.IUpdatePwdPresenter;
import com.tp.venus.module.user.presenter.impl.UpdatePwdPresenter;
import com.tp.venus.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.UpdatePwdActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/1/27/12:23
 */

public class UpdatePwdActivity extends BaseActivity implements ICommonView {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.oldPwd)
    EditText oldPwd;
    @InjectView(R.id.newPwd)
    EditText newPwd;
    @InjectView(R.id.againPwd)
    EditText againPwd;

    private IUpdatePwdPresenter mIUpdatePwdPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_updatepwd);
        ButterKnife.inject(this);
        if(  mIUpdatePwdPresenter == null){
            mIUpdatePwdPresenter = getPresenter(new UpdatePwdPresenter(this));
        }
        initView();
    }

    private void initView(){
        getToolbarBuilder(mToolbar).setTitle(R.string.update_pwd, R.string.update).addRightOnClickListener(new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIUpdatePwdPresenter.updatePwd(oldPwd.getText().toString(), newPwd.getText().toString(),
                        againPwd.getText().toString());
            }
        }).build();
    }

    @Override
    public void onSuccess() {
        ToastUtil.getInstance().show("修改密码成功");
        finishActivity();
    }

    @Override
    public void onError() {
        ToastUtil.getInstance().show("修改密码失败");
    }
}
