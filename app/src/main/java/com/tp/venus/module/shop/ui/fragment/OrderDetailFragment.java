/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.shop.ui.fragment</p>
 * <p>File：OrderTempDetailFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/30/14:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.adapter.OrderDetailAdapter;
import com.tp.venus.module.shop.bean.Order;
import com.tp.venus.module.shop.listener.OrderBottonOnClick;
import com.tp.venus.module.shop.presenter.IOrderDetailPresenter;
import com.tp.venus.module.shop.presenter.impl.OrderDetailPresenter;
import com.tp.venus.module.shop.ui.view.IOrderDetailView;
import com.tp.venus.module.shop.util.OrderUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.RippleView;

import butterknife.ButterKnife;
import butterknife.InjectView;

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

public class OrderDetailFragment extends BaseFragment implements IOrderDetailView {

    @InjectView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @InjectView(R.id.consignee)
    TextView consignee;
    @InjectView(R.id.mobile)
    TextView mobile;
/*    @InjectView(R.id.idcard)
    TextView idcard;*/
    @InjectView(R.id.address_message)
    TextView addressMessage;
    @InjectView(R.id.countProduct)
    TextView countProduct;
    @InjectView(R.id.order_message)
    EditText orderMessage;
    @InjectView(R.id.product_price)
    TextView productPrice;
    @InjectView(R.id.product_freight)
    TextView productFreight;
    @InjectView(R.id.forward)
    ImageView forward;

    private View bottomLayout;
    private RippleView pay;
    private TextView order_count_price;
    private String orderId;
    private IOrderDetailPresenter mIOrderDetailPresenter;
    private OrderDetailAdapter mOrderDetailAdapter;


    public static OrderDetailFragment newInstance(String orderId) {
        OrderDetailFragment mOrderDetailFragment = new OrderDetailFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(Status.ID, orderId);
        mOrderDetailFragment.setArguments(mBundle);
        return mOrderDetailFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        orderId = getArguments().getString(Status.ID);
        forward.setVisibility(View.GONE);
        mOrderDetailAdapter = new OrderDetailAdapter(getContext(), R.layout.shop_order_list_item);
        RecyclerViewBuilder.create(mRecyclerView).setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL)
                .setAutoMeasureEnabled(true)
                .setAdapter(mOrderDetailAdapter)
                .build();
        if (mIOrderDetailPresenter == null) {
            mIOrderDetailPresenter = getPresenter(new OrderDetailPresenter(this));
        }
        //sendHttp();
    }

    public void sendHttp(){
        mIOrderDetailPresenter.showDetail(orderId);
    }

    @Override
    public void onResume() {
        super.onResume();
        sendHttp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setBottomLayout(View bottomLayout) {
        this.bottomLayout = bottomLayout;
        this.order_count_price = (TextView) bottomLayout.findViewById(R.id.order_count_price);
        this.pay = (RippleView) bottomLayout.findViewById(R.id.pay);
    }

    @Override
    public void showDetail(Order order) {
        consignee.setText(order.getRecipientName());
        mobile.setText(order.getRecipientMobile());
       /* idcard.setText("身份证号:" + order.getRecipientID());*/
        addressMessage.setText(order.getRecipientAddress());
        countProduct.setText("共计" + order.getBuyCount() +"件商品" );
        if (StringUtil.isEmpty(order.getMessage())) {
            orderMessage.setHint("暂无备注");
        } else {
            orderMessage.setText(order.getMessage());
        }
        orderMessage.setEnabled(false);
        productPrice.setText("￥" + order.getPayableSum());
        productFreight.setText("￥" + order.getFreight());
        mOrderDetailAdapter.clear();
        mOrderDetailAdapter.addAll(order.getOrderDetails());

        order_count_price.setText("合计:￥" + order.getActuallyPaidSum());
        initButton(order);
    }

    private void initButton(Order order){
        OrderUtil.OrderBotton botton = OrderUtil.getInstance().parseDetailOrderBotton(order.getOrderStatus());
        pay.setText("");
        pay.setBackgroundResource(botton.bottonBg);
        RxViewListener.clicks(pay, new OrderBottonOnClick(mContext, botton.id, order, mIOrderDetailPresenter, 0));
    }


    @Override
    public void updateOrderStatusSuccess(Order order, int position) {
        mIOrderDetailPresenter.showDetail(order.getId());
    }

    @Override
    public void saveServiceInfoSuccess(Order mOrder, int position) {
        ToastUtil.getInstance().show("提交成功,我们会在24小时内为您处理");
        showDetail(mOrder);
    }

}
