/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui.fragment</p>
 * <p>File：ContentFavoriteFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/16:49.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.content.ui.ContentActivity;
import com.tp.venus.module.user.bean.Favorite;
import com.tp.venus.module.user.presenter.IFavoritePresenter;
import com.tp.venus.module.user.presenter.impl.FavoritePresenter;
import com.tp.venus.module.user.ui.view.IFavoriteView;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.user.ui.fragment.ContentFavoriteFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/16:49
 * @version 1.0.0
 */

public class ContentFavoriteFragment extends BaseSwipRefreshFragment<Favorite> implements IFavoriteView {

    private IFavoritePresenter mIFavoritePresenter;


    public static ContentFavoriteFragment newInstance(){
        ContentFavoriteFragment mFavoriteFragment = new ContentFavoriteFragment();


        return mFavoriteFragment;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if( mIFavoritePresenter == null){
            mIFavoritePresenter = getPresenter(new FavoritePresenter(this));
        }
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(Url.USER_FAVORITE).post(bodyBuilder.build()).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.user_favorite_content_list_item;
    }

    @Override
    protected void convertLayout(final CommonViewHolder mCommonViewHolder, final Favorite item, int position) {
        ImageView mImageView = mCommonViewHolder.findViewById(R.id.click_icon);
        mImageView.setBackgroundResource(R.drawable.favorite_sel);
        RxViewListener.clicks(mImageView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIFavoritePresenter.cancelFavorite(item.getContentId(), mRecyclerViewBuilder.getAdapterPosition(mCommonViewHolder));
            }
        });
        ImageView contetnImage = mCommonViewHolder.findViewById(R.id.content_image);
        if(StringUtil.isEmpty(item.getUrl())){
            GlideManager.with(this).loadVideo4other(contetnImage, item.getVideo());
        } else{
            GlideManager.with(this).loadImage4other(contetnImage, item.getUrl());
        }
        TextView commentCount = mCommonViewHolder.findViewById(R.id.comment_count);
        commentCount.setText(item.getCommentCount() + "");
        TextView pariseCount = mCommonViewHolder.findViewById(R.id.parise_count);
        pariseCount.setText(item.getPraiseCount() + "");
        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = getIntentBuilder(ContentActivity.class).putString(Status.Content.ID, item.getContentId())
                        .build();
                startActivity(mIntent);
            }
        });
    }

    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setLayoutManager(RecyclerViewBuilder.VERTICAL, 2).setItemDecoration(null);
    }

    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.MUST;
    }

    @Override
    public void delete(int position) {
        adapter.remove(position);
    }
}
