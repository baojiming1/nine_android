/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：ShopHomeFragemtn.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/6/16:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
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
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.common.bean.Banner;
import com.tp.venus.module.common.presenter.IBannerPersenter;
import com.tp.venus.module.common.presenter.impl.BannerPersenter;
import com.tp.venus.module.common.ui.view.IBannerView;
import com.tp.venus.module.content.adapter.BannerStaticPagerAdapter;
import com.tp.venus.module.shop.adapter.ClassesAdapter;
import com.tp.venus.module.shop.bean.Classes;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.bean.Special;
import com.tp.venus.module.shop.presenter.ICartCountPresenter;
import com.tp.venus.module.shop.presenter.IHomePresenter;
import com.tp.venus.module.shop.presenter.impl.CartCountPresenter;
import com.tp.venus.module.shop.presenter.impl.HomePresenter;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.module.shop.ui.SearchActivity;
import com.tp.venus.module.shop.ui.ShopCartActivity;
import com.tp.venus.module.shop.ui.SpecialActivity;
import com.tp.venus.module.shop.ui.view.ICartCountView;
import com.tp.venus.module.shop.ui.view.IHomeView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;

import java.util.List;

/**
 * <p>Class：com.tp.venus.module.shop.ui.fragment.ShopHomeFragemtn</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/4/6/16:23
 */

public class ShopHomeFragemtn extends BaseSwipRefreshFragment<Special> implements IBannerView, IHomeView, ICartCountView {

    Toolbar mToolbar;
    RecyclerView classes;
    private FrameLayout mFrameLayout;
    private TextView mBadgeView;

    private View header;
    private IBannerPersenter mIBannerPersenter;
    private IHomePresenter iHomePresenter;
    private ICartCountPresenter mICartCountPresenter;
    private BannerStaticPagerAdapter mBannerStaticPagerAdapter;
    private ClassesAdapter mHomeClassesAdapter;
    private RollPagerView mRollPagerView;
    private int specialWidth;
    private int width;
    private boolean isFirst = true;         //是否是第一次加载

    public static ShopHomeFragemtn newInstance() {
        return new ShopHomeFragemtn();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {                  //可见时
            if( isFirst ){
                sendHttp();
            }
        }                               //不可见时
    }


    @Override
    protected boolean lazyLoad() {
        return true;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        header = inflater.inflate(R.layout.shop_home_header, container, false);
        initHeaderView(header);
        View view = inflater.inflate(R.layout.shop_fragment_home, container, false);
        init(view);
        initPresenter();
        return view;
    }

    private void init(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.mToolbar);
        initShopCartView();
        ToolbarBuilder mToolbarBuilder = ToolbarBuilder.create(mToolbar);
        mToolbarBuilder.getRightTextView().setBackgroundResource(R.drawable.ic_action_action_search);
        mToolbarBuilder.getMidTextView().setBackgroundResource(R.drawable.home_icon);
        mToolbar.setBackgroundResource(R.drawable.shop_top_toolbar_bg);
        RxViewListener.clicks(mToolbarBuilder.getRightTextView(), new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(SearchActivity.class);
            }
        });
        mToolbarBuilder.build();
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

    private void initPresenter(){
        if (mIBannerPersenter == null) {
            mIBannerPersenter = new BannerPersenter(this);
        }
        if( iHomePresenter == null){
            iHomePresenter = new HomePresenter(this);
        }
        if(mICartCountPresenter == null){
            mICartCountPresenter = new CartCountPresenter(this);
        }
    }


    @Override
    protected void sendSearch(Request request, @Status.TokenStatus int tokenStatus) {
        super.sendSearch(request, tokenStatus);
        if (mRequestBodyBuilder.getPageNow() == 1) {
            mIBannerPersenter.searchBanner(Status.Banner.SHOP_HOME);
            iHomePresenter.getHomeClass();
            mICartCountPresenter.showBadgeView();
        }
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_HOME_SPECIAL_LIST).post(bodyBuilder.build()).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_home_list_item;
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, final Special item, int position) {
        ImageView special = mCommonViewHolder.findViewById(R.id.special);
        special.getLayoutParams().height = specialWidth;
        GlideManager.with(this).loadImage(item.getImageUrl()).centerCrop().into(special);
        RecyclerView mRecyclerView = mCommonViewHolder.findViewById(R.id.mRecyclerView);
        ProductAdapter  mProductAdapter = new ProductAdapter(this.getContext(), R.layout.shop_home_special_product_item);
        mProductAdapter.addAll(item.getProducts());
        RecyclerViewBuilder.create(mRecyclerView).setAdapter(mProductAdapter).setLayoutManager(RecyclerViewBuilder.LINER_HORIZONTAL)
                .setSpaceItemDecoration(R.dimen.dp2, RecyclerViewBuilder.DECOR_HORIZONTAL_LIST)
              /*  .setAutoMeasureEnabled(true)*/
                .build();
        RxViewListener.clicks(special, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = IntentBuilder.create(getContext(), SpecialActivity.class).putParcelable(Status.PARCELABLE_KEY, item).build();
                startActivity(mIntent);
            }
        });
    }

    private void initHeaderView(View header) {
        mRollPagerView = (RollPagerView) header.findViewById(R.id.mRollPagerView);
        width = ResourcesUtil.getPoint(this.getContext()).x;
        specialWidth = (int) (width * 0.5);
        int height = (int) (width * 0.5);
        mRollPagerView.getLayoutParams().height = height;
        mRollPagerView.setHintView(new ColorPointHintView(mContext, ResourcesUtil.getColor(mContext, R.color.themeTextColor),
                ResourcesUtil.getColor(mContext, R.color.themeColor)));
        mBannerStaticPagerAdapter = new BannerStaticPagerAdapter(getFragment(), width, height);
        mRollPagerView.setAdapter(mBannerStaticPagerAdapter);
        mRollPagerView.setPlayDelay(0);

        classes = (RecyclerView) header.findViewById(R.id.classes);
        mHomeClassesAdapter = new ClassesAdapter(this.getContext(), R.layout.shop_classes_list_item, width / 5);
        RecyclerViewBuilder.create(classes).setAdapter(mHomeClassesAdapter).setLayoutManager(RecyclerViewBuilder.VERTICAL, 5)
                .setDefaultItemDecoration(RecyclerViewBuilder.DECOR_HORIZONTAL_LIST, R.drawable.divider_bg_fine)
                .setAutoMeasureEnabled(true)
                .build();
    }



    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setDefaultItemDecoration(RecyclerViewBuilder.DECOR_VERTICAL_LIST, R.drawable.divider_bg)
                .setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL)
                .setAutoMeasureEnabled(true)
                .addHeaderView(header);
    }

    @Override
    public void showBanner(List<Banner> datas) {
        if (CollectionUtils.isNotEmpty(datas)) {
            mBannerStaticPagerAdapter.addAll(datas);
        }
    }

    @Override
    public void showClasses(List<Classes> datas) {
        if(CollectionUtils.isNotEmpty(datas)){
            mHomeClassesAdapter.addAll(datas);
        }
    }

    @Override
    public void showBadgeView(Integer count) {
        if( count == null){
            mBadgeView.setText("");
            if( mBadgeView.getVisibility() != View.GONE){
                mBadgeView.setVisibility(View.GONE);
            }
        } else {
           /* if( count.intValue() > 99){
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

    class ProductAdapter extends CommonAdapter<Product, CommonViewHolder>{

        public ProductAdapter(Context mContext, @LayoutRes int resources) {
            super(mContext, resources);
        }


        @Override
        public void convert(CommonViewHolder mCommonViewHolder, final Product item, int position) {
            ImageView product_image = mCommonViewHolder.findViewById(R.id.product_image);
            GlideManager.with(ShopHomeFragemtn.this).loadImage4other(product_image, item.getMainImage());

            TextView product_title = mCommonViewHolder.findViewById(R.id.product_title);
            product_title.setText(StringUtil.splitString(item.getTitle(), 7));

            RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    Intent mIntent = getIntentBuilder(ProductActivity.class).putString(Status.Product.ID, item.getId()).build();
                    startActivity(mIntent);
                }
            });
        }
    }
}
