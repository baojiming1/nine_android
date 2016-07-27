/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.fragment</p>
 * <p>File：ContentInfoFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/19/11:26.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.common.adapter.ImageStaticPagerAdapter;
import com.tp.venus.module.content.bean.Comment;
import com.tp.venus.module.content.bean.ContentImage;
import com.tp.venus.module.content.bean.ContentResult;
import com.tp.venus.module.content.event.CommentEvent;
import com.tp.venus.module.content.presenter.ICommentPresenter;
import com.tp.venus.module.content.presenter.IContentPresenter;
import com.tp.venus.module.content.presenter.impl.CommentPresenter;
import com.tp.venus.module.content.presenter.impl.ContentPresenter;
import com.tp.venus.module.content.ui.view.ICommentView;
import com.tp.venus.module.content.ui.view.IContentView;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.widget.MyRollPagerView;
import com.tp.venus.widget.MyTextHintView;
import com.tp.venus.widget.RippleView;

import java.util.List;

/**<p>Class：com.tp.venus.module.content.ui.fragment.ContentInfoFragment</p>
 * <p>Description：</p>
 * <pre>
 *       帖子详情页
 * </pre>
 * @author 鲍建明
 * @date 2015/10/19/11:26
 * @version 1.0.0
 */

public class ContentInfoFragment extends BaseCommentFragment<Comment> implements ICommentView, IContentView {

    private IContentPresenter mIContentPresenter;
    private View contentView;

    private String contentId;
/*    private RxBus mRxBus;
    private CompositeSubscription mCompositeSubscription;*/
/*    private ICommentPresenter mICommentPresenter;*/

    /** content **/
    private RippleView buy;
    private ImageView favoriteImage;
    private ImageView parised;
    private MyRollPagerView mMyRollPagerView;
    private ImageView more;
    private boolean isPraise;
    private boolean isFavorite;
    private int width;



    public static ContentInfoFragment newInstance(String contentId){
        ContentInfoFragment mContentInfoFragment =  new ContentInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Status.Content.ID, contentId);
        mContentInfoFragment.setArguments(bundle);
        return mContentInfoFragment;
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, Comment item, int position) {
        showComment(mCommonViewHolder, item, position);
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentId = getArguments().getString(Status.Content.ID);
        initContentView(inflater, container);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    private void initContentView(LayoutInflater inflater, ViewGroup container){
        if( contentView == null ){
            contentView = inflater.inflate(R.layout.content_list_item, container, false);
        }
        if( mIContentPresenter == null){
            mIContentPresenter = getPresenter(new ContentPresenter(this));
        }
        width = ResourcesUtil.getPoint(mContext).x;
        buy = (RippleView) contentView.findViewById(R.id.buy);
        favoriteImage = (ImageView) contentView.findViewById(R.id.favorite_image);
        parised = (ImageView) contentView.findViewById(R.id.parise);
        mMyRollPagerView = (MyRollPagerView) contentView.findViewById(R.id.mRollPagerView);
        mMyRollPagerView.setHintView(new MyTextHintView(mContext, ResourcesUtil.getColor(mContext, R.color.themeTextColor)));
        mMyRollPagerView.setPlayDelay(0);
        mMyRollPagerView.setHintPadding(30,30,30,30);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mMyRollPagerView.getLayoutParams();
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = width;
        mMyRollPagerView.setLayoutParams(lp);
        more = (ImageView) contentView.findViewById(R.id.more);
    }


    @Override
    public Request buildRequest( Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(Url.COMMENT_SEARCH + contentId ).post(bodyBuilder.build()).build();
    }


    @Override
    public int getItemLayout() {
        return R.layout.comment_list_item;
    }


    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.addHeaderView(contentView);
    }


    @Override
    protected void sendSearch(Request request, @Status.TokenStatus int tokenStatus) {
        super.sendSearch(request, tokenStatus);
        if( mRequestBodyBuilder.getPageNow() == 1){
            mIContentPresenter.contentDetails(contentId);
        }
    }

    @Override
    public void showContentInfo(final ContentResult mContentResult, int postion) {
        sendBusSetToUserId(mContentResult);
        //购买
        if( StringUtil.isNotEmpty(mContentResult.getProductId())){
            buy.setVisibility(View.VISIBLE);
            RxViewListener.clicks(buy, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    Intent mIntent = getIntentBuilder(ProductActivity.class).putString(Status.Product.ID, mContentResult.getProductId()).build();
                    startActivity(mIntent);
                }
            });
        } else {
            buy.setVisibility(View.GONE);
        }

        //收藏
        favorite(mContentResult.isFavorite());
        RxViewListener.clicks(favoriteImage, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIContentPresenter.favorite(mContentResult.getId(), !isFavorite);
            }
        });

        //点赞
        praise(mContentResult.isPraise(), null);
        RxViewListener.clicks( parised, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIContentPresenter.praise(mContentResult.getId(), !isPraise);
            }
        });
        more.setVisibility(View.GONE);
        setImageViewPager( mContentResult.getImages() );
    }

    //发送事件改变toUserId
    public void sendBusSetToUserId(ContentResult mContentResult){
        if( mRxBus == null){
            return ;
        }
        if( StringUtil.isNotEmpty(mContentResult.getUserId())){
            CommentEvent mCommentEvent = new CommentEvent();
            mCommentEvent.type = Status.Comment.SET_TOUSERID;
            mCommentEvent.toUserId = mContentResult.getUserId();
            mRxBus.send(mCommentEvent);
        }

    }

    //设置图片轮播
    public void setImageViewPager(List<ContentImage> images){
        if(CollectionUtils.isEmpty(images)){
            return ;
        }
        ImageStaticPagerAdapter mImageStaticPagerAdapter =
                new ImageStaticPagerAdapter(getFragment(), width, width);
        mImageStaticPagerAdapter.addAll(images);
        mMyRollPagerView.setAdapter(mImageStaticPagerAdapter);
    }

    @Override
    public void praise(boolean isPraise, User user) {
        this.isPraise = isPraise;
        if( this.isPraise ){                            //点赞
            parised.setBackgroundResource(R.drawable.parised_sel);
        } else {                                        //取消点赞
            parised.setBackgroundResource(R.drawable.parised_nor);
        }
    }

    @Override
    public void favorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
        if( this.isFavorite ){       //收藏
            favoriteImage.setBackgroundResource(R.drawable.favorite_sel);
        }else {                 //取消收藏
            favoriteImage.setBackgroundResource(R.drawable.favorite_nor);
        }
    }

    @Override
    public void sendComment(Comment comment) {
        sendEvent(comment);
    }

    @Override
    protected ICommentPresenter initCommentPresenter() {
        return getPresenter(new CommentPresenter(this));
    }
}
