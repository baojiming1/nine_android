/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.ui.fragment</p>
 * <p>File：MainFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/9/10:07.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Constant;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.common.bean.Banner;
import com.tp.venus.module.common.presenter.IBannerPersenter;
import com.tp.venus.module.common.presenter.impl.BannerPersenter;
import com.tp.venus.module.common.ui.VideoRandomActivity;
import com.tp.venus.module.common.ui.view.IBannerView;
import com.tp.venus.module.content.adapter.BannerStaticPagerAdapter;
import com.tp.venus.module.content.adapter.HotTagAdapter;
import com.tp.venus.module.content.bean.ContentResult;
import com.tp.venus.module.content.bean.Tag;
import com.tp.venus.module.content.presenter.IHotTagPresenter;
import com.tp.venus.module.content.presenter.impl.HotTagPresenter;
import com.tp.venus.module.content.ui.view.IHotTagView;
import com.tp.venus.module.qinjia.entity.LiveProgram;
import com.tp.venus.module.qinjia.persenter.ILivePersenter;
import com.tp.venus.module.qinjia.persenter.impl.LivePersenter;
import com.tp.venus.module.qinjia.ui.PlayActivity;
import com.tp.venus.module.qinjia.ui.view.ILiveView;
import com.tp.venus.module.shop.presenter.ICartCountPresenter;
import com.tp.venus.module.shop.presenter.impl.CartCountPresenter;
import com.tp.venus.module.shop.ui.ShopCartActivity;
import com.tp.venus.module.shop.ui.view.ICartCountView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.ResourcesUtil;

import java.util.List;

import butterknife.ButterKnife;

/**
 * <p>Class：com.tp.venus.module.content.ui.fragment.MainFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/9/10:07
 */

public class MainFragment extends BaseContentFragment<ContentResult> implements IBannerView, IHotTagView, ICartCountView, ILiveView {

    private View header;
    private RollPagerView mRollPagerView;
    private RecyclerView hotTags;
    private IBannerPersenter mIBannerPersenter;
    private ICartCountPresenter mICartCountPresenter;
    private IHotTagPresenter mHotTagPresenter;
    private HotTagAdapter mHotTagAdapter;
    private BannerStaticPagerAdapter mBannerStaticPagerAdapter;
    private View hotTagLayout;
    private FloatingActionButton mFloatingActionButton;
    private Toolbar mToolbar;
    private ILivePersenter mLivePersenter;

    private FrameLayout mFrameLayout;
    private TextView mBadgeView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }




    @Override
    public Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(Url.FOUND_URL).post(bodyBuilder.build()).build();
    }


    @Override
    public View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // header = LayoutInflater.from(mContext).inflate(R.layout.home_fragment_found, null);
        header = inflater.inflate(R.layout.home_fragment_found, container, false);
        initView(header);
        initPresenter();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, ContentResult item, int position) {
        showContent(mCommonViewHolder, item, position);
    }

    private void initPresenter(){
        if (mIBannerPersenter == null) {
            mIBannerPersenter = new BannerPersenter(this);
        }
        if (mHotTagPresenter == null) {
            mHotTagPresenter = new HotTagPresenter(this);
        }
        if(mICartCountPresenter == null){
            mICartCountPresenter = new CartCountPresenter(this);
        }
        if( mLivePersenter == null){
            mLivePersenter = new LivePersenter(this);
        }
    }

    private void init(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.mToolbar);
        initShopCartView();
        ToolbarBuilder mToolbarBuilder = ToolbarBuilder.create(mToolbar);
        mToolbarBuilder.getRightTextView().setBackgroundResource(R.drawable.video_icon);
        mToolbarBuilder.getMidTextView().setBackgroundResource(R.drawable.home_icon);
        mToolbarBuilder.build();

        RxViewListener.clicks(mToolbarBuilder.getRightTextView(), new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mLivePersenter.findLive(Constant.PROGRAM_ID);
            }
        });

        mFloatingActionButton = (FloatingActionButton) view.findViewById(R.id.mFloatingActionButton);
        RxViewListener.clicks(mFloatingActionButton, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mRecyclerViewBuilder.getLayoutManager().scrollToPosition(0);
            }
        });
    }

    //初始化购物车图标
    private void initShopCartView(){
        ViewStub mViewStub = (ViewStub) mToolbar.findViewById(R.id.toolbarLeftView);
        mViewStub.setLayoutResource(R.layout.shop_cart_count_layout);
        mFrameLayout = (FrameLayout) mViewStub.inflate();

        mBadgeView = (TextView) mFrameLayout.findViewById(R.id.mBadgeView);
        int i =  ResourcesUtil.getDimens(getContext(), R.dimen.dp8);
        ViewGroup.LayoutParams p =  mBadgeView.getLayoutParams();
        p.height = i;
        p.width = i;

        ImageView view = (ImageView) mFrameLayout.findViewById(R.id.image);
        view.setImageResource(R.drawable.shop_cart);
        RxViewListener.clicks(mFrameLayout, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(ShopCartActivity.class);
            }
        });
    }


    private void initView(View header) {
        mRollPagerView = (RollPagerView) header.findViewById(R.id.mRollPagerView);
        int width = ResourcesUtil.getPoint(this.getContext()).x;
        int height = (int) (width * 0.75);
        ViewGroup.LayoutParams lp = mRollPagerView.getLayoutParams();
        lp.height = height;
        lp.width = width;
        mRollPagerView.setLayoutParams(lp);

        hotTagLayout = header.findViewById(R.id.hot_tag_layout);
        mRollPagerView.setHintView(new ColorPointHintView(mContext, ResourcesUtil.getColor(mContext, R.color.themeTextColor),
                ResourcesUtil.getColor(mContext, R.color.themeColor)));
        mBannerStaticPagerAdapter = new BannerStaticPagerAdapter(getFragment(), width, height);
        mRollPagerView.setAdapter(mBannerStaticPagerAdapter);
        //mRollPagerView.setHintPadding(0, 30, 0, 30);
        mRollPagerView.setPlayDelay(0);

        hotTags = (RecyclerView) header.findViewById(R.id.hot_tags);
        mHotTagAdapter = new HotTagAdapter(mContext, R.layout.hot_tag_item);
        RecyclerViewBuilder.create(hotTags).setAdapter(mHotTagAdapter).setLayoutManager(RecyclerViewBuilder.LINER_HORIZONTAL)
                .setSpaceItemDecoration(R.dimen.dp10, RecyclerViewBuilder.DECOR_HORIZONTAL_LIST)
                .build();
    }

    @Override
    protected void sendSearch(Request request, @Status.TokenStatus int tokenStatus) {
        super.sendSearch(request, tokenStatus);
        if (mRequestBodyBuilder.getPageNow() == 1) {
            mIBannerPersenter.searchBanner(Status.Banner.ADVERT);
            mHotTagPresenter.searchHotTag();
            mICartCountPresenter.showBadgeView();
        }
    }


    @Override
    public int getItemLayout() {
        return R.layout.content_list_item;
    }


    @Override
    public void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder
                //.setDefaultItemDecoration(RecyclerViewBuilder.DECOR_VERTICAL_LIST, R.drawable.divider_bg)
                .setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL)
                .setNoItemDecoration()
               // .addRecyclerListener(new VideoRecyclerListener())
                .addHeaderView(header);
    }


    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.COMPLET;
    }

    @Override
    public void showBanner(List<Banner> datas) {
        if (CollectionUtils.isNotEmpty(datas)) {
            mBannerStaticPagerAdapter.addAll(datas);
        }
    }


    @Override
    public void showHotTag(List<Tag> datas) {
        int visi = hotTagLayout.getVisibility();
        if (CollectionUtils.isEmpty(datas)) {
            if (visi == View.VISIBLE) {
                hotTagLayout.setVisibility(View.GONE);
            }
        } else {
            if (visi == View.GONE) {
                hotTagLayout.setVisibility(View.VISIBLE);
            }
        }
        mHotTagAdapter.clear();
        mHotTagAdapter.addAll(datas);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showBadgeView(Integer count) {
        if( count == null){
            mBadgeView.setText("");
            if( mBadgeView.getVisibility() != View.GONE){
                mBadgeView.setVisibility(View.GONE);
            }
        } else {
        /*    if( count.intValue() > 99){
                mBadgeView.setText("99+");
            } else {
                mBadgeView.setText(count + "");
            }*/
            mBadgeView.setText("");
            if( mBadgeView.getVisibility() != View.VISIBLE){
                mBadgeView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void playLive(LiveProgram mLiveProgram) {
        //startActivity(VideoRandomActivity.class);
        Intent intent = getIntentBuilder(PlayActivity.class).putParcelable(Status.PARCELABLE_KEY, mLiveProgram).build();
        startActivity(intent);
    }

    @Override
    public void playVideo() {
        startActivity(VideoRandomActivity.class);
    }


   /* class VideoRecyclerListener implements RecyclerView.RecyclerListener {
        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            if (MyVideoPlayManager.isPlaying()) {
                MyVideoPlayManager.stop();
            }
        }
    }*/

}
