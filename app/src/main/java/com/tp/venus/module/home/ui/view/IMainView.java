/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.home.ui.view</p>
 * <p>File：IMainView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/13/11:47.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.home.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.module.home.ui.view.IMainView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/13/11:47
 * @version 1.0.0
 */

public interface IMainView extends BaseView {

    void onSuccess(User user);

    void onError();
}
