/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.builder</p>
 * <p>File：DialogBuilder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/6/15:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.builder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

/**<p>Class：com.tp.venus.base.builder.DialogBuilder</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/6/15:15
 * @version 1.0.0
 */

public class DialogBuilder {

    private Context mContext;

    DialogBuilder(Context mContext){
        this.mContext = mContext;
    }

    public static DialogBuilder create(Context mContext){
        return new DialogBuilder(mContext);
    }

    /**
     * 创建一个简单的对话框
     * @param message
     * @param mOnClickListener
     */
    public void createSimpleDialog(String message, DialogInterface.OnClickListener mOnClickListener){
        new AlertDialog.Builder(mContext).setMessage(message)
                .setPositiveButton("确认", mOnClickListener)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public DialogPlus createDialogPlus(int viewResourceId, int gravity){
        return DialogPlus.newDialog(mContext).setContentHolder(new ViewHolder(viewResourceId))
                .setGravity(gravity)
                .create();
    }


}
