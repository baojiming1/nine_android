/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：UmengUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/1/16:48.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import android.content.Context;

import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UpdateStatus;

import java.util.List;
import java.util.Map;

/**<p>Class：com.tp.venus.util.UmengUtil</p>
 * <p>Description：</p>
 * <pre>
 *      友盟相关的工具类
 * </pre>
 * @author 鲍建明
 * @date 2016/3/1/16:48
 * @version 1.0.0
 */

public class UmengUtil {

    public static final String UPDATE_KEY = "upgrade_mode";
    public static final String F = "F";             //F:强制更新  S:不强制

    public static final String S = "S";

    /**
     *  更新APK
     */
    public static void updateApk(final Context mContext){
        //获取友盟在线参数
        OnlineConfigAgent mOnlineConfigAgent = OnlineConfigAgent.getInstance();
        mOnlineConfigAgent.updateOnlineConfig(mContext);
        String value = mOnlineConfigAgent.getConfigParams(mContext, UPDATE_KEY);
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        UmengUpdateAgent.update(mContext);
        if( !isForce(value, mContext) ){
            return ;
        }
        //强制更新
        UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {
            @Override
            public void onClick(int status) {
                switch (status) {
                    case UpdateStatus.Update:
                        break;
                    default:
                        ToastUtil.getInstance().show("非常抱歉，您需要更新应用才能继续使用");
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        AppManager.getInstance().AppExit(mContext);
                        break;
                }
            }
        });
    }


    /**
     * 判断是否是强制升级
     * F:强制更新  S:不强制
     *
     * @param names
     * @return
     */
    private static boolean isForce(String names, Context mContext) {
        List<Map<String, String>> list = StringUtil.convertStrToArray(names);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        String curr_version_name = ResourcesUtil.getVersionName(mContext);
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> map = list.get(i);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                entry.getKey();
                if ( !curr_version_name.equals(entry.getKey()) ) {               //版本相同
                    if ( F.equalsIgnoreCase(entry.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
