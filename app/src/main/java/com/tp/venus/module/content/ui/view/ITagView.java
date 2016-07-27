/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.view</p>
 * <p>File：ITagView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/13:01.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.content.bean.TagResult;

/**<p>Class：com.tp.venus.module.content.ui.view.ITagView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/13:01
 * @version 1.0.0
 */

public interface ITagView extends BaseView {

    /**
     * 展示详情信息
     * @param tag
     */
    void showTagInfo(TagResult tag);


}
