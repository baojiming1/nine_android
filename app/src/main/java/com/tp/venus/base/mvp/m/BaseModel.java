/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base</p>
 * <p>File：BaseModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/14/13:45.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.m;

import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.model.JsonMessage;

import rx.Observable;

/**<p>Class：com.tp.venus.base.mvp.m.BaseModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/14/13:45
 * @version 1.0.0
 */

public abstract   class BaseModel {



    /**
     * 主线程中跑
     * @param observable
     */
    public void doMain(Observable<?> observable,final RxViewListener.Action<?> ation){
       /* observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber() {
                    @Override
                    public void onCompleted() {
                        ToastUtil.toastShort("onCompleted", ApplicationHandler.getApp().getApplicationContext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.toastShort("onError", ApplicationHandler.getApp().getApplicationContext());
                    }

                    @Override
                    public void onNext(Object entity) {
                        ation.call(entity);
                    }
                });*/
    }

    protected void doSubThread(Observable<JsonMessage<?>> observable, ProgressSubscriber<JsonMessage<?>> mProgressSubscriber){
       // observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }


    protected void doMainThread(Observable<?> observable, ProgressSubscriber<JsonMessage> mProgressSubscriber){
     //   observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }





}
