/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：PayResult.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/16/15:44.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import android.text.TextUtils;

/**<p>Class：com.tp.venus.module.shop.bean.PayResult</p>
 * <p>Description：</p>
 * <pre>
 *   支付宝的对象
 * </pre>
 * @author 鲍建明
 * @date 2016/5/16/15:44
 * @version 1.0.0
 */

public class PayResult {

    private String resultStatus;
    private String result;
    private String memo;

    public PayResult(String rawResult) {

        if (TextUtils.isEmpty(rawResult))
            return;

        String[] resultParams = rawResult.split(";");
        for (String resultParam : resultParams) {
            if (resultParam.startsWith("resultStatus")) {
                resultStatus = gatValue(resultParam, "resultStatus");
            }
            if (resultParam.startsWith("result")) {
                result = gatValue(resultParam, "result");
            }
            if (resultParam.startsWith("memo")) {
                memo = gatValue(resultParam, "memo");
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    private String gatValue(String content, String key) {
        String prefix = key + "={";
        return content.substring(content.indexOf(prefix) + prefix.length(),
                content.lastIndexOf("}"));
    }

    /**
     * @return the resultStatus
     */
    public String getResultStatus() {
        return resultStatus;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

}
