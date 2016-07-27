/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp</p>
 * <p>File：BaseListModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/15/17:44.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.m;


import com.squareup.okhttp.Request;
import com.tp.venus.base.rx.RxUtils;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.util.ApiUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.base.mvp.m.BaseListModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/15/17:44
 * @version 1.0.0
 */

public class BaseListModel extends BaseModel implements IBaseListModel {

/*    public Observable<JsonMessage> search(@Status.TokenStatus int tokenStatus, final Request request){
        return searchMessage(tokenStatus, request, null);
    }*/


    @Override
    public void search(@Status.TokenStatus int tokenStatus, final Request request, final Subscriber<JsonMessage> mProgressSubscriber) {
        RxUtils.getToken(tokenStatus).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String token) {
                        Observable.just(token).map(new Func1<String, Request>() {
                            @Override
                            public Request call(String token) {
                                Request mRequest = null;
                                try{
                                    mRequest = ApiUtil.getInstance().getDefaultRequest(token, request);
                                } catch (Exception e){
                                    mProgressSubscriber.onError(e);
                                }
                                return mRequest;
                            }
                        }).map(new Func1<Request, JsonMessage>() {
                            @Override
                            public JsonMessage call(Request request) {
                                return ApiUtil.getInstance().sendJsonMessage(request);
                            }
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(mProgressSubscriber);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mProgressSubscriber.onError(throwable);
                    }
                });
    }




}
