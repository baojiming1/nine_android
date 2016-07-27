/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.youzan.presenter.impl</p>
 * <p>File：YouzanPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/19/11:03.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.module.youzan.presenter.IYouzanPresenter;
import com.tp.venus.module.youzan.ui.view.IYouzanView;
import com.tp.venus.util.StringUtil;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**<p>Class：com.tp.venus.module.youzan.presenter.impl.YouzanPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/19/11:03
 * @version 1.0.0
 */

public class YouzanPresenter extends BasePresenter implements IYouzanPresenter {

    private IYouzanView mIYouzanView;


    public YouzanPresenter(IYouzanView mIYouzanView) {
        super(mIYouzanView);
        this.mIYouzanView = mIYouzanView;
    }

    @Override
    public void openYouzan(final String url) {
        Observable.just(url).filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return StringUtil.isNotEmpty(url);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mIYouzanView.openYouzanView(url);
            }
        });
    }
}
