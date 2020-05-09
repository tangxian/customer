package com.boot.modular.customer.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusImport;
import com.baomidou.mybatisplus.service.IService;
import com.boot.modular.system.model.CusImport;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据导入表 服务类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-05-09
 */
public interface ICusImportService extends IService<CusImport> {
    /**
     *根据条件查询导入备注
     */
    List<Map<String, Object>> selectCusImport(Page<CusImport> page, String beginTime, String endTime, String importremark);

    /**
     * 删除客户通过导入备注
     */
    int deleteCustomerByImport(Integer id);

}
