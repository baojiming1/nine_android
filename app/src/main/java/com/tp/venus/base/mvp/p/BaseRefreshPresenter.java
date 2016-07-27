/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.mvp.p</p>
 * <p>File：BaseRefreshPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/17:37.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.p;

import com.squareup.okhttp.Request;
import com.tp.venus.base.mvp.m.BaseRefreshModel;
import com.tp.venus.base.mvp.v.BaseRefreshView;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.util.JSONUtil;

/**<p>Class：com.tp.venus.base.mvp.p.BaseRefreshPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/17:37
 * @version 1.0.0
 */

public class BaseRefreshPresenter<T> extends BasePresenter implements IBaseRefreshPresenter {

    private BaseRefreshView<T> mBaseRefreshView;
    private Class classType;
    private BaseRefreshModel baseRefreshModel;

    public BaseRefreshPresenter(BaseRefreshView mBaseView) {
        super(mBaseView);
        this.mBaseRefreshView = mBaseView;
        this.baseRefreshModel = new BaseRefreshModel();
    }


    @Override
    public void start(Request request, @Status.TokenStatus int tokenStatus) {
        baseRefreshModel.start(tokenStatus, request, new RxSubscriber<JsonMessage>(mBaseRefreshView) {
            @Override
            public void onSuccess(JsonMessage message) {
                parseMessae(message);
            }
            @Override
            public void onError(Throwable e) {
                mBaseRefreshView.onError();
                super.onError(e);
            }

            @Override
            public void onCompleted() {
                mBaseRefreshView.onAddDataBefore();
                super.onCompleted();
            }
        });
    }

    private void parseMessae(JsonMessage jsonMessage){
        T o = (T) JSONUtil.parseObject(jsonMessage.getObj().toString(), classType);
        if( o == null){
            mBaseRefreshView.dataNull();
            return ;
        }
        mBaseRefreshView.show(o);
    }

    /**
     * 设置返回的参数类型
     * @param classType
     */
    public void setClassType(Class<?> classType){
        this.classType = classType;
    }
}
