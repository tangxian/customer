package com.boot.modular.customer.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.common.constant.factory.PageFactory;
import com.boot.core.common.exception.FileFormatErrorException;
import com.boot.core.common.page.PageInfoBT;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.log.LogObjectHolder;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.core.util.ExcelUtils;
import com.boot.modular.customer.model.CustomerCarInfo;
import com.boot.modular.customer.model.CustomerHouseInfo;
import com.boot.modular.customer.service.ICustomerService;
import com.boot.modular.customer.warpper.CustomerWarpper;
import com.boot.modular.system.model.NoticeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.boot.modular.system.model.Customer;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 跳转到客户管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "customer.html";
    }

    /**
     * 跳转到导入客户
     */
    @RequestMapping("/customer_import")
    public String customerImport() {
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
    public Object list( @RequestParam(required = false) String customername, @RequestParam(required = false) String mobile, @RequestParam(required = false) String idcard, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer customertype, @RequestParam(required = false) Integer customerstatus, @RequestParam(required = false) Integer datasources) {
        Page<Customer> page = new PageFactory<Customer>().defaultPage();
        List<Map<String, Object>> customer = customerService.selectCustomer(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources);
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
    public Object delete(@RequestParam Integer customerId) {
        Customer customer = customerService.selectById(customerId);
        customer.setCustomerstatus(BizConstantEnum.customerstatus_del.getCode());
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
    @RequestMapping(value = "/detail/{customerId}")
    @ResponseBody
    public Object detail(@PathVariable("customerId") Integer customerId) {
        return customerService.selectById(customerId);
    }

    /**
     * 读取excel
     * @param file
     * @param model
     * @return
     */
    @PostMapping(value = "/readExcel")
    @Transactional
    public String readExcel(@RequestParam(value="uploadFile", required = false) MultipartFile file,@RequestParam(required = false) Integer customertype, Model model){
        try{
            ShiroUser shiroUser = ShiroKit.getUser();
            long t1 = System.currentTimeMillis();
            int successCount = 0;
            int fileCount = 0;
            if(customertype.equals(BizConstantEnum.customertype_car.getCode())){
                //车辆抵押客户
                List<CustomerCarInfo> list = ExcelUtils.readExcel("", CustomerCarInfo.class, file);
                for (CustomerCarInfo customerCarInfo : list){
                    System.out.println("解析车辆抵押客户信息"+ JSON.toJSONString(customerCarInfo));
                    //写入数据库
                    String customername = customerCarInfo.getCustomername();
                    String mobile = customerCarInfo.getMobile();
                    String idcard = customerCarInfo.getIdcard();
                    //姓名、电话、身份证号不为空的前提下保存客户信息
                    if(StringUtils.isNotEmpty(customername)&&StringUtils.isNotEmpty(mobile)&&StringUtils.isNotEmpty(idcard)){
                        String carid = customerCarInfo.getCarid();
                        String cartype = customerCarInfo.getCartype();
                        if (StringUtils.isNotEmpty(carid)||StringUtils.isNotEmpty(cartype)){
                            //确定是正确的模板
                            Customer customer = new Customer();
                            customer.setCustomername(customername);
                            customer.setMobile(mobile);
                            customer.setIdcard(idcard);
                            customer.setCarid(carid);
                            customer.setCartype(cartype);
                            if(StringUtils.isNotEmpty(customerCarInfo.getCustomerstatus())){
                                if(customerCarInfo.getCustomerstatus().equals("是")){
                                    customer.setCustomerstatus(BizConstantEnum.customerstatus_has.getCode());
                                }else{
                                    customer.setCustomerstatus(BizConstantEnum.customerstatus_not.getCode());
                                }
                            }else{
                                customer.setCustomerstatus(BizConstantEnum.customerstatus_not.getCode());
                            }
                            customer.setCreatedate(new Date());
                            customer.setCreateuserid(shiroUser.getId());
                            customer.setDatasources(BizConstantEnum.datasources_excel.getCode());
                            customer.setCustomertype(BizConstantEnum.customertype_car.getCode());
                            customerService.insert(customer);
                            successCount++;
                        }else{
                            model.addAttribute("tips", "车辆抵押客户模板不正确");
                            return PREFIX + "customer_import.html";
                        }
                    }else{
                        fileCount++;
                    }
                }
            }else if(customertype.equals(BizConstantEnum.customertype_house.getCode())){
                //房产抵押客户
                List<CustomerHouseInfo> list = ExcelUtils.readExcel("", CustomerHouseInfo.class, file);
                for (CustomerHouseInfo customerHouseInfo : list){
                    System.out.println("解析房产抵押客户信息"+ JSON.toJSONString(customerHouseInfo));
                    //写入数据库
                    String customername = customerHouseInfo.getCustomername();
                    String mobile = customerHouseInfo.getMobile();
                    String idcard = customerHouseInfo.getIdcard();
                    //姓名、电话、身份证号不为空的前提下保存客户信息
                    if(StringUtils.isNotEmpty(customername)&&StringUtils.isNotEmpty(mobile)&&StringUtils.isNotEmpty(idcard)){
                        String houseinfo = customerHouseInfo.getHouseinfo();
                        String carinfo = customerHouseInfo.getCarinfo();
                        String insurance = customerHouseInfo.getInsurance();
                        String sbgjj = customerHouseInfo.getSbgjj();
                        String businesslicense = customerHouseInfo.getBusinesslicense();
                        String otherinfo = customerHouseInfo.getOtherinfo();
                        if (StringUtils.isNotEmpty(houseinfo)||StringUtils.isNotEmpty(carinfo)||StringUtils.isNotEmpty(insurance)||StringUtils.isNotEmpty(sbgjj)||StringUtils.isNotEmpty(businesslicense)||StringUtils.isNotEmpty(otherinfo)){
                            Customer customer = new Customer();
                            customer.setCustomername(customername);
                            customer.setMobile(mobile);
                            customer.setIdcard(idcard);
                            customer.setHouseinfo(houseinfo);
                            customer.setCarinfo(carinfo);
                            customer.setInsurance(insurance);
                            customer.setSbgjj(sbgjj);
                            customer.setBusinesslicense(businesslicense);
                            customer.setOtherinfo(otherinfo);
                            if(StringUtils.isNotEmpty(customerHouseInfo.getCustomerstatus())){
                                if(customerHouseInfo.getCustomerstatus().equals("是")){
                                    customer.setCustomerstatus(BizConstantEnum.customerstatus_has.getCode());
                                }else{
                                    customer.setCustomerstatus(BizConstantEnum.customerstatus_not.getCode());
                                }
                            }else{
                                customer.setCustomerstatus(BizConstantEnum.customerstatus_not.getCode());
                            }
                            customer.setCreatedate(new Date());
                            customer.setCreateuserid(shiroUser.getId());
                            customer.setDatasources(BizConstantEnum.datasources_excel.getCode());
                            customer.setCustomertype(BizConstantEnum.customertype_house.getCode());
                            customerService.insert(customer);
                            successCount++;
                        }else{
                            model.addAttribute("tips", "房产抵押客户模板不正确");
                            return PREFIX + "customer_import.html";
                        }

                    }else{
                        fileCount++;
                    }
                }
            }

            long t2 = System.currentTimeMillis();
            System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
            model.addAttribute("tips", "Excel总计"+(successCount+fileCount)+"条数据,成功导入"+successCount+"条数据,因数据不完善导致"+fileCount+"条数据未导入，耗时"+(t2 - t1)+"ms");
        }catch (FileFormatErrorException fileException){
            model.addAttribute("tips", "文件格式不正确");
        }
        return PREFIX + "customer_import.html";
    }
}
