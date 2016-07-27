/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.adapter</p>
 * <p>File：CartAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/21/10:13.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.CheckStatus;
import com.tp.venus.module.shop.bean.ShopCart;
import com.tp.venus.module.shop.presenter.IShopCartPresenter;
import com.tp.venus.module.shop.ui.ProductActivity;
import com.tp.venus.module.shop.widget.AddSubstractWidget;
import com.tp.venus.util.DoubleUtils;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.StringUtil;
import com.tp.venus.widget.LabelView;
import com.tp.venus.widget.recyclerView.RecyclerViewUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**<p>Class：com.tp.venus.module.shop.adapter.CartAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/21/10:13
 * @version 1.0.0
 */

public class CartAdapter extends CommonAdapter<ShopCart, CommonViewHolder> {

    private Map<Integer, CheckStatus> isCheckMap ;
    private CheckBox allCheckBox;
    private TextView countPrice;
  //  private TextView countFreight;
    private IShopCartPresenter mIShopCartPresenter;
    private RecyclerView mRecyclerView;


    public CartAdapter(Context mContext, @LayoutRes int resources) {
        super(mContext, resources);
    }


    /**
     * 使用前必须先条用该方法
     * @param checkBox
     * @param countPrice
     * @param mIShopCartPresenter
     * @param isCheckMap
     */
    public void init(CheckBox checkBox, TextView countPrice, /*TextView countFreight,*/ IShopCartPresenter mIShopCartPresenter, Map<Integer, CheckStatus> isCheckMap, RecyclerView mRecyclerView){
        this.allCheckBox = checkBox;
        this.countPrice = countPrice;
       /* this.countFreight = countFreight;*/
        this.isCheckMap = isCheckMap;
        this.mRecyclerView = mRecyclerView;
        this.mIShopCartPresenter = mIShopCartPresenter;
      //  setCheckMap(false);
        allCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( allCheckBox.isChecked() ) {         //不选
                    setCheckMap(true);
                } else {                                //选
                    setCheckMap(false);
                }
            }
        });
    }

    /**
     * 判断已经全选
     *
     * @return
     */
    public boolean isCheckAll() {
        for (Map.Entry<Integer, CheckStatus> entry : isCheckMap.entrySet()) {
            CheckStatus mCheckStatus = entry.getValue();
            if (!mCheckStatus.isChecked) {
                return false;
            }
        }
        return true;
    }

    public boolean getChecked(int position){
        CheckStatus mCheckStatus = isCheckMap.get(position);
        if( mCheckStatus == null){
            return false;
        }
        return mCheckStatus.isChecked;
    }

    public Map<Integer, CheckStatus> getCheckedMap(){
        return this.isCheckMap;
    }


    @Override
    public void convert(final CommonViewHolder mCommonViewHolder, final ShopCart item, final int position) {
        final CheckBox mCheckBox = mCommonViewHolder.findViewById(R.id.mCheckBox);
        LabelView mLabelView = mCommonViewHolder.findViewById(R.id.mLabelView);
        final int index = RecyclerViewUtils.getAdapterPosition(mRecyclerView, mCommonViewHolder);
        boolean isChecked = getChecked(index);
        final boolean onSaleStatus = item.getOnSaleStatus();
       // final boolean onSaleStatus = false;
        if( !onSaleStatus ){           //下架商品
            mLabelView.setVisibility(View.VISIBLE);
            mCheckBox.setEnabled(false);
            mCheckBox.setChecked(false);
        } else {
            CheckStatus mCheckStatus = new CheckStatus(mCheckBox, isChecked, item);
            isCheckMap.put(position, mCheckStatus);
            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    setCheck(index, isChecked);
                }
            });
            mCheckBox.setChecked(isChecked);
        }


        ImageView productImage = mCommonViewHolder.findViewById(R.id.shop_cart_product_image);
        GlideManager.with(getContext()).loadImage4user(productImage, item.getMainImage());

        TextView productTitle = mCommonViewHolder.findViewById(R.id.product_title);
        productTitle.setText( StringUtil.splitString(item.getTitle(), 30));

        TextView freight = mCommonViewHolder.findViewById(R.id.freight);
        freight.setText("运费:￥" + item.getFreight());

        TextView productSku = mCommonViewHolder.findViewById(R.id.product_sku);
        productSku.setText(item.getSelectedSku());

        TextView productRealPrice = mCommonViewHolder.findViewById(R.id.product_real_price);
        productRealPrice.setText("￥" + item.getRealPrice());


        ImageView delete = mCommonViewHolder.findViewById(R.id.cart_delete);
        RxViewListener.clicks(delete, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                mIShopCartPresenter.delete(item.getId(), position, mCommonViewHolder);
            }
        });

        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = IntentBuilder.create(getContext(), ProductActivity.class).putString(Status.Product.ID, item.getProductId()).build();
                getContext().startActivity(mIntent);
            }
        });

        AddSubstractWidget mAddSubstractWidget = new AddSubstractWidget(mCommonViewHolder.itemView, getContext());
        mAddSubstractWidget.setBuyCount(item.getBuyCount());
        mAddSubstractWidget.addOnclickListener(new AddSubstractWidget.OnClickListener() {
            @Override
            public void onAddClickListener(View view, EditText mEditText, int count) {
                if( onSaleStatus ){
                    mIShopCartPresenter.changeBuyCount(mEditText, item,  count);
                }
            }

            @Override
            public void onSubtractListener(View view, EditText mEditText, int count) {
                if( onSaleStatus ){
                    mIShopCartPresenter.changeBuyCount(mEditText, item,  count);
                }
            }

            @Override
            public void onEditChangeListener(View view) {

            }
        });
    }

    /**
     * 获取所有选择的商品并清空列表
     * @return
     */
    public ArrayList<ShopCart> getCheckProduct(){
        ArrayList<ShopCart> result = new ArrayList<>();
        Iterator<Map.Entry<Integer, CheckStatus>> mIterator = isCheckMap.entrySet().iterator();
        while (mIterator.hasNext()){
            Map.Entry<Integer, CheckStatus> entry = mIterator.next();
            CheckStatus mCheckStatus = entry.getValue();
            if (mCheckStatus.isChecked) {
                result.add(mCheckStatus.item);
                mIterator.remove();
            }
        }
        return result;
    }

    /**
     * 计算总价格/计算总运费
     */
    public void sumPrice(){
        String price = "0.00";
        String freight = "0.00";
        DoubleUtils mDoubleUtils = DoubleUtils.getInstance();
        for (Map.Entry<Integer, CheckStatus> entry : isCheckMap.entrySet()) {
            CheckStatus mCheckStatus = entry.getValue();
            if (mCheckStatus.isChecked) {
                ShopCart simpleProduct = mCheckStatus.item;
                if( simpleProduct == null){
                    continue;
                }
                String d = mDoubleUtils.multiply(simpleProduct.getRealPrice(), simpleProduct.getBuyCount() + "") + "";
                price = mDoubleUtils.addToString(price, d) + "";
                freight = mDoubleUtils.addToString(freight, simpleProduct.getFreight()) + "";
            }
        }
        countPrice.setText("合计:￥" + price);
      //  countFreight.setText("运费:￥" + freight);
    }


    /**
     * 设置checkBox状态
     * @param position
     * @param isChecked
     */
    public void setCheck(int position, boolean isChecked) {
        CheckStatus mCheckStatus = isCheckMap.get(position);
        mCheckStatus.isChecked = isChecked;
        boolean b = isCheckAll();
        if( allCheckBox.isChecked() ){              //选中状态
            if( !b ){
                allCheckBox.setChecked(false);
            }
        } else {                                    //未选中
            if( b ){
                allCheckBox.setChecked(true);
            }
        }
        sumPrice();
    }


    @Override
    public void remove(int position) {
        super.remove(position);
        isCheckMap.remove(position);
        sumPrice();
    }

    /**
     * 设置全选或者全不选
     *
     * @param checked
     */
    public void setCheckMap(boolean checked) {
        for (Map.Entry<Integer, CheckStatus> entry : isCheckMap.entrySet()) {
            CheckStatus mCheckStatus = entry.getValue();
            mCheckStatus.mCheckBox.setChecked(checked);
            mCheckStatus.isChecked = checked;
            isCheckMap.put(entry.getKey(), mCheckStatus);
        }

       /* for (int i = 0; i < getDatas().size(); i++) {
            CheckStatus mCheckStatus = isCheckMap.get(i);
            if(mCheckStatus == null){
                mCheckStatus = new CheckStatus();
            } else {
                mCheckStatus.mCheckBox.setChecked(checked);
            }
            mCheckStatus.isChecked = checked;
            isCheckMap.put(i, mCheckStatus);
        }*/
        sumPrice();
    }




}