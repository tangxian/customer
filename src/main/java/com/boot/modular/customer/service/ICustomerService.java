package com.boot.modular.customer.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.Customer;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
public interface ICustomerService extends IService<Customer> {
    /**
     *根据条件查询客户
     */
    List<Map<String, Object>> selectCustomer(Page<Customer> page, String customername ,String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, Integer iscustomermanager);
}
