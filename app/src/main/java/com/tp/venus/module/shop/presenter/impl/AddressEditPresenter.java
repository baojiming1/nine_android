/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.presenter.impl</p>
 * <p>File：AddressEditPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/3/10:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.shop.model.IAddressModel;
import com.tp.venus.module.shop.model.impl.AddressModel;
import com.tp.venus.module.shop.presenter.IAddressEditPresenter;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.shop.presenter.impl.AddressEditPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/3/10:40
 * @version 1.0.0
 */

public class AddressEditPresenter extends BasePresenter implements IAddressEditPresenter {

    private IAddressModel addressModel;
    private ICommonView commonView;

    public AddressEditPresenter(ICommonView mBaseView) {
        super(mBaseView);
        this.commonView = mBaseView;
        this.addressModel = new AddressModel();
    }


    @Override
    public void saveOrUpdate(String addressId, String name, String mobile, String address, boolean defaultAddress) {
        if(StringUtil.isEmpty(name)){
            commonView.showError("请输入收货人的真实姓名");
            return ;
        }
        if( StringUtil.isEmpty(mobile) ){
            commonView.showError("请输入收货人的联系方式");
            return ;
        }
        if( StringUtil.isEmpty(address)){
            commonView.showError("请输入收货的详情地址");
            return ;
        }
        ProgressSubscriber mProgressSubscriber = new ProgressSubscriber<JsonMessage>(commonView) {
            @Override
            public void onSuccess(JsonMessage message) {
                commonView.onSuccess();
            }
        };
        if( StringUtil.isEmpty(addressId)){             //保存
            addressModel.save(name, mobile, address,  defaultAddress, mProgressSubscriber);
        } else {                                        //修改
            addressModel.update(addressId, name, mobile, address,  defaultAddress, mProgressSubscriber);
        }
    }
}
