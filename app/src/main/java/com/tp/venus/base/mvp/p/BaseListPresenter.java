/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp</p>
 * <p>File：BaseListPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/15/17:16.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.p;

import com.squareup.okhttp.Request;
import com.tp.venus.base.mvp.m.BaseListModel;
import com.tp.venus.base.mvp.m.IBaseListModel;
import com.tp.venus.base.mvp.v.BaseListView;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.base.rx.RxFunc2;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.util.JSONUtil;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.base.mvp.p.BaseListPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/15/17:16
 * @version 1.0.0
 */

public class BaseListPresenter extends BasePresenter implements IBaseListPresenter{

    private BaseListView mBaseListView;
    private IBaseListModel mBaseListModel;
    private Class classType;

    public BaseListPresenter(BaseListView mBaseListView){
        super(mBaseListView);
        this.mBaseListView = mBaseListView;
        this.mBaseListModel = new BaseListModel();
    }

    /**
     * 通用搜索
     * @param request
     * @param tokenStatus
     */
    public void search(Request request, @Status.TokenStatus int tokenStatus){
        mBaseListModel.search(tokenStatus, request, new RxSubscriber<JsonMessage>(this.mBaseListView) {
            @Override
            public void onSuccess(JsonMessage jsonMessage) {
                mBaseListView.onAddDataBefore();
                parseMessage(jsonMessage);
            }

            @Override
            public void onError(Throwable e) {
                mBaseListView.onError();
                super.onError(e);
            }

            @Override
            public void onCompleted() {
                mBaseListView.onCompleted();
                super.onCompleted();

            }
        });
    }

    /**
     * 用户自定义组合数据使用
     * @param request
     * @param tokenStatus
     * @return
     */
   /* public Observable<JsonMessage> list(Request request, @Status.TokenStatus int tokenStatus){
       return mBaseListModel.search( tokenStatus, request);
    }*/


    /**
     * 数据合并
     * @param o1
     * @param o2
     * @param rxFunc2
     * @param mProgressSubscriber
     */
    public void zip(Observable<JsonMessage> o1, Observable<JsonMessage> o2, RxFunc2 rxFunc2, ProgressSubscriber mProgressSubscriber){
        Observable.zip(o1,
                o2, rxFunc2
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }


    private void parseMessage(JsonMessage jsonMessage){
        PageResult result = JSONUtil.parseObject(jsonMessage.getObj().toString(), PageResult.class);
        if (result.getTotal() == 0) {                           //无数据
            mBaseListView.dataNull();
            return ;
        }
        List<?> datas =  JSONUtil.parseArray(result.getRows().toString(), classType);
        result.setRows(datas);
        mBaseListView.addData(result);
    }

    /**
     * 设置返回的参数类型
     * @param classType
     */
    public void setClassType(Class<?> classType){
        this.classType = classType;
    }


}
