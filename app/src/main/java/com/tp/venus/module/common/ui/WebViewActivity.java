/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.ui.view</p>
 * <p>File：WebViewActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/12:55.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.common.ui.view.WebViewActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2016/1/27/12:55
 */

public class WebViewActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    public static final String URL_KEY = "url";
    public static final String TITLE_KEY = "title";

    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;
    @InjectView(R.id.webview_layout)
    LinearLayout webviewLayout;
    @InjectView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected String currentUrl;
    protected String title;
    protected TextView midTextView;
    protected  ToolbarBuilder mToolbarBuilder;
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout();
        currentUrl = getIntent().getStringExtra(URL_KEY);
        title = getIntent().getStringExtra(TITLE_KEY);
        if (StringUtil.isEmpty(currentUrl)) {
            ToastUtil.getInstance().show("页面不存在");
            return;
        }
        ButterKnife.inject(this);
        initView();
        if(null != savedInstanceState){
            mWebView.restoreState(savedInstanceState);
        }else{
            mWebView.loadUrl(currentUrl);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }




    private void initWebView(){
        mWebView = new WebView(ApplicationHandler.getApp().getApplicationContext());

        WebChromeClient wcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String str) {
                super.onReceivedTitle(view, title);
                //midTextView.setText(title);
                title = str;
                midTextView.setText(StringUtil.splitString(str, 10));
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //隐藏进度条
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    if (!mSwipeRefreshLayout.isRefreshing())
                        mSwipeRefreshLayout.setRefreshing(true);
                }
                super.onProgressChanged(view, newProgress);
            }
        };
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                currentUrl = url;
                mWebView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                onViewFinished();
            }
        };
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebSettings.setJavaScriptEnabled(true);
        //取消滚动条
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setWebChromeClient(wcc);
        mWebView.setWebViewClient(wvc);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {  //表示按返回键 时的操作
                        mWebView.goBack();   //后退
                        //webview.goForward();//前进
                        return true;    //已处理
                    }
                }
                return false;
            }
        });
        webviewLayout.addView(mWebView);
    }

    /**
     * 可重写
     */
    protected void setContentLayout(){
        setContentView(R.layout.activity_webview);
    }

    private void initView() {
        mToolbarBuilder = getToolbarBuilder(mToolbar).setTitle(title == null ? "" : title);
        midTextView = mToolbarBuilder.getMidTextView();
        mToolbarBuilder.build();
        initWebView();
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mWebView.loadUrl(currentUrl);
        setSwipeRefreshLoadedState();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }


    /**
     * 设置顶部正在加载的状态
     */
    void setSwipeRefreshLoadingState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(true);
            // 防止多次重复刷新
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    void setSwipeRefreshLoadedState() {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setRefreshing(false);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        webviewLayout.removeAllViews();
        mWebView.stopLoading();
        mWebView.removeAllViews();
        mWebView.destroy();
        mWebView = null;
        webviewLayout = null;
        mToolbarBuilder = null;
        super.onDestroy();
        System.exit(0);
    }

    protected void onViewFinished(){

    }
}
