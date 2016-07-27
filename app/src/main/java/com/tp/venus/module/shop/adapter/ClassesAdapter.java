/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.adapter</p>
 * <p>File：ClassesAdapter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/8/14:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.widget.ImageView;

import com.tp.venus.R;
import com.tp.venus.base.adapter.CommonAdapter;
import com.tp.venus.base.adapter.CommonViewHolder;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.rx.RxViewListener;
import com.tp.venus.config.Status;
import com.tp.venus.module.shop.bean.Classes;
import com.tp.venus.module.shop.ui.ClassesProductActivity;
import com.tp.venus.module.shop.ui.SelectClassesActivity;
import com.tp.venus.util.GlideManager;

import java.util.Collection;

/**<p>Class：com.tp.venus.module.shop.adapter.ClassesAdapter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/8/14:38
 * @version 1.0.0
 */

public class ClassesAdapter extends CommonAdapter<Classes, CommonViewHolder> {

    private int height;

    public ClassesAdapter(Context mContext, @LayoutRes int resources, int height) {
        super(mContext, resources);
        this.height = height;
    }


    @Override
    public void convert(CommonViewHolder mCommonViewHolder, final Classes item, int position) {
        ImageView imageView = mCommonViewHolder.findViewById(R.id.image);
        imageView.getLayoutParams().height = height;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //  url = QiNiuUtil.getImage(url, width, height);
        GlideManager.with(getContext()).loadImageCus(imageView, item.getImageUrl() );
        RxViewListener.clicks(imageView, new RxViewListener.Action() {
            @Override
            public void call(Object o) {
                if ( item.getListType() == 1 ){             //1级分类
                    Intent mIntent = IntentBuilder.create(getContext(), SelectClassesActivity.class)
                            .putParcelable(Status.PARCELABLE_KEY, item)
                            .build();
                    getContext().startActivity(mIntent);
                } else {                                    //2 产品
                    Intent mIntent = IntentBuilder.create(getContext(), ClassesProductActivity.class)
                            .putParcelable(Status.PARCELABLE_KEY, item)
                            .build();
                    getContext().startActivity(mIntent);
                }
            }
        });

    }

    @Override
    public void addAll(Collection<? extends Classes> collection) {
        getDatas().clear();
        super.addAll(collection);
    }
}
