/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui.view</p>
 * <p>File：IBannerView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/16:43.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.common.bean.Banner;

import java.util.List;

/**<p>Class：com.tp.venus.module.common.ui.view.IBannerView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/16:43
 * @version 1.0.0
 */

public interface IBannerView extends BaseView {

    /**
     * 显示
     * @param datas
     */
    void showBanner(List<Banner> datas);
}
