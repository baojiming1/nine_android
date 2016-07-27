/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：MyOrderFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/6/10:13.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.bean.OrderDetail;
import com.tp.venus.module.shop.listener.OrderBottonOnClick;
import com.tp.venus.module.shop.presenter.IOrderBottonPresenter;
import com.tp.venus.module.shop.presenter.impl.OrderBottonPresenter;
import com.tp.venus.module.shop.ui.OrderDetailActivity;
import com.tp.venus.module.shop.ui.view.IOderBottonView;
import com.tp.venus.module.shop.util.OrderUtil;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.ui.fragment.MyOrderFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/6/10:13
 * @version 1.0.0
 */

public class MyOrderFragment extends BaseSwipRefreshFragment<Order> implements IOderBottonView {

    private int orderStatus;
    private IOrderBottonPresenter mIOrderBottonPresenter;

    public static MyOrderFragment newInstance(int orderStatus){
        MyOrderFragment mMyOrderFragment = new MyOrderFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(Status.Order.CHANGE_ADDRESS_KEY, orderStatus);
        mMyOrderFragment.setArguments(mBundle);
        return mMyOrderFragment;
    }

    @Override
    protected View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orderStatus = getArguments().getInt(Status.Order.CHANGE_ADDRESS_KEY, Status.Order.ALL);
        if( mIOrderBottonPresenter == null){
            mIOrderBottonPresenter = getPresenter(new OrderBottonPresenter(this));
        }
        return super.oncreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder) {
        RequestBody mRequestBody = bodyBuilder.setParam("orderStatus", orderStatus)
                .build();
        return builder.url(AppConfig.SERVICE_HOST + Url.SHOP_ORDER_MY).post(mRequestBody).build();
    }

    @Override
    protected int getItemLayout() {
        return R.layout.shop_my_order_list_item;
    }


    @Override
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder) {
        mRecyclerViewBuilder.setNoItemDecoration();
    }

    @Override
    protected void convertLayout(final CommonViewHolder mCommonViewHolder, final Order item, int position) {

        TextView order_no = mCommonViewHolder.findViewById(R.id.order_no);
        order_no.setText("订单编号: " + item.getOrderNo());

        TextView order_status = mCommonViewHolder.findViewById(R.id.order_status);
        OrderUtil.OrderText mOrderText = OrderUtil.getInstance().parseOrderStatus(item.getOrderStatus());
        order_status.setText(mOrderText.str);
        order_status.setTextColor(ResourcesUtil.getColor(getContext(), mOrderText.color));

        List<OrderDetail> list =  item.getOrderDetails();
        if(CollectionUtils.isNotEmpty(list)){
            initOrderDetail(mCommonViewHolder, list.get(0));
        }

        initOrderCountText(mCommonViewHolder, item);

        List<OrderUtil.OrderBotton> bottons = OrderUtil.getInstance().parseOrderBotton(item.getOrderStatus());
        TagFlowLayout mTagFlowLayout = mCommonViewHolder.findViewById(R.id.mTagFlowLayout);
        mTagFlowLayout.setAdapter(new TagAdapter<OrderUtil.OrderBotton>(bottons) {
            @Override
            public View getView(FlowLayout parent, int position, OrderUtil.OrderBotton o) {
                RippleView mRippleView = (RippleView) LayoutInflater.from(mContext).inflate(R.layout.shop_my_order_status_btn,
                        parent, false);
                mRippleView.setText(o.str);
                mRippleView.setBackgroundResource(o.bottonBg);
                RxViewListener.clicks(mRippleView, new OrderBottonOnClick(mContext, o.id, item, mIOrderBottonPresenter, mRecyclerViewBuilder.getAdapterPosition(mCommonViewHolder)));
                return mRippleView;
            }
        });
        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = getIntentBuilder(OrderDetailActivity.class).putString(Status.ID, item.getId())
                        .build();
                startActivity(mIntent);
            }
        });
    }

    //实例化单个订单项数据
    private void initOrderDetail(CommonViewHolder mCommonViewHolder,  OrderDetail item){
        ImageView product_image = mCommonViewHolder.findViewById(R.id.product_image);
        GlideManager.with(getContext()).loadImage4other(product_image, item.getMainImage());

        TextView product_title = mCommonViewHolder.findViewById(R.id.product_title);
        product_title.setText(item.getTitle());

        TextView product_sku = mCommonViewHolder.findViewById(R.id.product_sku);
        product_sku.setText(item.getSelectedSku());

        TextView buyCount = mCommonViewHolder.findViewById(R.id.buyCount);
        buyCount.setText("X  " + item.getBuyCount());

        TextView product_price = mCommonViewHolder.findViewById(R.id.product_price);
        product_price.setText("￥" + item.getRealPrice());
    }

    //订单底部统计文字
    private void initOrderCountText(CommonViewHolder mCommonViewHolder, Order item){
        TextView order_count_text = mCommonViewHolder.findViewById(R.id.order_count_text);
        String buyCount = item.getBuyCount() + "";
        String sum = "￥" + item.getActuallyPaidSum();
        String freight = "￥" + item.getFreight();
        String text = String.format(getResources().getString(R.string.order_count_text), buyCount, sum, freight);
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        int indexCount = text.indexOf(buyCount);
        int indexSum = text.indexOf(sum);
        int indexFre = text.indexOf(freight);
        style.setSpan(new ForegroundColorSpan(Color.RED),indexCount,indexCount + buyCount.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.RED),indexSum,indexSum + sum.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.RED),indexFre,indexFre + freight.length(),Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        order_count_text.setText(style);
    }

    @Override
    protected int getTokenStatus() {
        return Status.TokenStatus.MUST;
    }


    @Override
    public void updateOrderStatusSuccess(Order order, int position) {
        adapter.refresh(position, order);
    }

    @Override
    public void saveServiceInfoSuccess(Order mOrder, int position) {
        ToastUtil.getInstance().show("提交成功,我们会在24小时内为您处理");
        adapter.refresh(position, mOrder);
    }
}
