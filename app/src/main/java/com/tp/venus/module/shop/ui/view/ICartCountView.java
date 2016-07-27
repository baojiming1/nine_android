/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：ICartCountView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/11/15:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.base.mvp.v.BaseView;

/**<p>Class：com.tp.venus.module.shop.ui.view.ICartCountView</p>
 * <p>Description：</p>
 * <pre>
 *      购物车商品总数接口
 * </pre>
 * @author 鲍建明
 * @date 2016/5/11/15:36
 * @version 1.0.0
 */

public interface ICartCountView extends BaseView {

    /**
     * 购物车商品总数接口
     */
    void showBadgeView(Integer count);
}
