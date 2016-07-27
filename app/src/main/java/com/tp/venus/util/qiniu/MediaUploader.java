package com.tp.venus.util.qiniu;

import android.content.Context;
import android.os.Environment;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.persistent.FileRecorder;
import com.tp.venus.config.Constant;

import org.json.JSONObject;

import java.io.File;

/**
 * 拍照录像的媒体文件上传到七牛存储。
 * 上传：调用upload/或upload([])。
 * 续传：使用未释放修改的Uploader对象，调用resume()。
 * 依赖：qiniu-android-sdk-7.0.9.jar
 * Created by xu on 2015-12-15.
 */
public class MediaUploader
        implements UpCancellationSignal, UpCompletionHandler, UpProgressHandler {
    Configuration mConfig;
    UploadManager mUploadManager;
    Context mContext;
    protected boolean mCancelling = false;    // 正在取消
    protected UploadListener mListener;
    protected Recorder mRecorder;    // 记录进度，以便续传

    /**
     * 成功后返回当前上传的资源地址
     *
     * @return
     */
    public String getUrl() {
        return null;
    }

    /**
     * 成功后返回所有已上传上传的资源地址
     *
     * @return
     */
    public String[] getUrls() {
        return new String[]{getUrl()};
    }

    public void setListener(UploadListener listener) {
        this.mListener = listener;
    }

    public interface UploadListener {
        /**
         * 准备工作完成（已获得UploadToken）
         */
        void onStarted(int total, int position);

        /**
         * 更新进度
         *
         * @param total    总共多少个
         * @param position 当前第几个（1开始）
         * @param progress 进度。0-1
         */
        void onProgress(int total, int position, double progress);

        /**
         * 成功上传
         *
         * @param total
         * @param position
         * @param url
         */
        void onOk(int total, int position, String url);

        /**
         * 失败
         *
         * @param total
         * @param position
         * @param progress
         * @param errorMessage
         */
        void onFailed(int total, int position, double progress, String errorMessage);
    }

    public MediaUploader(Context context) {
        mContext = context;
    }

    /**
     * 终止上传。清理引用
     */
    public void abort() {
        mCancelling = true;
        mListener = null;
        mUploadManager = null;
        mRecorder = null;
        mConfig = null;
    }

    /**
     * 同个上下文恢复上传。
     * ·之前应调用过upload，因网络问题中断。且未退出上下文。
     * ·之前不得调用abort。
     */
    public void resume() {
        mCancelling = false;
        retrieveToken();
    }

    Configuration getConfig() {
        if (mConfig == null) {
            String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
                    + Constant.FILENAMEDIR + File.separator +"recorder";
            File fDir = new File(dir);
            try {
                if (!fDir.exists())
                    fDir.mkdirs();
            } catch (Exception x) {
                x.printStackTrace();
            }
            try {
                mRecorder = new FileRecorder(dir);
            } catch (Exception x) {
                x.printStackTrace();
            }

            mConfig = new Configuration.Builder()
                    .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认 256K
                    .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认 512K
                    .connectTimeout(10) // 链接超时。默认 10秒
                    .responseTimeout(60) // 服务器响应超时。默认 60秒
//                    .recorder(mRecorder)    // recorder 分片上传时，已上传片记录器。默认 null
//					.recorder(recorder, keyGen)  // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//					.zone(Zone.zone0) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。默认 Zone.zone0
                    .build();
        }
        return mConfig;
    }

    UploadManager getUploadManager() {
        if (mUploadManager == null)
            mUploadManager = new UploadManager(getConfig());
        return mUploadManager;
    }

    @Override
    public boolean isCancelled() {
        return mCancelling;
    }

    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
    }

    @Override
    public void progress(String s, double v) {
        if (mListener != null)
            mListener.onProgress(getTotal(), getCurrent(), v);
    }

    static class UploadContext {
        protected File file;
        protected String ext;
        protected String uniqueName;
        protected String mimeType;
        protected String url;
        protected String token;
        protected boolean ok;    // true-完成。false-失败或未传

        protected UploadContext(File file, String defaultExt) {
            this.file = file;
            ext = file.getName();
            int dot = ext.lastIndexOf('.');
            if (dot >= 0 && dot < ext.length() - 1)
                ext = ext.substring(dot + 1);
            else
                ext = defaultExt;
            switch (ext.toLowerCase()) {
                case "jpg":
                    mimeType = "image/jpeg";
                    break;
                case "mp4":
                    mimeType = "video/mp4";
            }
        }
    }

    public void upload(File file) {
//		upload(new File[]{file});
    }

    public void upload(File[] files) {
//		throw new Exception("Method Not Supported");
    }

    /**
     * 获取一个七牛上传凭据
     */
    protected void retrieveToken() {
    }

    /**
     * @param token
     */
    protected void onRetrieveToken(String path, String token) {
    }

    int getTotal() {
        return 1;
    }

    int getCurrent() {
        return 1;
    }
}