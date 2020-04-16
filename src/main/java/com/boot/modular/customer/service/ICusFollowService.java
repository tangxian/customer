package com.boot.modular.customer.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusFollow;
import com.baomidou.mybatisplus.service.IService;
import com.boot.modular.system.model.Customer;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户跟进表 服务类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
public interface ICusFollowService extends IService<CusFollow> {
    /**
     *根据条件查询我的客户跟进
     */
    List<Map<String, Object>> selectMyCusFollow(Page<Customer> page, String customername , String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, String importremark, Integer iscustomermanager, Integer userid);

    /**
     * 根据客户id查询跟进记录
     */
    List<CusFollow> selectListByCustomerId(Integer customerid);

    /**
     *根据条件查询管理员客户跟进
     */
    List<Map<String, Object>> selectCusFollow(Page<Customer> page, String customername , String mobile, String idcard, Integer customertype, Integer customerstatus, String beginTime, String endTime, Integer datasources, String importremark, Integer iscustomermanager, Integer followuserid);

}
