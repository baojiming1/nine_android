/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：HomePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/16:17.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.shop.bean.Classes;
import com.tp.venus.module.shop.model.IClassesModel;
import com.tp.venus.module.shop.model.impl.ClassesModel;
import com.tp.venus.module.shop.presenter.IHomePresenter;
import com.tp.venus.module.shop.ui.view.IHomeView;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.HomePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/16:17
 * @version 1.0.0
 */

public class HomePresenter extends BasePresenter implements IHomePresenter {

    private IHomeView mIHomeView;
    private IClassesModel classesModel;

    public HomePresenter(IHomeView mBaseView) {
        super(mBaseView);
        this.mIHomeView = mBaseView;
        this.classesModel = new ClassesModel();
    }

    @Override
    public void getHomeClass() {
        classesModel.getHomeClasses(new RxSubscriber<JsonMessage<PageResult<Classes>>>(mIHomeView) {
            @Override
            public void onSuccess(JsonMessage<PageResult<Classes>> message) {
                mIHomeView.showClasses(message.getObj().getRows());
            }
        });
    }
}
