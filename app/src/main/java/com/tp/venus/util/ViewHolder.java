/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：ViewHolder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/12/10:11.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import android.util.SparseArray;
import android.view.View;

/**<p>Class：com.tp.venus.util.ViewHolder</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/12/10:11
 * @version 1.0.0
 */

public class ViewHolder {

    /**
     *
     * @param view
     * @param id
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends View> T findById(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
