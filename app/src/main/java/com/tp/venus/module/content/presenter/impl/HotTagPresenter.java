/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter.impl</p>
 * <p>File：HotTagPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/2/16:27.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.content.bean.Tag;
import com.tp.venus.module.content.model.ITagModel;
import com.tp.venus.module.content.model.impl.TagModel;
import com.tp.venus.module.content.presenter.IHotTagPresenter;
import com.tp.venus.module.content.ui.view.IHotTagView;

/**<p>Class：com.tp.venus.module.content.presenter.impl.HotTagPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/2/16:27
 * @version 1.0.0
 */

public class HotTagPresenter extends BasePresenter implements IHotTagPresenter {

    private IHotTagView mIHotTagView;
    private ITagModel iTagModel;

    public HotTagPresenter(IHotTagView mIHotTagView) {
        super(mIHotTagView);
        this.mIHotTagView = mIHotTagView;
        this.iTagModel = new TagModel();
    }

    @Override
    public void searchHotTag() {
        iTagModel.searchHotTag(new RxSubscriber<JsonMessage<PageResult<Tag>>>(mIHotTagView) {
            @Override
            public void onSuccess(JsonMessage<PageResult<Tag>> message) {
                mIHotTagView.showHotTag(message.getObj().getRows());
            }
        });
    }
}
