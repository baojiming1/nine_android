/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：RegisterActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/8/18:04.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.module.user.ui.fragment.MobleRegisterFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.RegisterActivity</p>
 * <p>Description：</p>
 * <pre>
 *      注册页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/8/18:04
 */

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    public Toolbar mToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_register);
        ButterKnife.inject(this);
        getToolbarBuilder(mToolbar).setTitle(R.string.register).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, MobleRegisterFragment.newInstance()).commit();
    }

}
