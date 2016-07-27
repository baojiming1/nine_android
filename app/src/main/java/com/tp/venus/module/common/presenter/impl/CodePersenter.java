/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.common.presenter.impl</p>
 * <p>File：CodePersenter.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/2/18/16:39.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.common.presenter.impl;

import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.model.ICommonModel;
import com.tp.venus.module.common.model.imp.CommonModel;
import com.tp.venus.module.common.presenter.ICodePersenter;
import com.tp.venus.module.common.ui.view.ICodeView;
import com.tp.venus.util.StringUtil;
import com.tp.venus.util.ValidateUtils;

/**<p>Class：com.tp.venus.module.common.presenter.impl.CodePersenter</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/2/18/16:39
 * @version 1.0.0
 */

public class CodePersenter extends BasePresenter implements ICodePersenter {

    private ICodeView mICodeView;
    private ICommonModel mICommonModel;

    public CodePersenter(ICodeView mICodeView) {
        super(mICodeView);
        this.mICodeView = mICodeView;
        this.mICommonModel = new CommonModel();
    }

    @Override
    public void sendCode(String phone) {
        if( !validPhone(phone) ){
            return ;
        }
        mICommonModel.sendCode(phone, new ProgressSubscriber<JsonMessage>(mICodeView) {
            @Override
            public void onSuccess(JsonMessage message) {
                mICodeView.sendCodeSuccess();
            }
        });
    }

    //验证码手机号
    private boolean validPhone(String phone){
        if( StringUtil.isEmpty(phone)){
            mICodeView.showError("手机号码不能为空");
            return false;
        }
        if ( !ValidateUtils.isMobile(phone) ){
            mICodeView.showError("手机号码格式不正确");
            return false;
        }
        return true;
    }
}
