/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：PermissionUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/6/16:44.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringRes;

import java.util.List;

/**<p>Class：com.tp.venus.util.PermissionUtil</p>
 * <p>Description：</p>
 * <pre>
 *     运行时，权限检查处理
 * </pre>
 * @author 鲍建明
 * @date 2016/5/6/16:44
 * @version 1.0.0
 */

public class PermissionUtil {
    /**
     * 拨打电话CODE
     */
    public static final int CALL_PHONE_REQUEST_CODE = 10777;

    private static PermissionUtil ourInstance = null;

    public static synchronized PermissionUtil getInstance() {
        if( ourInstance == null){
            ourInstance = new PermissionUtil();
        }
        return ourInstance;
    }

    private PermissionUtil() {
    }

    /**
     * 拨打电话(但不拨打)
     * @param mContext
     * @param str
     */
    public void callPhone(Context mContext, @StringRes int str){
        /*if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_REQUEST_CODE);
            return;
        }*/
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ResourcesUtil.getString(mContext, str)));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 获取进程名称
     * @param cxt
     * @param pid
     * @return
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

}
