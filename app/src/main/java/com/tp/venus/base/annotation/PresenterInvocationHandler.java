/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.annotation</p>
 * <p>File：LoginInvocationHandler.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/10:09.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.annotation;

import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.config.Status;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StringUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**<p>Class：com.tp.venus.base.annotation.LoginInvocationHandler</p>
 * <p>Description：</p>
 * <pre>
 *      用户判断登陆的动态代理
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/10:09
 * @version 1.0.0
 */
public class PresenterInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    /**
     * 构造方法
     * @param target 目标对象
     */
    public PresenterInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      //  Logger.i("动态代理开始..............................");
        IBasePresenter mIBasePresenter = (IBasePresenter) proxy;
        if( isLogin(method) ){
          //  Logger.i("动态代理需要登陆..............................");
            ProxyBean.put(proxy, method, args);
            mIBasePresenter.goLoginView();
            return null;
        }
        Object result = method.invoke(target, args);
      //  Logger.i("动态代理结束..............................");
        return result;
    }



    /**
     * 判断是否已经登陆
     * @param method
     * @return
     */
    private boolean isLogin(Method method){
        Annotation mAnnotation =  method.getAnnotation(Login.class);
        if( mAnnotation == null ){
            return false;
        }
        String token = SharePreferencesUtils.getString(ApplicationHandler.getApp().getApplicationContext(), Status.TOKEN);
        if(StringUtil.isNotEmpty(token)){
            return false;
        }
        return true;
    }


    /**
     * 获取目标对象的代理对象
     * @return 代理对象
     */
    public Object getProxy() {
        /*return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);*/
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

}
