/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：SelectClassesFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/17:52.</p>
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
import com.tp.venus.module.shop.adapter.ClassesAdapter;
import com.tp.venus.module.shop.bean.Classes;
import com.tp.venus.util.ResourcesUtil;

/**<p>Class：com.tp.venus.module.shop.ui.fragment.SelectClassesFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/17:52
 * @version 1.0.0
 */

public class SelectClassesFragment extends BaseSwipRefreshFragment<Classes> {

    private String classesId;

    public static SelectClassesFragment newInstance(String classesId){
        SelectClassesFragment mSelectClassesFragment = new SelectClassesFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Status.ID, classesId);
        mSelectClassesFragment.setArguments(mBundle);
        return mSelectClassesFragment;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        classesId = getArguments().getString(Status.ID);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        RequestBody mRequestBody = bodyBuilder.setParam("classId", classesId).build();
        return  builder.url(AppConfig.SERVICE_HOST + Url.SHOP_CLASSES_GET_CHILDREN).post(mRequestBody).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_classes_list_item;
    }

    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setLayoutManager(RecyclerViewBuilder.VERTICAL, 3)
                .setAutoMeasureEnabled(true);
    }


    @Override
    protected CommonAdapter<Classes, CommonViewHolder> getAdapter() {
        return new ClassesAdapter(getContext(), getItemLayout(), ResourcesUtil.getPoint(getContext()).x / 3);
    }
}
