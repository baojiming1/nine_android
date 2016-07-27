/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui.view</p>
 * <p>File：IListProductView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/7/1/10:11.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.qinjia.entity.LiveProduct;

import java.util.List;

/**<p>Class：com.tp.venus.module.qinjia.ui.view.IListProductView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/7/1/10:11
 * @version 1.0.0
 */

public interface IListProductView extends BaseView {

    void showProduct(List<LiveProduct> liveProducts);
}
