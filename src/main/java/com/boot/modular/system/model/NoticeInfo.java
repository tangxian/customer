package com.boot.modular.system.model;

import com.boot.core.common.annotion.ExcelColumn;

import java.util.Date;

public class NoticeInfo {
    /**
     * 标题
     */
    @ExcelColumn(value = "标题", col = 1)
    private String title;
    /**
     * 类型
     */
    @ExcelColumn(value = "类型", col = 2)
    private Integer type;
    /**
     * 内容
     */
    @ExcelColumn(value = "内容", col = 3)
    private String content;
    /**
     * 创建时间
     */
    @ExcelColumn(value = "创建时间", col = 4)
    private String createtime;
    /**
     * 创建人
     */
    @ExcelColumn(value = "创建人", col = 5)
    private Integer creater;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    @Override
    public String toString() {
        return "NoticeInfo{" +
                "title='" + title + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", creater=" + creater +
                '}';
    }
}
