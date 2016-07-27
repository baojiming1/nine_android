/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IShopCartPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/15:18.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import android.widget.EditText;

import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.module.shop.bean.ShopCart;

/**<p>Class：com.tp.venus.module.shop.presenter.IShopCartPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/15:18
 * @version 1.0.0
 */

public interface IShopCartPresenter extends IBasePresenter {

    @Login
    void changeBuyCount(EditText edit, ShopCart product, int buyCount);

    @Login
    void delete(String itemIds, int position, CommonViewHolder mCommonViewHolder);

    /**
     * 结算
     */
    @Login
    void statement();

}
