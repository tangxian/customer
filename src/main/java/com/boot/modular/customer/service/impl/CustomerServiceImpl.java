package com.boot.modular.customer.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.Customer;
import com.boot.modular.system.dao.CustomerMapper;
import com.boot.modular.customer.service.ICustomerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Override
    public List<Map<String, Object>> selectCustomer(Page<Customer> page, String customername, String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources) {
        return this.baseMapper.selectCustomer(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources);
    }
}