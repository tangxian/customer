package com.boot.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 客户跟进表
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
@TableName("cus_follow")
public class CusFollow extends Model<CusFollow> {

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
     * 跟进时间
     */
    private Date followdate;
    /**
     * 用户id(跟进客户的销售经理)
     */
    private Integer userid;
    /**
     * 备注
     */
    private String remark;

    /**
     * 跟进人姓名
     */
    private String username;

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

    public Date getFollowdate() {
        return followdate;
    }

    public void setFollowdate(Date followdate) {
        this.followdate = followdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CusFollow{" +
        ", id=" + id +
        ", customerid=" + customerid +
        ", followdate=" + followdate +
        ", userid=" + userid +
        ", remark=" + remark +
        ", username=" + username +
        "}";
    }
}
