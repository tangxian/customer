package com.boot.core.common.constant;

/**
 * @author TANGXIAN
 * @Description 所有业务的枚举
 */
public enum BizConstantEnum {

    /**
     * 客户类型
     */
    customertype_car(1,"车辆抵押客户"),
    customertype_house(2,"房产抵押客户"),
    customertype_other(3,"其他"),

    /**
     * 客户状态
     */
    customerstatus_not(1,"非意向"),
    customerstatus_has(2,"意向"),
    customerstatus_success(3,"成交"),
    customerstatus_del(4,"已删除"),

    /**
     * 数据来源
     */
    datasources_sys(1,"系统录入"),
    datasources_excel(2,"Excel导入"),

    /**
     * 状态
     */
    enabled(1,"启用"),
    disabled(2,"禁用");

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
