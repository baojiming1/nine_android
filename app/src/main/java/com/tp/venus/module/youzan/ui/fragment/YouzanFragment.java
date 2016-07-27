/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.youzan.ui.fragment</p>
 * <p>File：YouzanFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/19:00.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.base.fragment.BaseFragment;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.youzan.YouzanView;
import com.tp.venus.module.youzan.presenter.IYouzanPresenter;
import com.tp.venus.module.youzan.presenter.impl.YouzanPresenter;
import com.tp.venus.module.youzan.ui.view.IYouzanView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.youzan.ui.fragment.YouzanFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/2/18/19:00
 */

public class YouzanFragment extends BaseFragment implements IYouzanView, SwipeRefreshLayout.OnRefreshListener {

    public static String URL_KEY = "url";
    public static String ISVISIBLETOUSER = "isVisibleToUser";

    @InjectView(R.id.mToolbarTitle)
    TextView mToolbarTitle;
    @InjectView(R.id.mToolbarRightTitle)
    TextView mToolbarRightTitle;
    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @InjectView(R.id.webview_layout)
    LinearLayout webviewLayout;

    private YouzanView mYouzanView;
    private IYouzanPresenter mIYouzanPresenter;
    private String url;
    private boolean visibleToUser;
    private boolean isFirst = true;
    WebView mWebView;

    public static YouzanFragment newInstance(String url, boolean isVisibleToUser) {
        YouzanFragment mYouzanFragment = new YouzanFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString(URL_KEY, url);
        mBundle.putBoolean(ISVISIBLETOUSER, isVisibleToUser);
        mYouzanFragment.setArguments(mBundle);
        return mYouzanFragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {                  //可见时
            if(visibleToUser){
                if( isFirst ){
                    isFirst = false;
                    mIYouzanPresenter.openYouzan(url);
                } else {
                    User user = mIYouzanPresenter.getCurrentUser();
                    if( user == null){
                        mIYouzanPresenter.openYouzan(url);
                    }
                }
            }
        }                               //不可见时
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_webview, container, false);
        ButterKnife.inject(this, view);
        initWebView();
        return view;
    }

    private void initWebView(){
        mWebView = new WebView(ApplicationHandler.getApp().getApplicationContext());
        webviewLayout.addView(mWebView);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        url = getArguments().getString(URL_KEY);
        visibleToUser = getArguments().getBoolean(ISVISIBLETOUSER, false);
        if (mIYouzanPresenter == null) {
            mIYouzanPresenter = getPresenter(new YouzanPresenter(this));
        }
        if (!visibleToUser) {
            mIYouzanPresenter.openYouzan(url);
            initView(true);
        } else {
            initView(false);
        }


    }


    private void initView(boolean isShowBack) {
        if (isShowBack) {
            ToolbarBuilder.create(mToolbar).setTitle("").
                    setAppCompatActivity((AppCompatActivity) getActivity())
                    .build();
        } else {
            ToolbarBuilder.create(mToolbar).setTitle("")
                    .build();
        }
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorAccent, R.color.pink, R.color.themeColor, R.color.fontColor);
        mSwipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @Override
    public void openYouzanView(String url) {
        if (mYouzanView == null) {
            mYouzanView = new YouzanView(mWebView, getActivity(), url, mIYouzanPresenter.getCurrentUser(), mToolbarTitle);
        }
        mYouzanView.loadWebView(url);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onRefresh() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(false);
        }
        if( mYouzanView != null){
            mIYouzanPresenter.openYouzan(mYouzanView.getCurrentUrl());
        }
    }
}
