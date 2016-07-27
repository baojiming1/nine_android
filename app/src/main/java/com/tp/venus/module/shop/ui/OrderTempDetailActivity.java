/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：OrderDetailActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/30/13:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.ui.fragment.OrderTempDetailFragment;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.OrderDetailActivity</p>
 * <p>Description：</p>
 * <pre>
 *      订单临时详情页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/30/13:23
 */

public class OrderTempDetailActivity extends BaseActivity {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    private View bottomLayout;
    private OrderTempDetailFragment mOrderDetailFragment;


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
        ArrayList<ShopCart> products = getIntent().getParcelableArrayListExtra(Status.Order.ORDER_KEY);
        boolean isBuyNow = getIntent().getBooleanExtra(Status.Order.BUY_NOW_KEY, false);
        mOrderDetailFragment = OrderTempDetailFragment.newInstance(products, isBuyNow);
        mOrderDetailFragment.setBottomLayout(bottomLayout);
        getToolbarBuilder(mToolbar).setTitle(R.string.order_detail).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, mOrderDetailFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case Status.Order.CHANGE_ADDRESS_CODE:
                Address item = data.getParcelableExtra(Status.PARCELABLE_KEY);
                mOrderDetailFragment.showAddress(item);
                break;
            case Status.Pay.TEMP_ORDER_DETAIL:
                String orderId = data.getStringExtra("orderId");
                Intent mIntent = getIntentBuilder(OrderDetailActivity.class).putString(Status.ID, orderId).build();
                startActivity(mIntent);
                finishActivity();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }

    }
}
