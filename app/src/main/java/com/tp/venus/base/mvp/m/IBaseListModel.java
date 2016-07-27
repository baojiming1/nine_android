/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp</p>
 * <p>File：IBaseListModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/15/18:29.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.m;

import com.squareup.okhttp.Request;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;

import rx.Subscriber;

/**<p>Class：com.tp.venus.base.mvp.m.IBaseListModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/15/18:29
 * @version 1.0.0
 */

public interface IBaseListModel {

     void search(@Status.TokenStatus int tokenStatus, Request request, Subscriber<JsonMessage> mProgressSubscriber);


   //  Observable<JsonMessage> search(@Status.TokenStatus int tokenStatus, final Request request);
}
