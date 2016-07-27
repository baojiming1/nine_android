/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.rx</p>
 * <p>File：RxBus.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/20/16:15.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.rx;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**<p>Class：com.tp.venus.base.rx.RxBus</p>
 * <p>Description：</p>
 * <pre>
 *  rxjava + eventBus
 * </pre>
 * @author 鲍建明
 * @date 2016/1/20/16:15
 * @version 1.0.0
 */

public class RxBus {

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

    public boolean hasObservers() {
        return _bus.hasObservers();
    }

}
