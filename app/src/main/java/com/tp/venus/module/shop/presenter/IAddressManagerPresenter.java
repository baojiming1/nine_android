/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IAddressManagerPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/14:20.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import android.widget.CheckBox;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.shop.presenter.IAddressManagerPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/14:20
 * @version 1.0.0
 */

public interface IAddressManagerPresenter extends IBasePresenter {

    /**
     * 设置默认收货地址
     * @param addressId
     */
    @Login
    void setDefault(String addressId, CheckBox mCheckBox);

    /**
     * 删除收货地址
     * @param addressId
     */
    @Login
    void delete(String addressId, int position);
}
