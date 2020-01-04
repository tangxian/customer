package com.boot.modular.customer.warpper;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.kernel_core.base.warpper.BaseControllerWrapper;
import com.boot.core.kernel_model.page.PageResult;

import java.util.List;
import java.util.Map;

public class CusFollowWarpper extends BaseControllerWrapper {

    public CusFollowWarpper(Map<String, Object> single) {
        super(single);
    }

    public CusFollowWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CusFollowWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CusFollowWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {


    }

}
