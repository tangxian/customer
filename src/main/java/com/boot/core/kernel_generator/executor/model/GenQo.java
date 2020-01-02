package com.boot.core.kernel_generator.executor.model;


public class GenQo {
    private String userName;


    private String password;


    private String url;


    private String projectPath;


    private String author;


    private String projectPackage;


    private String corePackage;


    private String tableName;


    private String ignoreTabelPrefix;


    private String bizName;


    private String moduleName;


    private String parentMenuName;


    private Boolean controllerSwitch = Boolean.valueOf(false);


    private Boolean indexPageSwitch = Boolean.valueOf(false);


    private Boolean addPageSwitch = Boolean.valueOf(false);


    private Boolean editPageSwitch = Boolean.valueOf(false);


    private Boolean jsSwitch = Boolean.valueOf(false);


    private Boolean infoJsSwitch = Boolean.valueOf(false);


    private Boolean daoSwitch = Boolean.valueOf(false);


    private Boolean serviceSwitch = Boolean.valueOf(false);


    private Boolean entitySwitch = Boolean.valueOf(false);


    private Boolean sqlSwitch = Boolean.valueOf(false);

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectPath() {
        return this.projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCorePackage() {
        return this.corePackage;
    }

    public void setCorePackage(String corePackage) {
        this.corePackage = corePackage;
    }

    public String getProjectPackage() {
        return this.projectPackage;
    }

    public void setProjectPackage(String projectPackage) {
        this.projectPackage = projectPackage;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIgnoreTabelPrefix() {
        return this.ignoreTabelPrefix;
    }

    public void setIgnoreTabelPrefix(String ignoreTabelPrefix) {
        this.ignoreTabelPrefix = ignoreTabelPrefix;
    }

    public String getBizName() {
        return this.bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
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

    public String getParentMenuName() {
        return this.parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }
}