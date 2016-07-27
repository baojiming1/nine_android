/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui.view</p>
 * <p>File：IUserInfoView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/28/18:57.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.view;

import android.support.annotation.IntDef;

import com.tp.venus.base.mvp.v.BaseView;

/**<p>Class：com.tp.venus.module.user.ui.view.IUserInfoView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/28/18:57
 * @version 1.0.0
 */

public interface IUserInfoView extends BaseView {


    void onSuccess(String data, @UserInfoType int type);

    void onError(String message);

    @IntDef({UserInfoType.ICON, UserInfoType.NICKNAME, UserInfoType.SEX, UserInfoType.ADDRESS})
    @interface UserInfoType{
        int ICON = 1;
        int NICKNAME = 2;
        int SEX = 3;
        int ADDRESS = 4;
    }
}
