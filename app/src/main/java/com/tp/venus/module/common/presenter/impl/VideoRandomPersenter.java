/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.presenter.impl</p>
 * <p>File：VideoRandomPersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/7/16:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.bean.VideoInfo;
import com.tp.venus.module.common.model.ICommonModel;
import com.tp.venus.module.common.model.imp.CommonModel;
import com.tp.venus.module.common.presenter.IVideoRandomPersenter;
import com.tp.venus.module.common.ui.view.IVideoRandomView;

/**<p>Class：com.tp.venus.module.common.presenter.impl.VideoRandomPersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/7/16:15
 * @version 1.0.0
 */

public class VideoRandomPersenter extends BasePresenter implements IVideoRandomPersenter {

    private IVideoRandomView mIVideoRandomView;
    private ICommonModel mICommonModel;

    public VideoRandomPersenter(IVideoRandomView mIVideoRandomView) {
        super(mIVideoRandomView);
        this.mIVideoRandomView = mIVideoRandomView;
        this.mICommonModel = new CommonModel();
    }


    @Override
    public void searchRandomVideoInfo() {
        mICommonModel.searchVideoInfo(new ProgressSubscriber<JsonMessage<PageResult<VideoInfo>>>(mIVideoRandomView) {
            @Override
            public void onSuccess(JsonMessage<PageResult<VideoInfo>> message) {
                mIVideoRandomView.onSuccess(message.getObj().getRows());
            }
        });
    }
}
