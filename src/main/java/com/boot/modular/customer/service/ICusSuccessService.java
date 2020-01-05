package com.boot.modular.customer.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.boot.modular.system.model.CusSuccess;
import com.boot.modular.system.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户成交表 服务类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-05
 */
public interface ICusSuccessService extends IService<CusSuccess> {
    /**
     *根据条件查询我的客户跟进
     */
    List<Map<String, Object>> selectCusSuccess(Page<Customer> page, String customername, String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, String importremark, Integer status, Integer userid);

    /**
     * 根据客户id查询跟进记录
     */
    List<CusSuccess> selectListByCustomerId(Integer customerid);
}
