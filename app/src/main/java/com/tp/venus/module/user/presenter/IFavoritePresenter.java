/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：IFavoritePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/17:21.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.user.presenter.IFavoritePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/17:21
 * @version 1.0.0
 */

public interface IFavoritePresenter extends IBasePresenter {

    /**
     * 取消收藏
     * @param id
     * @param adapterPosition
     */
    @Login
    void cancelFavorite(String id, int adapterPosition);

}
