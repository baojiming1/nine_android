/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.content.ui.fragment</p>
 * <p>File：BaseCommentFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/26/11:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxBus;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.content.bean.Comment;
import com.tp.venus.module.content.event.CommentEvent;
import com.tp.venus.module.content.presenter.ICommentPresenter;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.TimeUtils;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.util.qiniu.QiNiuUtil;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**<p>Class：com.tp.venus.module.content.ui.fragment.BaseCommentFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/26/11:24
 * @version 1.0.0
 */

public abstract  class BaseCommentFragment<T> extends BaseSwipRefreshFragment<T>  {

    protected GlideManager mGlideManager;
    protected int width;

    protected RxBus mRxBus;
    protected CompositeSubscription mCompositeSubscription;
    protected ICommentPresenter mICommentPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxBus = getRxBus();
    }

    @Override
    public void onStart() {
        super.onStart();
        mCompositeSubscription = new CompositeSubscription();
        mCompositeSubscription.add(mRxBus.toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if( o instanceof CommentEvent){
                    CommentEvent mCommentEvent = (CommentEvent) o;
                    switch (mCommentEvent.type){
                        case Status.Comment.SEND__EVENT:
                            mICommentPresenter.save(mCommentEvent.contentId, mCommentEvent.message,
                                    mCommentEvent.toUserId, mCommentEvent.parent);
                            break;
                    }
                }
            }
        }));
    }

    @Override
    public void onStop() {
        super.onStop();
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mCompositeSubscription != null){
            mCompositeSubscription.clear();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        width = ResourcesUtil.getDimens(getContext(), R.dimen.dp50);
        if( mICommentPresenter == null){
            mICommentPresenter = initCommentPresenter();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 出事化回复的presenter
     */
    protected abstract ICommentPresenter initCommentPresenter();


    public void showComment(CommonViewHolder mCommonViewHolder, final Comment item, int position){
        TextView time = mCommonViewHolder.findViewById(R.id.comment_time);
        time.setText(TimeUtils.getTime(item.getCreateTime()));

        User sponsor = item.getSponsor();             //发起者
        CircleImageView mCircleImageView = mCommonViewHolder.findViewById(R.id.comment_icon);
        String url = sponsor.getIcon() == null ? "" : sponsor.getIcon();
        if( mGlideManager == null){
            mGlideManager = GlideManager.with(this);
        }
        url = QiNiuUtil.getImage(url, width, width);
        mGlideManager.loadImage4user(mCircleImageView,  url);
        TextView nickname = mCommonViewHolder.findViewById(R.id.receiver_nickname);
        nickname.setText(sponsor.getNickname());

        //   User receiver = item.getReceiver();               //接受者
        String message = item.getMessage();
           /* if(receiver != null ){
                if( authorId.equals(receiver.getUserId())){             //接受者为作者时
                    message = item.getMessage();
                } else {                    //非作者时
                    message = "回复" + item.getSponsor().getNickname() + ": " + item.getMessage();
                }
            }*/
        TextView commentContent = mCommonViewHolder.findViewById(R.id.comment_content);
        commentContent.setText(message);

        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                CommentEvent mCommentEvent = new CommentEvent();
                mCommentEvent.type = Status.Comment.REPLY_EVENT;
                mCommentEvent.parent = item;
                getRxBus().send(mCommentEvent);
            }
        });
    }


    public void sendEvent(T comment) {
        // adapter.add(comment);
        adapter.add(comment, 0);
        CommentEvent mCommentEvent = new CommentEvent();
        mCommentEvent.type = Status.Comment.CLEAR_EVENT;
        mRxBus.send(mCommentEvent);
        ToastUtil.getInstance().show("评论成功");
    }
}
