/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：AddressEditActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/5/18:07.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.presenter.IAddressEditPresenter;
import com.tp.venus.module.shop.presenter.impl.AddressEditPresenter;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.AddressEditActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/4/5/18:07
 */

public class AddressEditActivity extends BaseActivity implements ICommonView {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.saveOrUpdate)
    RippleView saveOrUpdate;
    @InjectView(R.id.name)
    EditText name;
    @InjectView(R.id.mobile)
    EditText mobile;
    @InjectView(R.id.address)
    EditText address;
    @InjectView(R.id.checkbox)
    CheckBox checkbox;

    private IAddressEditPresenter mIAddressEditPresenter;
    private String addressId;
    private int title = R.string.new_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity_address_edit);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        Address item = getIntent().getParcelableExtra(Status.PARCELABLE_KEY);
        initUpdateUI(item);
        getToolbarBuilder(mToolbar).setTitle(title).build();
        if (mIAddressEditPresenter == null) {
            mIAddressEditPresenter = getPresenter(new AddressEditPresenter(this));
        }
        RxViewListener.clicks(saveOrUpdate, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIAddressEditPresenter.saveOrUpdate(addressId,
                        name.getText().toString(), mobile.getText().toString(),
                        address.getText().toString(),
                        checkbox.isChecked());
            }
        });
    }

    private void initUpdateUI(Address item){
        if( item == null){
            return ;
        }
        title = R.string.edit_address;
        addressId = item.getId();
        name.setText(item.getRecipientName());
        mobile.setText(item.getMobile());
        address.setText(item.getDetailAddress());
        checkbox.setChecked(item.getIsDefault());
    }


    @Override
    public void onSuccess() {
        if (StringUtil.isEmpty(addressId)) {
            ToastUtil.getInstance().show(R.string.save_success);
        } else {
            ToastUtil.getInstance().show(R.string.update_success);
        }
        finishActivity();
    }

    @Override
    public void onError() {

    }
}
