/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.rx</p>
 * <p>File：RxSubscriber.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/21/17:36.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.rx;

import com.orhanobut.logger.Logger;
import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.config.Status;
import com.tp.venus.exception.AuthenticationException;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.util.ApiUtil;
import com.tp.venus.util.JPushUtil;
import com.tp.venus.util.ToastUtil;

import rx.Subscriber;

/**<p>Class：com.tp.venus.base.rx.RxSubscriber</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/21/17:36
 * @version 1.0.0
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    protected BaseView mBaseView;

    @Override
    public void onCompleted() {
        // ToastUtil.getInstance().show("onCompleted");
    }

    public RxSubscriber(BaseView mBaseView){
        this.mBaseView = mBaseView;
    }

    @Override
    public void onError(Throwable e) {
        paraseThrowable(e);
    }

    protected void paraseThrowable(Throwable e){
        Logger.e("数据解析错误", e);
        e.printStackTrace();
        if(e instanceof AuthenticationException){
            mBaseView.goLoginView();
        }  else {
            String error = ApiUtil.getInstance().parseErrorMessage(e);
            ToastUtil.getInstance().show(error);
        }
    }


    @Override
    public void onNext(T o) {
        if(o == null ){
            onFail(null);
            return ;
        }
        JsonMessage message = null;
        if( o instanceof  JsonMessage){
            message = (JsonMessage) o;
        } else {
            onFail(message);
            return ;
        }

        if( message.getCode().intValue() == Status.Code.SUCCESS ){
            onSuccess(o);
        } else if( message.getCode().intValue() == Status.Code.AuthenticationFail){             //无权操作
            authenticationFail(message);
        } else {
            onFail(message);
        }
    }


    /**
     * 无权操作的处理
     * @param message
     */
    protected void authenticationFail(JsonMessage message){
        ToastUtil.getInstance().show(message.getMessage());
        IBasePresenter basePresenter = new BasePresenter(mBaseView) ;
        JPushUtil.registerJpush(null);
        basePresenter.clearUser();
        if( !onAuthenticationFail(message) ) {
            mBaseView.goLoginView();
        }
    }

    /** 业务成功 **/
    public abstract void onSuccess(T message);

    public void onFail(JsonMessage jsonMessage){
        if( jsonMessage == null){
            ToastUtil.getInstance().show("请求数据为空");
        } else {
            ToastUtil.getInstance().show(jsonMessage.getMessage());
        }

    }

    /** 无权限操作 **/
    public boolean onAuthenticationFail(JsonMessage jsonMessage){
        return false;
    }


}
