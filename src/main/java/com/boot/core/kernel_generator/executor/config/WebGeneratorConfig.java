package com.boot.core.kernel_generator.executor.config;

import cn.hutool.core.util.StrUtil;
import com.boot.core.kernel_generator.executor.model.GenQo;
import com.boot.core.kernel_core.util.ToolUtil;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;

public class WebGeneratorConfig extends AbstractGeneratorConfig {
    private GenQo genQo;

    public WebGeneratorConfig(GenQo genQo) {
        this.genQo = genQo;
    }


    protected void config() {
        this.dataSourceConfig.setDbType(DbType.MYSQL);
        this.dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        this.dataSourceConfig.setUsername(this.genQo.getUserName());
        this.dataSourceConfig.setPassword(this.genQo.getPassword());
        this.dataSourceConfig.setUrl(this.genQo.getUrl());


        this.globalConfig.setOutputDir(this.genQo.getProjectPath() + File.separator + "src" + File.separator + "main" + File.separator + "java");
        this.globalConfig.setFileOverride(true);
        this.globalConfig.setEnableCache(false);
        this.globalConfig.setBaseResultMap(true);
        this.globalConfig.setBaseColumnList(true);
        this.globalConfig.setOpen(false);
        this.globalConfig.setAuthor(this.genQo.getAuthor());
        this.contextConfig.setProPackage(this.genQo.getProjectPackage());
        this.contextConfig.setCoreBasePackage(this.genQo.getCorePackage());


        if (this.genQo.getIgnoreTabelPrefix() != null) {
            this.strategyConfig.setTablePrefix(new String[]{this.genQo.getIgnoreTabelPrefix()});
        }
        this.strategyConfig.setInclude(new String[]{this.genQo.getTableName()});
        this.strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        this.packageConfig.setParent(null);
        this.packageConfig.setEntity(this.genQo.getProjectPackage() + ".modular.system.model");
        this.packageConfig.setMapper(this.genQo.getProjectPackage() + ".modular.system.dao");
        this.packageConfig.setXml(this.genQo.getProjectPackage() + ".modular.system.dao.mapping");


        this.contextConfig.setBizChName(this.genQo.getBizName());
        this.contextConfig.setModuleName(this.genQo.getModuleName());
        this.contextConfig.setProjectPath(this.genQo.getProjectPath());
        if (ToolUtil.isEmpty(this.genQo.getIgnoreTabelPrefix())) {
            String entityName = StrUtil.toCamelCase(this.genQo.getTableName());
            this.contextConfig.setEntityName(StrUtil.upperFirst(entityName));
            this.contextConfig.setBizEnName(StrUtil.lowerFirst(entityName));
        } else {
            String entiyName = StrUtil.toCamelCase(StrUtil.removePrefix(this.genQo.getTableName(), this.genQo.getIgnoreTabelPrefix()));
            this.contextConfig.setEntityName(StrUtil.upperFirst(entiyName));
            this.contextConfig.setBizEnName(StrUtil.lowerFirst(entiyName));
        }
        this.sqlConfig.setParentMenuName(this.genQo.getParentMenuName());


        this.contextConfig.setEntitySwitch(this.genQo.getEntitySwitch());
        this.contextConfig.setDaoSwitch(this.genQo.getDaoSwitch());
        this.contextConfig.setServiceSwitch(this.genQo.getServiceSwitch());


        this.contextConfig.setControllerSwitch(this.genQo.getControllerSwitch());
        this.contextConfig.setIndexPageSwitch(this.genQo.getIndexPageSwitch());
        this.contextConfig.setAddPageSwitch(this.genQo.getAddPageSwitch());
        this.contextConfig.setEditPageSwitch(this.genQo.getEditPageSwitch());
        this.contextConfig.setJsSwitch(this.genQo.getJsSwitch());
        this.contextConfig.setInfoJsSwitch(this.genQo.getInfoJsSwitch());
        this.contextConfig.setSqlSwitch(this.genQo.getSqlSwitch());
    }
}
