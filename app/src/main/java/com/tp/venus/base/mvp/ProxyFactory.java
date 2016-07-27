/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.mvp</p>
 * <p>File：ProxyFactory.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/14:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp;

import com.tp.venus.base.annotation.PresenterInvocationHandler;

/**<p>Class：com.tp.venus.base.mvp.ProxyFactory</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/14:23
 * @version 1.0.0
 */

public class ProxyFactory {

    /**
     * 获取被代理的接口对象
     * @param obj
     * @param <T>
     * @return
     */
    public static  <T> T getProxy(Object obj){
        PresenterInvocationHandler mPresenterInvocationHandler = new PresenterInvocationHandler(obj);
        return (T) mPresenterInvocationHandler.getProxy();
    }

}
