package com.boot.modular.customer.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.Customer;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

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
    List<Map<String, Object>> selectCustomer(Page<Customer> page, String customername ,String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, String importremark, Integer iscustomermanager, Integer userid);

    /**
     * 删除客户
     */
    int deleteCustomer(List<Integer> customerIdList);

    /**
     *根据条件查询客户(客户经理)
     */
    List<Map<String, Object>> selectCustomerManager(Page<Customer> page, String customername ,String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, String importremark, Integer iscustomermanager, Integer userid);

    /**
     *根据开发者id查询开发者余额信息
     */
    Customer selectCustomerById(Integer id);
}
