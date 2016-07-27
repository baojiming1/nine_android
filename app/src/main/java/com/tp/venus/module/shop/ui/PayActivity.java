/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：PayActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/30/16:25.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.PayResult;
import com.tp.venus.module.shop.presenter.IPayPresenter;
import com.tp.venus.module.shop.presenter.impl.PayPresenter;
import com.tp.venus.module.shop.ui.view.IPayView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.PayActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/30/16:25
 */

public class PayActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener, IPayView{

    public static String PRICE_KEY = "price";
    public static String prepayId;          //订单编号


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.price)
    TextView tPrice;
    @InjectView(R.id.wechat_checkbox)
    CheckBox wechatCheckbox;
    @InjectView(R.id.alibaba_checkbox)
    CheckBox alibabaCheckbox;
    @InjectView(R.id.pay)
    RippleView pay;
    @InjectView(R.id.wechat_pay)
    RelativeLayout wechatPay;
    @InjectView(R.id.alibaba_pay)
    RelativeLayout alibabaPay;

    private String orderId;
    private String price;
    //private int type;

    private IWXAPI msgApi;
    private int currentChecked = -1;
    private IPayPresenter mIPayPresenter;
    private int payType;                     //  2.微信支付  1.支付宝支付


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity_pay);
        ButterKnife.inject(this);
        initView();
    }

    @Override
    protected void closeCurrentActivity() {
        Intent intent = new Intent();
        intent.putExtra("orderId", orderId);
        setResult(Status.Pay.TEMP_ORDER_DETAIL, intent);
        super.closeCurrentActivity();
    }

    private void initView() {
        getToolbarBuilder(mToolbar).setTitle("收银台").build();
        orderId = getIntent().getStringExtra(Status.ID);
        price = getIntent().getStringExtra(PRICE_KEY);
       //
        // type = getIntent().getIntExtra(Status.TYPE_FIELD, Status.Pay.ORDER_DETAIL);
        tPrice.setText("￥" + price);
        wechatPay.setOnClickListener(this);
        alibabaPay.setOnClickListener(this);
        wechatCheckbox.setOnCheckedChangeListener(this);
        alibabaCheckbox.setOnCheckedChangeListener(this);
        pay.setOnClickListener(this);
        alibabaCheckbox.setChecked(true);
        if (mIPayPresenter == null) {
            mIPayPresenter = getPresenter(new PayPresenter(this));
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int current = buttonView.getId();
        if (current == currentChecked) {
            return;
        } else {
            if (currentChecked != -1) {               //非第一次
                CheckBox mCheckBox = (CheckBox) findViewById(currentChecked);
                mCheckBox.setChecked(false);
            }
            currentChecked = buttonView.getId();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pay:
                payType = Status.Pay.ALIBABA_PAY;
                if (wechatCheckbox.getId() == currentChecked) {
                    payType = Status.Pay.WECHAT_PAY;
                }
                mIPayPresenter.pay(orderId, payType);
                break;
            case R.id.wechat_pay:
                wechatCheckbox.setChecked(true);
                break;
            case R.id.alibaba_pay:
                alibabaCheckbox.setChecked(true);
                break;
        }

    }

    @Override
    public void onSuccess(Map<String, String> sign) {
        switch (payType) {
            case Status.Pay.ALIBABA_PAY:
                alibabaPay(sign);
                break;
            case Status.Pay.WECHAT_PAY:
                wechatPay(sign);
                break;
        }
    }

    @Override
    public void paySuccess() {
        startActivity(PayResultActivity.class);
    }

    /**
     * 微信支付
     *
     * @param sign
     */
    public void wechatPay(Map<String, String> sign) {
        String appid = sign.get("appid");
        if (msgApi == null) {
            //微信回调在这个类中：WXPayEntryActivity
            msgApi = WXAPIFactory.createWXAPI(this, Status.UMeng.WEIXIN_AppID);
            msgApi.registerApp(Status.UMeng.WEIXIN_AppID);
        }
        PayReq request = new PayReq();
        request.appId = appid;
        request.partnerId = sign.get("partnerid");
        this.prepayId = request.partnerId;
        request.prepayId = sign.get("prepayid");
        request.packageValue = sign.get("package_wexin");
        request.nonceStr = sign.get("noncestr");
        request.timeStamp = sign.get("timestamp");
        request.sign = sign.get("sign");
        msgApi.sendReq(request);
    }

    /**
     * 支付宝支付
     *
     * @param sign
     */
    public void alibabaPay(Map<String, String> sign) {
        if (CollectionUtils.isMapEmpty(sign)) {
            ToastUtil.getInstance().show("支付失败");
            return;
        }
        final String result = sign.get("result");
        if (StringUtil.isEmpty(result)) {
            ToastUtil.getInstance().show("支付失败");
            return;
        }
        this.prepayId = sign.get("prepayid");
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
                // 调用支付接口，获取支付结果

                String str = alipay.pay(result, true);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = str;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        mIPayPresenter.paySuccess(prepayId);
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            ToastUtil.getInstance().show("支付结果确认中");
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ToastUtil.getInstance().show("支付失败");
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
}
