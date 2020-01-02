package com.boot.core.kernel_generator.executor.config;

import cn.hutool.core.io.FileUtil;
import com.boot.core.kernel_generator.engine.SimpleTemplateEngine;
import com.boot.core.kernel_generator.engine.base.GunsTemplateEngine;
import com.boot.core.kernel_generator.engine.config.ContextConfig;
import com.boot.core.kernel_generator.engine.config.SqlConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.io.File;
import java.util.List;


public abstract class AbstractGeneratorConfig {
    GlobalConfig globalConfig = new GlobalConfig();

    DataSourceConfig dataSourceConfig = new DataSourceConfig();

    StrategyConfig strategyConfig = new StrategyConfig();

    PackageConfig packageConfig = new PackageConfig();

    TableInfo tableInfo = null;


    ContextConfig contextConfig = new ContextConfig();

    SqlConfig sqlConfig = new SqlConfig();

    protected abstract void config();

    public void init() {
        config();

        this.packageConfig.setService(this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".service");
        this.packageConfig.setServiceImpl(this.contextConfig.getProPackage() + ".modular." + this.contextConfig.getModuleName() + ".service.impl");


        this.packageConfig.setController("TTT");

        if (!this.contextConfig.getEntitySwitch().booleanValue()) {
            this.packageConfig.setEntity("TTT");
        }

        if (!this.contextConfig.getDaoSwitch().booleanValue()) {
            this.packageConfig.setMapper("TTT");
            this.packageConfig.setXml("TTT");
        }

        if (!this.contextConfig.getServiceSwitch().booleanValue()) {
            this.packageConfig.setService("TTT");
            this.packageConfig.setServiceImpl("TTT");
        }
    }


    public void destory() {
        String outputDir = this.globalConfig.getOutputDir() + "/TTT";
        FileUtil.del(new File(outputDir));
    }


    public void doMpGeneration() {
        init();
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(this.globalConfig);
        autoGenerator.setDataSource(this.dataSourceConfig);
        autoGenerator.setStrategy(this.strategyConfig);
        autoGenerator.setPackageInfo(this.packageConfig);
        autoGenerator.execute();
        destory();


        List<TableInfo> tableInfoList = autoGenerator.getConfig().getTableInfoList();
        if ((tableInfoList != null) && (tableInfoList.size() > 0)) {
            this.tableInfo = ((TableInfo) tableInfoList.get(0));
        }
    }

    public void doGunsGeneration() {
        GunsTemplateEngine GunsTemplateEngine = new SimpleTemplateEngine();
        GunsTemplateEngine.setContextConfig(this.contextConfig);
        this.sqlConfig.setConnection(this.dataSourceConfig.getConn());
        GunsTemplateEngine.setSqlConfig(this.sqlConfig);
        GunsTemplateEngine.setTableInfo(this.tableInfo);
        GunsTemplateEngine.start();
    }
}
