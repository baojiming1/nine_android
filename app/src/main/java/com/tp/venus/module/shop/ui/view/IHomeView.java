/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.view</p>
 * <p>File：IHomeView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/11:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.shop.bean.Classes;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.ui.view.IHomeView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/11:36
 * @version 1.0.0
 */

public interface IHomeView extends BaseView {

    /**
     * 首页商品分类列表接口
     */
    void showClasses(List<Classes> datas);

}
