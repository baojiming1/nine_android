/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IAddressEditPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/10:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.shop.presenter.IAddressEditPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/10:40
 * @version 1.0.0
 */

public interface IAddressEditPresenter extends IBasePresenter {


    /**
     * 保存以及修改
     * @param addressId
     * @param name
     * @param mobile
     * @param address
     * @param idCard
     */
    @Login
    void saveOrUpdate(String addressId, String name, String mobile, String address, boolean defaultAddress);


}
