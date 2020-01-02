package com.boot.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ZOUTAO
 * @since 2019-08-01
 */
@TableName("dev_login_log")
public class DevLoginLog extends Model<DevLoginLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 应用id
     */
    private Integer appid;
    /**
     * 用户id
     */
    private String devuuid;
    /**
     * 登录时间
     */
    private Date logindate;
    /**
     * 设备唯一标识
     */
    private String deviceid;
    /**
     * 设备系统版本
     */
    private String deviceversion;
    /**
     * 设备制造商
     */
    private String devicemerchant;
    /**
     * 网络类型
     */
    private String nettype;
    /**
     * 手机型号
     */
    private String devicetype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getDevuuid() {
        return devuuid;
    }

    public void setDevuuid(String devuuid) {
        this.devuuid = devuuid;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getDeviceversion() {
        return deviceversion;
    }

    public void setDeviceversion(String deviceversion) {
        this.deviceversion = deviceversion;
    }

    public String getDevicemerchant() {
        return devicemerchant;
    }

    public void setDevicemerchant(String devicemerchant) {
        this.devicemerchant = devicemerchant;
    }

    public String getNettype() {
        return nettype;
    }

    public void setNettype(String nettype) {
        this.nettype = nettype;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DevLoginLog{" +
                "id=" + id +
                ", appid=" + appid +
                ", devuuid='" + devuuid + '\'' +
                ", logindate=" + logindate +
                ", deviceid='" + deviceid + '\'' +
                ", deviceversion='" + deviceversion + '\'' +
                ", devicemerchant='" + devicemerchant + '\'' +
                ", nettype='" + nettype + '\'' +
                ", devicetype='" + devicetype + '\'' +
                '}';
    }
}
