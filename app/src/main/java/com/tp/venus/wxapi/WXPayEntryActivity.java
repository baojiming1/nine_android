/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.wxapi</p>
 * <p>File：WXPayEntryActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/16/16:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.presenter.IPayPresenter;
import com.tp.venus.module.shop.presenter.impl.PayPresenter;
import com.tp.venus.module.shop.ui.PayActivity;
import com.tp.venus.module.shop.ui.PayResultActivity;
import com.tp.venus.module.shop.ui.view.IPayView;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * <p>Class：com.tp.venus.wxapi.WXPayEntryActivity</p>
 * <p>Description：</p>
 * <pre>
 *      微信支付回调
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/5/16/16:22
 */

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler,IPayView {

    private IWXAPI api;
    private IPayPresenter mIPayPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.shop_activity_pay_result);
        ButterKnife.inject(this);
      // getToolbarBuilder(mToolbar).setTitle("支付结果").build();
        if( mIPayPresenter == null){
            mIPayPresenter = getPresenter(new PayPresenter(this));
        }
        api = WXAPIFactory.createWXAPI(this, Status.UMeng.WEIXIN_AppID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        //ToastUtil.getInstance().show(baseReq.toString());
    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
              //  result = R.string.errcode_success;
                mIPayPresenter.paySuccess(PayActivity.prepayId);
                return;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.errcode_cancel;
                finishActivity();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.errcode_deny;
                finishActivity();
                break;
            default:
                result = R.string.errcode_unknown;
                finishActivity();
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(Map<String, String> sign) {

    }

    @Override
    public void paySuccess() {
        startActivity(PayResultActivity.class);
        finishActivity();
    }
}
