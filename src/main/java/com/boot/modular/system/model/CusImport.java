package com.boot.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 数据导入表
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-05-09
 */
@TableName("cus_import")
public class CusImport extends Model<CusImport> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 导入数据流水号
     */
    private String importnumber;
    /**
     * 导入数据备注
     */
    private String importremark;
    /**
     * 导入时间
     */
    private Date importdate;
    /**
     * 导入人id
     */
    private Integer importuserid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getImportdate() {
        return importdate;
    }

    public void setImportdate(Date importdate) {
        this.importdate = importdate;
    }

    public Integer getImportuserid() {
        return importuserid;
    }

    public void setImportuserid(Integer importuserid) {
        this.importuserid = importuserid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CusImport{" +
        ", id=" + id +
        ", importnumber=" + importnumber +
        ", importremark=" + importremark +
        ", importdate=" + importdate +
        ", importuserid=" + importuserid +
        "}";
    }
}
