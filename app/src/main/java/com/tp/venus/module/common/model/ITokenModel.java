/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.model</p>
 * <p>File：ITokenModel.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/17/17:22.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.model;

import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.bean.Token;

/**<p>Class：com.tp.venus.module.common.model.ITokenModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/17/17:22
 * @version 1.0.0
 */

public interface ITokenModel {

    /**
     * 获取token
     */
    void getToken( ProgressSubscriber<JsonMessage<Token>> mProgressSubscriber);
}
