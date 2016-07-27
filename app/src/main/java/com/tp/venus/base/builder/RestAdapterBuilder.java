/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.builder</p>
 * <p>File：RestAdapterBuilder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/8/10:41.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.builder;

/**<p>Class：com.tp.venus.base.builder.RestAdapterBuilder</p>
 * <p>Description：RestAdapter统一构建类</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/8/10:41
 * @version 1.0.0
 */
public class RestAdapterBuilder {

   /* private RestAdapter mRestAdapter;*/

    private RestAdapterBuilder(){
      /*  mRestAdapter =   new RestAdapter.Builder().setEndpoint(AppConfig.SERVICE_HOST).build();*/
    }

    /*public static RestAdapterBuilder create(){
        return new RestAdapterBuilder();
    }*/

    /**
     * 构建服务
     * @param service
     * @param <T>
     * @return
     */
   /* public <T> T build(Class<T> service){
        return mRestAdapter.create(service);
    }*/

}
