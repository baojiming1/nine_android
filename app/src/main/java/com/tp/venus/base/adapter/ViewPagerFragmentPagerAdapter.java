/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.adapter</p>
 * <p>File：ViewPagerFragmentPagerAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/9/11:37.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.adapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**<p>Class：com.tp.venus.base.adapter.ViewPagerFragmentPagerAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/9/11:37
 * @version 1.0.0
 */

public class ViewPagerFragmentPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    protected Context mContext;
    protected ViewPager mViewPager;
    protected TabLayout mTabLayout;
    protected ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();
    protected int pageInfoType ;


    public ViewPagerFragmentPagerAdapter(FragmentManager fm, ViewPager mViewPager, TabLayout mTabLayout) {
        super(fm);
        this.mViewPager = mViewPager;
        this.mTabLayout = mTabLayout;
        this.mContext = mViewPager.getContext();
        this.mViewPager.setAdapter(this);
        this.mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);
        return  info.mFragment;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ViewPageInfo info = mTabs.get(position);
        return info.title;
    }

    /**
     * 显示TabLayout 的图标和自定义View
     */
    public void showTabTitle(){
        if( this.pageInfoType == ViewPageInfo.TEXT){
            return ;
        }
        if( this.pageInfoType == ViewPageInfo.ICON){
            mTabLayout.setOnTabSelectedListener(this);
        }
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            ViewPageInfo info = mTabs.get(i);
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            switch (info.infoType){
                case ViewPageInfo.ICON:
                    if( i == 0){
                        tab.setIcon(info.icon_sel);
                    } else {
                        tab.setIcon(info.icon_nor);
                    }
                    break;
                case ViewPageInfo.VIEW:
                    tab.setCustomView(info.view);
                    break;
            }

        }



    }

/*
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }*/

    /**
     * 更新PageViewFragment
     */
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mTabLayout.setTabsFromPagerAdapter(this);
    }

    /**
     * 添加一个新的Fragment
     * @param title
     * @param mFragment
     * @return
     */
    public ViewPagerFragmentPagerAdapter addTab(String title, Fragment mFragment) {
        ViewPageInfo info = new ViewPageInfo(title, mFragment, ViewPageInfo.TEXT);
        mTabs.add(info);
        this.pageInfoType =  ViewPageInfo.TEXT;
        return this;
    }

    /**
     * 添加一个新的Fragment
     * @param icon_nor
     * @param icon_sel
     * @param mFragment
     * @return
     */
    public ViewPagerFragmentPagerAdapter addTab(@DrawableRes int icon_nor, @DrawableRes int icon_sel, Fragment mFragment) {
        ViewPageInfo info = new ViewPageInfo(icon_nor, icon_sel, mFragment, ViewPageInfo.ICON);
        mTabs.add(info);
        this.pageInfoType =  ViewPageInfo.ICON;
        return this;
    }

    /**
     * 添加一个新的Fragment
     * @param view
     * @param mFragment
     * @return
     */
    public ViewPagerFragmentPagerAdapter addTab(View view, Fragment mFragment) {
        ViewPageInfo info = new ViewPageInfo(view, mFragment, ViewPageInfo.VIEW);
        mTabs.add(info);
        this.pageInfoType =  ViewPageInfo.VIEW;
        return this;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Logger.d("post");                   //0  0.0  0
    }                                       //0  0.0046  5    1

    @Override
    public void onPageSelected(int position) {
        Logger.d("post");                   //1
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Logger.d("post");           //1
    }                               //2

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        ViewPageInfo info = mTabs.get(position);
        switch (info.infoType){
            case ViewPageInfo.ICON:
                tab.setIcon(info.icon_sel);
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        ViewPageInfo info = mTabs.get(position);
        switch (info.infoType){
            case ViewPageInfo.ICON:
                tab.setIcon(info.icon_nor);
                break;
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }



}
