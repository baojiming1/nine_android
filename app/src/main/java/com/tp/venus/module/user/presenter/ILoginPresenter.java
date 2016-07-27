/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter</p>
 * <p>File：ILoginPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/21/18:23.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter;

import com.tp.venus.base.mvp.p.IBasePresenter;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**<p>Class：com.tp.venus.module.user.presenter.ILoginPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/21/18:23
 * @version 1.0.0
 */

public interface ILoginPresenter extends IBasePresenter {

    /**
     * 登陆
     */
    void login(String account, String pwd );

    /**
     * 快捷登录
     * @param account
     * @param code
     */
    void fastLogin(String account, String code);

    /**
     * 第三方登录
     * @param map
     * @param source
     */
    void login4other(Map<String, String> map, SHARE_MEDIA source);
}
