/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：ShopCartActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/29/17:51.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.module.shop.ui.fragment.ShopCartFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.ShopCartActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/29/17:51
 */

public class ShopCartActivity extends BaseActivity {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    private View bottomLayout;
    private ShopCartFragment mShopCartFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_toolbar_fragment_viewstub);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        getToolbarBuilder(mToolbar).setTitle(R.string.shopCart).build();
        if( bottomLayout == null){
            ViewStub mViewStub = (ViewStub) findViewById(R.id.bottom_viewstub);
            mViewStub.setLayoutResource(R.layout.shop_cart_bottom_menu);
            bottomLayout = mViewStub.inflate();
        }
        mShopCartFragment = ShopCartFragment.newInstance();
        mShopCartFragment.setBottomWidget(bottomLayout);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mShopCartFragment).commit();
    }




}
