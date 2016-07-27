/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui.view</p>
 * <p>File：ICodeView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/16:41.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui.view;

import com.tp.venus.base.mvp.v.BaseView;

/**<p>Class：com.tp.venus.module.common.ui.view.ICodeView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/18/16:41
 * @version 1.0.0
 */

public interface ICodeView extends BaseView {

    /**
     * 验证码成功
     */
    void sendCodeSuccess();
}
