/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：ClassesProductActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/8/15:06.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Classes;
import com.tp.venus.module.shop.ui.fragment.ClassesProductFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.ClassesProductActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/4/8/15:06
 */

public class ClassesProductActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    private Classes item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_toolbar_fragment);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        item = getIntent().getParcelableExtra(Status.PARCELABLE_KEY);
        if( item == null){
            return ;
        }
        getToolbarBuilder(mToolbar).setTitle(item.getClassName()).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, ClassesProductFragment.newInstance(item.getId())).commit();
    }
}
