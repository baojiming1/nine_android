/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui</p>
 * <p>File：TagActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/11/3/16:26.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui;

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
import com.tp.venus.module.content.bean.TagResult;
import com.tp.venus.module.content.presenter.ITagPresenter;
import com.tp.venus.module.content.presenter.impl.TagPresenter;
import com.tp.venus.module.content.ui.fragment.TagContentFragment;
import com.tp.venus.module.content.ui.view.ITagView;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StatusBarCompat;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.qiniu.QiNiuUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.content.ui.TagActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/11/3/16:26
 */

public class TagActivity extends BaseActivity implements AppBarLayout.OnOffsetChangedListener, ITagView {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mCollapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @InjectView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;
    @InjectView(R.id.backgroundImg)
    ImageView backgroundImg;


    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String tagId;
    private ITagPresenter mTagPresenter;
    private TagContentFragment mTagContentFragment;

    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_coordinatorlayout);
        ButterKnife.inject(this);
        this.mTagPresenter = new TagPresenter(this);
        initView();
    }


    private void initView() {
        mToolbar.setBackgroundResource(R.drawable.shop_top_toolbar_bg);
        getToolbarBuilder(mToolbar).setTitle("").build();
        tagId = getIntent().getExtras().getString(Status.Tag.ID);
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mCollapsingToolbarLayout.setStatusBarScrimColor(ResourcesUtil.getColorPrimary(this));
        width = ResourcesUtil.getPoint(this).x;
        height = width / 2;
        mCollapsingToolbarLayout.getLayoutParams().height = height;
        mTagContentFragment = TagContentFragment.newInstance(tagId);
        mTagPresenter.showTagInfo(tagId);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_tag_framelayout, mTagContentFragment).commit();
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


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        initSwipeRefreshLayout();
        if( mSwipeRefreshLayout != null){
            if (i == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        }
    }

    private void initSwipeRefreshLayout(){
        if( mSwipeRefreshLayout == null){
            List<Fragment> list = getSupportFragmentManager().getFragments();
            int index = list.indexOf(mTagContentFragment);
            if( index != -1){
                mSwipeRefreshLayout = (SwipeRefreshLayout) list.get(index).getView().findViewById(R.id.mSwipeRefreshLayout);
            }
        }
    }


    @Override
    public void showTagInfo(TagResult tag) {
        getToolbarBuilder(mToolbar).setTitle( tag.getTag().getName()).build();
        String backgroud = tag.getTag().getBackgroud();
        if (StringUtil.isNotEmpty(backgroud)) {
            String url = QiNiuUtil.getImageCompress(backgroud, width, height);
            GlideManager.with(this).loadImage4other(backgroundImg, url);
        }
    }


}
