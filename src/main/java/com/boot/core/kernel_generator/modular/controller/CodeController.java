package com.boot.core.kernel_generator.modular.controller;

import com.boot.core.kernel_generator.executor.config.WebGeneratorConfig;
import com.boot.core.kernel_generator.executor.model.GenQo;
import com.boot.core.kernel_generator.modular.factory.DefaultTemplateFactory;
import com.boot.core.kernel_generator.modular.service.TableService;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_core.config.properties.DruidProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/code"})
public class CodeController extends BaseController {
    private static String PREFIX = "/code";


    @Autowired
    private TableService tableService;

    @Autowired
    private DruidProperties druidProperties;


    @RequestMapping({""})
    public String blackboard(Model model) {
        model.addAttribute("tables", this.tableService.getAllTables());
        model.addAttribute("params", DefaultTemplateFactory.getDefaultParams());
        model.addAttribute("templates", DefaultTemplateFactory.getDefaultTemplates());
        return PREFIX + "/code.html";
    }


    @RequestMapping(value = {"/generate"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Object generate(GenQo genQo) {
        genQo.setUrl(this.druidProperties.getUrl());
        genQo.setUserName(this.druidProperties.getUsername());
        genQo.setPassword(this.druidProperties.getPassword());
        WebGeneratorConfig webGeneratorConfig = new WebGeneratorConfig(genQo);
        webGeneratorConfig.doMpGeneration();
        webGeneratorConfig.doGunsGeneration();
        return SUCCESS_TIP;
    }
}
