package com.tp.venus.module.common.model;

import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.bean.VideoInfo;

/**
 * Created by pc on 2016/1/26.
 */
public interface ICommonModel  {

    /**
     * 发送验证码
     * @param phone
     * @param mProgressSubscriber
     */
    void sendCode(String phone, ProgressSubscriber<JsonMessage> mProgressSubscriber);

    /**
     * 发送邮件
     * @param email
     * @param type
     */
    void sendEmail(String email, short type, ProgressSubscriber<JsonMessage> mProgressSubscriber);

    /**
     * 查询默认的视频播放
     * @param mProgressSubscriber
     */
    void searchVideoInfo(ProgressSubscriber<JsonMessage<PageResult<VideoInfo>>> mProgressSubscriber);


}
