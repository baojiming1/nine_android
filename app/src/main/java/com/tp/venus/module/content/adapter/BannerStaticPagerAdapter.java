/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.adapter</p>
 * <p>File：BannerStaticPagerAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/19/13:51.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.model.AutoImage;
import com.tp.venus.module.common.adapter.ImageStaticPagerAdapter;
import com.tp.venus.module.common.bean.Banner;
import com.tp.venus.module.common.ui.WebViewActivity;
import com.tp.venus.module.common.ui.WebViewShareActivity;
import com.tp.venus.util.StringUtil;

import java.util.List;


/**<p>Class：com.tp.venus.module.content.adapter.BannerStaticPagerAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/19/13:51
 * @version 1.0.0
 */
public class BannerStaticPagerAdapter extends ImageStaticPagerAdapter {


    public BannerStaticPagerAdapter(Context mContext, int width, int height) {
        super(mContext, width, height);
    }

    public BannerStaticPagerAdapter(Fragment mFragment, int width, int height) {
        super(mFragment, width, height);
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        View view = setImageView(container, position);
        AutoImage mAutoImage = datas.get(position);
        if( mAutoImage instanceof Banner){
            Banner mBanner = (Banner)mAutoImage;
            final String url = mBanner.getUrl();
            if( mBanner.getShowType() == Status.WEB && StringUtil.isNotEmpty(url)){
                RxViewListener.clicks(view, new RxViewListener.Action() {
                    @Override
                    public void call(Object o) {
                        Intent mIntent = IntentBuilder.create(mContext, WebViewShareActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                .putString(WebViewActivity.URL_KEY, url).build();
                        mContext.startActivity(mIntent);
                    }
                });
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public void addAll(List<? extends AutoImage> datas){
        this.datas.clear();
        synchronized (this){
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }
}
