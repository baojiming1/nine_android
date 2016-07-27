/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.nine.util</p>
 * <p>File：JSONUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/9/14:01.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**<p>Class：com.tp.nine.util.JSONUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/9/14:01
 * @version 1.0.0
 */

public class JSONUtil {

    /**
     * obj --> string
     * @param obj
     * @return
     */
    public static String toJSONString(Object obj){
        return JSON.toJSONString(obj);
    }

    public static <T> T  parseObject(String obj, Class<T> clas){
        return JSON.parseObject(obj, clas);
    }

    public static Object parse(String str){
        return JSON.parse(str);
    }


    public static <T> List<T> parseArray(String array, final Class<T> clas){
        return JSON.parseArray(array, clas);
    }

}
