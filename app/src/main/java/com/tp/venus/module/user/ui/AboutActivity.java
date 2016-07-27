/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：AboutActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/14/18:28.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.util.ResourcesUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.AboutActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/14/18:28
 */

public class AboutActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.version)
    TextView version;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_about);
        ButterKnife.inject(this);
        initView();

    }

    private void initView() {
        getToolbarBuilder(mToolbar).setTitle(R.string.about).build();
        String versionName = ResourcesUtil.getVersionName(this);
        version.setText(versionName);
    }

}
