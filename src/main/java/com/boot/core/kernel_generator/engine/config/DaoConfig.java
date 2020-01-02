package com.boot.core.kernel_generator.engine.config;


public class DaoConfig {
    private ContextConfig contextConfig;


    private String daoPathTemplate;

    private String xmlPathTemplate;

    private String packageName;


    public void init() {
        this.daoPathTemplate = ("/src/main/java/" + this.contextConfig.getProPackage().replaceAll("\\.", "/") + "/modular/" + this.contextConfig.getModuleName() + "/dao/{}Dao.java");
        this.xmlPathTemplate = ("/src/main/java/" + this.contextConfig.getProPackage().replaceAll("\\.", "/") + "/modular/" + this.contextConfig.getModuleName() + "/dao/mapping/{}Dao.xml");
        this.packageName = (this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".dao");
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDaoPathTemplate() {
        return this.daoPathTemplate;
    }

    public void setDaoPathTemplate(String daoPathTemplate) {
        this.daoPathTemplate = daoPathTemplate;
    }

    public String getXmlPathTemplate() {
        return this.xmlPathTemplate;
    }

    public void setXmlPathTemplate(String xmlPathTemplate) {
        this.xmlPathTemplate = xmlPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return this.contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}