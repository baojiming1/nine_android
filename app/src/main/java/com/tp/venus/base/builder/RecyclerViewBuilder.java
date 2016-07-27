/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.builder</p>
 * <p>File：RecyclerViewBuilder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/12/14:58.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.builder;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.decoration.DividerItemDecoration;
import com.tp.venus.base.decoration.SpaceItemDecoration;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.widget.recyclerView.HeaderAndFooterRecyclerViewAdapter;
import com.tp.venus.widget.recyclerView.HeaderSpanSizeLookup;
import com.tp.venus.widget.recyclerView.RecyclerViewUtils;

/**<p>Class：com.tp.venus.base.builder.RecyclerViewBuilder</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/12/14:58
 * @version 1.0.0
 */

public class RecyclerViewBuilder {

    /**
     * 线性布局-水平
     */
    public static final int LINER_VERTICAL = 1;

    /**
     *   线性布局 - 垂直
     */
    public static final int LINER_HORIZONTAL = 2;

    /**
     * 垂直布局
     */
    public static final int HORIZONTAL  = 3;
    /**
     * 水平布局
     */
    public static final int VERTICAL = 4;

    /**
     * 瀑布流布局
     */
    public static final int WATERFALL = 5;

    @IntDef({LINER_VERTICAL, LINER_HORIZONTAL, HORIZONTAL, VERTICAL, WATERFALL})
    public @interface RecyclerViewLayout{}

    /**
     * 水平线
     */
    public static final int DECOR_VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    /**
     * 垂直线
     */
    public static final int DECOR_HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;


    @IntDef({DECOR_VERTICAL_LIST, DECOR_HORIZONTAL_LIST})
    public @interface DecorLayout{}



    private RecyclerView mRecyclerView;
    private Context mContext;
    private int layoutManager;
    private int showCount = 5;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private CommonAdapter<?, ?> adapter;
    private RecyclerView.ItemDecoration decor;
    private RecyclerView.OnScrollListener listener;
    private CommonAdapter.OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private View headView;
    private View footerView;
    private RecyclerView.ItemAnimator animator;
    private RecyclerView.RecyclerListener mRecyclerListener;
    private boolean autoMeasureEnabled = true;

    public static RecyclerViewBuilder create(RecyclerView mRecyclerView){
        return new RecyclerViewBuilder(mRecyclerView);
    }

    public static RecyclerViewBuilder create(){
        return new RecyclerViewBuilder();
    }

    private RecyclerViewBuilder(){}

    private RecyclerViewBuilder(RecyclerView mRecyclerView){
        this.mContext = mRecyclerView.getContext();
        this.mRecyclerView = mRecyclerView;
        this.layoutManager = LINER_VERTICAL;
       // this.mRecyclerView.setHasFixedSize(true);
    }

    /**
     * 设置布局
     * @param layoutManager
     */
    public RecyclerViewBuilder setLayoutManager(@RecyclerViewLayout int layoutManager){
        this.layoutManager = layoutManager;
        return this;
    }


    /**
     * 设置底部View
     * @param footerView
     * @return
     */
    public RecyclerViewBuilder addFooterView(View footerView){
        this.footerView = footerView;
        return this;
    }

    /**
     * 设置头部View
     * @param headView
     * @return
     */
    public RecyclerViewBuilder addHeaderView(View headView){
        this.headView = headView;
        return this;
    }

    /**
     * 是否自动测量
     * @param enabled
     */
    public RecyclerViewBuilder setAutoMeasureEnabled(boolean enabled){
        this.autoMeasureEnabled = enabled;
        return this;
    }

    /**
     * 返回数据个数
     * @param holder
     * @return
     */
    public int getAdapterPosition(RecyclerView.ViewHolder holder){
        return RecyclerViewUtils.getAdapterPosition(mRecyclerView, holder);
    }


    /**
     * 设置布局
     * @param count
     */
    public RecyclerViewBuilder setLayoutManager(@RecyclerViewLayout int layoutManager, int count){
        this.layoutManager = layoutManager;
        this.showCount = count;
        return this;
    }

    /**
     * 设置数据
     * @param adapter
     * @return
     */
    public RecyclerViewBuilder setAdapter(CommonAdapter<?, ?> adapter){
        this.adapter = adapter;
        this.mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        return this;
    }

    /*
     * 设置ItemClick事件
     * @param mOnRecyclerViewItemClickListener
     * @return
     */
    public RecyclerViewBuilder addOnRecyclerViewItemClickListener(CommonAdapter.OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener){
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
        return  this;
    }



    public RecyclerViewBuilder setItemDecoration(RecyclerView.ItemDecoration decor){
        this.decor = decor;
        return this;
    }

    public RecyclerViewBuilder setSpaceItemDecoration(@DimenRes int dimen, @DecorLayout int orientation){
        this.decor = new SpaceItemDecoration(ResourcesUtil.getDimens(mContext, dimen), orientation);
        return this;
    }


    public RecyclerViewBuilder setDefaultItemDecoration(@DecorLayout int orientation){
        this.decor = new DividerItemDecoration(this.mContext, orientation);
        return this;
    }

    /**
     * 不需要分割线
     * @return
     */
    public RecyclerViewBuilder setNoItemDecoration(){
        this.decor = null;
        return this;
    }


    public RecyclerViewBuilder setDefaultItemDecoration(){
        this.decor = new DividerItemDecoration(this.mContext, DividerItemDecoration.VERTICAL_LIST);
        return this;
    }

    public RecyclerViewBuilder setItemAnimator(RecyclerView.ItemAnimator animator){
        this.animator = animator;
        return this;
    }

    /**
     * 获取布局管理器
     * @return
     */
    public RecyclerView.LayoutManager getLayoutManager(){
        return mLayoutManager;
    }


    public RecyclerViewBuilder setDefaultItemDecoration(@DecorLayout int orientation, @DrawableRes int drawable){
        this.decor = new DividerItemDecoration(this.mContext, orientation, drawable);
        return this;
    }



    public RecyclerViewBuilder addOnScrollListener(RecyclerView.OnScrollListener listener){
        this.listener = listener;
        return this;
    }

    public RecyclerViewBuilder addRecyclerListener(RecyclerView.RecyclerListener mRecyclerListener){
        this.mRecyclerListener = mRecyclerListener;
        return this;
    }




    public RecyclerViewBuilder build(){
        buildLayoutManager();

        if( mOnRecyclerViewItemClickListener != null ){
            adapter.addOnRecyclerViewItemClickListener(mOnRecyclerViewItemClickListener);
        }
        if( adapter == null ){
            throw new RuntimeException("RecyclerView adapter == null");
        }
        mRecyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        if( decor != null){
            mRecyclerView.addItemDecoration(decor);
        }
        if( listener != null ){
            this.mRecyclerView.addOnScrollListener(listener);
        }
        if( animator != null){
            mRecyclerView.setItemAnimator(animator);
        }
        if( headView != null ){
            RecyclerViewUtils.setHeaderView(mRecyclerView, headView);
        }
        if( footerView != null){
            RecyclerViewUtils.setFooterView(mRecyclerView, footerView);
        }
        if(mRecyclerListener != null){
            mRecyclerView.setRecyclerListener(mRecyclerListener );
        }
        mLayoutManager.setAutoMeasureEnabled(autoMeasureEnabled);
        return this;
    }

   /* public int getY(RecyclerView.ViewHolder holder){
        RecyclerViewUtils.getLayoutPosition(mRecyclerView, )
    }*/

    /**
     * 隐藏底部布局
     * @return
     */
    public RecyclerViewBuilder hideFootView(){
        RecyclerViewUtils.removeFooterView(mRecyclerView);
        return this;
    }

    /**
     *
     * @param resid
     * @return
     */
    public RecyclerViewBuilder setBackgroundResource( int resid){
        mRecyclerView.setBackgroundResource(resid);
        return this;
    }


    /**
     * 布局管理器构建
     */
    private void buildLayoutManager(){
        switch (this.layoutManager) {
            case HORIZONTAL:       //纵向GridView
                mLayoutManager = new StaggeredGridLayoutManager(this.showCount, StaggeredGridLayoutManager.HORIZONTAL);
                break;
            case VERTICAL:           //横向GridView
                GridLayoutManager manager = new GridLayoutManager(this.mContext, this.showCount);
                manager.setSpanSizeLookup(new HeaderSpanSizeLookup(mHeaderAndFooterRecyclerViewAdapter, manager.getSpanCount()));
                this.mLayoutManager = manager;
                break;
            case WATERFALL:                    //瀑布流
                mLayoutManager = new StaggeredGridLayoutManager(this.showCount, StaggeredGridLayoutManager.VERTICAL);
                break;
            case LINER_HORIZONTAL:              //线性水平
                LinearLayoutManager mm = new LinearLayoutManager(this.mContext);
                mm.setOrientation(LinearLayoutManager.HORIZONTAL);
                this.mLayoutManager = mm;
                break;
            case LINER_VERTICAL:           //ListView
            default:
                //  this.mLayoutManager = isAdaptive ? new AdaptiveLinerLayoutManager(this.mContext) : new LinearLayoutManager(this.mContext);
                LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this.mContext);
                mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                this.mLayoutManager = mLinearLayoutManager;
                break;
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public RecyclerView getRecyclerView(){
        return  this.mRecyclerView;
    }




}
