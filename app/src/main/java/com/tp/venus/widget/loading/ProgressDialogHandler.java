/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.base.rx</p>
 * <p>File：ProgressDialogHandler.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/21/17:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget.loading;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.OnCancelListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.tp.venus.R;

/**<p>Class：com.tp.venus.widget.loading.ProgressDialogHandler</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/21/17:40
 * @version 1.0.0
 */

public class ProgressDialogHandler extends Handler {

    private DialogPlus mDialogPlus;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }

    public void initProgressDialog(){
        if (mDialogPlus == null) {
            DialogPlusBuilder mDialogPlusBuilder = DialogPlus.newDialog(context).setContentHolder(new ViewHolder(R.layout.common_loading))
                    .setGravity(Gravity.CENTER)
                    .setContentWidth(ViewGroup.LayoutParams.WRAP_CONTENT)  // or any custom width ie: 300
                    .setContentHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setContentBackgroundResource(android.R.color.transparent)
                    .setCancelable(cancelable);
            if( cancelable ){
                mDialogPlusBuilder.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogPlus dialog) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }
            mDialogPlus = mDialogPlusBuilder.create();
            if (!mDialogPlus.isShowing()) {
                mDialogPlus.show();
            }
        }
    }

    public void dismissProgressDialog(){
        if (mDialogPlus != null) {
            mDialogPlus.dismiss();
            mDialogPlus = null;
        }
    }

}