/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.config</p>
 * <p>File：AppConfig.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/7/11:30.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.config;

/**<p>Class：com.tp.venus.config.AppConfig</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/7/11:30
 * @version 1.0.0
 */

public class AppConfig {

    /**
     * 本地数据库版本号
     */
    public static final int SCHEMA_VERSION = 1;

    /**
     * 获取视频的真图
     */
    public static final int VIDEO_NEEDLE = 2;
    /**
     * 网络请求超时
     */
    public static final int HTTP_TIMEOUT = 1000;

    public static final String LOG_TAG = "com.tp.nine";

    /**
     * 全局debug模式
     *
     * 开发的模式
     */
    public static final boolean IS_DEBUG = true;

    //服务器地址
    //public static final String SERVICE_HOST = "http://uat.api.qbt365.com/";           //uat
    public static final String SERVICE_HOST = "http://nine.api.qbt365.com/";               //生成
//    public static final String SERVICE_HOST = "http://192.168.5.101:8085/";          //测试

    public static final String JSON_TYPE = "application/json;charset=UTF-8";

    /**
     * 全局默认当前页
     */
    public static final int DEFAULT_PAGE_NOW = 1;

    /**
     * 全局默认每页记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 20;



}
