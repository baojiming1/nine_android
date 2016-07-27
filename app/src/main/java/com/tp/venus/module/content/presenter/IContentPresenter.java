/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter</p>
 * <p>File：IContentPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/10:11.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.content.presenter.IContentPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/10:11
 * @version 1.0.0
 */

public interface IContentPresenter extends IBasePresenter {


    /**
     * 点赞
     * @param contentId
     * @param isPraise
     */
    @Login
    void praise( String contentId,  boolean isPraise);

    /**
     * 帖子详情
     * @param contentId
     */
    void contentDetails(String contentId);


    /**
     * 关注
     * @param contentId
     * @param isFavorite
     */
    @Login
    void favorite(String contentId, boolean isFavorite);



}
