package com.boot.modular.customer.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.modular.system.model.CusImport;
import com.boot.modular.system.dao.CusImportMapper;
import com.boot.modular.customer.service.ICusImportService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据导入表 服务实现类
 * </p>
 *
 * @author TANGXIAN
 * @since 2020-05-09
 */
@Service
public class CusImportServiceImpl extends ServiceImpl<CusImportMapper, CusImport> implements ICusImportService {
    @Override
    public List<Map<String, Object>> selectCusImport(Page<CusImport> page, String beginTime, String endTime, String importremark) {
        return this.baseMapper.selectCusImport(page, beginTime, endTime, importremark);
    }

    @Override
    public int deleteCustomerByImport(Integer id){
        return this.baseMapper.deleteCustomerByImport(id);
    }
}
