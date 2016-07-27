/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：FavoritePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/17:21.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.model.IFavoriteModel;
import com.tp.venus.module.common.model.imp.FavoriteModel;
import com.tp.venus.module.user.presenter.IFavoritePresenter;
import com.tp.venus.module.user.ui.view.IFavoriteView;

/**<p>Class：com.tp.venus.module.user.presenter.impl.FavoritePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/17:21
 * @version 1.0.0
 */

public class FavoritePresenter extends BasePresenter implements IFavoritePresenter {

    private IFavoriteView mIFavoriteView;
    private IFavoriteModel favoriteModel;

    public FavoritePresenter(IFavoriteView mIFavoriteView) {
        super(mIFavoriteView);
        this.mIFavoriteView = mIFavoriteView;
        this.favoriteModel = new FavoriteModel();
    }

    @Override
    public void cancelFavorite(String id, final int adapterPosition) {
        favoriteModel.favorite(id, false, Status.Favorite.CONTENT, new RxSubscriber<JsonMessage>(mIFavoriteView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIFavoriteView.delete(adapterPosition);
            }
        });
    }
}
