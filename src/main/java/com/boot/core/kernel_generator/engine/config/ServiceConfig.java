package com.boot.core.kernel_generator.engine.config;

import java.util.ArrayList;
import java.util.List;


public class ServiceConfig {
    private ContextConfig contextConfig;
    private String servicePathTemplate;
    private String serviceImplPathTemplate;
    private String packageName;
    private List<String> serviceInterfaceImports;
    private List<String> serviceImplImports;

    public void init() {
        ArrayList<String> imports = new ArrayList();
        imports.add("org.springframework.stereotype.Service");
        imports.add("com.baomidou.mybatisplus.service.impl.ServiceImpl");
        imports.add(this.contextConfig.getModelPackageName() + "." + this.contextConfig.getEntityName());
        imports.add(this.contextConfig.getModelMapperPackageName() + "." + this.contextConfig.getEntityName() + "Mapper");
        imports.add(this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".service.I" + this.contextConfig.getBizEnBigName() + "Service");
        this.serviceImplImports = imports;

        ArrayList<String> interfaceImports = new ArrayList();
        interfaceImports.add("com.baomidou.mybatisplus.service.IService");
        interfaceImports.add(this.contextConfig.getModelPackageName() + "." + this.contextConfig.getEntityName());
        this.serviceInterfaceImports = interfaceImports;

        this.servicePathTemplate = ("/src/main/java/" + this.contextConfig.getProPackage().replaceAll("\\.", "/") + "/modular/" + this.contextConfig.getModuleName() + "/service/I{}Service.java");
        this.serviceImplPathTemplate = ("/src/main/java/" + this.contextConfig.getProPackage().replaceAll("\\.", "/") + "/modular/" + this.contextConfig.getModuleName() + "/service/impl/{}ServiceImpl.java");
        this.packageName = (this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".service");
    }

    public String getServicePathTemplate() {
        return this.servicePathTemplate;
    }

    public void setServicePathTemplate(String servicePathTemplate) {
        this.servicePathTemplate = servicePathTemplate;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getServiceImplPathTemplate() {
        return this.serviceImplPathTemplate;
    }

    public void setServiceImplPathTemplate(String serviceImplPathTemplate) {
        this.serviceImplPathTemplate = serviceImplPathTemplate;
    }

    public List<String> getServiceImplImports() {
        return this.serviceImplImports;
    }

    public void setServiceImplImports(List<String> serviceImplImports) {
        this.serviceImplImports = serviceImplImports;
    }

    public ContextConfig getContextConfig() {
        return this.contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public List<String> getServiceInterfaceImports() {
        return this.serviceInterfaceImports;
    }

    public void setServiceInterfaceImports(List<String> serviceInterfaceImports) {
        this.serviceInterfaceImports = serviceInterfaceImports;
    }
}
