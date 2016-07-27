/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter</p>
 * <p>File：IHomePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/16:17.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.shop.presenter.IHomePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/16:17
 * @version 1.0.0
 */

public interface IHomePresenter  extends IBasePresenter{

    /**
     * 首页商品分类列表接口
     */
    void getHomeClass();

}
