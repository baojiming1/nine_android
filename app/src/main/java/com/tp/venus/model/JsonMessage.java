/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.model</p>
 * <p>File：JsonMessage.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/7/14:36.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.model;

/**<p>Class：com.tp.venus.model.JsonMessage</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/7/14:36
 * @version 1.0.0
 */

public final class JsonMessage<T> {

    private Integer code;
    private String message;
    private T obj;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
