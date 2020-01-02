package com.boot.core.kernel_generator.engine.config;

import java.util.ArrayList;
import java.util.List;


public class ControllerConfig {
    private ContextConfig contextConfig;
    private String controllerPathTemplate;
    private String packageName;
    private List<String> imports;

    public void init() {
        ArrayList<String> imports = new ArrayList();
        imports.add("com.boot.core.kernel_core.base.controller");
        imports.add("org.springframework.stereotype.Controller");
        imports.add("org.springframework.web.bind.annotation.RequestMapping");
        imports.add("org.springframework.web.bind.annotation.ResponseBody");
        imports.add("org.springframework.ui.Model");
        imports.add("org.springframework.web.bind.annotation.PathVariable");
        imports.add("org.springframework.beans.factory.annotation.Autowired");
        imports.add(this.contextConfig.getProPackage() + ".core.log.LogObjectHolder");
        imports.add("org.springframework.web.bind.annotation.RequestParam");
        imports.add(this.contextConfig.getModelPackageName() + "." + this.contextConfig.getEntityName());
        imports.add(this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".service.I" + this.contextConfig.getEntityName() + "Service");
        this.imports = imports;
        this.packageName = (this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".controller");
        this.controllerPathTemplate = ("/src/main/java/" + this.contextConfig.getProPackage().replaceAll("\\.", "/") + "/modular/" + this.contextConfig.getModuleName() + "/controller/{}Controller.java");
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImports() {
        return this.imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getControllerPathTemplate() {
        return this.controllerPathTemplate;
    }

    public void setControllerPathTemplate(String controllerPathTemplate) {
        this.controllerPathTemplate = controllerPathTemplate;
    }

    public ContextConfig getContextConfig() {
        return this.contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }
}
