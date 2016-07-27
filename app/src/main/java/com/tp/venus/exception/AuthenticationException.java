/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.exception</p>
 * <p>File：AuthenticationException.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/25/18:06.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.exception;

/**<p>Class：com.tp.venus.exception.AuthenticationException</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/25/18:06
 * @version 1.0.0
 */

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message){
        super(message);
    }

}
