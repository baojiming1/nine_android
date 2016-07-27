/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.content.ui.view</p>
 * <p>File：IHotTagView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/2/16:27.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.content.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.content.bean.Tag;

import java.util.List;

/**<p>Class：com.tp.venus.module.content.ui.view.IHotTagView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/2/16:27
 * @version 1.0.0
 */

public interface IHotTagView extends BaseView {

    /**
     * 显示热门标签
     * @param datas
     */
    void showHotTag(List<Tag> datas);

}
