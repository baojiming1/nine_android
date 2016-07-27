/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.model.imp</p>
 * <p>File：TokenModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/17/17:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.model.imp;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.api.TokenApi;
import com.tp.venus.module.common.bean.Token;
import com.tp.venus.module.common.model.ITokenModel;
import com.tp.venus.util.ApiUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.common.model.imp.TokenModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/17/17:22
 * @version 1.0.0
 */

public class TokenModel extends BaseModel implements ITokenModel {
    @Override
    public void getToken(ProgressSubscriber<JsonMessage<Token>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(TokenApi.class).getToken().
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
