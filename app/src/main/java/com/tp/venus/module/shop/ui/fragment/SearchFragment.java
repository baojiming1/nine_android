/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：SearchFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/9/16:33.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.qiniu.QiNiuUtil;

/**<p>Class：com.tp.venus.module.shop.ui.fragment.SearchFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/9/16:33
 * @version 1.0.0
 */

public class SearchFragment extends BaseSwipRefreshFragment<Product> {

    private String keyword;
    private int imageW;

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageW = ResourcesUtil.getDimens(getContext(), R.dimen.dp80);
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        RequestBody mRequestBody = bodyBuilder.setParam("keyword", keyword).build();
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_PRODUCT_SEARCH).post(mRequestBody).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_product_list_item_one;
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, final Product item, int position) {
        ImageView product_image = mCommonViewHolder.findViewById(R.id.shop_cart_product_image);
        String url = QiNiuUtil.getImage(item.getMainImage(), imageW, imageW);
        GlideManager.with(this).loadImage4other(product_image, url);

        TextView product_title = mCommonViewHolder.findViewById(R.id.product_title);
        product_title.setText(StringUtil.splitString(item.getTitle(), 40));

        TextView product_real_price = mCommonViewHolder.findViewById(R.id.product_real_price);
        product_real_price.setText("￥" + item.getRealPrice());

        TextView buyCount = mCommonViewHolder.findViewById(R.id.buyCount);
        buyCount.setText("");
        buyCount.setBackgroundResource(R.drawable.cart);
        //buyCount.setVisibility(View.GONE);

        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = getIntentBuilder(ProductActivity.class).putString(Status.Product.ID, item.getId()).build();
                startActivity(mIntent);
            }
        });

    }

    @Override
    protected boolean lazyLoad() {
        return true;
    }

    /**
     * 开始搜索
     * @param keyword
     */
    public void searchByKeyword(String keyword){
        if(StringUtil.isEmpty(keyword)){
            return ;
        }
        this.keyword = keyword;
        sendHttp();
    }

    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL, R.drawable.divider_bg);
    }

    @Override
    public void addData(PageResult<Product> pageResult) {
        super.addData(pageResult);
    }

}
