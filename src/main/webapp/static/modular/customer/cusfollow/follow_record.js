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
 * 标记意向客户
 */
followRecord.delete = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/cusFollow/del_cusfollow_record", function (data) {
        Feng.success("删除成功!");
        window.location.reload();
    }, function (data) {
        Feng.error("删除失败!" + data.responseJSON.message + "!");
    });
    ajax.set("followId",id);
    ajax.start();
};


$(function () {

});
