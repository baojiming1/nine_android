/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：ProductActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/10:25.</p>
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
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.ui.fragment.ProductFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.ProductActivity</p>
 * <p>Description：</p>
 * <pre>
 *     产品 详情页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/28/10:25
 */

public class ProductActivity extends BaseActivity  {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    private View bottom;


    public String productId;
    public ToolbarBuilder mToolbarBuilder;
    private ProductFragment mProductFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_toolbar_fragment_viewstub);
        ButterKnife.inject(this);
        initView();
        initFoot();
    }


    private void initView() {
        productId = getIntent().getStringExtra(Status.Product.ID);
        mToolbarBuilder = getToolbarBuilder(mToolbar).setTitle("").build();
        mProductFragment = ProductFragment.newInstance(productId);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mProductFragment).commit();
    }

    private void initFoot(){
        if( bottom == null){
            ViewStub mViewStub = (ViewStub) findViewById(R.id.bottom_viewstub);
            mViewStub.setLayoutResource(R.layout.shop_product_bottom_menu);
            bottom = mViewStub.inflate();
        }
        mProductFragment.setBotoomLayout(bottom, mToolbarBuilder.getMidTextView());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mToolbarBuilder = null;
     /*   System.exit(0);*/
    }
}
