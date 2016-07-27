package com.tp.venus.module.common.model.imp;

import com.tp.venus.base.mvp.m.BaseModel;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.model.PageResult;
import com.tp.venus.module.common.api.CommonApi;
import com.tp.venus.module.common.bean.VideoInfo;
import com.tp.venus.module.common.model.ICommonModel;
import com.tp.venus.util.ApiUtil;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**<p>Class：com.tp.venus.module.common.model.imp.CommonModel</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/26/16:48
 * @version 1.0.0
 */
public class CommonModel extends BaseModel implements ICommonModel {
    @Override
    public void sendCode(String phone, ProgressSubscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile", phone);
        ApiUtil.getInstance().createApi(CommonApi.class).sendCode(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);

    }

    @Override
    public void sendEmail(String email, short type, ProgressSubscriber<JsonMessage> mProgressSubscriber) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("email", email);
        ApiUtil.getInstance().createApi(CommonApi.class).sendEmail(params)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);


    }

    @Override
    public void searchVideoInfo(ProgressSubscriber<JsonMessage<PageResult<VideoInfo>>> mProgressSubscriber) {
        ApiUtil.getInstance().createApi(CommonApi.class).searchVideoInfo()
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(mProgressSubscriber);
    }
}
