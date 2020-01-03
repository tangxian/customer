package com.boot.modular.customer.model;

import com.boot.core.common.annotion.ExcelColumn;

/**
 * 车辆抵押客户模板数据
 */
public class CustomerCarInfo {
    @ExcelColumn(value = "姓名", col = 1)
    private String customername;

    @ExcelColumn(value = "电话", col = 2)
    private String mobile;

    @ExcelColumn(value = "身份证号码", col = 3)
    private String idcard;

    @ExcelColumn(value = "车牌", col = 4)
    private String carid;

    @ExcelColumn(value = "车型", col = 5)
    private String cartype;

    @ExcelColumn(value = "意向客户", col = 5)
    private String customerstatus;

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getCustomerstatus() {
        return customerstatus;
    }

    public void setCustomerstatus(String customerstatus) {
        this.customerstatus = customerstatus;
    }

    @Override
    public String toString() {
        return "CustomerCarInfo{" +
                "customername='" + customername + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idcard='" + idcard + '\'' +
                ", carid='" + carid + '\'' +
                ", cartype='" + cartype + '\'' +
                ", customerstatus='" + customerstatus + '\'' +
                '}';
    }
}
