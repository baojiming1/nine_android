/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.youzan.adapter</p>
 * <p>File：YouzanImageStaticPagerAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/14/14:18.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.youzan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.module.common.adapter.ImageStaticPagerAdapter;
import com.tp.venus.module.youzan.ui.YouzanImageViewSeeActvity;

import java.util.ArrayList;

/**<p>Class：com.tp.venus.module.youzan.adapter.YouzanImageStaticPagerAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/14/14:18
 * @version 1.0.0
 */

public class YouzanImageStaticPagerAdapter extends ImageStaticPagerAdapter {

    private String alias;

    public YouzanImageStaticPagerAdapter(Context mContext, int width, int height, String alias) {
        super(mContext, width, height);
        this.alias = alias;
    }

    public YouzanImageStaticPagerAdapter(Fragment mFragment, int width, int height, String alias) {
        super(mFragment, width, height);
        this.alias = alias;
    }


    @Override
    public View getView(ViewGroup container, final int position) {
        View view = setImageView(container, position);
        RxViewListener.clicks(view, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                ArrayList<String> urls = toUrlList();
                Intent mIntent = IntentBuilder.create(mContext, YouzanImageViewSeeActvity.class).
                        putStringArrayList(YouzanImageViewSeeActvity.URLS_KEY, urls)
                        .putString(YouzanImageViewSeeActvity.ALIAS_KEY, alias)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putInt(YouzanImageViewSeeActvity.POSITION_KEY, position).build();
                mContext.startActivity(mIntent);
            }
        });
        return view;
    }
}
