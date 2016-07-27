/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.adapter</p>
 * <p>File：ProductListItemTwoAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/5/10:25.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Product;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.qiniu.QiNiuUtil;

/**<p>Class：com.tp.venus.module.shop.adapter.ProductListItemTwoAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/5/10:25
 * @version 1.0.0
 */

public class ProductListItemTwoAdapter extends CommonAdapter<Product, CommonViewHolder> {
    private int width;
    private int height;

    public ProductListItemTwoAdapter(Context mContext, @LayoutRes int resources, int width, int height) {
        super(mContext, resources);
        this.width = width;
        this.height = height;
    }


    @Override
    public void convert(CommonViewHolder mCommonViewHolder, final Product item, int position) {
        ImageView product_image = mCommonViewHolder.findViewById(R.id.product_image);
        String url = QiNiuUtil.getImage(item.getMainImage(), width, height);
        GlideManager.with(getContext()).loadImage4other(product_image, url);

        TextView product_title = mCommonViewHolder.findViewById(R.id.product_title);
        product_title.setText(StringUtil.splitString(item.getTitle(), 20));

        TextView price = mCommonViewHolder.findViewById(R.id.price);
        price.setText("￥" + item.getRealPrice());

        ImageView shop_nor = mCommonViewHolder.findViewById(R.id.shop_nor);
        RxViewListener.clicks(shop_nor, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                gotoProductActivity(item.getId());
            }
        });
        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                gotoProductActivity(item.getId());
            }
        });
    }

    //跳转产品详情页
    private void gotoProductActivity(String productId){
        Intent mIntent = IntentBuilder.create(getContext(), ProductActivity.class).putString(Status.Product.ID, productId).build();
        getContext().startActivity(mIntent);
    }

}
