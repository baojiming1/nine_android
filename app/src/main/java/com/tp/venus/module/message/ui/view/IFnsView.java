/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.ui.view</p>
 * <p>File：IFnsView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/17:02.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.ui.view;

/**<p>Class：com.tp.venus.module.message.ui.view.IFnsView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/17:02
 * @version 1.0.0
 */

public interface IFnsView extends IMessageView {

    /**
     * 关注成功
     * @param isAttention
     */
    void attentionSuccess(boolean isAttention);

}
