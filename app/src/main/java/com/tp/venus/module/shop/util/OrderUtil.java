/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.util</p>
 * <p>File：OrderUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/10:28.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.util;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.tp.venus.R;
import com.tp.venus.config.Status;

import java.util.ArrayList;
import java.util.List;

/**<p>Class：com.tp.venus.module.shop.util.OrderUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/10:28
 * @version 1.0.0
 */

public class OrderUtil {



    private static OrderUtil instance = null;
    public static synchronized  OrderUtil getInstance(){
        if(instance == null){
            instance = new OrderUtil();
        }
        return instance;
    }
    private OrderUtil(){}

    /**
     * 订单状态 -1.未支付(订单创建);2.用户支付完成等待确认;3.支付成功(待发货);4.已发货(确认收货);5.完成订单（已收货）;6.申请售后;7.申请退款;9.订单关闭
     * @param orderStatus
     * @return
     */
    public OrderText parseOrderStatus(int orderStatus){
        OrderText mOrderText = new OrderText("待付款", R.color.themeGray);
        switch (orderStatus){
            case Status.Order.STATUS_WAIT_PAY:
                mOrderText.color = R.color.red;
                break;
            case Status.Order.STATUS_WAIT_CONFIRM :
                mOrderText.str = "支付完成等待确认";
                break;
            case Status.Order.STATUS_PAY_SUCCESS :
                mOrderText.str = "待发货";
                break;
            case Status.Order.STATUS_SEND_GOODS:
                mOrderText.str = "待收货";
                break;
            case Status.Order.STATUS_FINISH :
                mOrderText.str = "已完成";
                break;
            case Status.Order.STATUS_CUSTOMER_SERVICE :
                mOrderText.str = "售后处理中";
                break;
            case Status.Order.STATUS_REFUND :
                mOrderText.str = "退款处理中";
                break;
            case Status.Order.STATUS_CLOSE :
                mOrderText.str = "已关闭";
                break;
        }
        return mOrderText;
    }

    /**
     * 解析需要多少个按钮
     * @param orderStatus
     * @return
     */
    public List<OrderBotton> parseOrderBotton(int orderStatus){
        List<OrderBotton> bottons = new ArrayList<>();
        switch (orderStatus){
            case Status.Order.STATUS_WAIT_PAY:
                bottons.add(new OrderBotton(Status.Order.STATUS_WAIT_PAY, R.string.statement, R.drawable.btn_bg_red_shape));
                bottons.add(new OrderBotton(Status.Order.STATUS_CLOSE, R.string.cancel_order, R.drawable.btn_bg_grey_shape));
                break;
            case Status.Order.STATUS_WAIT_CONFIRM :
                bottons.add(new OrderBotton(Status.Order.STATUS_REFUND, R.string.apply_refund, R.drawable.btn_bg_grey_shape));
                bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));
                break;
            case Status.Order.STATUS_PAY_SUCCESS :
                bottons.add(new OrderBotton(Status.Order.STATUS_REFUND, R.string.apply_refund, R.drawable.btn_bg_grey_shape));
                bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));
                break;
            case Status.Order.STATUS_SEND_GOODS:
                bottons.add(new OrderBotton(Status.Order.STATUS_SEND_GOODS, R.string.confirm_receipt, R.drawable.btn_bg_red_shape));
                bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));
                break;
            case Status.Order.STATUS_FINISH :
                bottons.add(new OrderBotton(Status.Order.STATUS_CUSTOMER_SERVICE, R.string.apply_after_sales, R.drawable.btn_bg_lightgreen_shape));
                break;
            case Status.Order.STATUS_CUSTOMER_SERVICE :
            case Status.Order.STATUS_REFUND :
                bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));
                break;
            case Status.Order.STATUS_CLOSE :
                bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));
                break;
        }
        return bottons;
    }


    /**
     * 解析需要多少个按钮(详情页)
     * @param orderStatus
     * @return
     */
    public OrderBotton parseDetailOrderBotton(int orderStatus){
        OrderBotton botton = null;
        switch (orderStatus){
            case Status.Order.STATUS_WAIT_PAY:
                botton = new OrderBotton(Status.Order.STATUS_WAIT_PAY, R.string.statement, R.drawable.statement);
                break;
            case Status.Order.STATUS_WAIT_CONFIRM :
                botton = new OrderBotton(Status.Order.STATUS_REFUND, R.string.apply_refund, R.drawable.apply_refund);
               /* bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));*/
                break;
            case Status.Order.STATUS_PAY_SUCCESS :
                botton = new OrderBotton(Status.Order.STATUS_REFUND, R.string.apply_refund, R.drawable.apply_refund);
               /* bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));*/
                break;
            case Status.Order.STATUS_SEND_GOODS:
                botton = new OrderBotton(Status.Order.STATUS_SEND_GOODS, R.string.confirm_receipt, R.drawable.confirm_receipt);
               /* bottons.add(new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.btn_bg_lightgreen_shape));*/
                break;
            case Status.Order.STATUS_FINISH :
                botton = new OrderBotton(Status.Order.STATUS_CUSTOMER_SERVICE, R.string.apply_after_sales, R.drawable.apply_after_sales);
                break;
            case Status.Order.STATUS_CUSTOMER_SERVICE :
            case Status.Order.STATUS_REFUND :
                botton = new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.call_cutomer);
                break;
            case Status.Order.STATUS_CLOSE :
            default:
                botton = new OrderBotton(Status.Order.CALL_CUSTOMER, R.string.call_cutomer, R.drawable.call_cutomer);
                break;
        }
        return botton;
    }


    //状态文字
    public class OrderText{
        public String str;
        public @ColorRes int color;

        public OrderText(String str, @ColorRes int color) {
            this.str = str;
            this.color = color;
        }
    }
    //状态按钮
    public class OrderBotton{
        public @StringRes int str;
        public @DrawableRes int bottonBg;
        public int id;              //1.未支付(订单创建);2.用户支付完成等待确认;3.支付成功(待发货);4.已发货(确认收货);5.完成订单（已收货）;6.申请售后;7.申请退款;9.订单关闭

        public OrderBotton(int id, @StringRes int str, @DrawableRes int bottonBg) {
            this.str = str;
            this.bottonBg = bottonBg;
            this.id = id;
        }
    }

}
