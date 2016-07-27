/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui</p>
 * <p>File：ContentActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/19/11:02.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui;

import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.module.content.ui.fragment.ContentInfoFragment;

import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.content.ui.ContentActivity</p>
 * <p>Description：</p>
 * <pre>
 *          帖子详情页
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/19/11:02
 */

public class ContentActivity extends BaseSendCommentActivity {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;

    @Override
    protected void setViewLayout() {
        setContentView(R.layout.content_activity_info);
    }

    @Override
    protected void initView() {
        getToolbarBuilder(mToolbar).setTitle(R.string.details).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_info_frameLayout, ContentInfoFragment.newInstance(contentId)).commit();
    }
}
