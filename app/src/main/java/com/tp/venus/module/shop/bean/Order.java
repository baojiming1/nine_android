/**
 * <p>Project：nine_android</p>
 * <p>Package：	com.tp.venus.module.shop.bean</p>
 * <p>File：Order.java</p>
 * <p>Version： 4.0.0</p>
 * <p>Date： 2016/5/4/9:51.</p>
 * Copyright © 2016 www.qbt365.com Corporation Inc. All rights reserved.
 */
package com.tp.venus.module.shop.bean;

import java.util.List;

/**<p>Class：com.tp.venus.module.shop.bean.Order</p>
 * <p>Description：</p>
 * <pre>
 *
 * </pre>
 * @author 鲍建明
 * @date 2016/5/4/9:51
 * @version 1.0.0
 */

public class Order {


    /**
     * id : 124342545363646567
     * mainImage : http://71JHZ8uwD...
     * actuallyPaidSum : 0.0
     * applyServicesTime : 0
     * buyCount : 10
     * cancelTime : 1453008464449
     * closeTime : 1453008464449
     * delStatus : false
     * discountSum : 200.0
     * expiredTime : 1453008464449
     * finishTime : 1453008464449
     * freight : 10.0
     * isLock : false
     * orderNo : 200634
     * orderStatus : 0
     * orderTime : 0
     * orderTitle : 朗姆酒
     * payTime : 1453008464449
     * payType : 1
     * payableSum : 200.0
     * recipientName : 董恒
     * recipientID : 310222656151546465655
     * recipientMobile : 13548851265
     * recipientAddress : 上海市静安区高平路100 号9号楼3楼
     */

    private String id;
    private String mainImage;
    private String actuallyPaidSum;                 //实付金额
    private Long applyServicesTime;
    private Integer buyCount;
    private Long cancelTime;
    private Long closeTime;
    private Boolean delStatus;
    private String discountSum;                     //折扣金额
    private Long expiredTime;
    private Long finishTime;
    private String freight;                         //运费
    private Boolean isLock;
    private String orderNo;
    /**
     * 订单状态 -1.未支付(订单创建);2.用户支付完成等待确认;3.支付成功(待收货);4.已发货(确认收货);5.完成订单（已收货）;6.申请售后;7.申请退款;9.订单关闭
     */
    private Integer orderStatus;                    //
    private Long orderTime;
    private String orderTitle;
    private Long payTime;
    private Integer payType;
    private String payableSum;                          //总计金额(商品总价)
    private String recipientName;
    private String recipientID;
    private String recipientMobile;
    private String recipientAddress;
    private String message;
    private List<OrderDetail> orderDetails;
    private Boolean isSupportAliPay;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getActuallyPaidSum() {
        return actuallyPaidSum;
    }

    public void setActuallyPaidSum(String actuallyPaidSum) {
        this.actuallyPaidSum = actuallyPaidSum;
    }

    public Long getApplyServicesTime() {
        return applyServicesTime;
    }

    public void setApplyServicesTime(Long applyServicesTime) {
        this.applyServicesTime = applyServicesTime;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Long getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Long cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Boolean getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Boolean delStatus) {
        this.delStatus = delStatus;
    }

    public String getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(String discountSum) {
        this.discountSum = discountSum;
    }

    public Long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Long expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean lock) {
        this.isLock = lock;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayableSum() {
        return payableSum;
    }

    public void setPayableSum(String payableSum) {
        this.payableSum = payableSum;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public void setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsSupportAliPay() {
        return isSupportAliPay;
    }

    public void setIsSupportAliPay(Boolean supportAliPay) {
        isSupportAliPay = supportAliPay;
    }
}
