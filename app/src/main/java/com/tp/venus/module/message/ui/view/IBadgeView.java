/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.ui.view</p>
 * <p>File：IBadgeView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/14:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.message.bean.BadgeCount;

/**<p>Class：com.tp.venus.module.message.ui.view.IBadgeView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/14:38
 * @version 1.0.0
 */

public interface IBadgeView extends BaseView {

    /**
     *
     * @param mBadgeCount
     */
    void showBadge(BadgeCount mBadgeCount);
}
