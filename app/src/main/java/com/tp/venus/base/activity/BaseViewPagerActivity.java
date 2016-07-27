/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.activity</p>
 * <p>File：BaseViewPagerFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/11/2/18:07.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.adapter.ViewPagerFragmentStatePagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**<p>Class：com.tp.venus.base.activity.BaseViewPagerFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/11/2/18:07
 * @version 1.0.0
 */

public abstract class BaseViewPagerActivity extends BaseActivity {

    @InjectView(R.id.mTabLayout)
    public TabLayout mTabLayout;
    @InjectView(R.id.mViewPager)
    public ViewPager mViewPager;

    protected ViewPagerFragmentStatePagerAdapter mViewPagerFragmentStatePagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewLayout();
        ButterKnife.inject(this);
        initView();

        mViewPagerFragmentStatePagerAdapter = getViewPagerFragmentPagerAdapter(getSupportFragmentManager(), mTabLayout, mViewPager );
        onSetupTabAdapter(mViewPagerFragmentStatePagerAdapter);
        mViewPagerFragmentStatePagerAdapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPagerFragmentStatePagerAdapter.showTabTitle();          //显示图标和自定义View
    }

    /**
     * 自定义时可重写
     * @param mFragmentManager
     * @param mTabLayout
     * @param mViewPager
     * @return
     */
    protected ViewPagerFragmentStatePagerAdapter getViewPagerFragmentPagerAdapter(FragmentManager mFragmentManager, TabLayout mTabLayout, ViewPager mViewPager){
        ViewPagerFragmentStatePagerAdapter mViewPagerFragmentStatePagerAdapter = new ViewPagerFragmentStatePagerAdapter(mFragmentManager, mViewPager, mTabLayout);
        return mViewPagerFragmentStatePagerAdapter;
    }

    /**
     * 设置子Fragment
     * @param mViewPagerFragmentStatePagerAdapter
     */
    public abstract void onSetupTabAdapter(ViewPagerFragmentStatePagerAdapter mViewPagerFragmentStatePagerAdapter);


    /**
     * 设置布局文件，文件中必须包含R.id.mViewPager   、 R.id.mTabLayout
     */
    public abstract void setContentViewLayout();

    /**
     * 自定义其他参数初始化
     */
    public abstract void initView();

}
