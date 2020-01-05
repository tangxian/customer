package com.boot.modular.customer.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.common.constant.factory.PageFactory;
import com.boot.core.common.page.PageInfoBT;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.modular.customer.service.ICusFollowService;
import com.boot.modular.customer.service.ICusSuccessService;
import com.boot.modular.customer.service.ICustomerService;
import com.boot.modular.customer.warpper.CusSuccessWarpper;
import com.boot.modular.customer.warpper.CustomerWarpper;
import com.boot.modular.system.model.CusFollow;
import com.boot.modular.system.model.CusSuccess;
import com.boot.modular.system.model.Customer;
import com.boot.modular.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户成交控制器
 *
 * @author TANGXIAN
 * @Date 2020年1月5日18:41:45
 */
@Controller
@RequestMapping("/cusSuccess")
public class CusSuccessController extends BaseController {

    private String PREFIX = "/customer/cussuccess/";

    @Autowired
    private ICusFollowService cusFollowService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICusSuccessService cusSuccessService;

    /**
     * 跳转到客户跟进
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cusfollow.html";
    }

    /**
     * 客户成交申请
     */
    @RequestMapping(value = "/success_apply/{customerId}")
    public String successApply(@PathVariable("customerId") Integer customerId, Model model) {
        model.addAttribute("customerId",customerId);
        return PREFIX + "cussuccess_apply.html";
    }

    /**
     * 跳转到我的成交
     */
    @RequestMapping("/mysuccess")
    public String mysuccess() {
        return PREFIX + "mysuccess.html";
    }


    /**
     * 客户成交申请保存
     */
    @RequestMapping(value = "/success_apply_save")
    @ResponseBody
    @Transactional
    public Object successApplySave(CusSuccess cusSuccess) {
        ShiroUser shiroUser = ShiroKit.getUser();
        cusSuccess.setSuccessuserid(shiroUser.getId());
        cusSuccess.setSuccessdate(new Date());
        cusSuccess.setStatus(BizConstantEnum.cussuccessstatus_not.getCode());
        cusSuccessService.insert(cusSuccess);
        return SUCCESS_TIP;
    }

    /**
     * 获取客户管理列表
     */
    @RequestMapping(value = "/followlist")
    @ResponseBody
    public Object followlist( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark, @RequestParam(required = false) Integer iscustomermanager, @RequestParam(required = false) Integer followuserid) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        List<Map<String, Object>> customer = customerService.selectCustomer(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, iscustomermanager, followuserid);
        page.setRecords(new CustomerWarpper(customer).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 获取我的成交列表
     */
    @RequestMapping(value = "/mysuccesslist")
    @ResponseBody
    public Object myfollowlist( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark, @RequestParam(required = false) Integer status) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer userid = shiroUser.getId();
        List<Map<String, Object>> success = cusSuccessService.selectCusSuccess(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, status, userid);
        page.setRecords(new CusSuccessWarpper(success).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 客户跟进详情
     */
    @RequestMapping(value = "/cusfollow_record/{customerId}")
    public String customerDetail(@PathVariable("customerId") Integer customerId, Model model) {
        List<CusFollow> follows = cusFollowService.selectListByCustomerId(customerId);
        model.addAttribute("followsList", follows);
        return PREFIX + "cusfollow_record.html";
    }

}