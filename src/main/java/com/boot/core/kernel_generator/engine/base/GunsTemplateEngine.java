package com.boot.core.kernel_generator.engine.base;

import com.boot.core.kernel_core.util.ToolUtil;
import com.sun.javafx.PlatformUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;


public abstract class GunsTemplateEngine
        extends AbstractTemplateEngine {
    private GroupTemplate groupTemplate;

    public GunsTemplateEngine() {
        initBeetlEngine();
    }

    protected void initBeetlEngine() {
        Properties properties = new Properties();
        properties.put("RESOURCE.root", "");
        properties.put("DELIMITER_STATEMENT_START", "<%");
        properties.put("DELIMITER_STATEMENT_END", "%>");
        properties.put("HTML_TAG_FLAG", "##");
        Configuration cfg = null;
        try {
            cfg = new Configuration(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        this.groupTemplate = new GroupTemplate(resourceLoader, cfg);
        this.groupTemplate.registerFunctionPackage("tool", new ToolUtil());
    }

    protected void configTemplate(Template template) {
        template.binding("controller", this.controllerConfig);
        template.binding("context", this.contextConfig);
        template.binding("dao", this.daoConfig);
        template.binding("service", this.serviceConfig);
        template.binding("sqls", this.sqlConfig);
        template.binding("table", this.tableInfo);
    }

    protected void generateFile(String template, String filePath) {
        Template pageTemplate = this.groupTemplate.getTemplate(template);
        configTemplate(pageTemplate);
        if (PlatformUtil.isWindows()) {
            filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
        } else {
            filePath = filePath.replaceAll("/+|\\\\+", "/");
        }
        File file = new File(filePath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            pageTemplate.renderTo(fileOutputStream);
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        super.initConfig();


        if (this.contextConfig.getControllerSwitch().booleanValue()) {
            generateController();
        }
        if (this.contextConfig.getIndexPageSwitch().booleanValue()) {
            generatePageHtml();
        }
        if (this.contextConfig.getAddPageSwitch().booleanValue()) {
            generatePageAddHtml();
        }
        if (this.contextConfig.getEditPageSwitch().booleanValue()) {
            generatePageEditHtml();
        }
        if (this.contextConfig.getJsSwitch().booleanValue()) {
            generatePageJs();
        }
        if (this.contextConfig.getInfoJsSwitch().booleanValue()) {
            generatePageInfoJs();
        }
        if (this.contextConfig.getSqlSwitch().booleanValue()) {
            generateSqls();
        }
    }

    protected abstract void generatePageEditHtml();

    protected abstract void generatePageAddHtml();

    protected abstract void generatePageInfoJs();

    protected abstract void generatePageJs();

    protected abstract void generatePageHtml();

    protected abstract void generateController();

    protected abstract void generateSqls();
}
