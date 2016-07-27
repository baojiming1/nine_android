/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.base.annotation</p>
 * <p>File：Login.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/1/18/10:08.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**<p>Class：com.tp.venus.base.annotation.Login</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/1/18/10:08
 * @version 1.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
}
