package com.boot.modular.customer.warpper;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.kernel_core.base.warpper.BaseControllerWrapper;
import com.boot.core.kernel_model.page.PageResult;

import java.util.List;
import java.util.Map;

public class CusImportWarpper extends BaseControllerWrapper {

    public CusImportWarpper(Map<String, Object> single) {
        super(single);
    }

    public CusImportWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CusImportWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CusImportWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {


    }

}
