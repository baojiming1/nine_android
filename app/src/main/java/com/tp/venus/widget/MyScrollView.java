/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.widget</p>
 * <p>File：MyScrollView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/29/10:24.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ScrollView;

/**<p>Class：com.tp.venus.widget.MyScrollView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/29/10:24
 * @version 1.0.0
 */

public class MyScrollView extends ScrollView {

    private GestureDetector mGestureDetector;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        if (focused instanceof WebView)
            return;
        super.requestChildFocus(child, focused);
    }

    // Return false if we're scrolling in the x direction
    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }

}
