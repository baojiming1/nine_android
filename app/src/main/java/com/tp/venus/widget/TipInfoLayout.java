/**<p>项目名：OSChinaDemo</p>
 * <p>包名：	com.bob.oschinademo.widget</p>
 * <p>文件名：TipInfoLayout.java</p>
 * <p>版本信息： 2.1.0</p>
 * <p>日期： 2015/6/18/14:48.</p>
 * Copyright (c) 2015帮你公司-版权所有
 */
package com.tp.venus.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tp.venus.R;


/**
 * <p>名称：com.bob.oschinademo.widget.TipInfoLayout</p>
 * <p>描述：</p>
 * <pre>
 *     一些提示信息显示，包含有加载过程的显示
 * </pre>
 *
 * @author 鲍建明
 * @version 2.1.0
 * @date 2015/6/18/14:48
 */

public class TipInfoLayout extends FrameLayout{

    private String netWorkError = "轻触重新加载";
    private String empty = "暂无数据";

    private ProgressBar mProgressBar;
    private TextView mTvTipState;
    private TextView mTvTipMsg;
    private LinearLayout mTipContainer;

    public TipInfoLayout(Context context) {
        super(context);
    }

    public TipInfoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TipInfoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TipInfoLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    //初始化
    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.base_tip_info_layout, null, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.pb_loading);
        mTvTipState = (TextView) view.findViewById(R.id.tv_tip_state);
        mTvTipMsg = (TextView) view.findViewById(R.id.tv_tip_msg);
        mTipContainer = (LinearLayout) view.findViewById(R.id.ll_tip);
        setLoading();
        addView(view);
    }

    /**
     * 设置Loding 状态
     */
    public void setLoading() {
        this.setVisibility(VISIBLE);
        this.mProgressBar.setVisibility(View.VISIBLE);
        this.mTipContainer.setVisibility(View.GONE);
    }

    /**
     * 隐藏提示信息布局
     */
    public void setHiden() {
        this.setVisibility(View.GONE);
    }


    public void setLoadError(CharSequence errorTip) {
        CharSequence tip = netWorkError;
        if ( !TextUtils.isEmpty(errorTip) ) {
            tip = errorTip;
        }
        this.mProgressBar.setVisibility(View.GONE);
        this.mTipContainer.setVisibility(View.VISIBLE);
        this.mTvTipState.setText("图片");
        this.mTvTipMsg.setText(tip);
    }

    public void setLoadError(@StringRes int strId){
        CharSequence errorTip = getContext().getResources().getText(strId);
        setLoadError(errorTip);
    }


    public void setEmptyData(CharSequence emptyTip) {
        this.setVisibility(VISIBLE);
        CharSequence tip = empty;
        if ( !TextUtils.isEmpty(emptyTip))
            tip = emptyTip;
        this.mProgressBar.setVisibility(View.GONE);
        this.mTipContainer.setVisibility(View.VISIBLE);
        this.mTvTipState.setText("点击刷新");
        this.mTvTipMsg.setText(tip);
    }

    public void setEmptyData(@StringRes int strId) {
        CharSequence emptyTip = getContext().getResources().getText(strId);
        setEmptyData(emptyTip);
    }
}
