/**
 * 初始化客户成交申请对话框
 */
var successApplyInfoDlg = {
    successApplyInfoData : {},
    validateFields: {
        bank: {
            validators: {
                notEmpty: {
                    message: '贷款银行不能为空'
                }
            }
        },
        amount: {
            validators: {
                notEmpty: {
                    message: '贷款金额不能为空'
                },
                regexp: {
                    regexp: /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,5})))$/,
                    message: '请输入有效的数字'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
successApplyInfoDlg.clearData = function() {
    this.successApplyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
successApplyInfoDlg.set = function(key, val) {
    this.successApplyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
successApplyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
successApplyInfoDlg.close = function() {
    parent.layer.close(window.parent.Myfollow.layerIndex);
}

/**
 * 收集数据
 */
successApplyInfoDlg.collectData = function() {
    this
    .set('customerid')
    .set('bank')
    .set('amount')
    .set('remark');
}

/**
 * 验证数据是否为空
 */
successApplyInfoDlg.validate = function () {
    $('#successApplyInfoForm').data("bootstrapValidator").resetForm();
    $('#successApplyInfoForm').bootstrapValidator('validate');
    return $("#successApplyInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
successApplyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/cusSuccess/success_apply_save", function(data){
        Feng.success("操作成功!");
        window.parent.Myfollow.table.refresh();
        successApplyInfoDlg.close();
    },function(data){
        Feng.error("操作失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.successApplyInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("successApplyInfoForm", successApplyInfoDlg.validateFields);

});
