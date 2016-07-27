/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.model</p>
 * <p>File：LiveModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:12.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.qinjia.api.LiveApi;
import com.tp.venus.module.qinjia.entity.LiveProgram;
import com.tp.venus.util.ApiUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.qinjia.model.LiveModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/18:12
 * @version 1.0.0
 */

public class LiveModel {

    public void findLive(String programId, Subscriber<JsonMessage<LiveProgram>> mProgressSubscriber ){
        ApiUtil.getInstance().createApi(LiveApi.class).findLive(programId).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }



}
