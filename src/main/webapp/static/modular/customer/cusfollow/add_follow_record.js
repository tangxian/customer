/**
 * 跟进记录初始化
 */
var followRecord = {
    id: "myfollowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 客户跟进
 */
followRecord.addfollow = function () {
    var id = $("#customerid").val();
    var index = layer.open({
        type: 2,
        title: '添加跟进记录',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cusFollow/add_cusfollow_record/' + id
    });
    this.layerIndex = index;
};

/**
 * 关闭此对话框
 */
followRecord.close = function() {
    parent.layer.close(window.parent.followRecord.layerIndex);
    parent.parent.layer.close(window.parent.parent.Myfollow.layerIndex);
}

/**
 * 客户跟进保存
 */
followRecord.saveFollow = function () {
    var customerId = $("#customerId").val();
    var remark = $("#remark").val();
    if (remark.length == 0) {
        Feng.error("请填写跟进内容");
        return;
    }
    var ajax = new $ax(Feng.ctxPath + "/cusFollow/customer_follow_save", function (data) {
        Feng.success("添加跟进成功!");
        parent.layer.close(window.parent.followRecord.layerIndex);
        parent.parent.layer.close(window.parent.parent.Myfollow.layerIndex);
    }, function (data) {
        Feng.error("操作失败!" + data.responseJSON.errorMsg + "!");
    });
    ajax.set("customerId", customerId);
    ajax.set("remark", remark);
    ajax.start();
}

$(function () {

});
