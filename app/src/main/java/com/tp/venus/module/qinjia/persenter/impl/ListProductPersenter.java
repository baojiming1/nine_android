/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.persenter.impl</p>
 * <p>File：ListProductPersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/7/1/10:12.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.persenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.qinjia.entity.LiveProduct;
import com.tp.venus.module.qinjia.model.ListProductModel;
import com.tp.venus.module.qinjia.ui.view.IListProductView;

/**<p>Class：com.tp.venus.module.qinjia.persenter.impl.ListProductPersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/7/1/10:12
 * @version 1.0.0
 */

public class ListProductPersenter extends BasePresenter {

    private IListProductView mIListProductView;
    private ListProductModel model;

    public ListProductPersenter(IListProductView mIListProductView) {
        super(mIListProductView);
        this.mIListProductView = mIListProductView;
        this.model = new ListProductModel();
    }

    public void findLiveProduct(String programId){
        model.findLiveProduct(programId, new RxSubscriber<JsonMessage<PageResult<LiveProduct>>>(mIListProductView) {
            @Override
            public void onSuccess(JsonMessage<PageResult<LiveProduct>> message) {
                mIListProductView.showProduct(message.getObj().getRows());
            }
        });

    }

}
