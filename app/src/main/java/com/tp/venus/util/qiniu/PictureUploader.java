package com.tp.venus.util.qiniu;

import android.content.Context;
import android.util.Log;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UploadOptions;
import com.tp.venus.base.mvp.v.BaseView;
import com.tp.venus.base.rx.ProgressSubscriber;
import com.tp.venus.model.JsonMessage;
import com.tp.venus.module.common.bean.Token;
import com.tp.venus.module.common.model.ITokenModel;
import com.tp.venus.module.common.model.imp.TokenModel;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片上传，含单张和多张
 * Created by xu on 2015-12-15.
 */
public class PictureUploader extends MediaUploader {

    PictureUploadContext mUploadContext;

    private ITokenModel mITokenModel;
    private BaseView mBaseView;

    /**
     * 成功后返回当前上传的资源地址
     *
     * @return
     */
    @Override
    public String getUrl() {
        if (mUploadContext != null)
            return mUploadContext.url;
        else
            return null;
    }

    /**
     * 成功后返回所有已上传上传的资源地址
     *
     * @return
     */
    @Override
    public String[] getUrls() {
        String[] urls = new String[mUploadContext.mContexts.size()];
        for (int i = 0; i < urls.length; i++)
            urls[i] = mUploadContext.mContexts.get(i).url;
        return urls;
    }

    static class PictureUploadContext  extends UploadContext {
        /**
         * 多个上传凭据。或1个时等同于mToken
         */
        List<UploadContext> mContexts = new ArrayList<>();
        int mPosition = -1;

        PictureUploadContext(File[] imageFiles) {
            super(imageFiles[0], Definitions.PictureDefaultExt);
            for (File imageFile : imageFiles) {
                mContexts.add(new UploadContext(imageFile, Definitions.PictureDefaultExt));
            }
            move(0);
        }

        void move(int position) {
            UploadContext context = mContexts.get(position);
            mPosition = position;
            file = context.file;
            ext = context.ext;
            uniqueName = context.uniqueName;
            mimeType = context.mimeType;
            url = context.url;
            token = context.token;
        }

        boolean hasNext() {
            return mContexts != null
                    && mPosition >= 0
                    && mPosition < mContexts.size() - 1;
        }

        UploadContext getCurrent() {
            return mContexts.get(mPosition);
        }
    }

    public PictureUploader(Context context, BaseView mBaseView) {
        super(context);
        mContext = context;
        this.mITokenModel = new TokenModel();
        this.mBaseView = mBaseView;
    }

    @Override
    public void upload(File imageFile) {
        upload(new File[]{imageFile});
    }

    @Override
    public void upload(File[] imageFiles) {
        mUploadContext = new PictureUploadContext(imageFiles);
        retrieveToken();
    }

    @Override
    protected void retrieveToken() {
        UploadContext ctx = null;
        // 跳过已完成的
        while (true) {
            ctx = mUploadContext.getCurrent();

            if (!ctx.ok)
                break;    // 未完成，继续上传
            else if (mUploadContext.hasNext())
                mUploadContext.move(mUploadContext.mPosition + 1);    // 已完成，跳过1个
            else {
                // 全部已完成，发送完成通知
                if (mListener != null) {
                    mListener.onOk(getTotal(), getCurrent(), mUploadContext.url);
                }
                return;
            }
        }

        mUploadContext.uniqueName = ctx.uniqueName = PutPolicy.createUniqueName(mUploadContext.ext);

        mITokenModel.getToken(new ProgressSubscriber<JsonMessage<Token>>(mBaseView) {
            @Override
            public void onSuccess(JsonMessage message) {
                Token token = (Token) message.getObj();
                if( token != null){
                    if(mListener != null){
                        Log.d("上传凭证", message.toString());
                        mListener.onStarted(getTotal(), getCurrent());
                    }
                    onRetrieveToken(token.getPath(), token.getUptoken());
                }

            }
        });

     /*   HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("", "");
        HashMap<String, String> header = new HashMap<>();
        header.put("token", "9A8CF827F04D4E9DB457300B538FE0E0");

        HttpUtil.getIntance().postJson(mContext, Url.COMMON_TOKEN, ParameUtil.mapToJson(hashMap), header, new IHttpListener() {
            @Override
            public void onResponseJson(com.alibaba.fastjson.JSONObject response) {
                com.alibaba.fastjson.JSONObject obj = response.getJSONObject("obj");
                if (obj != null) {
                    if (mListener != null) {
                        Log.d("上传凭证", response.toString());
                        mListener.onStarted(getTotal(), getCurrent());
                    }
                    onRetrieveToken(obj.getString("path"), obj.getString("uptoken"));
                } else
                    onFailure();
            }

            @Override
            public void onFailure() {
                if (mListener != null) {
                    Log.e("上传文件", "无法从服务器获取上传凭证");
                    mListener.onFailed(getTotal(), getCurrent(),
                            0, "无法从服务器获取上传凭证");
                }
            }
        });*/
    }

    @Override
    protected void onRetrieveToken(String path, String token) {
        UploadContext ctx = mUploadContext.mContexts.get(mUploadContext.mPosition);
        mUploadContext.token = ctx.token = token;
        mUploadContext.url = ctx.url = PutPolicy.pictureUrl(path, mUploadContext.uniqueName);

//		mUploadContext.token = UploadToken.build(
//				PutPolicy.newPolicy(Definitions.PictureScope, mUploadContext.uniqueName));
        getUploadManager().put(mUploadContext.file, mUploadContext.uniqueName, mUploadContext.token,
                this,
                new UploadOptions(null, mUploadContext.mimeType, false, this, this));
        if (mListener != null)
            mListener.onStarted(getTotal(), getCurrent());
    }

    @Override
    public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
        mUploadContext.getCurrent().ok = true;
        if (mListener != null) {
            mListener.onOk(getTotal(), getCurrent(), mUploadContext.url);
        }

        // 继续下一个
        if (mUploadContext.hasNext()) {
            mUploadContext.move(mUploadContext.mPosition + 1);
            retrieveToken();
        }
    }

    @Override
    int getTotal() {
        return mUploadContext.mContexts.size();
    }

    @Override
    int getCurrent() {
        return mUploadContext.mPosition + 1;
    }
}
