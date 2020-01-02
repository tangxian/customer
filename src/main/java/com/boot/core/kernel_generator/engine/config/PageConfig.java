package com.boot.core.kernel_generator.engine.config;


public class PageConfig {
    private ContextConfig contextConfig;

    private String pagePathTemplate;

    private String pageAddPathTemplate;

    private String pageEditPathTemplate;

    private String pageJsPathTemplate;

    private String pageInfoJsPathTemplate;

    public void init() {
        this.pagePathTemplate = ("/src/main/webapp/WEB-INF/view/" + this.contextConfig.getModuleName() + "/{}/{}.html");
        this.pageAddPathTemplate = ("/src/main/webapp/WEB-INF/view/" + this.contextConfig.getModuleName() + "/{}/{}_add.html");
        this.pageEditPathTemplate = ("/src/main/webapp/WEB-INF/view/" + this.contextConfig.getModuleName() + "/{}/{}_edit.html");
        this.pageJsPathTemplate = ("/src/main/webapp/static/modular/" + this.contextConfig.getModuleName() + "/{}/{}.js");
        this.pageInfoJsPathTemplate = ("/src/main/webapp/static/modular/" + this.contextConfig.getModuleName() + "/{}/{}_info.js");
    }

    public String getPagePathTemplate() {
        return this.pagePathTemplate;
    }

    public void setPagePathTemplate(String pagePathTemplate) {
        this.pagePathTemplate = pagePathTemplate;
    }

    public String getPageJsPathTemplate() {
        return this.pageJsPathTemplate;
    }

    public void setPageJsPathTemplate(String pageJsPathTemplate) {
        this.pageJsPathTemplate = pageJsPathTemplate;
    }

    public String getPageAddPathTemplate() {
        return this.pageAddPathTemplate;
    }

    public void setPageAddPathTemplate(String pageAddPathTemplate) {
        this.pageAddPathTemplate = pageAddPathTemplate;
    }

    public String getPageEditPathTemplate() {
        return this.pageEditPathTemplate;
    }

    public void setPageEditPathTemplate(String pageEditPathTemplate) {
        this.pageEditPathTemplate = pageEditPathTemplate;
    }

    public String getPageInfoJsPathTemplate() {
        return this.pageInfoJsPathTemplate;
    }

    public void setPageInfoJsPathTemplate(String pageInfoJsPathTemplate) {
        this.pageInfoJsPathTemplate = pageInfoJsPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return this.contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
