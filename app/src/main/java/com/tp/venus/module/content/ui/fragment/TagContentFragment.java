/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.fragment</p>
 * <p>File：TagContentFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/11/4/10:35.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.content.bean.ContentResult;

/**<p>Class：com.tp.venus.module.content.ui.fragment.TagContentFragment</p>
 * <p>Description：</p>
 * <pre>
 *     标签详情页中的帖子列表
 * </pre>
 * @author 鲍建明
 * @date 2015/11/4/10:35
 * @version 1.0.0
 */

public class TagContentFragment extends BaseContentFragment<ContentResult> {

    private String tagId;


    public static TagContentFragment newInstance(String tagId){
        TagContentFragment mTagContentFragment = new TagContentFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Status.Tag.ID, tagId);
        mTagContentFragment.setArguments(mBundle);
        return mTagContentFragment;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tagId = getArguments().getString(Status.Tag.ID);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url( bodyBuilder.buildGet(Url.TAG_CONTENT_SEARCH + tagId) ).get().build();
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, ContentResult item, int position) {
        showContent(mCommonViewHolder, item, position);
    }


    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.COMPLET;
    }

    @Override
    public int getItemLayout() {
        return  R.layout.content_list_item;
    }

    @Override
    public void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder
                //.setDefaultItemDecoration(RecyclerViewBuilder.DECOR_VERTICAL_LIST,R.drawable.divider_bg )
                .setNoItemDecoration()
                .setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL);

      //  mRecyclerViewBuilder.setLayoutManager(RecyclerViewBuilder.HORIZONTAL, 2).setItemDecoration(null);
    }
}
