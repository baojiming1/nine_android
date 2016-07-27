/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.persenter.impl</p>
 * <p>File：LivePersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:05.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.persenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.qinjia.entity.LiveProgram;
import com.tp.venus.module.qinjia.model.LiveModel;
import com.tp.venus.module.qinjia.persenter.ILivePersenter;
import com.tp.venus.module.qinjia.ui.view.ILiveView;

/**<p>Class：com.tp.venus.module.qinjia.persenter.impl.LivePersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/18:05
 * @version 1.0.0
 */

public class LivePersenter extends BasePresenter implements ILivePersenter {

    private ILiveView mILiveView;
    private LiveModel mLiveModel;

    public LivePersenter(ILiveView mILiveView) {
        super(mILiveView);
        this.mILiveView = mILiveView;
        this.mLiveModel = new LiveModel();
    }

    @Override
    public void findLive(String programId) {
        mLiveModel.findLive(programId, new ProgressSubscriber<JsonMessage<LiveProgram>>(mILiveView) {
            @Override
            public void onSuccess(JsonMessage<LiveProgram> message) {
                LiveProgram mLiveProgram = message.getObj();
                if( mLiveProgram.getType().intValue() == 1){                //直播
                    mILiveView.playLive(mLiveProgram);
                } else {                            //重播
                    mILiveView.playVideo();
                }
            }
        });
    }
}
