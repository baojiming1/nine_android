/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.adapter</p>
 * <p>File：HotTagAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/2/16:44.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.content.bean.Tag;
import com.tp.venus.module.content.ui.TagActivity;
import com.tp.venus.util.GlideManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.qiniu.QiNiuUtil;

/**<p>Class：com.tp.venus.module.content.adapter.HotTagAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/2/16:44
 * @version 1.0.0
 */

public class HotTagAdapter extends CommonAdapter<Tag, CommonViewHolder> {

    private int width;
    private int height;

    public HotTagAdapter(Context mContext, @LayoutRes int resources) {
        super(mContext, resources);
        this.width = ResourcesUtil.dipToPx(mContext, 55f);
        this.height = ResourcesUtil.dipToPx(mContext, 55f);
    }

    @Override
    public void convert(CommonViewHolder mCommonViewHolder, final Tag item, int position) {
        ImageView imageView = mCommonViewHolder.findViewById(R.id.image);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        String img = QiNiuUtil.getImage(item.getBackgroud(), width, height);
        GlideManager.with(getContext()).loadImage(img).centerCrop().into(imageView);
        TextView tv = mCommonViewHolder.findViewById(R.id.tag_txt);
        tv.setText(StringUtil.splitString(item.getName(), 4));
        RxViewListener.clicks(mCommonViewHolder.itemView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                Intent mIntent = IntentBuilder.create(getContext(), TagActivity.class).putString(Status.Tag.ID, item.getId()).build();
                getContext().startActivity(mIntent);
            }
        });
    }
}
