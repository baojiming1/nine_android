/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.view</p>
 * <p>File：ContentView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/13/17:28.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.content.bean.ContentResult;
import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.module.content.ui.view.impl.ContentView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/13/17:28
 * @version 1.0.0
 */

public interface IContentView  extends BaseView{


    /**
     * 展现帖子详情
     * @param item
     */
    void showContentInfo(ContentResult item, int postion);


    /**
     * 点赞
     */
    void praise(boolean isPraise, User user);

    /**
     * 收藏
     */
    void favorite(boolean isFavorite);

}
