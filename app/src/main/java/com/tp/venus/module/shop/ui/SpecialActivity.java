/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.ui</p>
 * <p>File：SpecialActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/18/16:43.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Special;
import com.tp.venus.module.shop.ui.fragment.SpecialFragment;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StatusBarCompat;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**<p>Class：com.tp.venus.module.shop.ui.SpecialActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/18/16:43
 * @version 1.0.0
 */

public class SpecialActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener {

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mCollapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @InjectView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;
    @InjectView(R.id.backgroundImg)
    ImageView backgroundImg;

    private int width;
    private int height;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Special item;
    private SpecialFragment mSpecialFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_coordinatorlayout);
        ButterKnife.inject(this);
        initView();
    }



    private void initView() {
        item = getIntent().getParcelableExtra(Status.PARCELABLE_KEY);
        GlideManager.with(this).loadImageCus(backgroundImg, item.getImageUrl());
        mToolbar.setBackgroundResource(R.drawable.shop_top_toolbar_bg);
        getToolbarBuilder(mToolbar).setTitle(item.getTitle()).build();
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mCollapsingToolbarLayout.setStatusBarScrimColor(ResourcesUtil.getColorPrimary(this));
        width = ResourcesUtil.getPoint(this).x;
        height = width / 2;
        mCollapsingToolbarLayout.getLayoutParams().height = height;
        mSpecialFragment = SpecialFragment.newInstance(item.getId());
        getSupportFragmentManager().beginTransaction().replace(R.id.content_tag_framelayout, mSpecialFragment).commit();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

        initSwipeRefreshLayout();

        if (i == 0) {
            mSwipeRefreshLayout.setEnabled(true);
        } else {
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    private void initSwipeRefreshLayout(){
        if( mSwipeRefreshLayout == null){
            List<Fragment> list = getSupportFragmentManager().getFragments();
            int index = list.indexOf(mSpecialFragment);
            if( index > 0){
                mSwipeRefreshLayout = (SwipeRefreshLayout) list.get(index).getView().findViewById(R.id.mSwipeRefreshLayout);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAppBarLayout.removeOnOffsetChangedListener(this);
    }
}
