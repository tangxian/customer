package com.boot.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户表
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
@TableName("cus_customer")
public class Customer extends Model<Customer> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 客户姓名
     */
    private String customername;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 身份证号码
     */
    private String idcard;
    /**
     * 房产信息
     */
    private String houseinfo;
    /**
     * 车辆信息
     */
    private String carinfo;
    /**
     * 社保公积金
     */
    private String sbgjj;
    /**
     * 保单信息
     */
    private String insurance;
    /**
     * 营业执照
     */
    private String businesslicense;
    /**
     * 车牌
     */
    private String carid;
    /**
     * 车型
     */
    private String cartype;
    /**
     * 其他信息
     */
    private String otherinfo;
    /**
     * 客户类型(1.车辆抵押客户、2.房产抵押客户、3.其他)
     */
    private Integer customertype;
    /**
     * 客户状态(1.非意向、2.意向、3.成交、4.删除)
     */
    private Integer customerstatus;
    /**
     * 创建时间
     */
    private Date createdate;
    /**
     * 创建人id
     */
    private Integer createuserid;
    /**
     * 数据来源(1.系统添加、2.Excel导入)
     */
    private Integer datasources;
    /**
     * 导入数据流水号
     */
    private String importnumber;
    /**
     * 导入数据备注
     */
    private String importremark;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSbgjj() {
        return sbgjj;
    }

    public void setSbgjj(String sbgjj) {
        this.sbgjj = sbgjj;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getBusinesslicense() {
        return businesslicense;
    }

    public void setBusinesslicense(String businesslicense) {
        this.businesslicense = businesslicense;
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

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }

    public Integer getCustomertype() {
        return customertype;
    }

    public void setCustomertype(Integer customertype) {
        this.customertype = customertype;
    }

    public Integer getCustomerstatus() {
        return customerstatus;
    }

    public void setCustomerstatus(Integer customerstatus) {
        this.customerstatus = customerstatus;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Integer createuserid) {
        this.createuserid = createuserid;
    }

    public Integer getDatasources() {
        return datasources;
    }

    public void setDatasources(Integer datasources) {
        this.datasources = datasources;
    }

    public String getImportnumber() {
        return importnumber;
    }

    public void setImportnumber(String importnumber) {
        this.importnumber = importnumber;
    }

    public String getImportremark() {
        return importremark;
    }

    public void setImportremark(String importremark) {
        this.importremark = importremark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Customer{" +
        ", id=" + id +
        ", customername=" + customername +
        ", mobile=" + mobile +
        ", idcard=" + idcard +
        ", houseinfo=" + houseinfo +
        ", carinfo=" + carinfo +
        ", sbgjj=" + sbgjj +
        ", insurance=" + insurance +
        ", businesslicense=" + businesslicense +
        ", carid=" + carid +
        ", cartype=" + cartype +
        ", otherinfo=" + otherinfo +
        ", customertype=" + customertype +
        ", customerstatus=" + customerstatus +
        ", createdate=" + createdate +
        ", createuserid=" + createuserid +
        ", datasources=" + datasources +
        ", importnumber=" + importnumber +
        ", importremark=" + importremark +
        "}";
    }
}
