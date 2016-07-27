/**<p>项目名：MyClient</p>
 * <p>包名：	com.bob.myclient.base.adapter</p>
 * <p>文件名：CommonViewHolder.java</p>
 * <p>版本信息： 4.0.0</p>
 * <p>日期： 2015/7/31/16:37.</p>
 * Copyright (c) 2015qnvip-版权所有
 */
package com.tp.venus.base.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * <p>名称：com.bob.myclient.base.adapter.CommonViewHolder</p>
 * <p>描述：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 2.1.0
 * @date 2015/7/31/16:37
 */

public class CommonViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewHolder;

    public CommonViewHolder(View itemView) {
        super(itemView);
        viewHolder = new SparseArray<View>();
    }

    /**
     * 通过ID来获取指定的View
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends  View> T findViewById(@IdRes int viewId){
        View childView = viewHolder.get(viewId);
        if(childView == null){
            childView = itemView.findViewById(viewId);
            viewHolder.put(viewId, childView);
        }
        return (T) childView;
    }
}