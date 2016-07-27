/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：ShopCartFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/21/10:05.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.fragment.BaseSwipRefreshFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.config.Url;
import com.tp.venus.module.shop.adapter.CartAdapter;
import com.tp.venus.module.shop.bean.CheckStatus;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.presenter.IShopCartPresenter;
import com.tp.venus.module.shop.presenter.impl.ShopCartPresenter;
import com.tp.venus.module.shop.ui.OrderTempDetailActivity;
import com.tp.venus.module.shop.ui.view.IShopCartView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**<p>Class：com.tp.venus.module.shop.ui.fragment.ShopCartFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/21/10:05
 * @version 1.0.0
 */

public class ShopCartFragment extends BaseSwipRefreshFragment<ShopCart> implements IShopCartView {


    public static final String CHECK_KEY = "check_json_key";

    public static ShopCartFragment newInstance(){
        return new ShopCartFragment();
    }

    private CheckBox allCheckBox;
    private TextView countPrice;
   // private TextView countFreight;
    private  RippleView statement;
    private IShopCartPresenter mIShopCartPresenter;
    private  CartAdapter mCartAdapter;

    private Map<Integer, CheckStatus> checkmap;

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if( mIShopCartPresenter == null){
            mIShopCartPresenter = getPresenter(new ShopCartPresenter(this));
        }
        initNativeData();
        return super.oncreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        sendHttp();
      //  initNativeData();
    }

    @Override
    protected boolean lazyLoad() {
        return true;
    }

    @Override
    public void dataNull() {
        super.dataNull();
        allCheckBox.setChecked(false);
    }

    //初始化本地数据
    private void initNativeData(){
        checkmap = new HashMap<>();
      /*  if( CollectionUtils.isMapNotEmpty(checkmap)){
            return ;
        }
        String str = SharePreferencesUtils.getString(getContext(), CHECK_KEY);
        if(StringUtil.isNotEmpty(str)){
            checkmap = JSON.parseObject(str, new TypeReference<HashMap<Integer, CheckStatus>>(){});
            //checkmap = (Map<Integer, CartAdapter.CheckStatus>) JSONUtil.parse(str);
        } else {
            checkmap = new HashMap<>();
        }*/
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_CART_LIST).post(bodyBuilder.build()).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_cart_list_item;
    }


    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.MUST;
    }

    /**
     * 设置全选的按钮组件
     * @param bottomLayout
     */
    public void setBottomWidget(View bottomLayout){
        allCheckBox = (CheckBox) bottomLayout.findViewById(R.id.allCheckBox);
        countPrice = (TextView) bottomLayout.findViewById(R.id.countPrice);
        statement = (RippleView) bottomLayout.findViewById(R.id.statement);
      //  countFreight = (TextView) bottomLayout.findViewById(R.id.countFreight);
        RxViewListener.clicks(statement, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIShopCartPresenter.statement();
            }
        });
    }

    @Override
    protected CommonAdapter<ShopCart, CommonViewHolder> getAdapter() {
        mCartAdapter = new CartAdapter(this.getContext(), R.layout.shop_cart_list_item);
        //mCartAdapter.init(allCheckBox, countPrice, countFreight, mIShopCartPresenter, checkmap);
        mCartAdapter.init(allCheckBox, countPrice,  mIShopCartPresenter, checkmap, mRecyclerView);
        return mCartAdapter;
    }

    @Override
    public void changeBuyCount(TextView count, int buyCount, ShopCart product) {
        count.setText(buyCount + "");
        product.setBuyCount(buyCount);
        mCartAdapter.sumPrice();
    }

    @Override
    public void delete(int position, CommonViewHolder mCommonViewHolder) {
        mCartAdapter.remove(mRecyclerViewBuilder.getAdapterPosition(mCommonViewHolder));
        mCartAdapter.sumPrice();
    }

    @Override
    public void statement() {
        ArrayList<ShopCart> products =  mCartAdapter.getCheckProduct();
        if(CollectionUtils.isEmpty(products)){
            ToastUtil.getInstance().show(R.string.not_checked_product);
            return ;
        }
        Intent mIntent = IntentBuilder.create(getContext(), OrderTempDetailActivity.class)
                .putParcelableArrayList(Status.Order.ORDER_KEY, products).build();
        startActivity(mIntent);
    }

    @Override
    public void onStop() {
        super.onStop();
      /*  Map<Integer, CheckStatus> map =  mCartAdapter.getCheckedMap();
        if( CollectionUtils.isMapNotEmpty(map)){
            String result = JSONUtil.toJSONString(map);
            SharePreferencesUtils.putString(this.getContext(), CHECK_KEY, result);
        }*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
