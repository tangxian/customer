/**
 * 初始化客户管理详情对话框
 */
var customerInfoDlg = {
    customerInfoData : {},
    validateFields: {
        customername: {
            validators: {
                notEmpty: {
                    message: '客户姓名不能为空'
                }
            }
        },
        mobile: {
            validators: {
                notEmpty: {
                    message: '电话不能为空'
                }
            }
        },
        idcard: {
            validators: {
                notEmpty: {
                    message: '身份证号码不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
customerInfoDlg.clearData = function() {
    this.customerInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
customerInfoDlg.set = function(key, val) {
    this.customerInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
customerInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
customerInfoDlg.close = function() {
    parent.layer.close(window.parent.Customer.layerIndex);
}

/**
 * 收集数据
 */
customerInfoDlg.collectData = function() {
    this
    .set('id')
    .set('customername')
    .set('mobile')
    .set('idcard')
    .set('houseinfo')
    .set('carinfo')
    .set('sbgjj')
    .set('insurance')
    .set('businesslicense')
    .set('carid')
    .set('cartype')
    .set('otherinfo')
    .set('customertype')
    .set('customerstatus');
}

/**
 * 验证数据是否为空
 */
customerInfoDlg.validate = function () {
    $('#customerInfoForm').data("bootstrapValidator").resetForm();
    $('#customerInfoForm').bootstrapValidator('validate');
    return $("#customerInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
customerInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/customer/add", function(data){
        Feng.success("添加成功!");
        window.parent.Customer.table.refresh();
        customerInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.customerInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
customerInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/customer/update", function(data){
        Feng.success("修改成功!");
        window.parent.Customer.table.refresh();
        customerInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.customerInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("customerInfoForm", customerInfoDlg.validateFields);
    //初始化客户类型
    var customertype = $("#customertype1").val();
    if (customertype != "" && typeof(customertype) != "undefined") {//客户类型有值
        if (customertype == 1) {//车辆抵押客户
            $("#customertype").val(1);
        }else if (customertype == 2){//房产抵押客户
            $("#customertype").val(2);
        }else if (customertype == 3){//其他
            $("#customertype").val(3);
        }
    }

    //初始化客户状态
    var customerstatus = $("#customerstatus1").val();
    if (customerstatus != "" && typeof(customerstatus) != "undefined") {//客户状态有值
        if (customerstatus == 1) {//非意向
            $("#customerstatus").val(1);
        }else if (customerstatus == 2){//意向
            $("#customerstatus").val(2);
        }else if (customerstatus == 3){//其他
            $("#customerstatus").val(3);
        }
    }
});
