/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.rx</p>
 * <p>File：RxFunc2.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/15:08.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.rx;

import com.tp.venus.config.Constant;
import com.tp.venus.model.JsonMessage;

import rx.functions.Func2;

/**<p>Class：com.tp.venus.base.rx.RxFunc2</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/15:08
 * @version 1.0.0
 */

public abstract class RxFunc2 implements Func2<JsonMessage, JsonMessage, JsonMessage> {
    @Override
    public JsonMessage call(JsonMessage jsonMessage, JsonMessage jsonMessage2) {
        if(jsonMessage.getCode().intValue() != Constant.SUCCES_CODE){
            return jsonMessage;
        }
        if(jsonMessage2.getCode().intValue() != Constant.SUCCES_CODE){
            return jsonMessage2;
        }
        return doCall(jsonMessage, jsonMessage2);
    }

    public abstract JsonMessage doCall(JsonMessage jsonMessage, JsonMessage jsonMessage2);

}
