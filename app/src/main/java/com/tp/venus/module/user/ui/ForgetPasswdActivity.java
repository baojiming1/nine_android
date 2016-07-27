/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：ForgetPasswdActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/8/17:41.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;
import com.tp.venus.module.user.ui.fragment.MobileForgetFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.ForgetPasswdActivity</p>
 * <p>Description：</p>
 * <pre>
 *    忘记密码页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/8/17:41
 */

public class ForgetPasswdActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    public Toolbar mToolbar;

    private int type;
    public static final String MOBILE = "mobile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_register);
        type = getIntent().getIntExtra(Status.TYPE_FIELD, 0);
        String mobile = getIntent().getStringExtra(MOBILE);
        ButterKnife.inject(this);
        if( type == 0){
            getToolbarBuilder(mToolbar).setTitle(R.string.forgetPwd).build();
        } else {
            getToolbarBuilder(mToolbar).setTitle(R.string.set_pwd).build();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, MobileForgetFragment.newInstance(mobile, type)).commit();

    }
}
