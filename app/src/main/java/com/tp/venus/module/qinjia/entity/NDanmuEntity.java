/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.entity</p>
 * <p>File：NDanmuEntity.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/16:27.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.entity;

import com.google.gson.Gson;

/**<p>Class：com.tp.venus.module.qinjia.entity.NDanmuEntity</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/16:27
 * @version 1.0.0
 */

public class NDanmuEntity {

   // {"c":"0,16777215,1,25,196050,1364468342","m":"。。。。。。。。。。。。。。。。。。。。。。"}
    public String c ;
    public String m;

    public NDanmuEntity(){}
    public NDanmuEntity(String c, String m) {
        this.c = c;
        this.m = m;
    }

    public NDanmuEntity(String m) {
        this.m = m;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
