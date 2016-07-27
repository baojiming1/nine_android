/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui</p>
 * <p>File：CommentActivity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/19/9:41.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui;

import android.support.v7.widget.Toolbar;

import com.tp.venus.R;
import com.tp.venus.module.content.ui.fragment.CommentFragment;

import butterknife.InjectView;

/**
 * <p>Class：com.tp.venus.module.content.ui.CommentActivity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 1.0.0
 * @date 2015/10/19/9:41
 */

public class CommentActivity extends BaseSendCommentActivity {


    @InjectView(R.id.mToolbar)
    Toolbar mToolbar;


    @Override
    protected void setViewLayout() {
        setContentView(R.layout.comment_activity);
    }

    @Override
    protected void initView() {
        getToolbarBuilder(mToolbar).setTitle(R.string.comment).build();
        getSupportFragmentManager().beginTransaction().replace(R.id.comment_frameLayout, CommentFragment.newInstance(contentId)).commit();
    }

}
