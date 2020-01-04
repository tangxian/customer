package com.boot.modular.customer.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusFollow;
import com.boot.modular.system.dao.CusFollowMapper;
import com.boot.modular.customer.service.ICusFollowService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.boot.modular.system.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户跟进表 服务实现类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
@Service
public class CusFollowServiceImpl extends ServiceImpl<CusFollowMapper, CusFollow> implements ICusFollowService {
    @Override
    public List<Map<String, Object>> selectCusFollow(Page<Customer> page, String customername, String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, Integer userid) {
        return this.baseMapper.selectCusFollow(page, customername, mobile, idcard, customertype, customerstatus, beginTime, endTime, datasources, userid);
    }

    @Override
    public List<CusFollow> selectListByCustomerId(Integer customerid){
        return this.baseMapper.selectListByCustomerId(customerid);
    }
}
