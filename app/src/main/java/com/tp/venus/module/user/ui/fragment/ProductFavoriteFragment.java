/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.user.ui.fragment</p>
 * <p>File：ProductFavoriteFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/11/13:32.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.fragment;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.user.ui.fragment.ProductFavoriteFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/11/13:32
 * @version 1.0.0
 */

public class ProductFavoriteFragment extends BaseSwipRefreshFragment<Product> {

    public static ProductFavoriteFragment newInstance(){
        return new ProductFavoriteFragment();
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        RequestBody mRequestBody = bodyBuilder.setParam("type", Status.Favorite.PRODUCT).build();
        return builder.url(Url.USER_FAVORITE).post(mRequestBody).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_product_list_item_one;
    }

    @Override
    protected void convertLayout(CommonViewHolder mCommonViewHolder, final Product item, int position) {
        ImageView shop_cart_product_image = mCommonViewHolder.findViewById(R.id.shop_cart_product_image);
        GlideManager.with(this).loadImage4other(shop_cart_product_image, item.getMainImage());

        TextView product_title = mCommonViewHolder.findViewById(R.id.product_title);
        product_title.setText(StringUtil.splitString(item.getTitle(), 30));

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
    protected int getTokenStatus() {
        return Status.TokenStatus.MUST;
    }
}
