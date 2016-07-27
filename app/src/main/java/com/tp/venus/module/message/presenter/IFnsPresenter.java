/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.message.presenter</p>
 * <p>File：IFnsPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/17:04.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.message.presenter;

import com.tp.venus.base.annotation.Login;

/**<p>Class：com.tp.venus.module.message.presenter.IFnsPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/17:04
 * @version 1.0.0
 */

public interface IFnsPresenter extends IMessagePresenter {

    /**
     * 用户关注/取消关注
     * @param userId
     * @param isAttention
     */
    @Login
    void attention(String userId, boolean isAttention);


}
