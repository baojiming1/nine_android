/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui.view</p>
 * <p>File：ICommonView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/12:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui.view;

import com.tp.venus.base.mvp.v.BaseView;

/**<p>Class：com.tp.venus.module.common.ui.view.ICommonView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/12:36
 * @version 1.0.0
 */

public interface ICommonView extends BaseView {

    /**
     * 成功时
     */
    void onSuccess();

    /**
     * 错误
     */
    void onError();
}
