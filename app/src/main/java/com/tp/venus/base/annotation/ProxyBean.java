/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.model</p>
 * <p>File：ProxyBean.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/3/16:19.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.annotation;

import com.orhanobut.logger.Logger;
import com.tp.venus.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**<p>Class：com.tp.venus.base.bean.ProxyBean</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/3/16:19
 * @version 1.0.0
 */

public  class ProxyBean  {
    //public static final String KEY = "mProxyBean";
    public static final Map<String, ProxyBean> PROXY = new HashMap<>();
    public Object proxy;
    public Method method;
    public Object[] args;

    private ProxyBean(){}

    public static void put(Object proxy, Method method, Object[] args){
        ProxyBean mProxyBean = new ProxyBean();
        mProxyBean.proxy = proxy;
        mProxyBean.method = method;
        mProxyBean.args = args;
        PROXY.put(method.getName(), mProxyBean);
    }

    public static void clear(){
        PROXY.clear();
    }

    public static Map<String, ProxyBean> getProxyBean(){
        return PROXY;
    }

    /**
     * 是否有任务
     * @return
     */
    public static boolean isNotNullTask(){
        return CollectionUtils.isMapNotEmpty(PROXY);
    }


    /**
     * 执行
     */
    public static void execute( ProxyBean mProxyBean){
        try {
            mProxyBean.method.invoke(mProxyBean.proxy, mProxyBean.args);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("执行代理失败", e);
        }
    }

    /**
     * 执行所有的任务
     */
    public static void execute(){
        if(CollectionUtils.isMapNotEmpty(PROXY)){
            for (Map.Entry<String, ProxyBean> entry : PROXY.entrySet()){
                ProxyBean mProxyBean = entry.getValue();
                execute(mProxyBean);
            }
            clear();
        }
    }


}
