package com.tp.venus.util.qiniu;

/**
 * 上传参数定义
 * Created by xu on 2015-12-15.
 */
class Definitions
{
    /**
     * 七牛图片保存空间bucket
     */
    static final String PictureScope = "--";    // Qbit Android client generated Pictures
    static final String PictureDefaultExt = "jpg";    // 没有扩展名时，默认jpg
    /**
     * 七牛视频保存空间bucket
     */
    static final String VideoScope = "--";    //"qbitagv";	// Android client generated Videos
    static final String VideoDefaultExt = "mp4";    // 没有扩展名是，默认mp4
    /**
     *
     */
    static final String PictureDomain = "picture-domain";
    /**
     *
     */
    static final String VideoDomain = "video-domain";

    /**
     * 七牛SK
     */
    static final byte[] SecretKey = "--".getBytes();
    /**
     * 七牛AK
     */
    static final String AccessKey = "--";
}