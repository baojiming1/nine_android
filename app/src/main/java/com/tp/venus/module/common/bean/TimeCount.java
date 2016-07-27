/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.bean</p>
 * <p>File：TimeCount.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/26/16:48.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.bean;

import android.os.CountDownTimer;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

/**<p>Class：com.tp.venus.module.common.bean.TimeCount</p>
 * <p>Description：</p>
 * <pre>
 *      验证码倒计时
 * </pre>
 * @author 鲍建明
 * @date 2016/1/26/16:48
 * @version 1.0.0
 */
public class TimeCount extends CountDownTimer {

    private Button mButton;

    public TimeCount(Button mButton, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.mButton = mButton;
    }

    public TimeCount(Button mButton){
        this(mButton, TimeUnit.MINUTES.toMillis(2), TimeUnit.SECONDS.toMillis(1));
    }


    @Override
    public void onFinish() {//计时完毕时触发
        mButton.setText("重新获取");
        mButton.setClickable(true);
    }
    @Override
    public void onTick(long millisUntilFinished){//计时过程显示
        mButton.setClickable(false);
        mButton.setText(millisUntilFinished /1000+"秒");
    }

}
