package com.tp.venus.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>名称：com.bob.designsupportdemo.CommonAdapter</p>
 * <p>描述：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 2.1.0
 * @date 2015/7/17/9:55
 */

public abstract class CommonAdapter<T, VH extends CommonViewHolder> extends RecyclerView.Adapter<CommonViewHolder> {

    private List<T> datas;
    protected LayoutInflater mLayoutInflater;
    private int resources;
    private Context mContext;
    private final Object mLock = new Object();

    private OnRecyclerViewItemClickListener<T> mOnRecyclerViewItemClickListener;

    public CommonAdapter(Context mContext, @LayoutRes int resources){
        this(mContext, resources, new ArrayList<T>());
    }

    public CommonAdapter(Context mContext, @LayoutRes int resources, List<T> datas){
        this.resources = resources;
        this.datas = datas;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public  interface OnRecyclerViewItemClickListener<T>{
        void onItemClick(View view, T item, int position);
    }

    public void addOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener ){
        this.mOnRecyclerViewItemClickListener = mOnRecyclerViewItemClickListener;
    }

    /**
     * 获取点击事件
     * @return
     */
    protected View.OnClickListener getOnClickListener(final View view,final T item, final int position){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mOnRecyclerViewItemClickListener.onItemClick(view, item, position);
            }
        };
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(resources, parent, false);
        return (VH) getViewHolder(view, parent);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        if( mOnRecyclerViewItemClickListener != null ){
            View.OnClickListener mOnClickListener = getOnClickListener(holder.itemView, getItem(position), position);
            holder.itemView.setOnClickListener(mOnClickListener);
        }
        convert((VH) holder, getItem(position), position);
    }


    protected CommonViewHolder getViewHolder(View view, ViewGroup parent){
        return  new CommonViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public T getItem(int position){
        return datas.get(position);
    }

    /**
     * 新增条目
     * @param collection
     */
    public void addAll(Collection<? extends T> collection){
        if( collection == null ){
            return ;
        }
        synchronized (mLock) {
            this.datas.addAll(collection);
        }
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据
     * @param data
     */
    public void add(T data){
        if( data == null){
            return ;
        }
        synchronized (mLock){
            this.datas.add(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 刷新某项
     * @param postion
     * @param data
     */
    public void refresh(int postion, T data){
        synchronized (mLock) {
            datas.set(postion, data);
        }
        notifyItemChanged(postion);
    }

    /**
     * 插入到某个位置
     * @param data
     * @param location
     */
    public void add(T data, int location){
        if( data == null){
            return ;
        }
        synchronized (mLock){
            this.datas.add(location, data);
        }
        notifyDataSetChanged();
    }

    /**
     *  删除某个数据
     * @param position
     */
    public void remove(int position){
        synchronized (mLock) {
            this.datas.remove(position);
        }
        notifyItemRemoved(position);
    }

    /**
     *  删除某个数据
     * @param data
     */
    public void remove(T data){
        synchronized (mLock){
            this.datas.remove(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 清楚掉所有数据
     */
    public void clear(){
        synchronized (mLock) {
            this.datas.clear();
        }
        notifyDataSetChanged();
    }

    /**
     * 获取所有的数据
     * @return
     */
    public List<T> getDatas(){
        return this.datas;
    }

    public Context getContext(){
        return this.mContext;
    }

    public boolean isLast(int position){
        return (datas.size() - 1) == position;
    }

    public abstract void convert(VH mCommonViewHolder, T item, int position);



}
