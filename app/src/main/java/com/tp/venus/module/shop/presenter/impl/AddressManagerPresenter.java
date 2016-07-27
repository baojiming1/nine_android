/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：AddressManagerPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/14:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import android.widget.CheckBox;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.shop.model.IAddressModel;
import com.tp.venus.module.shop.model.impl.AddressModel;
import com.tp.venus.module.shop.presenter.IAddressManagerPresenter;
import com.tp.venus.module.shop.ui.view.IAddressManagerView;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.AddressManagerPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/14:22
 * @version 1.0.0
 */

public class AddressManagerPresenter extends BasePresenter implements IAddressManagerPresenter {

    private IAddressManagerView addressManagerView;
    private IAddressModel iAddressModel;

    public AddressManagerPresenter(IAddressManagerView addressManagerView) {
        super(addressManagerView);
        this.addressManagerView = addressManagerView;
        this.iAddressModel = new AddressModel();
    }

    @Override
    public void setDefault(String addressId, final CheckBox mCheckBox) {
        iAddressModel.setDefault(addressId, new ProgressSubscriber<JsonMessage>(addressManagerView) {
            @Override
            public void onSuccess(JsonMessage message) {
                addressManagerView.setDefaultSucceess();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                addressManagerView.setDefaulltError(mCheckBox);
            }

            @Override
            public void onFail(JsonMessage jsonMessage) {
                super.onFail(jsonMessage);
                addressManagerView.setDefaulltError(mCheckBox);
            }
        });
    }

    @Override
    public void delete(String addressId, final int position) {
        iAddressModel.delete(addressId, new ProgressSubscriber<JsonMessage>(addressManagerView) {
            @Override
            public void onSuccess(JsonMessage message) {
                addressManagerView.deleteSuccess(position);
            }
        });
    }
}
