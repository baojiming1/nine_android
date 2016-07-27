/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base</p>
 * <p>File：ProgressSubscriber.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/14/14:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.rx;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.widget.loading.ProgressCancelListener;
import com.tp.venus.widget.loading.ProgressDialogHandler;

/**<p>Class：com.tp.venus.base.rx.ProgressSubscriber</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/14/14:40
 * @version 1.0.0
 */
public abstract class ProgressSubscriber<T> extends RxSubscriber<T> implements ProgressCancelListener {

    private ProgressDialogHandler mProgressDialogHandler;

    @Override
    public void onCompleted() {
        dismissProgressDialog();
       super.onCompleted();
    }

    @Override
    public void onStart() {
        showProgressDialog();
        super.onStart();
    }


    public ProgressSubscriber(BaseView mBaseView){
        super(mBaseView);
        mProgressDialogHandler = new ProgressDialogHandler(mBaseView.getCurrentContext(), this, true);
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        super.onError(e);
    }

    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.initProgressDialog();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.dismissProgressDialog();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onNext(T o) {
      super.onNext(o);
    }

    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
