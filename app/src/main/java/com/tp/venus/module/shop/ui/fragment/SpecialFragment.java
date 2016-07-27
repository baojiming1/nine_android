/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：SpecialFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/18/17:14.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.shop.adapter.ProductListItemTwoAdapter;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.util.ResourcesUtil;

/**<p>Class：com.tp.venus.module.shop.ui.fragment.SpecialFragment</p>
 * <p>Description：</p>
 * <pre>
 *      专题Fragment
 * </pre>
 * @author 鲍建明
 * @date 2016/4/18/17:14
 * @version 1.0.0
 */

public class SpecialFragment extends BaseSwipRefreshFragment<Product> {

    private String specialId;
    private int width;

    public static SpecialFragment newInstance(String specialId){
        SpecialFragment mSpecialFragment = new SpecialFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Status.ID, specialId);
        mSpecialFragment.setArguments(mBundle);
        return mSpecialFragment;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        specialId = getArguments().getString(Status.ID);
        width = ResourcesUtil.getDimens(getContext(), R.dimen.dp160);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        RequestBody mRequestBody = bodyBuilder.setParam("bannerId", specialId).build();
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_SPECIAL_PRODUCT_LIST).post(mRequestBody).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_product_list_item_two;
    }


    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setLayoutManager(RecyclerViewBuilder.VERTICAL, 2).setItemDecoration(null);
    }

    @Override
    protected CommonAdapter<Product, CommonViewHolder> getAdapter() {
        return new ProductListItemTwoAdapter(getContext(), getItemLayout(), width, width);
    }
}
