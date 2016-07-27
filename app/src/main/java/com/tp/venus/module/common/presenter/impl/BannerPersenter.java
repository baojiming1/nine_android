/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.presenter.impl</p>
 * <p>File：BannerPersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/16:45.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.bean.Banner;
import com.tp.venus.module.common.model.IBannerModel;
import com.tp.venus.module.common.model.imp.BannerModel;
import com.tp.venus.module.common.presenter.IBannerPersenter;
import com.tp.venus.module.common.ui.view.IBannerView;

/**<p>Class：com.tp.venus.module.common.presenter.impl.BannerPersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/16:45
 * @version 1.0.0
 */
public class BannerPersenter extends BasePresenter implements IBannerPersenter {

    private IBannerView mIBannerView;
    private IBannerModel bannerModel;

    public BannerPersenter(IBannerView mIBannerView) {
        super(mIBannerView);
        this.mIBannerView = mIBannerView;
        this.bannerModel = new BannerModel();
    }

    @Override
    public void searchBanner(@Status.Banner int type) {
        bannerModel.search(type, new RxSubscriber<JsonMessage<PageResult<Banner>>>(mIBannerView) {
            @Override
            public void onSuccess(JsonMessage<PageResult<Banner>> message) {
                mIBannerView.showBanner( message.getObj().getRows() );
            }
        });
    }
}
