package com.boot.core.kernel_generator.engine;

import cn.hutool.core.util.StrUtil;
import com.boot.core.kernel_generator.engine.base.GunsTemplateEngine;


public class SimpleTemplateEngine
        extends GunsTemplateEngine {
    protected void generatePageEditHtml() {
        String path = StrUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageEditPathTemplate(), new Object[]{
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName()});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_edit.html.btl", path);
        System.out.println("生成编辑页面成功!");
    }

    protected void generatePageAddHtml() {
        String path = StrUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageAddPathTemplate(), new Object[]{
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName()});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_add.html.btl", path);
        System.out.println("生成添加页面成功!");
    }

    protected void generatePageInfoJs() {
        String path = StrUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageInfoJsPathTemplate(), new Object[]{
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName()});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page_info.js.btl", path);
        System.out.println("生成页面详情js成功!");
    }

    protected void generatePageJs() {
        String path = StrUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPageJsPathTemplate(), new Object[]{
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName()});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page.js.btl", path);
        System.out.println("生成页面js成功!");
    }

    protected void generatePageHtml() {
        String path = StrUtil.format(super.getContextConfig().getProjectPath() + getPageConfig().getPagePathTemplate(), new Object[]{
                super.getContextConfig().getBizEnName(), super.getContextConfig().getBizEnName()});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/page.html.btl", path);
        System.out.println("生成页面成功!");
    }

    protected void generateController() {
        String controllerPath = StrUtil.format(super.getContextConfig().getProjectPath() + super.getControllerConfig().getControllerPathTemplate(), new Object[]{
                StrUtil.upperFirst(super.getContextConfig().getBizEnName())});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/Controller.java.btl", controllerPath);
        System.out.println("生成控制器成功!");
    }

    protected void generateSqls() {
        String path = StrUtil.format(super.getContextConfig().getProjectPath() + this.sqlConfig.getSqlPathTemplate(), new Object[]{
                StrUtil.upperFirst(super.getContextConfig().getBizEnName())});
        generateFile(super.getContextConfig().getTemplatePrefixPath() + "/menu_sql.sql.btl", path);
        System.out.println("生成sql成功!");
    }
}
