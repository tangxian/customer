package com.boot.modular.customer.warpper;

import com.baomidou.mybatisplus.plugins.Page;
import com.boot.core.common.constant.BizConstantEnum;
import com.boot.core.common.constant.factory.ConstantFactory;
import com.boot.core.kernel_core.base.warpper.BaseControllerWrapper;
import com.boot.core.kernel_model.page.PageResult;

import java.util.List;
import java.util.Map;

public class CusSuccessWarpper extends BaseControllerWrapper {

    public CusSuccessWarpper(Map<String, Object> single) {
        super(single);
    }

    public CusSuccessWarpper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public CusSuccessWarpper(Page<Map<String, Object>> page) {
        super(page);
    }

    public CusSuccessWarpper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        Integer successuserid = (Integer) map.get("successuserid");
        map.put("successuserName", ConstantFactory.me().getUserNameById(successuserid));
        Integer checkuserid = (Integer) map.get("checkuserid");
        map.put("checkuserName", ConstantFactory.me().getUserNameById(checkuserid));
        int status = (int) map.get("status");
        if(status==1){
            map.put("statusName", BizConstantEnum.cussuccessstatus_not.getMessage());
        }else if(status==2){
            map.put("statusName", BizConstantEnum.cussuccessstatus_pass.getMessage());
        }else if(status==3){
            map.put("statusName", BizConstantEnum.cussuccessstatus_reject.getMessage());
        }
        int customertype = (int) map.get("customertype");
        if(customertype==1){
            map.put("customertypeName", BizConstantEnum.customertype_car.getMessage());
        }else if(customertype==2){
            map.put("customertypeName", BizConstantEnum.customertype_house.getMessage());
        }else if(customertype==3){
            map.put("customertypeName", BizConstantEnum.customertype_other.getMessage());
        }
    }

}
