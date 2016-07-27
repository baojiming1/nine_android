/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.builder</p>
 * <p>File：IntentBuilder.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2015/10/14/12:53.</p>
 * Copyright © 2015 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.builder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.tp.venus.model.SerializableMap;

import java.util.ArrayList;
import java.util.Map;

/**<p>Class：com.tp.venus.base.builder.IntentBuilder</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2015/10/14/12:53
 * @version 1.0.0
 */

public class IntentBuilder {

    private Intent mIntent;
    private Context mContext;
    private Class<?> clazz;
    private Bundle mBundle;

    public static IntentBuilder create(Context mContext, Class<?> clazz){
        return new IntentBuilder(mContext, clazz);
    }

    public static IntentBuilder IntentBuilder(){
        return new IntentBuilder();
    }

    IntentBuilder(){
        this.mIntent = new Intent();
        this.mBundle = new Bundle();
    }

    IntentBuilder(Context mContext, Class<?> clazz){
        this.mContext = mContext;
        this.clazz = clazz;
        this.mIntent = new Intent();
        this.mBundle = new Bundle();
    }

    public IntentBuilder setClass(Context packageContext, Class<?> cls){
        this.mContext = packageContext;
        this.clazz = cls;
        return this;
    }

    public Intent build(){
        mIntent.setClass(mContext, clazz);
        if ( !mBundle.isEmpty()){
            mIntent.putExtras(mBundle);
        }
        return mIntent;
    }

    public IntentBuilder setFlags(int flags){
        mIntent.setFlags(flags);
        return this;
    }

    public IntentBuilder putString(String key, String value){
        mBundle.putString(key, value);

        return this;
    }

    public IntentBuilder putStringArrayList(@Nullable String key, @Nullable ArrayList<String> value){
        mBundle.putStringArrayList(key, value);

        return this;
    }

    public IntentBuilder putParcelableArrayList(@Nullable String key,
                                                @Nullable ArrayList<? extends Parcelable> value){
        mBundle.putParcelableArrayList(key, value);
        return this;
    }

    public IntentBuilder putInt(String key, int value){
        mBundle.putInt(key, value);
        return this;
    }

    public IntentBuilder putBoolean(String key, boolean value){
        mBundle.putBoolean(key, value);
        return this;
    }

    public IntentBuilder putShort(String key, short value){
        mBundle.putShort(key, value);
        return this;
    }


    public IntentBuilder putAll(String key, Map map){
        SerializableMap pm = new SerializableMap();
        pm.setMap(map);
        mBundle.putSerializable(key, pm);
        return this;
    }

    public IntentBuilder putParcelable(String key, Parcelable value){
        mBundle.putParcelable(key, value);
        return this;
    }


}
