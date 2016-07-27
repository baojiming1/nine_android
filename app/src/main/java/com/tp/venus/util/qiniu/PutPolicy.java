package com.tp.venus.util.qiniu;

import org.json.JSONObject;

import java.util.UUID;


/**
 * 生成上传策略PutPolicy
 *
 * @see <url>http://developer.qiniu.com/docs/v6/api/reference/security/put-policy.html</url>
 * Created by xu on 2015-12-15.
 */
class PutPolicy {
    static String newPolicy(String bucket, String uniqueFileName) {
        try {
            JSONObject j = new JSONObject()
                    .put("scope", String.format("%s:%s", bucket, uniqueFileName))
                    .put("deadline", System.currentTimeMillis() + 3600 * 2 * 1000)    // 3天有效
//					.put("insertOnly", 1)
                    ;
            return j.toString();
        } catch (Exception x) {
            return "";
        }
    }

    /**
     * 创建唯一文件名
     *
     * @param ext
     * @return
     */
    static String createUniqueName(String ext) {
        // 随机生成唯一key
        return String.format("%d-%s.%s",
                System.currentTimeMillis(),
                UUID.randomUUID().toString().substring(0, 13),
                ext);
    }

    static String videoUrl(String path, String uniqueFileName) {
        return path + "/" + uniqueFileName;
    }

    static String pictureUrl(String path, String uniqueFileName) {
        return path + "/" + uniqueFileName;
    }

    static String videoUrl(String uniqueFileName) {
        return String.format("http://%s/%s", Definitions.VideoDomain, uniqueFileName);
    }

    static String pictureUrl(String uniqueFileName) {
        return String.format("http://%s/%s", Definitions.PictureDomain, uniqueFileName);
    }

    static String newJpgPicture(String uniqueFileName) {
        return newPolicy(Definitions.PictureScope, uniqueFileName);
    }

    static String newVideo(String uniqueFileName) {
        return newPolicy(Definitions.VideoScope, uniqueFileName);
    }

}