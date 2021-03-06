package com.boot.modular.customer.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.common.annotion.Permission;
import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.common.constant.Const;
import com.boot.core.common.constant.factory.PageFactory;
import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.common.page.PageInfoBT;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_model.exception.ServiceException;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.modular.customer.service.ICustomerService;
import com.boot.modular.customer.warpper.CustomerWarpper;
import com.boot.modular.system.model.Customer;
import com.boot.modular.system.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import com.boot.modular.system.model.CusFollow;
import com.boot.modular.customer.service.ICusFollowService;

import java.util.Date;
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
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService userService;

    /**
     * 跳转到客户跟进
     */
    @Permission({Const.DATABASE ,Const.LEADER})
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

    //查询客户经理下拉框
    @RequestMapping("/selectCustomerManagerList")
    @ResponseBody
    public Object selectCustomerManagerList() {
        return userService.selectCustomerManager();
    }

    /**
     * 获取客户跟进列表
     */
    @Permission({Const.DATABASE ,Const.LEADER})
    @RequestMapping(value = "/followlist")
    @ResponseBody
    public Object followlist( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark, @RequestParam(required = false) Integer iscustomermanager, @RequestParam(required = false) Integer followuserid) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        List<Map<String, Object>> customer = cusFollowService.selectCusFollow(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, iscustomermanager, followuserid);
        page.setRecords(new CustomerWarpper(customer).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 获取我的跟进列表
     */
    @RequestMapping(value = "/myfollowlist")
    @ResponseBody
    public Object myfollowlist( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark,  @RequestParam(required = false) Integer iscustomermanager) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer userid = shiroUser.getId();
        List<Map<String, Object>> customer = cusFollowService.selectMyCusFollow(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, iscustomermanager, userid);
        page.setRecords(new CustomerWarpper(customer).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 客户跟进详情
     */
    @RequestMapping(value = "/cusfollow_record/{customerId}")
    public String cusfollowRecord(@PathVariable("customerId") Integer customerId, Model model) {
        List<CusFollow> follows = cusFollowService.selectListByCustomerId(customerId);
        model.addAttribute("followsList", follows);
        model.addAttribute("customerid", customerId);
        return PREFIX + "cusfollow_record.html";
    }

    /**
     * 添加客户跟进详情
     */
    @RequestMapping(value = "/add_cusfollow_record/{customerId}")
    public String addCusfollowRecord(@PathVariable("customerId") Integer customerId, Model model) {
        model.addAttribute("customerId",customerId);
        return PREFIX + "add_cusfollow_record.html";
    }

    /**
     * 客户跟进保存
     */
    @RequestMapping(value = "/customer_follow_save")
    @ResponseBody
    @Transactional
    public Object customerFollowSave(Integer customerId, String remark) {
        ShiroUser shiroUser = ShiroKit.getUser();
        CusFollow cusFollow = new CusFollow();
        cusFollow.setUserid(shiroUser.getId());
        cusFollow.setCustomerid(customerId);
        cusFollow.setFollowdate(new Date());
        cusFollow.setRemark(remark);
        cusFollowService.insert(cusFollow);
        //更新跟进时间
        Customer customer = customerService.selectCustomerById(customerId);
        customer.setFollowdate(new Date());
        customerService.updateById(customer);
        return SUCCESS_TIP;
    }

    /**
     * 删除跟进记录
     */
    @RequestMapping(value = "/del_cusfollow_record")
    @ResponseBody
    public Object del_cusfollow_record(@RequestParam Long followId) {
        cusFollowService.deleteById(followId);
        return SUCCESS_TIP;
    }

}
