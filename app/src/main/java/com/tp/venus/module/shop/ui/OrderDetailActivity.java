/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：OrderDetailActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/30/13:23.</p>
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
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.ui.fragment.OrderDetailFragment;
import com.tp.venus.util.StringUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.OrderDetailActivity</p>
 * <p>Description：</p>
 * <pre>
 *   订单详情页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/30/13:23
 */

public class OrderDetailActivity extends BaseActivity {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    private View bottomLayout;
    private OrderDetailFragment mOrderDetailFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_toolbar_fragment_viewstub);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        if( bottomLayout == null){
            ViewStub mViewStub = (ViewStub) findViewById(R.id.bottom_viewstub);
            mViewStub.setLayoutResource(R.layout.shop_order_detail_bottom_menu);
            bottomLayout = mViewStub.inflate();
        }
        String orderId = getIntent().getStringExtra(Status.ID);
        if(StringUtil.isEmpty(orderId)){
            return ;
        }
        mOrderDetailFragment = OrderDetailFragment.newInstance(orderId);
        mOrderDetailFragment.setBottomLayout(bottomLayout);
        getToolbarBuilder(mToolbar).setTitle(R.string.order_detail).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mOrderDetailFragment).commit();
    }

}
