/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.ui.callback</p>
 * <p>File：MyTagAliasCallback.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/20/11:11.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.home.callback;

import com.orhanobut.logger.Logger;

import java.util.Set;

import cn.jpush.android.api.TagAliasCallback;

/**<p>Class：com.tp.venus.module.home.ui.callback.MyTagAliasCallback</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/20/11:11
 * @version 1.0.0
 */

public class MyTagAliasCallback implements TagAliasCallback {
    @Override
    public void gotResult(int i, String s, Set<String> set) {
        if( i != 0){
            Logger.e("极光推送设置别名失败:-->状态：" + i + ", String:" + s + ", set:" + set);
        }
    }
}
