/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base</p>
 * <p>File：BasePresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/13/18:40.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.mvp.p;

import android.content.Context;

import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.config.Status;
import com.tp.venus.module.user.bean.User;
import com.tp.venus.module.user.dao.UserDataManager;
import com.tp.venus.util.JPushUtil;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.base.mvp.p.BasePresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/13/18:40
 * @version 1.0.0
 */

public  class BasePresenter implements IBasePresenter{

    protected BaseView mBaseView;

    public BasePresenter(BaseView mBaseView){
        this.mBaseView = mBaseView;
    }



    /**
     * 用户当前的用户
     * @return
     */
    public User getCurrentUser(){
        String currentUserId = SharePreferencesUtils.getString(getCurrentContext(), Status.User.CURRENT_ID);
        if(StringUtil.isEmpty(currentUserId)){
            return null;
        }
        UserDataManager mUserDataManager = new UserDataManager(getCurrentContext());
        return mUserDataManager.selectByPrimaryKey(currentUserId);
    }

    public String getToken(){
        return SharePreferencesUtils.getString(getCurrentContext(), Status.TOKEN);
    }

    /**
     * 存储当前用户信息, 并注册极光推送
     * @param user
     * @return
     */
    public User setCurrentUser(User user){
        setCurrentUserNotRegister(user);
        JPushUtil.registerJpush(user);
        return user;
    }

    /**
     * 存储当前用户信息
     * @param user
     * @return
     */
    public User setCurrentUserNotRegister(User user){
        UserDataManager mUserDataManager = new UserDataManager(getCurrentContext());
        mUserDataManager.insert(user);
        SharePreferencesUtils.putString(getCurrentContext(), Status.User.CURRENT_ID, user.getId());
        SharePreferencesUtils.putString(getCurrentContext(), Status.TOKEN, user.getToken());
        return user;
    }

    /**
     * 清空当前用户
     */
    public void clearUser(){
        String currentUserId = SharePreferencesUtils.getString(getCurrentContext(), Status.User.CURRENT_ID);
        SharePreferencesUtils.putString(getCurrentContext(), Status.User.CURRENT_ID, "");
        SharePreferencesUtils.putString(getCurrentContext(), Status.TOKEN, "");
        if(StringUtil.isEmpty(currentUserId)){
            return ;
        }
        UserDataManager mUserDataManager = new UserDataManager(getCurrentContext());
        mUserDataManager.deleteByKey(currentUserId);
    }

    public void goLoginView(){
        mBaseView.goLoginView();
    }

    @Override
    public Context getCurrentContext() {
        return mBaseView.getCurrentContext();
    }


}
