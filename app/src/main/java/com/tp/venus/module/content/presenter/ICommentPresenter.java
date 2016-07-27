/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.presenter</p>
 * <p>File：ICommentPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/14:29.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.presenter;

import com.tp.venus.base.annotation.Login;
import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.module.content.bean.Comment;

/**<p>Class：com.tp.venus.module.content.presenter.ICommentPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/14:29
 * @version 1.0.0
 */

public interface ICommentPresenter extends IBasePresenter {

    /**
     * 保存评论
     * @param contentId 帖子ID
     * @param message  信息
     * @param toUserId 接收者ID
     * @param parent 帖子的父级ID
     */
    @Login
    void save(String contentId, String message, String toUserId, Comment parent);
}
