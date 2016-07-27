/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：PayResultActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/17/16:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.PayResultActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/5/17/16:36
 */

public class PayResultActivity extends BaseActivity {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity_pay_result);
        ButterKnife.inject(this);
        getToolbarBuilder(mToolbar).setTitle("支付结果").build();
    }


    @Override
    protected void closeCurrentActivity() {
        Intent intent = new Intent();
        setResult(Status.Pay.PAY_RESULT, intent);
        super.closeCurrentActivity();
    }
}
