/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.view</p>
 * <p>File：IUserContentView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/14:53.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.view;

import com.tp.venus.base.mvp.v.BaseView;

/**<p>Class：com.tp.venus.module.content.ui.view.IUserContentView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/14:53
 * @version 1.0.0
 */

public interface IContentItemView extends BaseView{

    /**
     * delete content
     * @param deleteIndex
     */
    void deleteContent(int deleteIndex);


}
