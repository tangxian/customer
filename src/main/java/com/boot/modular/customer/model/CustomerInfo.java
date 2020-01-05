package com.boot.modular.customer.model;

import com.boot.core.common.annotion.ExcelColumn;

/**
 * 车辆抵押客户模板数据
 */
public class CustomerInfo {
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

    @ExcelColumn(value = "房产信息", col = 4)
    private String houseinfo;

    @ExcelColumn(value = "车辆信息", col = 5)
    private String carinfo;

    @ExcelColumn(value = "保单信息", col = 5)
    private String insurance;

    @ExcelColumn(value = "社保公积金", col = 5)
    private String sbgjj;

    @ExcelColumn(value = "营业执照", col = 5)
    private String businesslicense;

    @ExcelColumn(value = "其他信息", col = 5)
    private String otherinfo;

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

    public String getHouseinfo() {
        return houseinfo;
    }

    public void setHouseinfo(String houseinfo) {
        this.houseinfo = houseinfo;
    }

    public String getCarinfo() {
        return carinfo;
    }

    public void setCarinfo(String carinfo) {
        this.carinfo = carinfo;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getSbgjj() {
        return sbgjj;
    }

    public void setSbgjj(String sbgjj) {
        this.sbgjj = sbgjj;
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    @Override
    public String toString() {
        return "CustomerCarInfo{" +
                "customername='" + customername + '\'' +
                ", mobile='" + mobile + '\'' +
                ", idcard='" + idcard + '\'' +
                ", carid='" + carid + '\'' +
                ", cartype='" + cartype + '\'' +
                ", houseinfo='" + houseinfo + '\'' +
                ", carinfo='" + carinfo + '\'' +
                ", insurance='" + insurance + '\'' +
                ", sbgjj='" + sbgjj + '\'' +
                ", businesslicense='" + businesslicense + '\'' +
                ", otherinfo='" + otherinfo + '\'' +
                '}';
    }
}
