/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.user.presenter.impl</p>
 * <p>File：UpdatePwdPresenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/27/12:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.user.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.RxSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.ui.view.ICommonView;
import com.tp.venus.module.user.model.IUserModel;
import com.tp.venus.module.user.model.impl.UserModel;
import com.tp.venus.module.user.presenter.IUpdatePwdPresenter;
import com.tp.venus.util.StringUtil;

/**<p>Class：com.tp.venus.module.user.presenter.impl.UpdatePwdPresenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/27/12:38
 * @version 1.0.0
 */

public class UpdatePwdPresenter extends BasePresenter implements IUpdatePwdPresenter {

    private ICommonView mICommonView;
    private IUserModel iUserModel;

    public UpdatePwdPresenter(ICommonView mICommonView) {
        super(mICommonView);
        this.mICommonView = mICommonView;
        this.iUserModel = new UserModel();
    }

    @Override
    public void updatePwd(String oldPwd, String newPwd, String againPwd) {
        if( !validPwd(oldPwd, newPwd, againPwd)){
            return ;
        }
        iUserModel.updatePwd(oldPwd, newPwd, new RxSubscriber<JsonMessage>(mICommonView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mICommonView.onSuccess();
            }
        });
    }


    //验证密码
    private boolean validPwd(String oldPwd, String pwd, String aginPwd){
        if(StringUtil.isEmpty(oldPwd)){
            mICommonView.showError("旧密码不能为空");
            return false;
        }
        if( StringUtil.isEmpty(pwd)){
            mICommonView.showError("新密码不能为空");
            return false;
        }
        if(StringUtil.isEmpty(aginPwd)){
            mICommonView.showError("确认密码不能为空");
            return false;
        }
        if( !pwd.equals(aginPwd) ){
            mICommonView.showError("输入的二次密码不一致");
            return false;
        }
        return true;
    }
}
