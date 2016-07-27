/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.model</p>
 * <p>File：IClassesModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/16:20.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.model;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.shop.bean.Classes;

import rx.Subscriber;

/**<p>Class：com.tp.venus.module.shop.model.IClassesModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/16:20
 * @version 1.0.0
 */

public interface IClassesModel {

    /**
     * 首页商品分类列表接口
     * @param mSubscriber
     */
    void getHomeClasses( Subscriber<JsonMessage<PageResult<Classes>>> mSubscriber);

}
