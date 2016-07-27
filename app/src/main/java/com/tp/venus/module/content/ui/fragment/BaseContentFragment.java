/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.content.ui.fragment</p>
 * <p>File：BaseContentFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/25/16:54.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.common.adapter.ImageStaticPagerAdapter;
import com.tp.venus.module.content.bean.ContentImage;
import com.tp.venus.module.content.bean.ContentResult;
import com.tp.venus.module.content.presenter.IContentPresenter;
import com.tp.venus.module.content.presenter.impl.ContentPresenter;
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

/**<p>Class：com.tp.venus.module.content.ui.fragment.BaseContentFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/25/16:54
 * @version 1.0.0
 */

public abstract class BaseContentFragment<T> extends BaseSwipRefreshFragment<T>  {

    protected int width;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        width = ResourcesUtil.getPoint(mContext).x;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 可重写
     * @param mIContentView
     */
    protected IContentPresenter initContentPresenter(IContentView mIContentView){
        return  getPresenter(new ContentPresenter(mIContentView));
    }

    /**
     * 显示内容
     * @param mCommonViewHolder
     * @param mContentResult
     * @param position
     */
    protected void showContent(CommonViewHolder mCommonViewHolder, final ContentResult mContentResult, int position){
        final ContentAction mContentAction = new ContentAction(mContentResult);
        mContentAction.mIContentPresenter = initContentPresenter(mContentAction);
        //购买按钮
        RippleView buy = mCommonViewHolder.findViewById(R.id.buy);
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
        ImageView favoriteImage = mCommonViewHolder.findViewById(R.id.favorite_image);
        mContentAction.setFavorite(favoriteImage);

        //点赞
        ImageView parised = mCommonViewHolder.findViewById(R.id.parise);
        mContentAction.setParised(parised);

        //更多按钮
        ImageView more = mCommonViewHolder.findViewById(R.id.more);
        if( isHome() ){
            more.setVisibility(View.VISIBLE);
            RxViewListener.clicks(more, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                   /* sendBusSetToUserId(mContentResult);*/
                    MoreDialogFragment mMoreDialogFragment = MoreDialogFragment.newInstance(mContentResult.getId(), mContentResult.getUserId());
                    mMoreDialogFragment.show(getFragmentManager(), "more");
                }
            });
        } else {
            more.setVisibility(View.GONE);
        }

        //轮播图片
        MyRollPagerView mMyRollPagerView = mCommonViewHolder.findViewById(R.id.mRollPagerView);
        mMyRollPagerView.setHintView(new MyTextHintView(mContext, ResourcesUtil.getColor(mContext, R.color.themeTextColor)));
        mMyRollPagerView.setPlayDelay(0);
        mMyRollPagerView.setHintPadding(30,30,30,30);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mMyRollPagerView.getLayoutParams();
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = width;
        mMyRollPagerView.setLayoutParams(lp);
        mContentAction.setImageViewPager( mContentResult.getImages(), mMyRollPagerView );
    }


    /**
     * 是否为主界面
     * @return
     */
    protected boolean isHome(){
        return true;
    }

  /*  //发送事件改变toUserId
    protected void sendBusSetToUserId(ContentResult mContentResult){
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
*/


    public  class ContentAction implements IContentView{

        public boolean isPraise;
        public boolean isFavorite;
        public String contentId;
        public ImageView favoriteImage;
        public ImageView parised;
        public IContentPresenter mIContentPresenter;
        private ContentResult mContentResult;


        public ContentAction(ContentResult mContentResult){
            this.mContentResult = mContentResult;
        }

        public void setFavorite(ImageView favoriteImage){
            this.favoriteImage = favoriteImage;
            favorite(mContentResult.isFavorite());
            RxViewListener.clicks(favoriteImage, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    mIContentPresenter.favorite(mContentResult.getId(), !isFavorite);
                }
            });
        }

        public void setParised(ImageView parised){
            this.parised = parised;
            praise(mContentResult.isPraise(), null);
            RxViewListener.clicks( parised, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    mIContentPresenter.praise(mContentResult.getId(), !isPraise);
                }
            });

        }

        @Override
        public void showContentInfo(ContentResult item, int postion) {

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
        public void showError(String message) {
            BaseContentFragment.this.showError(message);
        }

        @Override
        public void goLoginView() {
            BaseContentFragment.this.goLoginView();
        }

        @Override
        public Context getCurrentContext() {
            return BaseContentFragment.this.getCurrentContext();
        }


        //设置图片轮播
        public void setImageViewPager(List<ContentImage> images,  MyRollPagerView mMyRollPagerView){
            if(CollectionUtils.isEmpty(images)){
                return ;
            }
            ImageStaticPagerAdapter mImageStaticPagerAdapter =
                    new ImageStaticPagerAdapter(getFragment(), width, width);
            mImageStaticPagerAdapter.addAll(images);
            mMyRollPagerView.setAdapter(mImageStaticPagerAdapter);
        }

    }
}
