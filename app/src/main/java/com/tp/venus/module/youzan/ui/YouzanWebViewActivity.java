/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.youzan.ui</p>
 * <p>File：YouzanWebViewActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/19/11:02.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tp.venus.R;
import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.module.youzan.ui.fragment.YouzanFragment;

/**<p>Class：com.tp.venus.module.youzan.ui.YouzanWebViewActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/19/11:02
 * @version 1.0.0
 */

public class YouzanWebViewActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_fragemnt_layout);
        String url = getIntent().getStringExtra(YouzanFragment.URL_KEY);
        getSupportFragmentManager().beginTransaction().replace(R.id.mFrameLayout, YouzanFragment.newInstance(url, false)).commit();
    }

}
