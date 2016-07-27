/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：OrderTempDetailFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/30/14:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Address;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.presenter.IOrderTempDetailPresenter;
import com.tp.venus.module.shop.presenter.impl.OrderTempDetailPresenter;
import com.tp.venus.module.shop.ui.AddressManagerActivity;
import com.tp.venus.module.shop.ui.PayActivity;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.module.shop.ui.view.IOrderTempDetailView;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.DoubleUtils;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;
import com.tp.venus.widget.RippleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * <p>Class：com.tp.venus.module.shop.ui.fragment.OrderTempDetailFragment</p>
 * <p>Description：</p>
 * <pre>
 *      订单详情临时页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/3/30/14:24
 */

public class OrderTempDetailFragment extends BaseFragment implements IOrderTempDetailView, RxViewListener.Action {


    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.consignee)
    TextView consignee;
    @InjectView(R.id.mobile)
    TextView mobile;
  /*  @InjectView(R.id.idcard)
    TextView idcard;*/
    @InjectView(R.id.address_message)
    TextView address_message;
    @InjectView(R.id.countProduct)
    TextView countProduct;
    @InjectView(R.id.order_message)
    EditText orderMessage;
    @InjectView(R.id.product_price)
    TextView productPrice;
    @InjectView(R.id.product_freight)
    TextView productFreight;

    @InjectView(R.id.address_selected)
    View address_selected;
    @InjectView(R.id.address_normal)
    View address_normal;

    private TempOrderDetailAdapter mTempOrderDetailAdapter;
    private ArrayList<ShopCart> products;
    private IOrderTempDetailPresenter mIOrderDetailPresenter;
    private TextView orderCountPrice;
    private RippleView pay;
    private Address mAddress;
    private boolean buynow;

    public static OrderTempDetailFragment newInstance(ArrayList<ShopCart> products, boolean buynow) {
        OrderTempDetailFragment mOrderDetailFragment = new OrderTempDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Status.Order.ORDER_KEY, products);
        bundle.putBoolean(Status.Order.BUY_NOW_KEY, buynow);
        mOrderDetailFragment.setArguments(bundle);
        return mOrderDetailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootLayout = inflater.inflate(R.layout.fragment_order_detail, container, false);
        ButterKnife.inject(this, rootLayout);
        return rootLayout;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        products = getArguments().getParcelableArrayList(Status.Order.ORDER_KEY);
        buynow = getArguments().getBoolean(Status.Order.BUY_NOW_KEY, false);
        initPresenter();
        mTempOrderDetailAdapter = new TempOrderDetailAdapter(getContext(), R.layout.shop_product_list_item_one, products);
        RecyclerViewBuilder.create(mRecyclerView).setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL)
                .setAutoMeasureEnabled(true)
                .setAdapter(mTempOrderDetailAdapter).build();
        countProduct.setText(String.format("共计%d件商品", products.size()));


    }

    /**
     *  获取商品总价
     * @return
     */
    public void setCountPrice(ArrayList<ShopCart> products){
        String countPrice = "0.00";
        String countFreight = "0.00";
        DoubleUtils mDoubleUtils = DoubleUtils.getInstance();
        for (ShopCart simpleProduct : products) {
            countPrice = mDoubleUtils.addToString(countPrice,  simpleProduct.getRealPrice()) + "";
            countFreight = mDoubleUtils.addToString(countFreight,  simpleProduct.getFreight()) + "";
        }
        productPrice.setText("￥" + countPrice);
        productFreight.setText("￥" + countFreight);
        String price = mDoubleUtils.addToString(countPrice, countFreight) + "";
        orderCountPrice.setText("合计:￥" + price);
    }


    private void initPresenter() {
        if (mIOrderDetailPresenter == null) {
            mIOrderDetailPresenter =  getPresenter(new OrderTempDetailPresenter(this));
        }
        mIOrderDetailPresenter.getCurrentUserAdress();
        if(CollectionUtils.isNotEmpty(products)){
            StringBuffer productIds = new StringBuffer();
            for (ShopCart product : products) {
                productIds.append(product.getId() + ",");
            }
            if(productIds.length() != 0){
                productIds.deleteCharAt(productIds.length() - 1);
                mIOrderDetailPresenter.calc(productIds.toString());
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setBottomLayout(View bottomLayout) {
        this.pay = (RippleView) bottomLayout.findViewById(R.id.pay);
        pay.setText(R.string.create_order);
        this.orderCountPrice = (TextView) bottomLayout.findViewById(R.id.order_count_price);
        RxViewListener.clicks(pay, this);
    }

    //组合产品ID，以逗号隔开
    private String combinationProductId(){
        StringBuffer sb = new StringBuffer();
        if(CollectionUtils.isNotEmpty(products)){
            for (ShopCart product : products) {
                sb.append(product.getId() + ",");
            }
            if( sb.length() != 0){              //服务器计算
                sb.deleteCharAt(sb.length() - 1);
            } else {                            //本地计算
                setCountPrice(products);
            }
        }
        return sb.toString();
    }


    @Override
    public void showAddress(Address mAddress) {
        this.mAddress = mAddress;
        if (mAddress != null && StringUtil.isNotEmpty(mAddress.getId())) {                  //没有默认地址的时候
            address_normal.setVisibility(View.GONE);
            address_selected.setVisibility(View.VISIBLE);
            consignee.setText(mAddress.getRecipientName());
            mobile.setText(mAddress.getMobile());
          /*  idcard.setText(mAddress.getRecipientID());*/
            address_message.setText(mAddress.getDetailAddress());
        } else {                                //有默认地址
            address_normal.setVisibility(View.VISIBLE);
            address_selected.setVisibility(View.GONE);
        }
    }

    @Override
    public void createOrderSuccess(Order mOrder) {
        Intent mIntent = getIntentBuilder(PayActivity.class).putString(Status.ID, mOrder.getId())
                .putString(PayActivity.PRICE_KEY, mOrder.getActuallyPaidSum())
                .build();
        startActivityForResult(mIntent, Status.Pay.TEMP_ORDER_DETAIL);
    }

    @Override
    public void calc(Order order) {
        productPrice.setText("￥" + order.getPayableSum());
        productFreight.setText("￥" + order.getFreight());
        orderCountPrice.setText("合计:￥" + order.getActuallyPaidSum());
    }


    @OnClick({R.id.address_selected, R.id.address_normal})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.address_selected:
            case R.id.address_normal :
                Intent mIntent = getIntentBuilder(AddressManagerActivity.class).putString(Status.TYPE_FIELD, Status.Order.ORDER_DETAIL)
                        .putInt(Status.Order.CHANGE_ADDRESS_KEY, Status.Order.CHANGE_ADDRESS_CODE)

                        .build();
                startActivityForResult( mIntent,  Status.Order.CHANGE_ADDRESS_CODE );
                break;
        }
    }

    @Override
    public void call(Object o) {
        String addressId = mAddress == null ? "" : mAddress.getId();
        String message = orderMessage.getText().toString();
        String productIds = combinationProductId();
        if(buynow){                 //立即购买,暂时取消该功能
            if(StringUtil.isNotEmpty(productIds)){
                mIOrderDetailPresenter.buyNow(addressId, productIds, products.get(0).getSelectedSku(), message);
            }
        } else{                     //购物车，流程购买
            mIOrderDetailPresenter.createOrder(addressId, productIds, message);
        }
    }


    /**
     *
     */
    class TempOrderDetailAdapter extends CommonAdapter<ShopCart, CommonViewHolder> {

        public TempOrderDetailAdapter(Context mContext, @LayoutRes int resources) {
            super(mContext, resources);
        }

        public TempOrderDetailAdapter(Context mContext, @LayoutRes int resources, List<ShopCart> datas) {
            super(mContext, resources, datas);
        }

        @Override
        public void convert(CommonViewHolder mCommonViewHolder, final ShopCart item, int position) {
            ImageView productImage = mCommonViewHolder.findViewById(R.id.shop_cart_product_image);
            GlideManager.with(getContext()).loadImage4other(productImage, item.getMainImage());

            TextView title = mCommonViewHolder.findViewById(R.id.product_title);
            title.setText(StringUtil.splitString(item.getTitle(), 30));

            TextView productPrice = mCommonViewHolder.findViewById(R.id.product_real_price);
            productPrice.setText("￥" + item.getRealPrice());

            TextView productSku = mCommonViewHolder.findViewById(R.id.product_sku);
            productSku.setText(item.getSelectedSku());

            TextView buyCount = mCommonViewHolder.findViewById(R.id.buyCount);
            buyCount.setText("X " + item.getBuyCount());

            RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
                @Override
                public void call(Object o) {
                    Intent mIntent = getIntentBuilder(ProductActivity.class).putString(Status.Product.ID, item.getProductId()).build();
                    startActivity(mIntent);
                }
            });

        }
    }
}
