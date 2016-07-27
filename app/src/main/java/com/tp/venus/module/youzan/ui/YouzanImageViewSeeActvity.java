/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.youzan.ui</p>
 * <p>File：YouzanImageViewSeeActvity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/14/13:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.common.ui.ImageViewSeeActvity;
import com.tp.venus.module.youzan.ui.fragment.YouzanFragment;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.youzan.ui.YouzanImageViewSeeActvity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/14/13:40
 * @version 1.0.0
 */

public class YouzanImageViewSeeActvity extends ImageViewSeeActvity  {


    public static final String ALIAS_KEY = "alias";

    private String alias ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alias = getIntent().getStringExtra(ALIAS_KEY);
        if(StringUtil.isEmpty(alias)){
            return ;
        }
        details.setVisibility(View.VISIBLE);
        final String url = Status.YouZan.DETAIL + alias;
        RxViewListener.clicks(details, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = getIntentBuilder(YouzanWebViewActivity.class).putString(YouzanFragment.URL_KEY, url).build();
                startActivity(mIntent);
            }
        });
    }



}
