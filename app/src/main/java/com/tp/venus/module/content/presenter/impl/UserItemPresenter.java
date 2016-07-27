/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter.impl</p>
 * <p>File：UserContentPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/14:59.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.model.IContentModel;
import com.tp.venus.module.content.model.impl.ContentModel;
import com.tp.venus.module.content.presenter.IContentItemPresenter;
import com.tp.venus.module.content.ui.view.IContentItemView;

/**<p>Class：com.tp.venus.module.content.presenter.impl.UserContentPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/14:59
 * @version 1.0.0
 */

public class UserItemPresenter extends BasePresenter implements IContentItemPresenter {

    private IContentItemView mIUserContentView;
    private IContentModel mIContentModel;

    public UserItemPresenter(IContentItemView mIUserContentView) {
        super(mIUserContentView);
        this.mIContentModel = new ContentModel();
        this.mIUserContentView = mIUserContentView;
    }


    @Override
    public void deleteContent(String contentId, final int index) {
        mIContentModel.deleteContent(contentId, new RxSubscriber<JsonMessage>(mIUserContentView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mIUserContentView.deleteContent(index);
            }
        });
    }

}
