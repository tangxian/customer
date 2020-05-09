package com.boot.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusFollow;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.modular.system.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户跟进表 Mapper 接口
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-03
 */
public interface CusFollowMapper extends BaseMapper<CusFollow> {
    /**
     *根据条件查询客户跟进(客户经理)我的
     */
    List<Map<String, Object>> selectMyCusFollow(@Param("page") Page<Customer> page, @Param("customername") String customername , @Param("mobile") String mobile, @Param("idcard") String idcard, @Param("customertype") Integer customertype, @Param("customerstatus") Integer customerstatus, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("datasources") Integer datasources, @Param("importremark") String importremark, @Param("iscustomermanager") Integer iscustomermanager, @Param("userid") Integer userid);

    /**
     * 根据客户id查询跟进记录
     */
    List<CusFollow> selectListByCustomerId(@Param("customerid") Integer customerid);

    /**
     *根据条件查询客户跟进(管理员)
     */
    List<Map<String, Object>> selectCusFollow(@Param("page") Page<Customer> page, @Param("customername") String customername , @Param("mobile") String mobile, @Param("idcard") String idcard, @Param("customertype") Integer customertype, @Param("customerstatus") Integer customerstatus, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("datasources") Integer datasources, @Param("importremark") String importremark, @Param("iscustomermanager") Integer iscustomermanager, @Param("followuserid") Integer followuserid);

    /**
     * 删除客户跟进信息
     */
    int deleteFollowByCustomer(@Param("customerIdList") List<Integer> customerIdList);
}
