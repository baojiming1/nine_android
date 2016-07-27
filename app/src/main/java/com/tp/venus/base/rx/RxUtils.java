package com.tp.venus.base.rx;

import com.tp.venus.base.ApplicationHandler;
import com.tp.venus.config.Status;
import com.tp.venus.exception.AuthenticationException;
import com.tp.venus.util.SharePreferencesUtils;
import com.tp.venus.util.StringUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by pc on 2016/1/16.
 */
public class RxUtils {

    /**
     * 获取token
     * @return
     */
    public static Observable<String>  getToken(final @Status.TokenStatus int tokenStatus){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String token = SharePreferencesUtils.getString(ApplicationHandler.getApp().getApplicationContext(), Status.TOKEN);
                //1.有token 执行subscriber.onNext();
                switch (tokenStatus){
                    case Status.TokenStatus.COMPLET:
                        subscriber.onNext(token);
                        break;
                    case Status.TokenStatus.MUST:
                        if(StringUtil.isEmpty(token)){
                           AuthenticationException mAuthenticationException =  new AuthenticationException("token 为空");
                            subscriber.onError(mAuthenticationException);
                        } else {
                            subscriber.onNext(token);
                        }
                        break;
                    case Status.TokenStatus.NORMAL:
                    default:
                        subscriber.onNext(null);
                        break;
                }
                //2.没有token  执行subscriber.onError();
             //   subscriber.onNext("5AA2BC3B0BC241FFB4441FA67E05A8C8");

                //3.都会去执行的   subscriber.onCompleted();
            }
        });
    }

    /**
     * 子线程中执行
     * @param obj
     * @param onNext
     */
    public static void doSubThead(Object obj, final Action1<Object> onNext){
        Observable.just(obj).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(onNext);
    }


}
