package com.boot.modular.customer.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.common.constant.factory.PageFactory;
import com.boot.core.common.page.PageInfoBT;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.modular.customer.warpper.CustomerWarpper;
import com.boot.modular.system.model.Customer;
import com.boot.modular.system.model.Dict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.boot.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.boot.modular.system.model.CusFollow;
import com.boot.modular.customer.service.ICusFollowService;

import java.util.List;
import java.util.Map;

/**
 * 客户跟进控制器
 *
 * @author TANGXIAN
 * @Date 2020-01-03 21:47:36
 */
@Controller
@RequestMapping("/cusFollow")
public class CusFollowController extends BaseController {

    private String PREFIX = "/customer/cusfollow/";

    @Autowired
    private ICusFollowService cusFollowService;

    /**
     * 跳转到客户跟进
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "cusfollow.html";
    }

    /**
     * 跳转到我的跟进
     */
    @RequestMapping("/myfollow")
    public String myfollow() {
        return PREFIX + "myfollow.html";
    }

    /**
     * 获取客户管理列表
     */
    @RequestMapping(value = "/myfollowlist")
    @ResponseBody
    public Object myfollowlist( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer userid = shiroUser.getId();
        List<Map<String, Object>> customer = cusFollowService.selectCusFollow(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, userid);
        page.setRecords(new CustomerWarpper(customer).wrap());
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
