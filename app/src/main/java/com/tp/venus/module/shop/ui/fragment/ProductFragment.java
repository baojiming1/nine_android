/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：ProductFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/16:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.tp.venus.R;
import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.base.fragment.BaseRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.common.adapter.ImageStaticPagerAdapter;
import com.tp.venus.module.shop.adapter.SKUAdapter;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.presenter.ICartCountPresenter;
import com.tp.venus.module.shop.presenter.IProductPresenter;
import com.tp.venus.module.shop.presenter.impl.CartCountPresenter;
import com.tp.venus.module.shop.presenter.impl.ProductPresenter;
import com.tp.venus.module.shop.ui.OrderTempDetailActivity;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.module.shop.ui.ShopCartActivity;
import com.tp.venus.module.shop.ui.view.ICartCountView;
import com.tp.venus.module.shop.ui.view.IProductView;
import com.tp.venus.module.shop.util.SkuUtil;
import com.tp.venus.module.shop.widget.AddSubstractWidget;
import com.tp.venus.util.DoubleUtils;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.LabelView;
import com.tp.venus.widget.RippleView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.shop.ui.fragment.ProductFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/4/20/16:38
 */

public class ProductFragment extends BaseRefreshFragment<Product> implements IProductView, View.OnClickListener, ICartCountView {

    @InjectView(R.id.mRollPagerView)
    RollPagerView mRollPagerView;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.original_price)
    TextView originalPrice;
    @InjectView(R.id.freight)
    TextView freight;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.info)
    TextView info;
    @InjectView(R.id.mLabelView)
    LabelView mLabelView;
    @InjectView(R.id.webview_layout)
    LinearLayout wvLayout;

    WebView detail;

    private boolean isFavorite = false;
    private View botoomLayout;
    private Button addCart;
    private View favorite;
    private View shop_cart;
    private TextView mBadgeView;
    private ImageView product_favorite;
    private TextView favorite_tv;
    private TextView midText;

    private IProductPresenter mIProductPresenter;
    public String productId;
    private ImageStaticPagerAdapter mImageStaticPagerAdapter;
    private Product mProduct;
    ToolbarBuilder mToolbarBuilder;
    private ProductCartDialog mProductCartDialog;
    private ICartCountPresenter mICartCountPresenter;

    public static ProductFragment newInstance(String productId) {
        ProductFragment mProductFragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Status.Product.ID, productId);
        mProductFragment.setArguments(bundle);
        return mProductFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mIProductPresenter = getPresenter();
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        RequestBody mRequestBody = bodyBuilder.setParam("productId", productId).build();
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_PRODUCT_GET_INFO).post(mRequestBody).build();
    }

    @Override
    protected int getLayout() {
        return R.layout.shop_fragment_product_detail;
    }

    @Override
    protected IProductPresenter getPresenter() {
        if (mIProductPresenter == null) {
            mIProductPresenter = getPresenter(new ProductPresenter(this));
        }
        if( mICartCountPresenter == null){
            mICartCountPresenter = new CartCountPresenter(this);
        }
        return mIProductPresenter;
    }

    @Override
    protected void sendSearch(Request request, @Status.TokenStatus int tokenStatus) {
        super.sendSearch(request, tokenStatus);
        mICartCountPresenter.showBadgeView();
    }

    private void init() {
        productId = getArguments().getString(Status.Product.ID);
        detail = new WebView(ApplicationHandler.getApp().getApplicationContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        detail.setLayoutParams(params);
        WebSettings mWebSettings = detail.getSettings();
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
       /* mWebSettings.setLoadWithOverviewMode(true);*/
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
      /*  mWebSettings.setUseWideViewPort(true);*/
        wvLayout.addView(detail);
     /*   detail.setVerticalScrollBarEnabled(false);
        detail.setVerticalScrollbarOverlay(false);
        detail.setHorizontalScrollBarEnabled(false);
        detail.setHorizontalScrollbarOverlay(false);*/
        mImageStaticPagerAdapter = new ImageStaticPagerAdapter(this.getContext());
        mRollPagerView.setAdapter(mImageStaticPagerAdapter);
        mRollPagerView.setHintView(new ColorPointHintView(getContext(), ResourcesUtil.getColor(getContext(), R.color.themeTextColor),
                ResourcesUtil.getColor(getContext(), R.color.themeColor)));
        mToolbarBuilder = ((ProductActivity) getActivity()).mToolbarBuilder;
    }

    @Override
    public void showProductDetail(Product mProduct) {
        if (mProduct == null) {
            return;
        }
        this.mProduct = mProduct;
        favoriteSuccess(mProduct.getIsFavorite() == null ? false : mProduct.getIsFavorite());
        mToolbarBuilder.setTitle(mProduct.getProductName()).build();
        mImageStaticPagerAdapter.addAll(mProduct.getProductImages());
        price.setText("￥" + mProduct.getRealPrice());
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        originalPrice.setText("￥" + mProduct.getPrice());
        freight.setText("￥" + mProduct.getFreight());
        title.setText(mProduct.getTitle());
        midText.setText(StringUtil.splitString(mProduct.getTitle(), 15));
        String sku = SkuUtil.parseSku(mProduct.getProductSKUs());
        info.setText(sku);
        detail.loadDataWithBaseURL(null,mProduct.getDetail(), "text/html",  "UTF-8", null);
        if (!mProduct.getOnSaleStatus()) {                  //下架商品
            mLabelView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addCartSuccess() {
        String s = mBadgeView.getText().toString();
        int i = DoubleUtils.getInstance().toInteger(s, 0);
        showBadgeView( i+ 1) ;
//        mBadgeView.setText( (i + 1) + "");
        mProductCartDialog.hide();
        ToastUtil.getInstance().show("添加购物车成功");
    }

    @Override
    public void favoriteSuccess(boolean isFavorite) {
        this.isFavorite = isFavorite;
        if(isFavorite){             //收藏
            product_favorite.setBackgroundResource(R.drawable.product_favorite_select);
            favorite_tv.setText("已收藏");
        } else {
            product_favorite.setBackgroundResource(R.drawable.product_favorite_normal);
            favorite_tv.setText("收藏");
        }
    }


    @Override
    public void buyNowSuccess(ShopCart simpleProduct) {
        ArrayList<ShopCart> list = new ArrayList<>();
        list.add(simpleProduct);
        Intent mIntent = getIntentBuilder(OrderTempDetailActivity.class).putParcelableArrayList(Status.Order.ORDER_KEY, list)
                .putBoolean(Status.Order.BUY_NOW_KEY, true)
                .build();
        startActivity(mIntent);
    }


    public void setBotoomLayout(View botoomLayout, TextView midText){
        this.botoomLayout = botoomLayout;
        this.midText = midText;
        shop_cart = botoomLayout.findViewById(R.id.shop_cart);
        favorite = botoomLayout.findViewById(R.id.favorite);
        addCart = (Button) botoomLayout.findViewById(R.id.addCart);
        shop_cart.setOnClickListener(this);
        favorite.setOnClickListener(this);
        addCart.setOnClickListener(this);
        product_favorite = (ImageView) botoomLayout.findViewById(R.id.product_favorite);
        favorite_tv = (TextView) botoomLayout.findViewById(R.id.favorite_tv);
        View shop_cart = botoomLayout.findViewById(R.id.shop_cart);
        mBadgeView = (TextView) botoomLayout.findViewById(R.id.mBadgeView);
        RxViewListener.clicks(shop_cart, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                startActivity(ShopCartActivity.class);
            }
        });
    }



    @Override
    public void show(Product o) {
        showProductDetail(o);
    }


    @Override
    public void onDestroyView() {
        if( mProductCartDialog != null){
            mProductCartDialog.onDestroyView();
            mProductCartDialog = null;
        }
        wvLayout.removeAllViews();
        detail.stopLoading();
        detail.removeAllViews();
        detail.destroy();
        detail = null;
        wvLayout = null;
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.favorite:
                if( mProduct != null ){
                    mIProductPresenter.favorite(mProduct.getId(), !isFavorite);
                }
                break;
            case R.id.shop_cart:
                startActivity(ShopCartActivity.class);
                break;
            case R.id.addCart:
                if (mProductCartDialog == null) {
                    mProductCartDialog = new ProductCartDialog();
                }
                if (mProduct != null) {
                    mProductCartDialog.showDialog(mProduct, v.getId());
                }
                break;
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
            if( count.intValue() > 99){
                mBadgeView.setText("99+");
            } else {
                mBadgeView.setText(count + "");
            }
            if( mBadgeView.getVisibility() != View.VISIBLE){
                mBadgeView.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     * SKU选择对话框
     */
    class ProductCartDialog implements AddSubstractWidget.OnClickListener {
        private DialogPlus mDialogPlus;
        private ImageView headImageView;
        private TextView headTitle;
        private TextView headPrice;
        private RippleView footerNext;
        private SKUAdapter mSKUAdapter;
        private AddSubstractWidget mAddSubstractWidget;

        public ProductCartDialog() {
            mDialogPlus = initDialogPlus();
        }


        public void showDialog(Product mProduct, int viewId) {
            mSKUAdapter.addAll(mProduct.getProductSKUs());
            initHeader(mProduct);
            initFooter(viewId);
            mDialogPlus.show();
        }

        private void initHeader(Product mProduct) {
            GlideManager.with(getContext()).loadImage4user(headImageView, mProduct.getMainImage());
            headTitle.setText(mProduct.getTitle());
            headPrice.setText(mProduct.getRealPrice());
        }

        private void initFooter(int viewId) {
            switch (viewId) {
                case R.id.addCart:
                    footerNext.setText(R.string.addCart);
                    footerNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!mProduct.getOnSaleStatus()) {          //商品已经下架
                                ToastUtil.getInstance().show("该商品已经下架");
                                return ;
                            }
                            String sku = mSKUAdapter.getCheckSku();
                            mIProductPresenter.addCart(productId, mAddSubstractWidget.getBuyCount(), sku);
                        }
                    });
                    break;
            /*    case R.id.buy_now:
                    footerNext.setText(R.string.buy_now);
                    footerNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String sku = mSKUAdapter.getCheckSku();
                            mIProductPresenter.buyNow(mProduct, sku, mAddSubstractWidget.getBuyCount());
                        }
                    });
                    break;*/
            }

        }

        //初始化dialog
        private DialogPlus initDialogPlus() {
            mSKUAdapter = new SKUAdapter(getContext());
            DialogPlus mDialogPlus = DialogPlus.newDialog(getContext()).
                    setAdapter(mSKUAdapter)
                    .setHeader(R.layout.shop_product_shop_dialog_head_view)
                    .setFooter(R.layout.shop_product_shop_dialog_footer_view)
                    .setGravity(Gravity.BOTTOM) // This will enable the expand feature, (similar to android L share dialog)
                    .create();
            View headerView = mDialogPlus.getHeaderView();
            headImageView = (ImageView) headerView.findViewById(R.id.shop_view_image);
            headTitle = (TextView) headerView.findViewById(R.id.shop_view_title);
            headPrice = (TextView) headerView.findViewById(R.id.shop_view_price);
            View footerView = mDialogPlus.getFooterView();
            footerNext = (RippleView) footerView.findViewById(R.id.next);
            mAddSubstractWidget = new AddSubstractWidget(footerView, getContext());
            mAddSubstractWidget.addOnclickListener(this);
            return mDialogPlus;
        }

        @Override
        public void onAddClickListener(View view, EditText buyCount, int count) {
            buyCount.setText(count + "");
        }

        @Override
        public void onSubtractListener(View view, EditText buyCount, int count) {
            buyCount.setText(count + "");
        }

        @Override
        public void onEditChangeListener(View view) {

        }

        public void hide(){
            mDialogPlus.dismiss();
        }

        public void onDestroyView(){
            if( mDialogPlus != null){
                mDialogPlus.dismiss();
                mDialogPlus = null;
            }
            if( mSKUAdapter != null){
                mSKUAdapter = null;
            }
            if( mAddSubstractWidget != null){
                mAddSubstractWidget = null;
            }

        }
    }

}
