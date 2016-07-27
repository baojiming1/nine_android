/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：ReflectUtils.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/9/18:23.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**<p>Class：com.tp.venus.util.ReflectUtils</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/9/18:23
 * @version 1.0.0
 */

public class ReflectUtils {

    /**
     * 获得超类的参数类型，取第一个参数类型
     * @param <T> 类型参数
     * @param clazz 超类类型
     */
    @SuppressWarnings("rawtypes")
    public static <T> Class<T> getClassGenricType(final Class clazz) {
        return getClassGenricType(clazz, 0);
    }

    /**
     * 根据索引获得超类的参数类型
     * @param clazz 超类类型
     * @param index 索引
     */
    @SuppressWarnings("rawtypes")
    public static Class getClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

}
