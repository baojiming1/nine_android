/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.fragment</p>
 * <p>File：BaseRefreshFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/20/17:17.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.mvp.p.BaseRefreshPresenter;
import com.tp.venus.base.mvp.p.IBaseRefreshPresenter;
import com.tp.venus.base.mvp.v.BaseRefreshView;
import com.tp.venus.config.Status;
import com.tp.venus.util.ReflectUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**<p>Class：com.tp.venus.base.fragment.BaseRefreshFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/20/17:17
 * @version 1.0.0
 */

public abstract class BaseRefreshFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseRefreshView<T> {

    @InjectView(R.id.mSwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    protected Request.Builder mBuilder ;
    protected RequestBodyBuilder mRequestBodyBuilder;
    protected IBaseRefreshPresenter mIBaseRefreshPresenter;

    protected Class classType;

    public BaseRefreshFragment(){
        classType = ReflectUtils.getClassGenricType(this.getClass());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.inject(this, view);
        if( mIBaseRefreshPresenter == null){
            mIBaseRefreshPresenter = getPresenter();
            mIBaseRefreshPresenter.setClassType(classType);
        }
        if(mRequestBodyBuilder  == null){
            mRequestBodyBuilder = RequestBodyBuilder.create();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefresh();
        if( !lazyLoad() ){
            sendHttp();
        }
    }


    protected void initRefresh(){
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }



    @Override
    public void onRefresh() {
        setSwipeRefreshLoadingState();
        sendHttp();
    }


    protected void sendHttp(){
        Request request = buildRequest(getBuilder(), mRequestBodyBuilder);
        sendSearch(request, getTokenStatus());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //RequestBuilder参数设置
    private Request.Builder getBuilder(){
        if( mBuilder == null){
            mBuilder = new Request.Builder();
        }
        return mBuilder;
    }

    /**
     * 当当前的请求需要token的时候请重写该方法，默认 AppConfig.TokenStatus.NORMAL
     * @return
     */
    protected @Status.TokenStatus int getTokenStatus(){
        return Status.TokenStatus.NORMAL;
    }

    /**
     * 自定义发送请求组合数据，默认不组合数据
     * @param request
     * @param tokenStatus
     */
    protected void sendSearch(Request request, @Status.TokenStatus int tokenStatus){
        mIBaseRefreshPresenter.start(request, tokenStatus);
    }

    /**
     * 自定义的时候请重写
     * @return
     */
    protected IBaseRefreshPresenter getPresenter(){
        // return getPresenter(new BaseListPresenter(this));
        return new BaseRefreshPresenter(this);         //不需要代理

    }

    /**
     * 组织请求数据
     *
     * @param
     */
    protected abstract Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder);

    /**
     * 布局资源
     * @return
     */
    protected abstract @LayoutRes int getLayout();

    /**
     * 是否开启懒加载模式
     * @return
     */
    protected boolean lazyLoad(){
        return false;
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
    public void onError() {
        setSwipeRefreshLoadedState();
    }

    @Override
    public void dataNull() {
        setSwipeRefreshLoadedState();
    }


    @Override
    public void onAddDataBefore() {
        setSwipeRefreshLoadedState();
    }
}
