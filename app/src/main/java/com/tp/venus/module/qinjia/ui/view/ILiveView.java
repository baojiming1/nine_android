/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui.view</p>
 * <p>File：ILiveView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:06.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.qinjia.entity.LiveProgram;

/**<p>Class：com.tp.venus.module.qinjia.ui.view.ILiveView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/18:06
 * @version 1.0.0
 */

public interface ILiveView extends BaseView {

    void playLive(LiveProgram mLiveProgram);

    void playVideo();

}
