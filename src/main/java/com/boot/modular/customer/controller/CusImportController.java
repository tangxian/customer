package com.boot.modular.customer.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.common.annotion.Permission;
import com.boot.core.common.constant.Const;
import com.boot.core.common.constant.factory.PageFactory;
import com.boot.core.common.page.PageInfoBT;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.modular.customer.service.ICusImportService;
import com.boot.modular.customer.warpper.CusImportWarpper;
import com.boot.modular.system.model.CusImport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 客户导入控制器
 *
 * @author TANGXIAN
 * @Date 2020年5月9日15:10:30
 */
@Controller
@RequestMapping("/cusImport")
public class CusImportController extends BaseController {

    private String PREFIX = "/customer/cusimport/";

    @Autowired
    private ICusImportService cusImportService;

    /**
     * 跳转到客户导入管理
     */
    @Permission({Const.DATABASE ,Const.LEADER})
    @RequestMapping("")
    public String index() {
        return PREFIX + "cusimport.html";
    }

    /**
     * 获取客户导入管理列表
     */
    @RequestMapping(value = "/cusimportlist")
    @ResponseBody
    public Object cusimport( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark, @RequestParam(required = false) Integer status) {
        Page<CusImport> page = new PageFactory<CusImport>().defaultPage();
        List<Map<String, Object>> cusimport = cusImportService.selectCusImport(page, beginTime, endTime, importremark);
        page.setRecords(new CusImportWarpper(cusimport).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 删除客户管理
     */
    @RequestMapping(value = "/delete")
    @Transactional
    @ResponseBody
    public Object delete(@RequestParam Integer id) {
        cusImportService.deleteCustomerByImport(id);
        cusImportService.deleteById(id);
        return SUCCESS_TIP;
    }


}
