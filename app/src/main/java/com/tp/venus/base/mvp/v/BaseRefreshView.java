/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.mvp.v</p>
 * <p>File：BaseRefreshView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/17:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.v;

/**<p>Class：com.tp.venus.base.mvp.v.BaseRefreshView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/17:38
 * @version 1.0.0
 */

public interface BaseRefreshView<T> extends BaseView {

    void onError();

    void dataNull();

    void show(T o);

    void onAddDataBefore();
}
