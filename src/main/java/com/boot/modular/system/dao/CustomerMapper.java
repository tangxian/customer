package com.boot.modular.system.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 Mapper 接口
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
public interface CustomerMapper extends BaseMapper<Customer> {
    /**
     *根据条件查询客户
     */
    List<Map<String, Object>> selectCustomer(@Param("page") Page<Customer> page, @Param("customername") String customername , @Param("mobile") String mobile, @Param("idcard") String idcard, @Param("customertype") Integer customertype, @Param("customerstatus") Integer customerstatus, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("datasources") Integer datasources, @Param("importremark") String importremark, @Param("iscustomermanager") Integer iscustomermanager, @Param("userid") Integer userid);
    /**
     * 删除客户管理
     */
    int deleteCustomer(@Param("customerIdList") List<Integer> customerIdList);
    /**
     *根据条件查询客户
     */
    List<Map<String, Object>> selectCustomerManager(@Param("page") Page<Customer> page, @Param("customername") String customername , @Param("mobile") String mobile, @Param("idcard") String idcard, @Param("customertype") Integer customertype, @Param("customerstatus") Integer customerstatus, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("datasources") Integer datasources, @Param("importremark") String importremark, @Param("iscustomermanager") Integer iscustomermanager, @Param("userid") Integer userid);

    //根据开发者id查询开发者余额信息
    Customer selectCustomerById(@Param("id") Integer id);
}
