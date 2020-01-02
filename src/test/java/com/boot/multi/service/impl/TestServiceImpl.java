package com.boot.multi.service.impl;

import com.boot.core.common.constant.DatasourceEnum;
import com.boot.core.kernel_core.mutidatasource.annotion.DataSource;
import com.boot.multi.entity.Test;
import com.boot.multi.mapper.TestMapper;
import com.boot.multi.service.TestService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-10
 */
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public void testBiz() {
        Test test = new Test();
        test.setBbb("bizTest");
        testMapper.insert(test);
    }

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    @Transactional
    public void testGuns() {
        Test test = new Test();
        test.setBbb("gunsTest");
        testMapper.insert(test);
    }
}
