/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.widget</p>
 * <p>File：MyTextHintView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/23/9:53.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;

import com.jude.rollviewpager.hintview.TextHintView;

/**<p>Class：com.tp.venus.widget.MyTextHintView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/23/9:53
 * @version 1.0.0
 */

public class MyTextHintView extends TextHintView {

    protected int length;
    protected int color;

    public MyTextHintView(Context context) {
        super(context);
    }

    public MyTextHintView(Context context, @ColorInt int color){
        super(context);
        this.color = color;
    }


    public MyTextHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initView(int length, int gravity) {
        this.length = length;
        if( color != 0){
            setTextColor(color);
        } else {
            setTextColor(Color.WHITE);
        }
        switch (gravity) {
            case 0:
                setGravity(Gravity.LEFT| Gravity.CENTER_VERTICAL);
                break;
            case 1:
                setGravity(Gravity.CENTER);
                break;
            case 2:
                setGravity(Gravity.RIGHT| Gravity.CENTER_VERTICAL);
                break;
        }
        setCurrent(0);
    }

    @Override
    public void setCurrent(int current) {
        setText(current+1+"/"+ length);
    }
}
