package com.boot.core.kernel_generator.modular.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;


@com.baomidou.mybatisplus.annotations.TableName("code_dbinfo")
public class CodeDbInfo
        implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @com.baomidou.mybatisplus.annotations.TableId("id")
    private Long id;
    private String name;
    @TableField("db_driver")
    private String dbDriver;
    @TableField("db_url")
    private String dbUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CodeDbInfo)) return false;
        CodeDbInfo other = (CodeDbInfo) o;
        if (!other.canEqual(this)) return false;
        Object this$id = getId();
        Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        Object this$name = getName();
        Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        Object this$dbDriver = getDbDriver();
        Object other$dbDriver = other.getDbDriver();
        if (this$dbDriver == null ? other$dbDriver != null : !this$dbDriver.equals(other$dbDriver)) return false;
        Object this$dbUrl = getDbUrl();
        Object other$dbUrl = other.getDbUrl();
        if (this$dbUrl == null ? other$dbUrl != null : !this$dbUrl.equals(other$dbUrl)) return false;
        Object this$dbUserName = getDbUserName();
        Object other$dbUserName = other.getDbUserName();
        if (this$dbUserName == null ? other$dbUserName != null : !this$dbUserName.equals(other$dbUserName))
            return false;
        Object this$dbPassword = getDbPassword();
        Object other$dbPassword = other.getDbPassword();
        if (this$dbPassword == null ? other$dbPassword != null : !this$dbPassword.equals(other$dbPassword))
            return false;
        Object this$dbType = getDbType();
        Object other$dbType = other.getDbType();
        if (this$dbType == null ? other$dbType != null : !this$dbType.equals(other$dbType)) return false;
        Object this$createTime = getCreateTime();
        Object other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime))
            return false;
        Object this$updateTime = getUpdateTime();
        Object other$updateTime = other.getUpdateTime();
        return this$updateTime == null ? other$updateTime == null : this$updateTime.equals(other$updateTime);
    }

    protected boolean canEqual(Object other) {
        return other instanceof CodeDbInfo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $dbDriver = getDbDriver();
        result = result * 59 + ($dbDriver == null ? 43 : $dbDriver.hashCode());
        Object $dbUrl = getDbUrl();
        result = result * 59 + ($dbUrl == null ? 43 : $dbUrl.hashCode());
        Object $dbUserName = getDbUserName();
        result = result * 59 + ($dbUserName == null ? 43 : $dbUserName.hashCode());
        Object $dbPassword = getDbPassword();
        result = result * 59 + ($dbPassword == null ? 43 : $dbPassword.hashCode());
        Object $dbType = getDbType();
        result = result * 59 + ($dbType == null ? 43 : $dbType.hashCode());
        Object $createTime = getCreateTime();
        result = result * 59 + ($createTime == null ? 43 : $createTime.hashCode());
        Object $updateTime = getUpdateTime();
        result = result * 59 + ($updateTime == null ? 43 : $updateTime.hashCode());
        return result;
    }

    public String toString() {
        return "CodeDbInfo(id=" + getId() + ", name=" + getName() + ", dbDriver=" + getDbDriver() + ", dbUrl=" + getDbUrl() + ", dbUserName=" + getDbUserName() + ", dbPassword=" + getDbPassword() + ", dbType=" + getDbType() + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime() + ")";
    }

    @TableField("db_user_name")
    private String dbUserName;
    @TableField("db_password")
    private String dbPassword;

    public Long getId() {
        return this.id;
    }


    public String getName() {
        return this.name;
    }

    @TableField("db_type")
    private String dbType;

    public String getDbDriver() {
        return this.dbDriver;
    }

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    public String getDbUrl() {
        return this.dbUrl;
    }

    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    public String getDbUserName() {
        return this.dbUserName;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }

    public String getDbType() {
        return this.dbType;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }
}


/* Location:              D:\jars\guns-kernel_generator-1.0.0.jar!\cn\stylefeng\guns\kernel_generator\modular\entity\CodeDbInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */