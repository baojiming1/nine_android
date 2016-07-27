/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.util</p>
 * <p>File：FileUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/2/13:30.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.tp.venus.config.Constant;

import java.io.File;

/**<p>Class：com.tp.venus.util.FileUtil</p>
 * <p>Description：</p>
 * <pre>
 *   文件工具类
 * </pre>
 * @author 鲍建明
 * @date 2016/3/2/13:30
 * @version 1.0.0
 */

public class FileUtil {

    /**
     * 图片文件夹名.
     */
    public static String IMAGE_FILE_DIR = "image";

    /**
     * 压缩图片上传
     */
    public static File getFile(String filePath, Context context) {
        File file = new File(filePath);
        File newFile = null;
        Bitmap bitmap = null;
        if (file.exists()) {

            bitmap = BitmapUtil.decodeFile(file, ResourcesUtil.getPoint(context).x);
            if (bitmap == null)
                return null;
            try
            {
                newFile = new File(getPath(IMAGE_FILE_DIR), System.currentTimeMillis() + ".jpg");
                BitmapUtil.saveLocalBitmap(bitmap, newFile);
            }
            catch (Exception x)
            {
            }
        }
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
        return newFile;
    }

    public static String getPath(String str){
       return Environment.getExternalStorageDirectory()
                + File.separator + Constant.FILENAMEDIR + File.separator;
    }


    /**
     * 判断SD卡是否存�?.
     *
     * @return false
     */
    public boolean existSDCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
