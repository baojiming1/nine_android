/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.qinjia.entity</p>
 * <p>File：LiveProduct.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/6/30/18:38.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.qinjia.entity;

/**<p>Class：com.tp.venus.module.qinjia.entity.LiveProduct</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/6/30/18:38
 * @version 1.0.0
 */

public class LiveProduct  {

  /*  mainImage:”http…..jpg”,
    productId:”1323232323223333”,
    title:”产品标题”,
    desc:”产品描述”,
    price:21.22,
    realPrice:11.11*/
/*
    "buy_quota": 0,
            "desc": "<p><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/Fh-xymVDEcKXi-OueaCtPfVTt88M.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/Frtp6sUAMekuV-7xpUAjHqDM9b01.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FgDrkui6QkXB10otdcbxzr1EGPl6.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/Fv214OI1lNQiuYHlXP6jlq9Yy0Zl.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FiZAI_SZi5fIFEK0sc6CRLuI-EPy.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FkIpU4s6WImWhPJGgvKkTtl8U_3q.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FrwfYF7240PiS_zYuKPL3nk9tX4m.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/Fma61MgMxwNfLuMwsiEWlbw6Dv4t.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FhRPR6rloBseDNaIR9nPz1dk5n7Y.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FqZRypZFn4mOAsdQpZGo5TpA8Q1J.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/FqHUqUxzEhu10JWKgScDFCnT3UPo.jpg!730x0.jpg\"/><img src=\"https://img.yzcdn.cn/upload_files/2016/04/05/Fpc7jQcU3RsTIaViXji9QM8vDECe.jpg!730x0.jpg\"/></p>",
            "detail_url": "https://shop16372842.koudaitong.com/v2/showcase/goods?alias=1y3y1pr1aw19u&from=wsc&kdtfrom=wsc",
            "mainImage": "https://img.yzcdn.cn/upload_files/2016/04/05/FlmdK-Q5kpVv1kHjMAGa_iQPwnDU.jpg!120x120.jpg",
            "num": 10,
            "num_iid": "241223125",
            "price": "",
            "productId": "1y3y1pr1aw19u",
            "productUrl": "https://wap.koudaitong.com/v2/showcase/goods?alias=1y3y1pr1aw19u",
            "realPrice": "2837.00",
            "sold_num": 0,
            "status": true,
            "title": "Nesun 尼尚 精钢表带 葡萄牙系列 商务时尚 休闲防水 男士石英腕表 MS8601A 玫瑰金色"*/
    private Integer buy_quota;              //限购
    private String detail_url;
    private String mainImage;
    private String productId;
    private String title;
    private String desc;
    private String price;
    private String realPrice;

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getBuy_quota() {
        return buy_quota;
    }

    public void setBuy_quota(Integer buy_quota) {
        this.buy_quota = buy_quota;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }
}
