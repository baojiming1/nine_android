/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.widget.loading</p>
 * <p>File：LoadingProgress.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/21/19:02.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget.loading;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import com.tp.venus.R;

/**<p>Class：com.tp.venus.widget.loading.LoadingProgress</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/21/19:02
 * @version 1.0.0
 */

public class LoadingProgress extends AlertDialog {
    protected LoadingProgress(Context context) {
        super(context);
    }

    protected LoadingProgress(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected LoadingProgress(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_loading);
    }
}
