/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter</p>
 * <p>File：IUserContentPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/14:58.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;

/**<p>Class：com.tp.venus.module.content.presenter.IUserContentPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/14:58
 * @version 1.0.0
 */

public interface IContentItemPresenter extends IBasePresenter {


    /**
     * 删除帖子
     * @param contentId
     */
    @Login
    void deleteContent(String contentId, int index);

}
