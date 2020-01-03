package com.boot.modular.customer.warpper;

import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.common.constant.factory.ConstantFactory;
import com.boot.core.kernel_core.base.warpper.BaseControllerWrapper;
import com.boot.core.kernel_model.page.PageResult;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

public class CustomerWarpper extends BaseControllerWrapper {

    public CustomerWarpper(Map<String, Object> single) {
        super(single);
    }

    public CustomerWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CustomerWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CustomerWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        int customertype = (int) map.get("customertype");
        int customerstatus = (int) map.get("customerstatus");
        int datasources = (int) map.get("datasources");
        if(customertype==1){
            map.put("customertypeName", BizConstantEnum.customertype_car.getMessage());
        }else if(customertype==2){
            map.put("customertypeName", BizConstantEnum.customertype_house.getMessage());
        }else if(customertype==3){
            map.put("customertypeName", BizConstantEnum.customertype_other.getMessage());
        }

        if(customerstatus==1){
            map.put("customerstatusName", BizConstantEnum.customerstatus_not.getMessage());
        }else if(customerstatus==2){
            map.put("customerstatusName", BizConstantEnum.customerstatus_has.getMessage());
        }else if(customerstatus==3){
            map.put("customerstatusName", BizConstantEnum.customerstatus_success.getMessage());
        }else if(customerstatus==4){
            map.put("customerstatusName", BizConstantEnum.customerstatus_del.getMessage());
        }

        if(datasources==1){
            map.put("datasourcesName", BizConstantEnum.datasources_sys.getMessage());
        }else if(datasources==2){
            map.put("datasourcesName", BizConstantEnum.datasources_excel.getMessage());
        }

    }

}
