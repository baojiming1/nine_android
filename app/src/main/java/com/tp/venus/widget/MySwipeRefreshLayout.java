/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.widget</p>
 * <p>File：MySwipeRefreshLayout.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/7/14/10:48.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**<p>Class：com.tp.venus.widget.MySwipeRefreshLayout</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/7/14/10:48
 * @version 1.0.0
 */

public class MySwipeRefreshLayout extends SwipeRefreshLayout {

    ViewGroup viewGroup;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (viewGroup.getScrollY() > 1) {
            // 交由子View处理
            return false;
        } else {
            // 让SwipeRefreshLayout处理本次事件
            return super.onTouchEvent(arg0);
        }
    }

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        this.viewGroup.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (MySwipeRefreshLayout.this.viewGroup.getScrollY() <= 1) {
                    MySwipeRefreshLayout.this.setEnabled(true);
                }
                return false;
            }
        });
    }
}
