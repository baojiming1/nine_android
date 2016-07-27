/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：QiNiuUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/22/17:01.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util.qiniu;

import com.tp.venus.config.AppConfig;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.util.qiniu.QiNiuUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/22/17:01
 * @version 1.0.0
 */

public class QiNiuUtil {

    private static final float FORMAT_POINT = 0.6f;
    private static final String FORMAT = "?vframe/jpg";

    private static final String IMAGE_FORMAT = "?imageView2/0/w/%d/h/%d";

    public static String getImageCompress(String url, int w, int h){
        return  url + String.format(IMAGE_FORMAT, formatPoint(w), formatPoint(h));
    }

    public static String getImage(String url, int w, int h){
        return  url + String.format(IMAGE_FORMAT, w, h);
    }

    /**
     *  默认缩放比率
     * @param length
     * @return
     */
    private static int formatPoint(int length){
        return (int) (length * FORMAT_POINT);
    }


    /**
     * 获取视频封面图， 默认第5针
     * @param video
     * @return
     */
    public static   String getVideoImage(String video){
        return getVideoImage(video, 0, 0);
    }

    /**
     * 获取视频封面图， 默认第5针
     * @param video
     * @param rotate  旋转的度数
     * @return
     */
    public static  String getVideoImage(String video, int rotate){
        return getVideoImage(video, 0, 0, 0, rotate);
    }


    /**
     * 获取视频封面图 ，默认第5针
     * @param video
     * @param w  宽
     * @param h  高
     * @return
     */
    public static  String getVideoImage(String video, int w, int h){
        return getVideoImage(video, w, h, 0, 0);
    }

    /**
     * 获取视频封面图
     * @param video
     * @param w  宽
     * @param h   高
     * @param second  指定截取视频的时刻，单位：秒，精确到毫秒。
     * @return
     */
    public static  String getVideoImage(String video, int w, int h, int second, int rotate){
        if( StringUtil.isEmpty(video)){
            return "";
        }
        StringBuffer sb = new StringBuffer(video);
        sb.append(FORMAT);
        if( !StringUtil.isHttp(video) ){
            return "";
        }
        if( second != 0){
            sb.append("/offset/" + second);
        } else{
            sb.append("/offset/" + AppConfig.VIDEO_NEEDLE);
        }
        if( w != 0 && h == 0 ){
            sb.append("/w" + w + "/h" + h);
        }
        if( rotate != 0){
            sb.append("/rotate/" + rotate);
        }
        return sb.toString();
    }

}
