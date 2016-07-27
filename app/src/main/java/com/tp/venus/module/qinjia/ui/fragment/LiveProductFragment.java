/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.ui.fragment</p>
 * <p>File：LiveProductFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:37.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Constant;
import com.tp.venus.config.Status;
import com.tp.venus.module.qinjia.entity.LiveProduct;
import com.tp.venus.module.qinjia.persenter.impl.ListProductPersenter;
import com.tp.venus.module.qinjia.ui.view.IListProductView;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.qinjia.ui.fragment.LiveProductFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/6/30/18:37
 */

public class LiveProductFragment extends BaseFragment implements IListProductView {

    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String programId = Constant.PROGRAM_ID;
    private ListProductPersenter mListProductPersenter;
    private LiveProductAdapter mLiveProductAdapter;

    public static LiveProductFragment newInstance(String programId) {
        LiveProductFragment mLiveProductFragment = new LiveProductFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Status.ID, programId);
        mLiveProductFragment.setArguments(bundle);
        return mLiveProductFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_recyclerview, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String id = getArguments().getString(Status.ID);
        if (StringUtil.isNotEmpty(id)) {
            programId = id;
        }
        if (mListProductPersenter == null) {
            mListProductPersenter = new ListProductPersenter(this);
        }
        mLiveProductAdapter = new LiveProductAdapter(this.getContext(), R.layout.shop_product_list_item_one);
        RecyclerViewBuilder.create(mRecyclerView).setAdapter(mLiveProductAdapter)
                .setDefaultItemDecoration()
                .build();
        mListProductPersenter.findLiveProduct(programId);

//R.layout.shop_product_list_item_one
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    class LiveProductAdapter extends CommonAdapter<LiveProduct, CommonViewHolder>{

        public LiveProductAdapter(Context mContext, @LayoutRes int resources) {
            super(mContext, resources);
        }

        @Override
        public void convert(CommonViewHolder mCommonViewHolder, final LiveProduct item, int position) {
            ImageView shop_cart_product_image = mCommonViewHolder.findViewById(R.id.shop_cart_product_image);
            GlideManager.with(LiveProductFragment.this).loadImage4other(shop_cart_product_image, item.getMainImage());

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
                    Intent mIntent =  IntentBuilder.create(getContext(), ProductActivity.class).putString(Status.Product.ID, item.getProductId()).build();
                    startActivity(mIntent);
                }
            });
        }
    }



    @Override
    public void showProduct(List<LiveProduct> liveProducts) {
        mLiveProductAdapter.clear();
        mLiveProductAdapter.addAll(liveProducts);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
