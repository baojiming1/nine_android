/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.config</p>
 * <p>File：MyGlideModule.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/26/17:17.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.config;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp.OkHttpGlideModule;
import com.bumptech.glide.load.DecodeFormat;

/**<p>Class：com.tp.venus.config.MyGlideModule</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/26/17:17
 * @version 1.0.0
 */

public class MyGlideModule extends OkHttpGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        super.applyOptions(context, builder);
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }
}
