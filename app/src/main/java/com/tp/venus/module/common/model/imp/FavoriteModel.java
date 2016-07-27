/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.common.model.imp</p>
 * <p>File：FavoriteModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/11/10:00.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.model.imp;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.api.FavoriteApi;
import com.tp.venus.module.common.model.IFavoriteModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**<p>Class：com.tp.venus.module.common.model.imp.FavoriteModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/11/10:00
 * @version 1.0.0
 */

public class FavoriteModel extends BaseModel implements IFavoriteModel {


    @Override
    public void favorite(String foreignkey, boolean isFavorite, @Status.Favorite int favoriteType, Subscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("foreignkey", foreignkey);
        params.put("isFavorite", isFavorite);
        params.put("type", favoriteType);
        ApiUtil.getInstance().createApi(FavoriteApi.class).favorite(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
