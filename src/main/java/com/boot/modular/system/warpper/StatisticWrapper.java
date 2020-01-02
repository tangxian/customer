package com.boot.modular.system.warpper;

import com.boot.core.kernel_core.base.warpper.BaseControllerWrapper;
import com.boot.core.kernel_model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * 通用类型转换类
 */
public class StatisticWrapper extends BaseControllerWrapper {
    public StatisticWrapper(Map<String, Object> single) {
        super(single);
    }

    public StatisticWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public StatisticWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public StatisticWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
    }
}
