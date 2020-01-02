package com.boot.core.kernel_generator.engine.base;

import com.boot.core.kernel_generator.engine.config.ContextConfig;
import com.boot.core.kernel_generator.engine.config.ControllerConfig;
import com.boot.core.kernel_generator.engine.config.DaoConfig;
import com.boot.core.kernel_generator.engine.config.PageConfig;
import com.boot.core.kernel_generator.engine.config.ServiceConfig;
import com.boot.core.kernel_generator.engine.config.SqlConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

public class AbstractTemplateEngine {
    protected ContextConfig contextConfig;
    protected ControllerConfig controllerConfig;
    protected PageConfig pageConfig;
    protected DaoConfig daoConfig;
    protected ServiceConfig serviceConfig;
    protected SqlConfig sqlConfig;
    protected TableInfo tableInfo;

    public void initConfig() {
        if (this.contextConfig == null) {
            this.contextConfig = new ContextConfig();
        }
        if (this.controllerConfig == null) {
            this.controllerConfig = new ControllerConfig();
        }
        if (this.pageConfig == null) {
            this.pageConfig = new PageConfig();
        }
        if (this.daoConfig == null) {
            this.daoConfig = new DaoConfig();
        }
        if (this.serviceConfig == null) {
            this.serviceConfig = new ServiceConfig();
        }
        if (this.sqlConfig == null) {
            this.sqlConfig = new SqlConfig();
        }
        this.contextConfig.init();

        this.controllerConfig.setContextConfig(this.contextConfig);
        this.controllerConfig.init();

        this.serviceConfig.setContextConfig(this.contextConfig);
        this.serviceConfig.init();

        this.daoConfig.setContextConfig(this.contextConfig);
        this.daoConfig.init();

        this.pageConfig.setContextConfig(this.contextConfig);
        this.pageConfig.init();

        this.sqlConfig.setContextConfig(this.contextConfig);
        this.sqlConfig.init();
    }

    public PageConfig getPageConfig() {
        return this.pageConfig;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    public ContextConfig getContextConfig() {
        return this.contextConfig;
    }

    public void setContextConfig(ContextConfig contextConfig) {
        this.contextConfig = contextConfig;
    }

    public ControllerConfig getControllerConfig() {
        return this.controllerConfig;
    }

    public void setControllerConfig(ControllerConfig controllerConfig) {
        this.controllerConfig = controllerConfig;
    }

    public DaoConfig getDaoConfig() {
        return this.daoConfig;
    }

    public void setDaoConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    public ServiceConfig getServiceConfig() {
        return this.serviceConfig;
    }

    public void setServiceConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    public SqlConfig getSqlConfig() {
        return this.sqlConfig;
    }

    public void setSqlConfig(SqlConfig sqlConfig) {
        this.sqlConfig = sqlConfig;
    }

    public TableInfo getTableInfo() {
        return this.tableInfo;
    }

    public void setTableInfo(TableInfo tableInfo) {
        this.tableInfo = tableInfo;
    }
}

