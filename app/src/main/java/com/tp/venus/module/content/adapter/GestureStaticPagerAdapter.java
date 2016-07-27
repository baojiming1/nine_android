/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.adapter</p>
 * <p>File：GestureStaticPagerAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/26/16:42.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.alexvasilkov.gestures.GestureController;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.tp.venus.util.AppManager;
import com.tp.venus.util.GlideManager;

import java.util.ArrayList;
import java.util.List;

/**<p>Class：com.tp.venus.module.content.adapter.GestureStaticPagerAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/26/16:42
 * @version 1.0.0
 */

public class GestureStaticPagerAdapter extends DynamicPagerAdapter {

    private List<String> datas;
    private Context mContext;
    private ViewPager mViewPager;
    private GlideManager mGlideManager;

    public GestureStaticPagerAdapter(Context mContext, ViewPager mViewPager){
        this.datas = new ArrayList<>();
        this.mContext = mContext;
        this.mViewPager = mViewPager;
        this.mGlideManager = GlideManager.with(mContext);
    }

    @Override
    public View getView(ViewGroup container, int position) {
        String url = datas.get(position);
        GestureImageView mGestureImageView = new GestureImageView(mContext);
        mGlideManager.loadImageCus(mGestureImageView, url);
      //  mGestureImageView.getController().enableScrollInViewPager(mViewPager);
        mGestureImageView.getController().setOnGesturesListener(new GestureController.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
                AppManager.getInstance().finishActivity();
                return true;
            }
        });
        return mGestureImageView;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    public void addAll(List<String> datas){
        this.datas.clear();
        synchronized (this){
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void onDestroy(){
        mGlideManager.onDestroy();
        datas.clear();
        datas = null;
        mGlideManager = null;
        mContext = null;
    }
}
