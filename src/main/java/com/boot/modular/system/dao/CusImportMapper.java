package com.boot.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusImport;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.boot.modular.system.model.CusImport;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据导入表 Mapper 接口
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-05-09
 */
public interface CusImportMapper extends BaseMapper<CusImport> {
    /**
     *根据条件查询导入备注
     */
    List<Map<String, Object>> selectCusImport(@Param("page") Page<CusImport> page, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("importremark") String importremark);

    /**
     * 删除客户通过导入备注
     */
    int deleteCustomerByImport(@Param("id") Integer id);
}
