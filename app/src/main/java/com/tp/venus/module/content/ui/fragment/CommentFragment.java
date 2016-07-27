/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.fragment</p>
 * <p>File：CommentFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/19/10:06.</p>
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
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.content.bean.Comment;
import com.tp.venus.module.content.presenter.ICommentPresenter;
import com.tp.venus.module.content.presenter.impl.CommentPresenter;
import com.tp.venus.module.content.ui.view.ICommentView;

/**<p>Class：com.tp.venus.module.content.ui.fragment.CommentFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/19/10:06
 * @version 1.0.0
 */

public class CommentFragment extends BaseCommentFragment<Comment> implements ICommentView{

    private String contentId;


    public static CommentFragment newInstance(String contentId){
        CommentFragment mCommentFragment =  new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Status.Content.ID, contentId);
        mCommentFragment.setArguments(bundle);
        return mCommentFragment;
    }



    @Override
    protected ICommentPresenter initCommentPresenter() {
        return getPresenter(new CommentPresenter(this));
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentId = getArguments().getString(Status.Content.ID);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Request buildRequest( Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(Url.COMMENT_SEARCH + contentId ).post(bodyBuilder.build()).build();
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, Comment item, int position) {
        showComment(mCommonViewHolder, item, position);
    }

    @Override
    public int getItemLayout() {
        return R.layout.comment_list_item;
    }

    @Override
    public void sendComment(Comment comment) {
       sendEvent(comment);
    }
}
