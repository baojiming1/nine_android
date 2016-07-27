/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.util</p>
 * <p>File：UserUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/15:59.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.tp.venus.module.user.bean.User;
import com.tp.venus.util.GlideManager;

/**<p>Class：com.tp.venus.module.user.util.UserUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/15:59
 * @version 1.0.0
 */

public class UserUtil {

  /*  *//**
     * 显示用户的头像以及等级
     * @param mContext
     * @param rootView
     * @param user
     *//*
    public static void showLevelIcon(Context mContext, View rootView, User user){
        CircleImageView mCircleImageView = ViewHolder.findById(rootView, R.id.mCircleImageView);
        TextView level = ViewHolder.findById(rootView, R.id.level_txt);
        showLevelIcon(mContext, mCircleImageView, level, user);
    }*/

    /**
     * 显示用户的头像以及等级
     * @param mContext
     * @param icon
     * @param level
     * @param user
     */
    public static void showLevelIcon(Context mContext, ImageView icon, TextView level, User user){
        GlideManager.with(mContext).loadImage4user(icon, user.getIcon());
        level.setText(user.getLevel() + "");
    }

    /**
     * 性别
     0：保密
     1：女
     2：男
     * @param gender
     * @return
     */
    public static String getGender(short gender){
        String result = "保密";
        switch (gender){
            case 1:
                result = "女";
                break;
            case 2 :
                result = "男";
                break;
            default:
                break;
        }
        return result;
    }

}
