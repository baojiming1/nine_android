/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter.impl</p>
 * <p>File：TagPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/13:02.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.content.bean.TagResult;
import com.tp.venus.module.content.model.ITagModel;
import com.tp.venus.module.content.model.impl.TagModel;
import com.tp.venus.module.content.presenter.ITagPresenter;
import com.tp.venus.module.content.ui.view.ITagView;

/**<p>Class：com.tp.venus.module.content.presenter.impl.TagPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/13:02
 * @version 1.0.0
 */

public class TagPresenter extends BasePresenter implements ITagPresenter {

    private ITagView mTagView;
    private ITagModel mTagModel;

    public TagPresenter(ITagView mTagView){
        super(mTagView);
        this.mTagView = mTagView;
        this.mTagModel = new TagModel();
    }



    @Override
    public void showTagInfo(String tagId) {
        mTagModel.showTagInfo(tagId, new RxSubscriber<JsonMessage<TagResult>>(mTagView) {
            @Override
            public void onSuccess(JsonMessage<TagResult> jsonMessage) {
                mTagView.showTagInfo(jsonMessage.getObj());
            }
        });
    }

}
