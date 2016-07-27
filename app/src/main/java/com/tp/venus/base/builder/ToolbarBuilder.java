/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.builder</p>
 * <p>File：ToolbarBuilder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/14/13:20.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.builder;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.util.ResourcesUtil;

/**<p>Class：com.tp.venus.base.builder.ToolbarBuilder</p>
 * <p>Description：</p>
 * <pre>
 *     Toolbar构造器，耦合性比较高
 * </pre>
 * @author 鲍建明
 * @date 2015/10/14/13:20
 * @version 1.0.0
 */

public class ToolbarBuilder {

    private Context mContext;
    private ActionBar mActionBar;
    private AppCompatActivity mAppCompatActivity;
    private Toolbar mToolbar;
    private TextView midTextView;
    private TextView rightTextView;
    private int midTitle;
    private int rightTitle;
    private int rightIcon;
    private CharSequence midStr;
    private CharSequence rightStr;
    private @DrawableRes int homeAsUpIndicator;

    private boolean hideBack = false;           //隐藏返回键标示符
    private boolean hideToolbar = false;

    public static ToolbarBuilder create( Toolbar mToolbar){
        return new ToolbarBuilder(mToolbar);
    }

    ToolbarBuilder(Toolbar mToolbar){
        this.mToolbar = mToolbar;
        this.midTextView = (TextView) mToolbar.findViewById(R.id.mToolbarTitle);
        this.rightTextView = (TextView) mToolbar.findViewById(R.id.mToolbarRightTitle);
        this.mContext = mToolbar.getContext();
        this.mToolbar.setTitle("");
    }

    /**
     *
     * @param mAppCompatActivity
     * @return
     */
    public ToolbarBuilder setAppCompatActivity(AppCompatActivity mAppCompatActivity){
        this.mAppCompatActivity = mAppCompatActivity;
        return this;
    }

    /**
     * 构建
     * @return
     */
    public ToolbarBuilder build(){
        if( hideToolbar) {
            return this;
        }
        if( mAppCompatActivity != null){
            mAppCompatActivity.setSupportActionBar(mToolbar);
            this.mActionBar = mAppCompatActivity.getSupportActionBar();
            if( hideBack || mActionBar == null){
                return this;
            }
            mActionBar.setHomeButtonEnabled(true); //设置返回键可用
            mActionBar.setDisplayHomeAsUpEnabled(true);
            if(homeAsUpIndicator != 0){
                mActionBar.setHomeAsUpIndicator(homeAsUpIndicator);
            } else {
                mActionBar.setHomeAsUpIndicator(R.drawable.back);
            }

        }
        setTitles();
        return this;
    }

    /**
     * 设置返回键图标
     * @param drawable
     * @return
     */
    public ToolbarBuilder setHomeAsUpIndicator(@DrawableRes int drawable){
        this.homeAsUpIndicator = drawable;
        return this;
    }


    /**
     * 设置返回键
     */
    public void showBack(){
        mActionBar.setHomeButtonEnabled(true); //设置返回键可用
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.drawable.back);
    }

    /**
     * 影藏返回键
     */
    public void hideBack(){
        mActionBar.setHomeButtonEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(false);
    }

    /**
     * 隐藏Toolbar
     * @return
     */
    public ToolbarBuilder hideToolbar(){
        hideToolbar = true;
        return this;
    }

    /**
     * 设置Toolbar背景色
     * @param color
     * @return
     */
    public ToolbarBuilder setBackgroundColor(@ColorRes int color){
        if( color == 0){
            mToolbar.setDrawingCacheBackgroundColor(0);
            return this;
        }
        mToolbar.setDrawingCacheBackgroundColor(ResourcesUtil.getColor(mContext, color));
        return this;
    }

    /**
     * 设置标题
     * @param midTitle
     * @param rightTitle
     * @return
     */
    public ToolbarBuilder setTitle(@StringRes int midTitle, @StringRes int rightTitle){
        this.midTitle = midTitle;
        this.rightTitle = rightTitle;
        return  this;
    }

    /**
     * 设置主标题
     * @param midTitle
     * @return
     */
    public ToolbarBuilder setTitle(@StringRes int midTitle){
        this.midTitle = midTitle;
        this.rightTitle = 0;
        return  this;
    }

    /**
     * 设置标题
     * @param midTitle
     * @return
     */
    public ToolbarBuilder setTitle(CharSequence midTitle){
        this.midTitle = 0;
        this.rightTitle = 0;
        this.midStr = midTitle;
        return  this;
    }

    /**
     * 设置标题
     * @param midTitle
     *  @param rightTitle
     * @return
     */
    public ToolbarBuilder setTitle(CharSequence midTitle, CharSequence rightTitle){
        this.midTitle = 0;
        this.rightTitle = 0;
        this.midStr = midTitle;
        this.rightStr = rightTitle;
        return  this;
    }


    /**
     * 设置右边的图标
     * @param rightIcon
     * @return
     */
    public ToolbarBuilder setRightIcon(@DrawableRes int rightIcon){
        this.rightIcon = rightIcon;
        return this;
    }

    /**
     * 为右边组件添加监听事件
     * @param mAction
     * @return
     */
    public ToolbarBuilder addRightOnClickListener(RxViewListener.Action mAction){
        RxViewListener.clicks(rightTextView, mAction);
        return this;
    }


    /**
     * 隐藏返回键
     * @return
     */
    public ToolbarBuilder setHideBack(){
        this.hideBack = true;
        return this;
    }

    /**
     * 获取最后边的TextView
     * @return
     */
    public TextView getRightTextView(){
        return this.rightTextView;
    }

    /**
     * 获取中间的标题
     * @return
     */
    public TextView getMidTextView(){
        return this.midTextView;
    }

    public Toolbar getToolbar(){
        return this.mToolbar;
    }


    //设置标题
    private void setTitles(){
        if(midTitle != 0){
            midTextView.setText(midTitle);
        }
        if( rightTitle != 0 ){
            rightTextView.setText(rightTitle);
        }
        if( !TextUtils.isEmpty(midStr) ){
            midTextView.setText(midStr);
        }
        if( !TextUtils.isEmpty(rightStr) ){
            rightTextView.setText(rightStr);
        }
        if( rightIcon != 0){
            rightTextView.setBackgroundResource(rightIcon);
        }
    }




//    // 初始化ActionBar
//    private void initActionBar() {
//      /*  mActionBar =*/
//       /* int flags = ActionBar.DISPLAY_HOME_AS_UP;
//        int change = mActionBar.getDisplayOptions() ^ flags;
//        // 设置返回的图标
//        mActionBar.setDisplayOptions(change, flags);*/
//
//    }
}
