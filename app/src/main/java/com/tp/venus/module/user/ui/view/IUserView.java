/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.ui.view</p>
 * <p>File：UserView.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/19/11:12.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.ui.view;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.module.user.bean.User;

/**<p>Class：com.tp.venus.module.user.ui.view.UserView</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/19/11:12
 * @version 1.0.0
 */

public interface IUserView extends BaseView{

    /**
     * 用户信息显示
     * @param mUser
     */
    void showUserInfo(User mUser);

    /**
     * 关注/取消关注
     * @param isAttention
     */
    void attentionSuccess(boolean isAttention);


}
