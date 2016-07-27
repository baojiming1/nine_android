/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.fragment</p>
 * <p>File：BaseSwipRefreshFragment.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/9/15:39.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.RecyclerViewBuilder;
import com.tp.venus.base.builder.RequestBodyBuilder;
import com.tp.venus.base.mvp.p.BaseListPresenter;
import com.tp.venus.base.mvp.p.IBaseListPresenter;
import com.tp.venus.base.mvp.v.BaseListView;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.model.PageResult;
import com.tp.venus.util.ReflectUtils;
import com.tp.venus.util.ToastUtil;
import com.tp.venus.widget.recyclerView.EndlessRecyclerOnScrollListener;
import com.tp.venus.widget.recyclerView.LoadingFooter;
import com.tp.venus.widget.recyclerView.RecyclerViewStateUtils;

/**
 * <p>Class：com.tp.venus.base.fragment.BaseSwipRefreshFragment</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/9/15:39
 */

public abstract  class BaseSwipRefreshFragment<T> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseListView<T> {


    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
   // protected TipInfoLayout mTipInfoLayout;

    protected CommonAdapter<T, ?> adapter  ;
    protected RecyclerViewBuilder mRecyclerViewBuilder;
    protected RequestBodyBuilder mRequestBodyBuilder;
    private Class<T> classType;
    protected IBaseListPresenter mBaseListPresenter;
    private boolean hasNext;            //是否有更多的数据
    protected Request.Builder mBuilder ;
    public BaseSwipRefreshFragment(){
        classType = ReflectUtils.getClassGenricType(this.getClass());
    }


    private EndlessRecyclerOnScrollListener mOnScrollListener = new SwipEndlessRecyclerOnScrollListener() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        if( mBaseListPresenter == null){
            this.mBaseListPresenter = getPresenter();
            this.mBaseListPresenter.setClassType(classType);
        }
        if(mRequestBodyBuilder  == null){
            mRequestBodyBuilder = RequestBodyBuilder.create();
        }
        View view = oncreateView(inflater, container, savedInstanceState);
        if( view != null){
            return view;
        }
        return inflater.inflate(R.layout.base_swiprefresh, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        if( !lazyLoad() ){
            sendHttp();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
       // mTipInfoLayout = (TipInfoLayout) view.findViewById(R.id.tip_info_layout);
        mSwipeRefreshLayout.setColorSchemeColors(R.color.colorAccent, R.color.pink, R.color.themeColor, R.color.fontColor);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
       /* mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                .getDisplayMetrics()));*/
        adapter = getAdapter();
        mRecyclerViewBuilder = RecyclerViewBuilder.create(mRecyclerView).setAdapter(adapter).addOnScrollListener(mOnScrollListener).setDefaultItemDecoration(RecyclerViewBuilder.DECOR_VERTICAL_LIST, R.drawable.divider_bg_fine).
                setLayoutManager(RecyclerViewBuilder.LINER_VERTICAL);
        buildRecyclerViewBuilder(mRecyclerViewBuilder);
        mRecyclerViewBuilder.build();


      /*  mTipInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRequestBodyBuilder.setPageNow(AppConfig.DEFAULT_PAGE_NOW);
                sendHttp();
            }
        });*/
    }

    /**
     * 布局加载
     * @param layoutRes
     * @return
     */
    public View inflate(@LayoutRes int layoutRes){
        return inflater.inflate(layoutRes, null);
    }

    public RecyclerViewBuilder getRecyclerViewBuilder(){
        return this.mRecyclerViewBuilder;
    }

    public EndlessRecyclerOnScrollListener getEndlessRecyclerOnScrollListener(){
        return this.mOnScrollListener;
    }

    /**************************************** base view 实现 *********************************************/

    @Override
    public void onRefresh() {
       // ToastUtil.getInstance().show("下拉刷新...");
        setSwipeRefreshLoadingState();
        mRequestBodyBuilder.setPageNow(AppConfig.DEFAULT_PAGE_NOW);
        sendHttp();
    }

    @Override
    public void addData(PageResult<T> pageResult) {
        if( pageResult.getPageNum() == 1){                  //第一页
            setSwipeRefreshLoadedState();
            adapter.clear();
        } else {                                            //其他页处理
            RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Loading.Normal);
        }
        hasNext = pageResult.isHasNext();
        adapter.addAll(pageResult.getRows());
    }


    @Override
    public void onAddDataBefore() {
       // mTipInfoLayout.setHiden();
        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
        setSwipeRefreshLoadedState();
    }

    @Override
    public void onError() {
       // mTipInfoLayout.setLoadError(R.string.netword_error);
        if( adapter.getItemCount() == 0 ){
            ToastUtil.getInstance().show(R.string.netword_error);
           // mTipInfoLayout.setEmptyData(R.string.netword_error);
        } else {
            RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Loading.NetWorkError);
        }
        setSwipeRefreshLoadedState();
    }

    @Override
    public void dataNull() {
      //  mTipInfoLayout.setEmptyData(R.string.data_empty);
        setSwipeRefreshLoadedState();
        adapter.clear();
    }

    @Override
    public void onCompleted() {
        setSwipeRefreshLoadedState();
    }

    /************************************* 抽象的方法 ****************************************/

    /**
     * 组织请求数据
     *
     * @param
     */
    protected abstract Request buildRequest(Request.Builder builder, RequestBodyBuilder bodyBuilder);

    /**
     * 组织布局
     *
     * @param mCommonViewHolder
     * @param item
     * @param position
     */
    protected  void convertLayout(CommonViewHolder mCommonViewHolder, T item, int position){

    }


    /**
     *  获取列表的子布局
     */
    protected abstract @LayoutRes int getItemLayout();


    /******************************************* 可重写的方法 ***********************************************/


    /**
     * 自定义布局的时候使用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected  View oncreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return null;
    }


    /**
     * 需要另外设置RecyclerView参数时，需要重写
     * @param mRecyclerViewBuilder
     */
    protected void buildRecyclerViewBuilder(RecyclerViewBuilder mRecyclerViewBuilder){

    }

    /**
     *  重写头部资源布局文件
     * @return
     */
    protected @LayoutRes int getHeadLayout(){
        return 0;
    }

    /**
     * 当当前的请求需要token的时候请重写该方法，默认 AppConfig.TokenStatus.NORMAL
     * @return
     */
    protected @Status.TokenStatus int getTokenStatus(){
        return Status.TokenStatus.NORMAL;
    }

    //获取adapter
    protected  CommonAdapter<T,?> getAdapter(){
        return new CommonAdapter<T, CommonViewHolder>(this.mContext, getItemLayout()) {
            @Override
            public void convert(CommonViewHolder mCommonViewHolder, T item, int position) {
                convertLayout(mCommonViewHolder,  item, position);
            }
        };
    }

    /**
     * 自定义的时候请重写
     * @return
     */
    protected IBaseListPresenter getPresenter(){
       // return getPresenter(new BaseListPresenter(this));
        return new BaseListPresenter(this);         //不需要代理
    }

    /**
     * 自定义发送请求组合数据，默认不组合数据
     * @param request
     * @param tokenStatus
     */
    protected void sendSearch(Request request, @Status.TokenStatus int tokenStatus){
        mBaseListPresenter.search( request, tokenStatus);
    }

    /**
     * 是否开启懒加载模式
     * @return
     */
    protected boolean lazyLoad(){
        return false;
    }

    /************************************************ private **********************************************/


    /**
     * 发请求 请勿重写
     */
    protected final void sendHttp(){
        Request request = buildRequest(getBuilder(), mRequestBodyBuilder);
        if( adapter.getItemCount() == 0 ){
          //  mTipInfoLayout.setLoading();
        }
        sendSearch(request, getTokenStatus());
    }


    //RequestBuilder参数设置
    private Request.Builder getBuilder(){
        if( mBuilder == null){
            mBuilder = new Request.Builder();
        }
        return mBuilder;
    }

    /**
     * 设置顶部正在加载的状态
     */
    void setSwipeRefreshLoadingState() {
        if (mSwipeRefreshLayout != null) {
            if( !mSwipeRefreshLayout.isRefreshing() ){
                mSwipeRefreshLayout.setRefreshing(true);
                // 防止多次重复刷新
                mSwipeRefreshLayout.setEnabled(false);
            }
        }
    }

    /**
     * 设置顶部加载完毕的状态
     */
    void setSwipeRefreshLoadedState() {
        if (mSwipeRefreshLayout != null) {
            if( mSwipeRefreshLayout.isRefreshing() ){
                mSwipeRefreshLayout.setRefreshing(false);
                mSwipeRefreshLayout.setEnabled(true);
            }
        }
    }



    /**
     * 滚动监听
     */
    public  class  SwipEndlessRecyclerOnScrollListener extends EndlessRecyclerOnScrollListener{
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
            if(state == LoadingFooter.State.Loading) {
                //  ToastUtil.getInstance().show("正在加载中，请稍后。。。");
                Logger.d("the state is Loading, just wait..");
                return;
            }
            if( hasNext ){               //load more
                mRequestBodyBuilder.nextPage();
                sendHttp();
                RecyclerViewStateUtils.setFooterViewState(mContext, mRecyclerView, mRequestBodyBuilder.getPageSize(), LoadingFooter.State.Loading.Loading, null);
            } else {                 //load the end
                RecyclerViewStateUtils.setFooterViewState(mRecyclerView, LoadingFooter.State.Loading.TheEnd);
            }
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:                // 状态为0时：当前屏幕停止滚动；
                    Glide.with(getFragment()).resumeRequests();
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:            //状态为1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；
                    Glide.with(getFragment()).resumeRequests();
                    break;
                case RecyclerView.SCROLL_STATE_SETTLING:            // 状态为2时：随用户的操作，屏幕上产生的惯性滑动；
                    Glide.with(getFragment()).pauseRequests();
                    break;
            }

        }
    }

}
