/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.adapter</p>
 * <p>File：OrderDetailAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/5/15:08.</p>
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
import com.tp.venus.module.shop.bean.OrderDetail;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.adapter.OrderDetailAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/5/15:08
 * @version 1.0.0
 */

public class OrderDetailAdapter extends CommonAdapter<OrderDetail, CommonViewHolder> {

    public OrderDetailAdapter(Context mContext, @LayoutRes int resources) {
        super(mContext, resources);
    }

    public OrderDetailAdapter(Context mContext, @LayoutRes int resources, List<OrderDetail> datas) {
        super(mContext, resources, datas);
    }

    @Override
    public void convert(CommonViewHolder mCommonViewHolder, final OrderDetail item, int position) {
        ImageView product_image = mCommonViewHolder.findViewById(R.id.product_image);
        GlideManager.with(getContext()).loadImage4other(product_image, item.getMainImage());

        TextView product_title = mCommonViewHolder.findViewById(R.id.product_title);
        product_title.setText(StringUtil.splitString(item.getTitle(), 30));

        TextView product_sku = mCommonViewHolder.findViewById(R.id.product_sku);
        product_sku.setText(item.getSelectedSku());

        TextView buyCount = mCommonViewHolder.findViewById(R.id.buyCount);
        buyCount.setText("X  " + item.getBuyCount());

        TextView product_price = mCommonViewHolder.findViewById(R.id.product_price);
        product_price.setText("￥" + item.getRealPrice());
        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = IntentBuilder.create(getContext(), ProductActivity.class)
                        .putString(Status.Product.ID, item.getProductId()).build();
                getContext().startActivity(mIntent);
            }
        });
    }
}
