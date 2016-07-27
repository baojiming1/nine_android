package com.tp.venus.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tp.venus.base.activity.base.BaseActivity;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.base.rx.RxBus;
import com.umeng.analytics.MobclickAgent;

/**
 * <p>名称：com.bob.myclient.base.fragment.BaseFragment</p>
 * <p>描述：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 2.1.0
 * @date 2015/7/31/14:52
 */

public class BaseFragment extends Fragment {

    protected Context mContext;
    public LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("SplashScreen");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Context getContext(){
        return mContext;
    }

    /**
     * 获取IntentBuilder
     * @param cls
     * @return
     */
    public IntentBuilder getIntentBuilder(Class<?> cls){
        return IntentBuilder.create(this.mContext, cls);
    }


    public void startActivity(Class<?> cls){
        startActivity(IntentBuilder.create(getContext(), cls).build());
    }

    /**
     * 创建一个BasePresenter动态代理对象
     * @param mBasePresenter
     * @return
     */
    public  <P extends IBasePresenter> P getPresenter(BasePresenter mBasePresenter){
        return ((BaseActivity)getActivity()).getPresenter(mBasePresenter);
    }

    /**
     * 获取RxBus 事件
     * @return
     */
    public RxBus getRxBus(){
        return ((BaseActivity)getActivity()).getRxBus();
    }

    /**
     * 显示错误信息
     * @param message
     */
    public void showError(String message) {
        ((BaseActivity)getActivity()).showError(message);
    }

    /**
     * 关闭当前的activity
     */
    public void finishActivity(){
        ((BaseActivity)getActivity()).finishActivity();
    }

    //跳转登录界面
    public void goLoginView(){
        ((BaseActivity)getActivity()).goLoginView();
    }

    public Context getCurrentContext(){
        return ((BaseActivity)getActivity()).getCurrentContext();
    }

    public Fragment getFragment(){
        return this;
    }

}
