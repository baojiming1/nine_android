/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.youzan</p>
 * <p>File：YouzanView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/19/10:52.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan;

import android.app.Activity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ToastUtil;
import com.youzan.sdk.YouzanBridge;
import com.youzan.sdk.YouzanSDK;
import com.youzan.sdk.YouzanUser;
import com.youzan.sdk.http.engine.OnRegister;
import com.youzan.sdk.http.engine.QueryError;
import com.youzan.sdk.web.plugin.YouzanChromeClient;
import com.youzan.sdk.web.plugin.YouzanWebClient;

/**<p>Class：com.tp.venus.module.youzan.YouzanView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/19/10:52
 * @version 1.0.0
 */

public class YouzanView  {

    /**
     * H5和原生的桥接对象
     */
    private YouzanBridge bridge;
    /**
     * WebView
     */
    private WebView web;

    private Activity activity;
    private String url;
    private TextView mToolbarTitle;
    private int showLength = 20;            //显示的标题字数
    private String currentUrl;              //当前的URL
    private String data;


    public YouzanView(WebView web, Activity activity, String url, User currnetUser, TextView mToolbarTitle){
        this.web = web;
        this.activity = activity;
        this.url = url;
        this.mToolbarTitle = mToolbarTitle;
        this.currentUrl = url;
        init(currnetUser);
    }


    public YouzanView(WebView web, Activity activity){
        this.web = web;
        this.activity = activity;
        init(null);
    }

    private void init(User currnetUser){
        initWebView();
        initBridge();
        registerYouzanUser(currnetUser);
    }


    private void initWebView(){
      /*  web.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && pageGoBack() ) {  //表示按返回键 时的操作
                        //web.goBack();   //后退
                        return true;    //已处理
                    }
                }
                return false;
            }
        });*/
        WebSettings webSettings = web.getSettings();
       /* webSettings.setDefaultTextEncodingName("UTF-8");*/
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        web.getSettings().setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.setVerticalScrollBarEnabled(false);
        web.setVerticalScrollbarOverlay(false);
        web.setHorizontalScrollBarEnabled(false);
        web.setHorizontalScrollbarOverlay(false);

    }

    /**
     * 打开有赞入口网页需先注册有赞用户
     *
     * 如果你们App的用户这个时候还没有登录, 请先跳转你们的登录页面, 然后再回来同步用户信息
     */
    private void registerYouzanUser(User currnetUser) {
        YouzanUser user = new YouzanUser();
       /* String userId = currnetUser.getUserId();
        if(StringUtil.isEmpty(userId)){
            userId = currnetUser.getId();
        }*/
        user.setUserId("123456789");
        user.setAvatar("");
        user.setGender(1);
        user.setNickName("9号仓");
        user.setTelephone("13412341234");
        user.setUserName("9号仓");
        YouzanSDK.asyncRegisterUser(user, new OnRegister() {
            /**
             * 注册失败, 请参考错误信息修改注册参数
             *
             * 如报非法请求, 请检查UA是否正确
             */
            @Override
            public void onFailed(QueryError queryError) {
                ToastUtil.getInstance().show(queryError.getMsg());
            }

            /**
             * 注册成功, 打开有赞入口网页
             */
            @Override
            public void onSuccess() {
              //  loadWebView(url);
                loadData(data);
            }
        });
    }

    public String getCurrentUrl(){
        return currentUrl;
    }


    /**
     * 加载有赞网页
     */
    public void loadWebView(String url){
        if( StringUtil.isNotEmpty(url)){
            web.loadUrl(url);//店铺链接
        }
    }

    public void loadData(String data){
        this.data = data;
        if(StringUtil.isNotEmpty(data)){
           // web.loadData(data, "text/html", "utf-8");
            //web.loadDataWithBaseURL(null, data, "text/html", "utf-8", null);
            web.loadDataWithBaseURL(null,data, "text/html",  "UTF-8", null);
            web.getSettings().setJavaScriptEnabled(true);
        }
    }

    /**
     * 页面回退
     */
    public boolean pageGoBack(){
        return bridge != null && bridge.pageGoBack();
    }

    private void initBridge(){
        bridge = YouzanBridge.build(activity, web).
                setChromeClient(new YzWebClient())
                .setWebClient(new YzWebViewClient())
                .create();
        /*bridge.add(new ShareEvent())     //分享事件
                .add(new AppPayEvent()); //按需添加, 使用微信APP支付*/
    }

    public class YzWebClient extends YouzanChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if( mToolbarTitle != null){
                mToolbarTitle.setText( StringUtil.splitString(title, showLength) );
            }
        }


    }

    public class YzWebViewClient extends YouzanWebClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            currentUrl = url;
        }

    }

}
