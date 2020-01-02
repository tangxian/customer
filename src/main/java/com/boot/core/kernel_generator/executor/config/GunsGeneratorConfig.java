package com.boot.core.kernel_generator.executor.config;

import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GunsGeneratorConfig extends AbstractGeneratorConfig {
    protected void globalConfig() {
        this.globalConfig.setOutputDir("D:\\ttt");
        this.globalConfig.setFileOverride(true);
        this.globalConfig.setEnableCache(false);
        this.globalConfig.setBaseResultMap(true);
        this.globalConfig.setBaseColumnList(true);
        this.globalConfig.setOpen(false);
        this.globalConfig.setAuthor("admin");
    }

    protected void dataSourceConfig() {
        this.dataSourceConfig.setDbType(com.baomidou.mybatisplus.generator.config.rules.DbType.MYSQL);
        this.dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        this.dataSourceConfig.setUsername("root");
        this.dataSourceConfig.setPassword("root");
        this.dataSourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/guns?characterEncoding=utf8");
    }

    protected void strategyConfig() {
        this.strategyConfig.setTablePrefix(new String[]{"sys_"});
        this.strategyConfig.setNaming(NamingStrategy.underline_to_camel);
    }

    protected void packageConfig() {
        this.packageConfig.setParent(null);
        this.packageConfig.setEntity("com.boot.modular.system.model");
        this.packageConfig.setMapper("com.boot.modular.system.dao");
        this.packageConfig.setXml("com.boot.modular.system.dao.mapping");
    }

    protected void contextConfig() {
        this.contextConfig.setProPackage("com.boot.admin");
        this.contextConfig.setCoreBasePackage("com.boot.core");
        this.contextConfig.setBizChName("字典管理");
        this.contextConfig.setBizEnName("sysDict");
        this.contextConfig.setModuleName("system");
        this.contextConfig.setProjectPath("D:\\ideaSpace\\guns\\guns-admin");
        this.contextConfig.setEntityName("SysDict");
        this.sqlConfig.setParentMenuName(null);


        this.contextConfig.setEntitySwitch(Boolean.valueOf(true));
        this.contextConfig.setDaoSwitch(Boolean.valueOf(true));
        this.contextConfig.setServiceSwitch(Boolean.valueOf(true));


        this.contextConfig.setControllerSwitch(Boolean.valueOf(true));
        this.contextConfig.setIndexPageSwitch(Boolean.valueOf(true));
        this.contextConfig.setAddPageSwitch(Boolean.valueOf(true));
        this.contextConfig.setEditPageSwitch(Boolean.valueOf(true));
        this.contextConfig.setJsSwitch(Boolean.valueOf(true));
        this.contextConfig.setInfoJsSwitch(Boolean.valueOf(true));
        this.contextConfig.setSqlSwitch(Boolean.valueOf(true));
    }

    protected void config() {
        globalConfig();
        dataSourceConfig();
        strategyConfig();
        packageConfig();
        contextConfig();
    }
}
