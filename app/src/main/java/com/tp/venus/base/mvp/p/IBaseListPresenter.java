/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp.p</p>
 * <p>File：IBasePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/10:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.p;

import com.squareup.okhttp.Request;
import com.tp.venus.config.Status;

/**<p>Class：com.tp.venus.base.mvp.p.IBasePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/10:15
 * @version 1.0.0
 */

public interface IBaseListPresenter extends IBasePresenter{

    /**
     * 获取数据
     * @param request
     * @param tokenStatus
     */
    void search(Request request, @Status.TokenStatus int tokenStatus);


    /**
     * 用户自定义组合数据使用
     * @param request
     * @param tokenStatus
     * @return
     */
   // Observable<JsonMessage> list(Request request, @Status.TokenStatus int tokenStatus);

    /**
     * 获取反射类型
     * @param classType
     */
    void setClassType(Class<?> classType);
}
