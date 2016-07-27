/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：AddressManagerFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/24/11:49.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.presenter.IAddressManagerPresenter;
import com.tp.venus.module.shop.presenter.impl.AddressManagerPresenter;
import com.tp.venus.module.shop.ui.AddressEditActivity;
import com.tp.venus.module.shop.ui.view.IAddressManagerView;
import com.tp.venus.widget.RippleView;

/**<p>Class：com.tp.venus.module.shop.ui.fragment.AddressManagerFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/24/11:49
 * @version 1.0.0
 */

public class AddressManagerFragment extends BaseSwipRefreshFragment<Address> implements IAddressManagerView{

    private IAddressManagerPresenter mIAddressManagerPresenter;
    private int changeAddress;

    public static AddressManagerFragment newInstance(int changeAddress){
        AddressManagerFragment mAddressManagerFragment = new AddressManagerFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(Status.Order.CHANGE_ADDRESS_KEY, changeAddress);
        mAddressManagerFragment.setArguments(mBundle);
        return mAddressManagerFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        sendHttp();
    }

    @Override
    protected boolean lazyLoad() {
        return true;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if( mIAddressManagerPresenter == null){
            mIAddressManagerPresenter = getPresenter(new AddressManagerPresenter(this));
        }
        changeAddress = getArguments().getInt(Status.Order.CHANGE_ADDRESS_KEY, 0);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_ADDRESS_LIST).post(bodyBuilder.build()).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_address_list_item;
    }

    @Override
    protected void convertLayout(final CommonViewHolder mCommonViewHolder, final Address item, int position) {

        TextView name = mCommonViewHolder.findViewById(R.id.name);
        name.setText(item.getRecipientName());

        TextView mobile = mCommonViewHolder.findViewById(R.id.mobile);
        mobile.setText(item.getMobile());

        /*TextView id_card = mCommonViewHolder.findViewById(R.id.id_card);
        id_card.setText("身份证号:" + item.getRecipientID());*/

        TextView address = mCommonViewHolder.findViewById(R.id.address);
        address.setText(item.getDetail());
        final CheckBox mCheckBox = mCommonViewHolder.findViewById(R.id.mCheckBox);
        boolean isDefault = item.getIsDefault();
        mCheckBox.setChecked(isDefault);
        if( isDefault ){
            mCheckBox.setText(R.string.default_address);
            mCheckBox.setEnabled(false);
        } else {
            mCheckBox.setEnabled(true);
            mCheckBox.setText(R.string.set_default_address);
            RxViewListener.clicks(mCheckBox, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    mIAddressManagerPresenter.setDefault(item.getId(), mCheckBox);
                }
            });
        }

        RippleView edit = mCommonViewHolder.findViewById(R.id.edit);
        RxViewListener.clicks(edit, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = getIntentBuilder(AddressEditActivity.class).putParcelable(Status.PARCELABLE_KEY, item).build();
                startActivity(mIntent);
            }
        });

        RippleView delete = mCommonViewHolder.findViewById(R.id.delete);
        RxViewListener.clicks(delete, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIAddressManagerPresenter.delete(item.getId(),  mRecyclerViewBuilder.getAdapterPosition(mCommonViewHolder));
            }
        });

        if(changeAddress ==  Status.Order.CHANGE_ADDRESS_CODE){             //地址选择
            RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    Intent data = new Intent();
                    data.putExtra(Status.PARCELABLE_KEY, item);
                    getActivity().setResult(Status.Order.CHANGE_ADDRESS_CODE, data);
                    finishActivity();
                }
            });
        }



    }

    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.MUST;
    }

    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setAutoMeasureEnabled(true).build();
    }

    @Override
    public void setDefaultSucceess() {
        sendHttp();
    }

    @Override
    public void setDefaulltError(CheckBox mCheckBox) {
        mCheckBox.setChecked( !mCheckBox.isChecked() );
    }

    @Override
    public void deleteSuccess(int position) {
        adapter.remove(position);
    }
}
