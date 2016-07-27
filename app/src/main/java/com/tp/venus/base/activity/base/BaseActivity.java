package com.tp.venus.base.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.tp.venus.R;
import com.tp.venus.base.builder.IntentBuilder;
import com.tp.venus.base.builder.ToolbarBuilder;
import com.tp.venus.base.mvp.ProxyFactory;
import com.tp.venus.base.mvp.p.BasePresenter;
import com.tp.venus.base.mvp.p.IBasePresenter;
import com.tp.venus.base.rx.RxBus;
import com.tp.venus.config.Status;
import com.tp.venus.module.user.ui.LoginActivity;
import com.tp.venus.util.AppManager;
import com.tp.venus.util.ResourcesUtil;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StatusBarCompat;
import com.tp.venus.util.ToastUtil;
import com.umeng.analytics.MobclickAgent;


/**
 * <p>名称：com.bob.myclient.base.activity.BaseActivity</p>
 * <p>描述：</p>
 * <pre>
 *
 * </pre>
 *
 * @author 鲍建明
 * @version 2.1.0
 * @date 2015/7/29/13:46
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected RxBus mRxBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        setColorPrimary();
    }


    protected <T extends View> T findView(int id) {
        return (T)findViewById(id);
    }

    /**
     * 默认设置沉浸式状态栏
     * 不需要时请重写
     */
    protected void setColorPrimary(){
        StatusBarCompat.setColor(this, ResourcesUtil.getColor(this, R.color.themeColor), 0);
    }

    /**
     * 获取IntentBuilder
     * @param cls
     * @return
     */
    public IntentBuilder getIntentBuilder(Class<?> cls){
        return IntentBuilder.create(this, cls);
    }

    /**
     * 获取ToolbarBuilder
     * @param mToolbar
     * @return
     */
    public ToolbarBuilder getToolbarBuilder(Toolbar mToolbar ){
        return ToolbarBuilder.create(mToolbar).setAppCompatActivity(this);
    }

    public void startActivity(Class<?> cls){
        startActivity(getIntentBuilder(cls).build());
    }


    /**
     * 创建一个BasePresenter动态代理对象
     * @param mBasePresenter
     * @return
     */
    public  <P extends IBasePresenter> P getPresenter(BasePresenter mBasePresenter){
        return ProxyFactory.getProxy(mBasePresenter);
    }

    /**
     * 获取RxBus 事件
     * @return
     */
    public RxBus getRxBus(){
        if(mRxBus == null){
            mRxBus = new RxBus();
        }
        return mRxBus;
    }

    /**
     * 显示错误信息
     * @param message
     */
    public void showError(String message) {
        ToastUtil.getInstance().show(message);
    }

    /**
     * 获取当前用户的ID
     * @return
     */
    public String getCurrentUserId(){
        return SharePreferencesUtils.getString(this, Status.User.CURRENT_ID);
    }



    public void goLoginView() {
        startActivity(LoginActivity.class);
    }

    public Context getCurrentContext(){
        return this;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                closeCurrentActivity();
                break;
        }
        return true;
    }

    /**
     * 可重写
     */
    protected void closeCurrentActivity(){
        finishActivity();
    }



    /**
     * 关闭当前的activity
     */
    public void finishActivity() {
        AppManager.getInstance().finishActivity();
    }


    @Override
    public void onBackPressed() {
        closeCurrentActivity();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        /*Bugtags.onResume(this);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
       /* Bugtags.onPause(this);*/
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
      /*  Bugtags.onDispatchTouchEvent(this, event);*/
        return super.dispatchTouchEvent(event);
    }
}
