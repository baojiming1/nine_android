/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.adapter</p>
 * <p>File：SKUAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/15:53.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.module.shop.bean.ProductSku;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**<p>Class：com.tp.venus.module.shop.adapter.SKUAdapter</p>
 * <p>Description：</p>
 * <pre>
 *    商品详情页中的SKU适配器
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/15:53
 * @version 1.0.0
 */

public class SKUAdapter extends BaseAdapter {

    private List<ProductSku> list;
    private Context mContext;
    private Map<Integer, CheckSku> skuResult;

    public SKUAdapter(Context mContext) {
        this.mContext = mContext;
        this.list = new ArrayList<>();
        this.skuResult = new LinkedHashMap<>();
    }

    public void addAll(List<ProductSku> data){
        list.clear();
        skuResult.clear();
        synchronized (this){
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取拼接好的SKU字符串
     * @return
     */
    public String getCheckSku(){
        StringBuffer sb = new StringBuffer();
        if(CollectionUtils.isMapNotEmpty(skuResult)){
            for (Map.Entry<Integer, CheckSku> entry : skuResult.entrySet()) {
                CheckSku checkSku = entry.getValue();
                if( checkSku != null){
                    sb.append(checkSku.mainSku + ":" + checkSku.subSku + " ,");
                }
            }

            if( sb.length() > 0){
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_product_shop_dialog_adapter_view, parent, false);
        }
        ProductSku mProductSku = list.get(position);
        TextView main_tv = ViewHolder.findById(convertView, R.id.main_tv);
        main_tv.setText(mProductSku.getSkuName());
        final List<String> skus = StringUtil.splitToList(mProductSku.getSkuOptions());

        TagFlowLayout mTagFlowLayout = ViewHolder.findById(convertView, R.id.id_flowlayout);
        TagAdapter mTagAdapter = new TagAdapter<String>(skus) {
            @Override
            public View getView(FlowLayout parent, int position, String sku) {
                TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.shop_product_shop_tag_textview,
                        parent, false);
                tv.setText(sku);
                return tv;
            }
        };
        mTagFlowLayout.setAdapter(mTagAdapter);
        mTagAdapter.setSelectedList(0);

        skuResult.put(position, new CheckSku(mProductSku.getSkuName(), skus.get(0)));
        mTagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                for (Integer integer : selectPosSet) {
                    String check = skus.get(integer);
                    CheckSku mCheckSku = skuResult.get(position);
                    mCheckSku.subSku = check;
                }
            }
        });
        mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                   /* TextView tv = (TextView) view;
                    tv.setSelected(true);*/
                //  tv.setTextColor(ResourcesUtil.getColor(mContext, R.color.white));
                view.setSelected(true);
                return true;
            }
        });
        return convertView;
    }

    /**
     * 被选中的sku
     */
    static class CheckSku{
        public String mainSku;             //主类别
        public String subSku;              //子类别

        public CheckSku(String mainSku, String subSku) {
            this.mainSku = mainSku;
            this.subSku = subSku;
        }
        public CheckSku(){}
    }
}