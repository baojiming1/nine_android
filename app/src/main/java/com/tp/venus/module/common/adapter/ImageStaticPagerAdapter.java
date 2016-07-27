/**
<<<<<<< HEAD
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.adapter</p>
 * <p>File：ImageStaticPagerAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/28/15:55.</p>
=======
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.common.adapter</p>
 * <p>File：ImageStaticPagerAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/13/18:11.</p>
>>>>>>> remotes/origin/Branch_20160418_v2.0.3
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.model.AutoImage;
import com.tp.venus.module.common.ui.ImageViewSeeActvity;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.qiniu.QiNiuUtil;

import java.util.ArrayList;
import java.util.List;


/**<p>Class：com.tp.venus.module.common.adapter.ImageStaticPagerAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/13/18:11
 * @version 1.0.0
 */

public class ImageStaticPagerAdapter extends StaticPagerAdapter {

    protected List<AutoImage> datas;
    protected Context mContext;
    protected int width;
    protected int height;
    protected GlideManager mGlideManager;

    public ImageStaticPagerAdapter(Context mContext){
        this.datas = new ArrayList<>();
        this.mContext = mContext;
        Point mPoint = ResourcesUtil.getPoint(mContext);
        this.width = mPoint.x;
        this.height = mPoint.y;
        this.mGlideManager = GlideManager.with(mContext);
    }

    public ImageStaticPagerAdapter(Context mContext, int width, int height){
        this.datas = new ArrayList<>();
        this.mContext = mContext;
        this.width = width;
        this.height = height;
        this.mGlideManager = GlideManager.with(mContext);
    }

    public ImageStaticPagerAdapter(Fragment mFragment , int width, int height){
        this.mContext = mFragment.getContext();
        this.width = width;
        this.height = height;
        this.datas = new ArrayList<>();
        this.mGlideManager = GlideManager.with(mFragment);
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        View view = setImageView(container, position);
        RxViewListener.clicks(view, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                ArrayList<String> urls = toUrlList();
                Intent mIntent = IntentBuilder.create(mContext, ImageViewSeeActvity.class).putStringArrayList(ImageViewSeeActvity.URLS_KEY, urls)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putInt(ImageViewSeeActvity.POSITION_KEY, position).build();
                mContext.startActivity(mIntent);
            }
        });
        return view;
    }

    /**
     * 设置图片控件
     * @param container
     * @param position
     */
    protected View setImageView(ViewGroup container, final int position){
        AutoImage mAutoImage = datas.get(position);
        ImageView view = new ImageView(container.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // GlideManager.with(mContext).loadImageCus(view, mAutoImage.getImageUrl());
        String img = QiNiuUtil.getImageCompress(mAutoImage.getImageUrl(), width, height);
        mGlideManager.loadImage(img).centerCrop().into(view);
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

    protected ArrayList<String> toUrlList(){
        ArrayList<String> urls = new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(datas)){
            for (AutoImage data : datas) {
                urls.add(data.getImageUrl());
            }
        }
        return urls;
    }
}
