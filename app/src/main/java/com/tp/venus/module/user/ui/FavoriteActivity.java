/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui</p>
 * <p>File：FavoriteActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/13/16:40.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.base.activity.BaseViewPagerActivity;
import com.tp.venus.base.adapter.ViewPagerFragmentStatePagerAdapter;
import com.tp.venus.module.user.ui.fragment.ContentFavoriteFragment;
import com.tp.venus.module.user.ui.fragment.ProductFavoriteFragment;
import com.tp.venus.util.ResourcesUtil;

import java.util.List;

import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.user.ui.FavoriteActivity</p>
 * <p>Description：</p>
 * <pre>
 *      收藏和帖子页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/13/16:40
 */

public class FavoriteActivity extends BaseViewPagerActivity implements AppBarLayout.OnOffsetChangedListener {



    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mAppBarLayout)
    AppBarLayout mAppBarLayout;

    private ContentFavoriteFragment mContentFavoriteFragment;
    private ProductFavoriteFragment mProductFavoriteFragment;


    @Override
    public void onSetupTabAdapter(ViewPagerFragmentStatePagerAdapter mViewPagerFragmentStatePagerAdapter) {
        mContentFavoriteFragment = ContentFavoriteFragment.newInstance();
        mProductFavoriteFragment = ProductFavoriteFragment.newInstance();
        mViewPagerFragmentStatePagerAdapter.addTab("精选", mContentFavoriteFragment);
        mViewPagerFragmentStatePagerAdapter.addTab("商品", mProductFavoriteFragment );
    }

    @Override
    public void setContentViewLayout() {
        setContentView(R.layout.common_coordinatorlayout_tablayout_viewpager);
    }

    @Override
    public void initView() {
        mTabLayout.setSelectedTabIndicatorColor(ResourcesUtil.getColor(this, R.color.themeTextColor));
        mTabLayout.setTabTextColors(ResourcesUtil.getColor(this, R.color.white), ResourcesUtil.getColor(this, R.color.themeTextColor));
        getToolbarBuilder(mToolbar).setTitle(R.string.my_favorite).build();
        mViewPager.setOffscreenPageLimit(1);

    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        SwipeRefreshLayout mSwipeRefreshLayout = initSwipeRefreshLayout();
        if( mSwipeRefreshLayout != null){
            if (i == 0) {
                mSwipeRefreshLayout.setEnabled(true);
            } else {
                mSwipeRefreshLayout.setEnabled(false);
            }
        }
    }

    private SwipeRefreshLayout initSwipeRefreshLayout(){
        List<Fragment> list = getSupportFragmentManager().getFragments();
        int current = mViewPager.getCurrentItem();
        return findSwipeRefreshLayout(current, list);
    }


    private SwipeRefreshLayout findSwipeRefreshLayout(int current, List<Fragment> list){
        Fragment fragment = null;
        if( current == 0){
            fragment = mContentFavoriteFragment;
        } else if(current == 1){
            fragment = mProductFavoriteFragment;
        } else {
            return null;
        }
        int index = list.indexOf(fragment);
        if( index != -1){
            return (SwipeRefreshLayout) list.get(index).getView().findViewById(R.id.mSwipeRefreshLayout);
        }
        return null;
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
