/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：JPushUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/20/11:14.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.module.home.callback.MyTagAliasCallback;
import com.tp.venus.module.user.bean.User;

import cn.jpush.android.api.JPushInterface;

/**<p>Class：com.tp.venus.util.JPushUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/20/11:14
 * @version 1.0.0
 */

public class JPushUtil {

    /**
     * 注册极光推送
     * @param user  为空时，移除注册
     */
    public static void registerJpush(User user){
        String userId = "";
        if( user != null){
            if( StringUtil.isNotEmpty(user.getId())){
                userId = user.getId();
            } else if(StringUtil.isNotEmpty(user.getUserId())) {
                userId = user.getUserId();
            }
        }
        JPushInterface.setAlias(ApplicationHandler.getApp().getApplicationContext(), userId, new MyTagAliasCallback());
    }

}
