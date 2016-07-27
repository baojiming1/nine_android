/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.view</p>
 * <p>File：ICommentView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/10:54.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.content.bean.Comment;

/**<p>Class：com.tp.venus.module.content.ui.view.ICommentView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/10:54
 * @version 1.0.0
 */

public interface ICommentView extends BaseView {

    /**
     * 显示一条评论信息
     * @param comment
     */
    void sendComment(Comment comment);



}
