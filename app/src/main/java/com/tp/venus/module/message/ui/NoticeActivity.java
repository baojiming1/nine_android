/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.ui</p>
 * <p>File：NoticeActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/1/16:16.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.module.message.ui.fragment.NoticeFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.message.ui.NoticeActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/1/16:16
 */

public class NoticeActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_toolbar_fragment);
        ButterKnife.inject(this);
        initView();
    }

    private void initView(){
        getToolbarBuilder(mToolbar).setTitle(R.string.system).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, NoticeFragment.newInstance()).commit();
    }
}
