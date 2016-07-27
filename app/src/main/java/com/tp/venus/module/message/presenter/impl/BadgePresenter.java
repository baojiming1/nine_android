/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter.impl</p>
 * <p>File：BadgePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/14:39.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.message.bean.BadgeCount;
import com.tp.venus.module.message.model.IMessageModel;
import com.tp.venus.module.message.model.impl.MessageModel;
import com.tp.venus.module.message.presenter.IBadgePresenter;
import com.tp.venus.module.message.ui.view.IBadgeView;

/**<p>Class：com.tp.venus.module.message.presenter.impl.BadgePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/14:39
 * @version 1.0.0
 */

public class BadgePresenter extends BasePresenter implements IBadgePresenter {

    private IBadgeView mIMessageView;
    private IMessageModel mIMessageModel;

    public BadgePresenter(IBadgeView mIMessageView) {
        super(mIMessageView);
        this.mIMessageView = mIMessageView;
        this.mIMessageModel = new MessageModel();
    }

    @Override
    public void messageCount() {
        mIMessageModel.getMessageCount(new RxSubscriber<JsonMessage<BadgeCount>>(mIMessageView) {
            @Override
            public void onSuccess(JsonMessage<BadgeCount> message) {
                mIMessageView.showBadge(message.getObj());
            }
        });
    }
}
