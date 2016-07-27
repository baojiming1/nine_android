/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IOrderBottonPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/6/14:21.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import android.content.DialogInterface;

import com.orhanobut.dialogplus.DialogPlus;
import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.shop.presenter.IOrderBottonPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/6/14:21
 * @version 1.0.0
 */

public interface IOrderBottonPresenter extends IBasePresenter {

    /**
     * 修改订单相关信息(状态)接口
     * @param orderId
     * @param orderStatus
     */
    @Login
    void updateOrderStatus(String orderId, int orderStatus, DialogInterface dialog, int position);

    /**
     * 保存售后服务信息接口
     * @param orderId
     * @param phone
     * @param remark
     * @param mDialogPlus
     */
    @Login
    void save_aftersale_service_info(String orderId, String phone, String remark, DialogPlus mDialogPlus, int position);
}
