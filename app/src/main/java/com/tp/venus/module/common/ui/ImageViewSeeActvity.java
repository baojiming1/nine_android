/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui</p>
 * <p>File：ImageViewSeeActvity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/26/17:28.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.module.content.adapter.GestureStaticPagerAdapter;
import com.tp.venus.util.CollectionUtils;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.widget.RippleView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.common.ui.ImageViewSeeActvity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/2/26/17:28
 */

public class ImageViewSeeActvity extends BaseActivity implements ViewPager.OnPageChangeListener {

    public static final String URLS_KEY = "urls";
    public static final String POSITION_KEY = "position";


    protected ViewPager mViewPager;
    @InjectView(R.id.viewpage_layout)
    LinearLayout viewpagerLayout;
    @InjectView(R.id.pages)
    protected TextView pages;
    @InjectView(R.id.root)
    protected FrameLayout root;
    @InjectView(R.id.details)
    protected RippleView details;

    protected ArrayList<String> urls;
    protected int position;
    protected int pageCount;
    protected GestureStaticPagerAdapter mGestureStaticPagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gesture_image_view);
        ButterKnife.inject(this);
        init();
    }

    protected void init() {
        urls = getIntent().getStringArrayListExtra(URLS_KEY);
        position = getIntent().getIntExtra(POSITION_KEY, 0);
        if (CollectionUtils.isEmpty(urls)) {
            finishActivity();
            return;
        }
        mViewPager = new ViewPager(getApplicationContext());
        mViewPager.setBackgroundColor(ResourcesUtil.getColor(getApplicationContext(), R.color.themeColor));
        viewpagerLayout.addView(mViewPager);
        mGestureStaticPagerAdapter = getdapter();
        mGestureStaticPagerAdapter.addAll(urls);
        mViewPager.setAdapter(mGestureStaticPagerAdapter);
        mViewPager.setPageMargin(ResourcesUtil.getDimens(this, R.dimen.dp10));
        mViewPager.setCurrentItem(position);
        pageCount = urls.size();
        setPage(position);
        mViewPager.addOnPageChangeListener(this);
    }

    protected GestureStaticPagerAdapter getdapter() {
        return new GestureStaticPagerAdapter(this, mViewPager);
    }

    @Override
    protected void onDestroy() {
        viewpagerLayout.removeAllViews();
        mViewPager = null;
        viewpagerLayout = null;
        if(mGestureStaticPagerAdapter != null){
            mGestureStaticPagerAdapter.onDestroy();
            mGestureStaticPagerAdapter = null;
        }
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setPage(int position) {
        pages.setText((position + 1) + "/" + pageCount);
    }

}
