/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.module.home.api</p>
 * <p>File：ApiUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/14/11:52.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.util;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.config.AppConfig;
import com.tp.venus.config.Status;
import com.tp.venus.model.JsonMessage;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**<p>Class：com.tp.venus.util.ApiUtil</p>
 * <p>Description：</p>
 * <pre>
 *      API工具类
 * </pre>
 * @author 鲍建明
 * @date 2016/1/14/11:52
 * @version 1.0.0
 */

public class ApiUtil {

    private static ApiUtil instance = new ApiUtil();
    private OkHttpClient mOkHttpClient;
    private HttpLoggingInterceptor loggingInterceptor ;
    private  Interceptor headInterceptor ;
    private Retrofit retrofit;


    /**
     * 单例
     * @return
     */
    public static ApiUtil getInstance(){
        return instance;
    }

    /**
     * 构造器
     */
    private ApiUtil(){
        init();
        List<Interceptor> interceptors =  mOkHttpClient.interceptors();
        interceptors.add(loggingInterceptor);
        interceptors.add(headInterceptor);
       // okHttpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);

      //  createDialog();
    }

    private void init(){
        this.mOkHttpClient = getOkHttpClient();
        this.loggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        this.headInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String token = SharePreferencesUtils.getString(ApplicationHandler.getApp().getApplicationContext(), Status.TOKEN);
                Request newRequest = getDefaultRequest(token, chain.request());
                //  Logger.d(newRequest.toString());
                //  showDialog();
                Response mResponse = chain.proceed(newRequest);
                //hideDialog();
                return mResponse;
            }
        };
    }

    /**
     *
     * @return
     */
    public OkHttpClient getOkHttpClient(){
        OkHttpClient mOkHttpClient =  new OkHttpClient();
        mOkHttpClient.setConnectTimeout(AppConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        mOkHttpClient.setReadTimeout(AppConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS);
        mOkHttpClient.setWriteTimeout(AppConfig.HTTP_TIMEOUT, TimeUnit.MINUTES);
        File cacheDir = new File(ApplicationHandler.getApp().getApplicationContext().getCacheDir(), "HttpResponseCache");
        Cache cache = new Cache(cacheDir, 1024 * 1024 * 10); //10Mb
        mOkHttpClient.setCache(cache);
        return mOkHttpClient;
    }

    /**
     * 设置默认参数Request
     */
    public Request getDefaultRequest(String token, Request mRequest){
        Request.Builder mBuilder = mRequest.newBuilder()
                .addHeader("version", "1")
                .addHeader("source", "1");
        if(StringUtil.isNotEmpty(token)){
            mBuilder.addHeader("token", token);
        }
        Logger.d("url:--->" +  mRequest.urlString());
        Logger.d("version:-->" + "1" + ",source:-->1" + "token:-->" + token);
        return mBuilder.build();
    }

    /**
     * 解释错误信息
     * @param e
     * @return
     */
    public String parseErrorMessage(Throwable e){
        if( e instanceof ConnectException){
            return "无法连接网络，请重试";
        } else if( e instanceof SocketTimeoutException ){
            return "网络连接超时，请重试";
        } else if( e instanceof UnknownHostException){
            return "无法连接网络，请重试";
        }
        return "数据解析错误";
    }


    /**
     * 发送请求
     * @param request
     * @return
     */
    public JsonMessage sendJsonMessage(Request request){
        Response response = null;
        String message = "参数异常";
        try{
            response = ApiUtil.getInstance().getOkHttpClient().newCall(request).execute();
            if(response.code() == 200){
                String json =  response.body().string();
                Logger.json(json);
                if( StringUtil.isNotEmpty(json) ){
                    return JSONUtil.parseObject(json, JsonMessage.class);
                }
            }
        } catch (IOException e){
            message = parseErrorMessage(e);
        } finally {
            if( response != null ){
                try {
                    response.body().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        JsonMessage mJsonMessage = new JsonMessage();
        mJsonMessage.setCode(100);
        mJsonMessage.setMessage(message);
        return mJsonMessage;
    }

    /**
     * 创建一个service服务
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T createApi( Class<T> clazz) {
           /*       2.0  */
        if( retrofit == null){
            synchronized( this ){
                if( retrofit == null){
                    retrofit = createRetrofit();
                }
            }
        }
       return retrofit.create(clazz);
    }

    //
    private Retrofit createRetrofit(){
        return  new Retrofit.Builder().baseUrl(AppConfig.SERVICE_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
             //   .addConverterFactory(FastJsonConvertFactory.create())
                .client(mOkHttpClient)
                .build();
    }


        /*if( mRestAdapter == null ){
            synchronized( this ){
                RestAdapter.Builder builder = new RestAdapter.Builder();
                builder.setEndpoint(AppConfig.SERVICE_HOST);//设置远程地址
                   *//* builder.setConverter();*//*
                builder.setClient( getDefaultClient() );
                builder.setLogLevel( RestAdapter.LogLevel.FULL );
              *//*  builder.setLogLevel(
                        Config.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);*//*
                mRestAdapter = builder.build();
            }
        }
        return mRestAdapter.create(clazz);*/

  /*  *//**
     * 获取默认的OkClient
     * @return
     *//*
    public OkClient getDefaultClient(){
        return  new OkClient(mOkHttpClient);
    }
*/

}
