/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.common.model</p>
 * <p>File：IFavoriteModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/11/9:59.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.model;

import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.common.model.IFavoriteModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/11/9:59
 * @version 1.0.0
 */

public interface IFavoriteModel  {

    /**
     * 收藏
     * @param foreignkey
     * @param isFavorite
     * @param favoriteType
     * @param mProgressSubscriber
     */
    void favorite(String foreignkey, boolean isFavorite, @Status.Favorite int favoriteType, Subscriber<JsonMessage> mProgressSubscriber);

}
