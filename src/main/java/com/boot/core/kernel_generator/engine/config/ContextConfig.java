package com.boot.core.kernel_generator.engine.config;

import cn.hutool.core.util.StrUtil;


public class ContextConfig {
    private String templatePrefixPath = "gunsTemplate/advanced";
    private String projectPath = "D:\\ideaSpace\\guns";
    private String bizChName;
    private String bizEnName;
    private String bizEnBigName;
    private String moduleName = "system";

    private String proPackage = "com.boot.admin";
    private String coreBasePackage = "com.boot.core";
    private String modelPackageName = "com.boot.modular.system.model";
    private String modelMapperPackageName = "com.boot.modular.system.dao";

    private String entityName;
    private Boolean controllerSwitch = Boolean.valueOf(true);
    private Boolean indexPageSwitch = Boolean.valueOf(true);
    private Boolean addPageSwitch = Boolean.valueOf(true);
    private Boolean editPageSwitch = Boolean.valueOf(true);
    private Boolean jsSwitch = Boolean.valueOf(true);
    private Boolean infoJsSwitch = Boolean.valueOf(true);
    private Boolean daoSwitch = Boolean.valueOf(true);
    private Boolean serviceSwitch = Boolean.valueOf(true);
    private Boolean entitySwitch = Boolean.valueOf(true);
    private Boolean sqlSwitch = Boolean.valueOf(true);

    public void init() {
        if (this.entityName == null) {
            this.entityName = this.bizEnBigName;
        }
        this.modelPackageName = (this.proPackage + ".modular.system.model");
        this.modelMapperPackageName = (this.proPackage + ".modular.system.dao");
    }

    public String getBizEnBigName() {
        return this.bizEnBigName;
    }

    public void setBizEnBigName(String bizEnBigName) {
        this.bizEnBigName = bizEnBigName;
    }

    public String getBizChName() {
        return this.bizChName;
    }

    public void setBizChName(String bizChName) {
        this.bizChName = bizChName;
    }

    public String getBizEnName() {
        return this.bizEnName;
    }

    public void setBizEnName(String bizEnName) {
        this.bizEnName = bizEnName;
        this.bizEnBigName = StrUtil.upperFirst(this.bizEnName);
    }

    public String getProjectPath() {
        return this.projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Boolean getControllerSwitch() {
        return this.controllerSwitch;
    }

    public void setControllerSwitch(Boolean controllerSwitch) {
        this.controllerSwitch = controllerSwitch;
    }

    public Boolean getIndexPageSwitch() {
        return this.indexPageSwitch;
    }

    public void setIndexPageSwitch(Boolean indexPageSwitch) {
        this.indexPageSwitch = indexPageSwitch;
    }

    public Boolean getAddPageSwitch() {
        return this.addPageSwitch;
    }

    public void setAddPageSwitch(Boolean addPageSwitch) {
        this.addPageSwitch = addPageSwitch;
    }

    public Boolean getEditPageSwitch() {
        return this.editPageSwitch;
    }

    public void setEditPageSwitch(Boolean editPageSwitch) {
        this.editPageSwitch = editPageSwitch;
    }

    public Boolean getJsSwitch() {
        return this.jsSwitch;
    }

    public void setJsSwitch(Boolean jsSwitch) {
        this.jsSwitch = jsSwitch;
    }

    public Boolean getInfoJsSwitch() {
        return this.infoJsSwitch;
    }

    public void setInfoJsSwitch(Boolean infoJsSwitch) {
        this.infoJsSwitch = infoJsSwitch;
    }

    public Boolean getDaoSwitch() {
        return this.daoSwitch;
    }

    public void setDaoSwitch(Boolean daoSwitch) {
        this.daoSwitch = daoSwitch;
    }

    public Boolean getServiceSwitch() {
        return this.serviceSwitch;
    }

    public void setServiceSwitch(Boolean serviceSwitch) {
        this.serviceSwitch = serviceSwitch;
    }

    public String getTemplatePrefixPath() {
        return this.templatePrefixPath;
    }

    public void setTemplatePrefixPath(String templatePrefixPath) {
        this.templatePrefixPath = templatePrefixPath;
    }

    public String getModelPackageName() {
        return this.modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getModelMapperPackageName() {
        return this.modelMapperPackageName;
    }

    public void setModelMapperPackageName(String modelMapperPackageName) {
        this.modelMapperPackageName = modelMapperPackageName;
    }

    public Boolean getEntitySwitch() {
        return this.entitySwitch;
    }

    public void setEntitySwitch(Boolean entitySwitch) {
        this.entitySwitch = entitySwitch;
    }

    public Boolean getSqlSwitch() {
        return this.sqlSwitch;
    }

    public void setSqlSwitch(Boolean sqlSwitch) {
        this.sqlSwitch = sqlSwitch;
    }

    public String getProPackage() {
        return this.proPackage;
    }

    public void setProPackage(String proPackage) {
        this.proPackage = proPackage;
    }

    public String getCoreBasePackage() {
        return this.coreBasePackage;
    }

    public void setCoreBasePackage(String coreBasePackage) {
        this.coreBasePackage = coreBasePackage;
    }
}
