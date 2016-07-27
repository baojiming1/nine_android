/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：AddressManagerActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/5/14:55.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.ui.fragment.AddressManagerFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.AddressManagerActivity</p>
 * <p>Description：</p>
 * <pre>
 *   收货地址管理
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/4/5/14:55
 */

public class AddressManagerActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity_address_manager);
        ButterKnife.inject(this);
        initView();
    }

    private void initView(){
        getToolbarBuilder(mToolbar).setTitle(R.string.address_manager, R.string.add).addRightOnClickListener(new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(AddressEditActivity.class);
            }
        }).build();
        int change_address = getIntent().getIntExtra(Status.Order.CHANGE_ADDRESS_KEY, 0);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, AddressManagerFragment.newInstance(change_address)).commit();
    }

}
