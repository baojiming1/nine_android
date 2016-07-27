/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IAddressManagerView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/14:19.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import android.widget.CheckBox;

import com.tp.venus.base.mvp.v.BaseView;

/**<p>Class：com.tp.venus.module.shop.ui.view.IAddressManagerView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/14:19
 * @version 1.0.0
 */

public interface IAddressManagerView extends BaseView {

    /**
     * 设置默认收货地址成功
     */
    void setDefaultSucceess();

    /**
     * 设置默认收货地址成功
     */
    void setDefaulltError(CheckBox mCheckBox);

    /**
     * 删除成功
     * @param position
     */
    void deleteSuccess(int position);

}
