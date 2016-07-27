package com.tp.venus.module.common.api;

import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.bean.VideoInfo;

import java.util.Map;

import retrofit.http.Body;
import retrofit.http.POST;
import rx.Observable;

/**
 * Created by pc on 2016/1/26.
 */
public interface CommonApi {

    /**
     * 发送验证码
     * @param mobile
     * @return
     */
    @POST("/common/code/send")
    Observable<JsonMessage> sendCode(@Body Map<String, String> mobile);

    /**
     * 发送邮件
     * @param params
     * @return
     */
    @POST("/common/email/send")
    Observable<JsonMessage> sendEmail(@Body Map<String, Object> params);

    /**
     * 获取随机视频资源
     * @return
     */
    @POST("/common/daily/video/info")
    Observable<JsonMessage<PageResult<VideoInfo>>> searchVideoInfo();
}
