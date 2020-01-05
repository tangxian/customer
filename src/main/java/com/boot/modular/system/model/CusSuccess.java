package com.boot.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 客户成交表
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-05
 */
@TableName("cus_success")
public class CusSuccess extends Model<CusSuccess> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 客户id
     */
    private Integer customerid;
    /**
     * 成交时间
     */
    private Date successdate;
    /**
     * 成交用户id(成交客户的销售经理)
     */
    private Integer successuserid;
    /**
     * 成交银行
     */
    private String bank;
    /**
     * 贷款金额
     */
    private Double amount;
    /**
     * 备注
     */
    private String remark;

    /**
     * 状态(1.未审核、2.审核通过、3.审核未通过)
     */
    private Integer status;

    /**
     * 审核用户id(公司管理层或数据管理员)
     */
    private Integer checkuserid;

    /**
     * 审核日期
     */
    private Date checkdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Date getSuccessdate() {
        return successdate;
    }

    public void setSuccessdate(Date successdate) {
        this.successdate = successdate;
    }

    public Integer getSuccessuserid() {
        return successuserid;
    }

    public void setSuccessuserid(Integer successuserid) {
        this.successuserid = successuserid;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(Integer checkuserid) {
        this.checkuserid = checkuserid;
    }

    public Date getCheckdate() {
        return checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CusSuccess{" +
                "id=" + id +
                ", customerid=" + customerid +
                ", successdate=" + successdate +
                ", successuserid=" + successuserid +
                ", bank='" + bank + '\'' +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", checkuserid=" + checkuserid +
                ", checkdate=" + checkdate +
                '}';
    }
}
