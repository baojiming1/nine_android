/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.ui.receiver</p>
 * <p>File：MessageReceiver.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/19/18:14.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.home.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**<p>Class：com.tp.venus.module.home.ui.receiver.MessageReceiver</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/19/18:14
 * @version 1.0.0
 */

public class MessageReceiver extends BroadcastReceiver {

    private View hint;

    public MessageReceiver(View hint){
        this.hint = hint;
    }

    public static final String MESSAGE_RECEIVED_ACTION = "com.tp.venus.module.home.ui.receiver.MessageReceiver.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static final String IS_SHOW_BADGE = "isShowBadge";

    public static Boolean isShowHint = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
            //String messge = intent.getStringExtra(KEY_MESSAGE);
            //String extras = intent.getStringExtra(KEY_EXTRAS);
            boolean isShowBadge = intent.getBooleanExtra(IS_SHOW_BADGE, false);
         /*   StringBuilder showMsg = new StringBuilder();
            showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
            ToastUtil.getInstance().show(showMsg.toString());*/
            if( isShowBadge ){
                showHint();
                isShowHint = true;
            } else {
                hideHint();
                isShowHint = false;
            }

        }
    }

    /**
     * 显示提示
     */
    public void showHint(){
        if( hint.getVisibility() == View.GONE){
            hint.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏提示
     */
    public void hideHint(){
        if( hint.getVisibility() == View.VISIBLE){
            hint.setVisibility(View.GONE);
        }
    }



}
