package com.boot.modular.customer.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.modular.customer.service.ICusSuccessService;
import com.boot.modular.system.dao.CusSuccessMapper;
import com.boot.modular.system.model.CusSuccess;
import com.boot.modular.system.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户成交表 服务实现类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-05
 */
@Service
public class CusSuccessServiceImpl extends ServiceImpl<CusSuccessMapper, CusSuccess> implements ICusSuccessService {
    @Override
    public List<Map<String, Object>> selectCusSuccess(Page<Customer> page, String customername, String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, String importremark, Integer status, Integer userid) {
        return this.baseMapper.selectCusSuccess(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, importremark, status, userid);
    }

    @Override
    public List<CusSuccess> selectListByCustomerId(Integer customerid){
        return this.baseMapper.selectListByCustomerId(customerid);
    }
}
