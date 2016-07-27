/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.fragment</p>
 * <p>File：BaseViewPagerFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/11/2/17:35.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tp.venus.R;
import com.tp.venus.base.adapter.ViewPagerFragmentPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**<p>Class：com.tp.venus.base.fragment.BaseViewPagerFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/11/2/17:35
 * @version 1.0.0
 */

public abstract class BaseViewPagerFragment extends BaseFragment {


    @InjectView(R.id.mTabLayout)
    protected TabLayout mTabLayout;
    @InjectView(R.id.mViewPager)
    protected ViewPager mViewPager;

    private ViewPagerFragmentPagerAdapter mViewPagerFragmentPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view, savedInstanceState);

        setTabGravity(mTabLayout);
        mViewPagerFragmentPagerAdapter = new ViewPagerFragmentPagerAdapter(getChildFragmentManager(), mViewPager, mTabLayout);
        onSetupTabAdapter(mViewPagerFragmentPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPagerFragmentPagerAdapter.notifyDataSetChanged();
        if (savedInstanceState != null) {
            int pos = savedInstanceState.getInt("position");
            mViewPager.setCurrentItem(pos, true);
        }
    }

    /**
     * 设置子Fragment
     * @param mViewPagerFragmentPagerAdapter
     */
    public abstract void onSetupTabAdapter(ViewPagerFragmentPagerAdapter mViewPagerFragmentPagerAdapter);

    /**
     *  设置TabLayout的位置，默认情况下
     * @param mTabLayout
     */
    protected void setTabGravity(TabLayout mTabLayout){
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    /**
     * 初始化一些View
     * @param view
     * @param savedInstanceState
     */
    protected void initView(View view, @Nullable Bundle savedInstanceState){

    }

    /**
     * 布局资源
     * @return
     */
    protected abstract  @LayoutRes int getLayout();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
