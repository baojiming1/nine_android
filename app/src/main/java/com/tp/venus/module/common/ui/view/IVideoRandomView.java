/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui.view</p>
 * <p>File：IVideoRandomView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/7/16:16.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.common.bean.VideoInfo;

import java.util.List;

/**<p>Class：com.tp.venus.module.common.ui.view.IVideoRandomView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/7/16:16
 * @version 1.0.0
 */

public interface IVideoRandomView extends BaseView {

    /**
     * 成功时触发
     * @param datas
     */
    void onSuccess(List<VideoInfo> datas);

}
