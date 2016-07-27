/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp</p>
 * <p>File：BaseListView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/15/17:19.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.v;

import com.tp.venus.model.PageResult;

/**<p>Class：com.tp.venus.base.mvp.v.BaseListView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/15/17:19
 * @version 1.0.0
 */

public interface BaseListView<T> extends BaseView {


    /**
     * 暂无数据
     */
    void dataNull();

    /**
     * 始终会执行的方法
     */
    void onCompleted();


    /**
     * 添加数据
     * @param pageResult
     */
    void addData(PageResult<T> pageResult);


    /**
     *  添加数据前操作
     */
    void onAddDataBefore();

    /**
     * 请求错误时
     */
    void onError();




}
