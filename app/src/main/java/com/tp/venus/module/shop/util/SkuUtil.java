/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.util</p>
 * <p>File：SkuUtil.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/4/19/16:51.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.util;

import com.tp.venus.module.shop.bean.ProductSku;
import com.tp.venus.util.CollectionUtils;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.util.SkuUtil</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/4/19/16:51
 * @version 1.0.0
 */

public class SkuUtil {

    /**
     * 颜色：金色，蓝色，红色
        规格：25KG
     * @param mProductSkus
     * @return
     */
    public static String parseSku(List<ProductSku> mProductSkus){
        StringBuffer result = new StringBuffer();
        if(CollectionUtils.isNotEmpty(mProductSkus)){
            int length = mProductSkus.size();
            for (int i = 0; i < length; i++) {
                ProductSku mProductSku = mProductSkus.get(i);
                result.append(mProductSku.getSkuName() + "：" + mProductSku.getSkuOptions());
                if(length != 1 ){
                    if( (length - 1) != i){
                        result.append("\n");
                    }
                }
            }
        }
        return result.toString();
    }

}
