/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.mvp.p</p>
 * <p>File：IBaseRefreshPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/17:37.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.p;

import com.squareup.okhttp.Request;
import com.tp.venus.config.Status;

/**<p>Class：com.tp.venus.base.mvp.p.IBaseRefreshPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/17:37
 * @version 1.0.0
 */

public interface IBaseRefreshPresenter extends IBasePresenter {

    /**
     * 发送请求
     * @param request
     * @param tokenStatus
     */
    void start(Request request, @Status.TokenStatus int tokenStatus);


    void setClassType(Class<?> classType);

}
