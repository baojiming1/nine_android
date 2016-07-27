/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.presenter</p>
 * <p>File：ContentPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/13/17:30.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.model.IFavoriteModel;
import com.tp.venus.module.common.model.imp.FavoriteModel;
import com.tp.venus.module.content.bean.ContentResult;
import com.tp.venus.module.content.model.IContentModel;
import com.tp.venus.module.content.model.impl.ContentModel;
import com.tp.venus.module.content.presenter.IContentPresenter;
import com.tp.venus.module.content.ui.view.IContentView;
import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.module.content.presenter.impl.ContentPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/13/17:30
 * @version 1.0.0
 */

public class ContentPresenter extends BasePresenter implements IContentPresenter {

    private IContentModel contentModel;
    private IContentView contentView;
    private IFavoriteModel favoriteModel;


    public ContentPresenter(IContentView contentView){
        super(contentView);
        this.contentView = contentView;
        this.contentModel = new ContentModel();
        this.favoriteModel = new FavoriteModel();
    }



    /**
     * 点赞
     * @param isPraise
     */
    public void praise(final String contentId, final boolean isPraise){
        contentModel.praise(contentId, isPraise, new RxSubscriber<JsonMessage>(contentView){
            @Override
            public void onSuccess(JsonMessage jsonMessage) {
                User currentUser = getCurrentUser();
                contentView.praise(isPraise, currentUser);
            }
        });
    }

    @Override
    public void contentDetails(String contentId) {
        contentModel.contentDetails(contentId, new RxSubscriber<JsonMessage<ContentResult>>(contentView) {
            @Override
            public void onSuccess(JsonMessage<ContentResult> jsonMessage) {
                contentView.showContentInfo(jsonMessage.getObj(), 0);
            }
        });
    }

    @Override
    public void favorite(String contentId, final boolean isFavorite) {
        favoriteModel.favorite(contentId, isFavorite, Status.Favorite.CONTENT, new RxSubscriber<JsonMessage>(contentView) {
            @Override
            public void onSuccess(JsonMessage message) {
                contentView.favorite(isFavorite);
            }
        });
    }




}
