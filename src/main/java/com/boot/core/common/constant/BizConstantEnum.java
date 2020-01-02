package com.boot.core.common.constant;

/**
 * @author TANGXIAN
 * @Description 所有业务的枚举
 */
public enum BizConstantEnum {

    /**
     * 三方平台
     */
    dividedpart_dev(1,"开发者"),
    dividedpart_pub(2,"出版社"),
    dividedpart_drc(3,"迪瑞云"),

    /**
     * 状态
     */
    enabled(1,"启用"),
    disabled(2,"禁用"),

    /**
     * 资源状态
     */
    resourcestatus_not(1,"未上架"),
    resourcestatus_has(2,"已上架"),

    /**
     * 订单状态
     */
    orderstatus_toBePaid(1,"未支付"),
    orderstatus_done(2,"已支付"),
    orderstatus_file(3,"支付失败"),
    orderstatus_refund(4,"已退款"),

    /**
     * 支付方式
     */
    paytype_weixin(1,"微信"),
    paytype_alipay(2,"支付宝"),
    paytype_unionPay(3,"银联"),
    paytype_miniProgram(4,"小程序"),

    /**
     * 平台类型
     */
    platform_android(1,"Android平台"),
    platform_ios(2,"ios平台"),
    platform_pen(3,"智能笔平台"),
    platform_gardener(4,"园丁系统"),
    platform_wx_web(5,"微信小程序"),

    /**
     * 应用类型
     */
    apptype_ios(1,"IOS"),
    apptype_android(2,"Android"),
    apptype_miniProgram(3,"小程序"),

    /**
     * 合作模式
     */
    salemodel_lsj(1,"统一零售价"),
    salemodel_dj(2,"统一定价"),

    /**
     * 交易方式
     */
    tradingmanner_weixin(1,"微信"),
    tradingmanner_alipay(2,"支付宝"),
    tradingmanner_unionpay(3,"银联"),
    tradingmanner_balance(4,"余额扣款"),
    tradingmanner_divide(5,"分成收入"),

    /**
     * 交易类型
     */
    tradingtype_recharge(1,"充值"),
    tradingtype_consume(2,"消费"),
    tradingtype_withdraw(3,"提现"),
    tradingtype_divide(4,"分成收入"),

    /**
     *结算状态
     */
    settlementstatus_No(1,"未结算"),
    settlementstatus_Yes(2,"已结算");

    BizConstantEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
