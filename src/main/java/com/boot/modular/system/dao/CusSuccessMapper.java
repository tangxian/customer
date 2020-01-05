package com.boot.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusSuccess;
import com.boot.modular.system.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户成交 Mapper 接口
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-01-05
 */
public interface CusSuccessMapper extends BaseMapper<CusSuccess> {
    /**
     *根据条件查询客户跟进
     */
    List<Map<String, Object>> selectCusSuccess(@Param("page") Page<Customer> page, @Param("customername") String customername, @Param("mobile") String mobile, @Param("idcard") String idcard, @Param("customertype") Integer customertype, @Param("customerstatus") Integer customerstatus, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("datasources") Integer datasources, @Param("importremark") String importremark, @Param("status") Integer status, @Param("userid") Integer userid);

    /**
     * 根据客户id查询跟进记录
     */
    List<CusSuccess> selectListByCustomerId(@Param("customerid") Integer customerid);
}
