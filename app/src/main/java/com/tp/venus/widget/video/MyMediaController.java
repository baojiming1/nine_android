/**
 * <p>Project：venus</p>
 * <p>Package：	com.tp.venus.widget.video</p>
 * <p>File：MyMediaController.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/3/3/14:09.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.widget.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import io.vov.vitamio.widget.MediaController;

/**<p>Class：com.tp.venus.widget.video.MyMediaController</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/3/3/14:09
 * @version 1.0.0
 */

public class MyMediaController extends MediaController {


    public MyMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMediaController(Context context) {
        super(context);
    }

    public MyMediaController(Context context, boolean fromXml, View container) {
        super(context, fromXml, container);
    }
}
