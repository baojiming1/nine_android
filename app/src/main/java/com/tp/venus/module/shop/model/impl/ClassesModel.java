/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model.impl</p>
 * <p>File：ClassesModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/16:20.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model.impl;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.shop.api.ClassesApi;
import com.tp.venus.module.shop.bean.Classes;
import com.tp.venus.module.shop.model.IClassesModel;
import com.tp.venus.util.ApiUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.shop.model.impl.ClassesModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/16:20
 * @version 1.0.0
 */

public class ClassesModel extends BaseModel implements IClassesModel{
    @Override
    public void getHomeClasses(Subscriber<JsonMessage<PageResult<Classes>>> mSubscriber) {
        ApiUtil.getInstance().createApi(ClassesApi.class).getHomeClasses().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mSubscriber);
    }
}
