package com.boot.modular.customer.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.boot.core.common.annotion.Permission;
import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.common.constant.Const;
import com.boot.core.common.constant.factory.PageFactory;
import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.common.exception.FileFormatErrorException;
import com.boot.core.common.page.PageInfoBT;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_model.exception.ServiceException;
import com.boot.core.log.LogObjectHolder;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.core.util.DateHelper;
import com.boot.core.util.ExcelUtils;
import com.boot.modular.customer.model.CustomerInfo;
import com.boot.modular.customer.service.ICusFollowService;
import com.boot.modular.customer.service.ICusImportService;
import com.boot.modular.customer.service.ICustomerService;
import com.boot.modular.customer.warpper.CustomerWarpper;
import com.boot.modular.system.model.CusFollow;
import com.boot.modular.system.model.CusImport;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.boot.modular.system.model.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * 客户管理控制器
 *
 * @author TANGXIAN
 * @Date 2020-01-03 12:36:57
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private String PREFIX = "/customer/customer/";

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICusFollowService cusFollowService;
    @Autowired
    private ICusImportService cusImportService;

    /**
     * 跳转到客户管理首页
     */
    @RequestMapping("")
    public String index() {
        List<Integer> roleList = ShiroKit.getUser().roleList;
        if(roleList.size()>0){
            int roleId = roleList.get(0);
            if (roleId == Const.ADMIN_ROLE_ID) {
                //说明是超级管理员
                return PREFIX + "customer.html";
            }else if(roleId == Const.LEADER_ROLE_ID){
                //说明是公司管理
                return PREFIX + "customer.html";
            }else if(roleId == Const.DATABASE_ROLE_ID){
                //说明是数据管理员
                return PREFIX + "customer.html";
            }else if(roleId == Const.CUSTOMERMANAGER_ROLE_ID){
                //说明是客户经理
                return PREFIX + "customer_customermanager.html";
            }else{
                return PREFIX + "customer_customermanager.html";
            }
        }else{
            return PREFIX + "customer_customermanager.html";
        }

    }

    /**
     * 跳转到导入客户
     */
    @Permission({Const.DATABASE ,Const.LEADER})
    @RequestMapping("/customer_import")
    public String customerImport(Model model) {
        model.addAttribute("tips", "init");
        return PREFIX + "customer_import.html";
    }

    /**
     * 跳转到添加客户管理
     */
    @RequestMapping("/customer_add")
    public String customerAdd() {
        return PREFIX + "customer_add.html";
    }


    /**
     * 跳转到修改客户管理
     */
    @RequestMapping("/customer_update/{customerId}")
    public String customerUpdate(@PathVariable Integer customerId, Model model) {
        Customer customer = customerService.selectById(customerId);
        model.addAttribute("item",customer);
        LogObjectHolder.me().set(customer);
        return PREFIX + "customer_edit.html";
    }

    /**
     * 获取客户管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark, @RequestParam(required = false) Integer iscustomermanager) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer userid = shiroUser.getId();
        List<Map<String, Object>> customer = customerService.selectCustomer(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, iscustomermanager, userid);
        page.setRecords(new CustomerWarpper(customer).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 获取客户管理列表(客户经理)
     */
    @RequestMapping(value = "/managerlist")
    @ResponseBody
    public Object managerlist( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources, @RequestParam(required = false) String importremark, @RequestParam(required = false) Integer iscustomermanager) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        ShiroUser shiroUser = ShiroKit.getUser();
        Integer userid = shiroUser.getId();
        List<Map<String, Object>> customer = customerService.selectCustomerManager(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, iscustomermanager, userid);
        page.setRecords(new CustomerWarpper(customer).wrap());
        return new PageInfoBT<>(page);
    }

    /**
     * 新增客户管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Customer customer) {
        ShiroUser shiroUser = ShiroKit.getUser();
        customer.setCreatedate(new Date());
        customer.setCreateuserid(shiroUser.getId());
        customer.setDatasources(BizConstantEnum.datasources_sys.getCode());
        customerService.insert(customer);
        return SUCCESS_TIP;
    }

    /**
     * 删除客户管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String customerIds) {
        String customerIdsArr[] = customerIds.split(",");
        List<Integer> customerIdsList = new ArrayList<Integer>();
        for(String str : customerIdsArr){
            customerIdsList.add(Integer.valueOf(str));
        }
        if(customerIdsList.size()>0){
            customerService.deleteCustomer(customerIdsList);
        }
        return SUCCESS_TIP;
    }

    /**
     * 标记意向客户
     */
    @RequestMapping(value = "/customerstatushas")
    @ResponseBody
    public Object customerstatushas(@RequestParam Integer customerId) {
        Customer customer = customerService.selectById(customerId);
        customer.setCustomerstatus(BizConstantEnum.customerstatus_has.getCode());
        customerService.updateById(customer);
        return SUCCESS_TIP;
    }

    /**
     * 取消标记意向客户
     */
    @RequestMapping(value = "/cancelcustomerstatushas")
    @ResponseBody
    public Object cancelcustomerstatushas(@RequestParam Integer customerId) {
        Customer customer = customerService.selectById(customerId);
        customer.setCustomerstatus(BizConstantEnum.customerstatus_not.getCode());
        customer.setFollowuserid(0);
        customerService.updateById(customer);
        return SUCCESS_TIP;
    }

    /**
     * 修改客户管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Customer customer) {
        customerService.updateById(customer);
        return SUCCESS_TIP;
    }

    /**
     * 客户管理详情
     */
    @RequestMapping(value = "/customer_detail/{customerId}")
    public String customerDetail(@PathVariable("customerId") Integer customerId, Model model) {
        Customer customer =  customerService.selectById(customerId);
        model.addAttribute("item",customer);
        return PREFIX + "customer_detail.html";
    }

    /**
     * 读取excel
     * @param file
     * @param model
     * @return
     */
    @Permission({Const.DATABASE ,Const.LEADER})
    @PostMapping(value = "/readExcel")
    @Transactional
    public String readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) String importremark, Model model){
        try{
            ShiroUser shiroUser = ShiroKit.getUser();
            String importnumber = DateHelper.getNumberForPK();
            long t1 = System.currentTimeMillis();
            int successCount = 0;
            int fileCount = 0;
            //查询的导入备注是否重复
            EntityWrapper<CusImport> cusImportEntityWrapper = new EntityWrapper<CusImport>();
            cusImportEntityWrapper.eq("importremark",importremark);
            List<CusImport> CusImportList = cusImportService.selectList(cusImportEntityWrapper);
            if(CusImportList.size()>0){
                model.addAttribute("tips", "导入数据备注与之前重复，请重新填写导入数据备注");
            }else{
                //客户信息
                List<CustomerInfo> list = ExcelUtils.readExcel("", CustomerInfo.class, file);
                if(list.size()==0){
                    model.addAttribute("tips", "文件模板不正确");
                }else{
                    for (CustomerInfo customerInfo : list){
                        //System.out.println("解析客户信息"+ JSON.toJSONString(customerInfo));
                        //写入数据库
                        String customername = customerInfo.getCustomername();
                        String mobile = customerInfo.getMobile();
                        String idcard = customerInfo.getIdcard();
                        //电话不为空的前提下保存客户信息
                        if(StringUtils.isNotEmpty(mobile)){
                            //车辆
                            String carid = customerInfo.getCarid();
                            String cartype = customerInfo.getCartype();
                            //房产
                            String houseinfo = customerInfo.getHouseinfo();
                            String carinfo = customerInfo.getCarinfo();
                            String insurance = customerInfo.getInsurance();
                            String sbgjj = customerInfo.getSbgjj();
                            String businesslicense = customerInfo.getBusinesslicense();
                            String otherinfo = customerInfo.getOtherinfo();
                            Customer customer = new Customer();
                            customer.setCustomername(customername);
                            customer.setMobile(mobile);
                            customer.setIdcard(idcard);
                            //车辆抵押客户
                            customer.setCarid(carid);
                            customer.setCartype(cartype);
                            //房产抵押客户
                            customer.setHouseinfo(houseinfo);
                            customer.setCarinfo(carinfo);
                            customer.setInsurance(insurance);
                            customer.setSbgjj(sbgjj);
                            customer.setBusinesslicense(businesslicense);
                            customer.setOtherinfo(otherinfo);

                            customer.setCustomerstatus(BizConstantEnum.customerstatus_not.getCode());
                            customer.setCreatedate(new Date());
                            customer.setCreateuserid(shiroUser.getId());
                            customer.setDatasources(BizConstantEnum.datasources_excel.getCode());
                            customer.setCustomertype(customertype);
                            customer.setImportnumber(importnumber);
                            customer.setImportremark(importremark);
                            customerService.insert(customer);
                            successCount++;
                        }else{
                            fileCount++;
                        }
                    }

                    long t2 = System.currentTimeMillis();
                    //将数据写入import表
                    CusImport cusImport = new CusImport();
                    cusImport.setImportnumber(importnumber);
                    cusImport.setImportremark(importremark);
                    cusImport.setImportuserid(shiroUser.getId());
                    cusImport.setImportdate(new Date());
                    cusImportService.insert(cusImport);
                    //System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
                    model.addAttribute("tips", "Excel总计"+(successCount+fileCount)+"条数据,成功导入"+successCount+"条数据,因数据不完善导致"+fileCount+"条数据未导入，耗时"+(t2 - t1)+"毫秒");
                }
            }

        }catch (FileFormatErrorException fileException){
        model.addAttribute("tips", "文件格式不正确");
        }
        return PREFIX + "customer_import.html";
    }


    /**
     * 客户跟进
     */
    @RequestMapping(value = "/customer_follow/{customerId}")
    public String customerFollow(@PathVariable("customerId") Integer customerId, Model model) {
        model.addAttribute("customerId",customerId);
        return PREFIX + "customer_follow.html";
    }

    /**
     * 客户跟进保存
     */
    @RequestMapping(value = "/customer_follow_save")
    @ResponseBody
    @Transactional
    public Object customerFollowSave(Integer customerId, String remark) {
        ShiroUser shiroUser = ShiroKit.getUser();
        //先查询是否已经存在跟进表，存在则说明已有客户经理标记意向客户不能标记
        Customer customer = customerService.selectCustomerById(customerId);
        if (customer.getCustomerstatus().equals(BizConstantEnum.customerstatus_has.getCode())) {
            throw new ServiceException(BizExceptionEnum.ALREADYFOLLOW);
        }else{
            customer.setCustomerstatus(BizConstantEnum.customerstatus_has.getCode());
            customer.setFollowuserid(shiroUser.getId());
            customer.setFollowdate(new Date());
            customerService.updateById(customer);
            CusFollow cusFollow = new CusFollow();
            cusFollow.setUserid(shiroUser.getId());
            cusFollow.setCustomerid(customerId);
            cusFollow.setFollowdate(new Date());
            cusFollow.setRemark(remark);
            cusFollowService.insert(cusFollow);

        }

        return SUCCESS_TIP;
    }

    //查询导入备注
    @RequestMapping("/selectImportRemarkList")
    @ResponseBody
    public Object selectImportRemarkList() {
        EntityWrapper<CusImport> cusImportEntityWrapper = new EntityWrapper<CusImport>();
        cusImportEntityWrapper.orderBy("importdate",false);
        List<CusImport> CusImport = cusImportService.selectList(cusImportEntityWrapper);
        return CusImport;
    }
}
