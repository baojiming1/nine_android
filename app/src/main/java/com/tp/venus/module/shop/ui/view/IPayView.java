/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IPayView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/12/14:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.base.mvp.v.BaseView;

import java.util.Map;

/**<p>Class：com.tp.venus.module.shop.ui.view.IPayView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/12/14:23
 * @version 1.0.0
 */

public interface IPayView extends BaseView {

    /**
     * 订单支付接口
     * @param sign
     */
    void onSuccess(Map<String, String> sign);

    /**
     * 支付完成后
     */
    void paySuccess();

}
