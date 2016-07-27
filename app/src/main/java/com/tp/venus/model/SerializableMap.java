/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.bean</p>
 * <p>File：SerializableMap.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/15:34.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.model;

import java.io.Serializable;
import java.util.Map;

/**<p>Class：com.tp.venus.model.SerializableMap</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/18/15:34
 * @version 1.0.0
 */

public class SerializableMap implements Serializable {


    private Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


}
