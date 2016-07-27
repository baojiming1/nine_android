/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.listener</p>
 * <p>File：OrderBottonOnClick.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/6/13:39.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.listener;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.tp.venus.R;
import com.tp.venus.base.builder.DialogBuilder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.presenter.IOrderBottonPresenter;
import com.tp.venus.module.shop.ui.PayActivity;
import com.tp.venus.util.PermissionUtil;

/**<p>Class：com.tp.venus.module.shop.listener.OrderBottonOnClick</p>
 * <p>Description：</p>
 * <pre>
 *订单状态按钮点击事件
 * </pre>
 * @author 鲍建明
 * @date 2016/5/6/13:39
 * @version 1.0.0
 */

public class OrderBottonOnClick implements RxViewListener.Action {

    public static final int CALL_PHONE_REQUEST_CODE = 10777;


    private int bottonId;
    private Context mContext;
    private Order order;
    private IOrderBottonPresenter mIOrderBottonPresenter;
    private int position;

    public OrderBottonOnClick(Context mContext, int bottonId, Order order, IOrderBottonPresenter mIOrderBottonPresenter, int position) {
        this.bottonId = bottonId;
        this.mContext = mContext;
        this.order = order;
        this.position = position;
        this.mIOrderBottonPresenter = mIOrderBottonPresenter;
    }

    @Override
    public void call(Object o) {
        switch (bottonId) {
            case Status.Order.STATUS_WAIT_PAY:                      //结算
                Intent mIntent = IntentBuilder.create(mContext, PayActivity.class).putString(Status.ID, order.getId())
                        .putString(PayActivity.PRICE_KEY, order.getActuallyPaidSum())
                        .putInt(Status.TYPE_FIELD, Status.Pay.ORDER_DETAIL)
                        .build();
                ((Activity)mContext).startActivityForResult(mIntent, Status.Pay.ORDER_DETAIL);
                break;
            case Status.Order.STATUS_SEND_GOODS:                    //确认收货
                DialogBuilder.create(mContext).createSimpleDialog("您是否收到该订单商品?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mIOrderBottonPresenter.updateOrderStatus(order.getId(), Status.Order.STATUS_FINISH, dialog, position);
                    }
                });
                break;
            case Status.Order.STATUS_CUSTOMER_SERVICE:              //申请售后
                createCustomerDialog(order.getId(), position);
                break;
            case Status.Order.STATUS_REFUND:                        //申请退款
                DialogBuilder.create(mContext).createSimpleDialog("我们会在24小时之内为您处理，您还要继续申请退款吗?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mIOrderBottonPresenter.updateOrderStatus(order.getId(), Status.Order.STATUS_REFUND, dialog, position);
                    }
                });
                break;
            case Status.Order.CALL_CUSTOMER:                       //联系客服
                //用intent启动拨打电话
                PermissionUtil.getInstance().callPhone(mContext,  R.string.customer_phone);
                break;
            case Status.Order.STATUS_CLOSE :                    //取消订单
                DialogBuilder.create(mContext).createSimpleDialog("您确定要取消该订单吗?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mIOrderBottonPresenter.updateOrderStatus(order.getId(), Status.Order.STATUS_CLOSE, dialog, position);
                    }
                });
                break;

        }
    }

    /**
     * 售后输入对话框
     * @param orderId
     */
    private void createCustomerDialog(final String orderId, final int position){
        final DialogPlus mDialogPlus = DialogBuilder.create(mContext).createDialogPlus(R.layout.dialog_input_box, Gravity.CENTER);
        TextView title = (TextView) mDialogPlus.findViewById(R.id.title);
        title.setText("申请售后服务");
        final EditText mEditText = (EditText) mDialogPlus.findViewById(R.id.message);
        final EditText phone = (EditText) mDialogPlus.findViewById(R.id.phone);
        mEditText.setHint("请输入您要退货、换货以及售后的备注说明");
        Button confirm = (Button) mDialogPlus.findViewById(R.id.confirm);
        RxViewListener.clicks(confirm, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIOrderBottonPresenter.save_aftersale_service_info(orderId, phone.getText().toString(),
                        mEditText.getText().toString(), mDialogPlus, position);
            }
        });
        Button cannel = (Button) mDialogPlus.findViewById(R.id.cannel);
        RxViewListener.clicks(cannel, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mDialogPlus.dismiss();
            }
        });
        mDialogPlus.show();

    }




}