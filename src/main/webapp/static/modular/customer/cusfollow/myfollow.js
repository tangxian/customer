/**
 * 我的成单初始化
 */
var Myfollow = {
    id: "myfollowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Myfollow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true, visible: false},
        {
            field: 'SerialNumber',
            title: '序号',
            sortable: false,
            align: "center",
            width: 45,
            formatter: function (value, row, index) {
                //获取每页显示的数量
                var pageSize = $('#myfollowTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber = $('#myfollowTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return pageSize * (pageNumber - 1) + index + 1;
            }
        },
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '客户姓名', width:100, field: 'customername', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', width:100, field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号码', width:130, field: 'idcard', visible: true, align: 'center', valign: 'middle'},
        {title: '客户类型', width:110, field: 'customertypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '客户状态', width:90, field: 'customerstatusName', visible: true, align: 'center', valign: 'middle'},
        {title: '导入备注', width:130, field: 'importremark', visible: true, align: 'center', valign: 'middle'},
        {title: '跟进时间', width:140, field: 'followdate', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', field: '', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index, field) {
                return [
                    '<button type="button" onclick="Myfollow.detail(' + row["id"] + ')" class="btn btn-primary  btn-xs" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
                    ,'<button type="button" onclick="Myfollow.follow(' + row["id"] + ')" class="btn btn-primary  btn-xs" style="margin-right:15px;    margin-bottom: 0px;">跟进记录</button>'
                    ,'<button type="button" onclick="Myfollow.successapply(' + row["id"] + ')" class="btn btn-primary  btn-xs" style="margin-right:15px;    margin-bottom: 0px;">成交申请</button>'
                    ,'<button type="button" onclick="Myfollow.customerstatus(' + row["id"] + ')" class="btn btn-primary  btn-xs" style="margin-right:15px;    margin-bottom: 0px;">取消意向标记</button>'
                ].join('');

            }
        }
    ];
};

/**
 * 客户详情
 */
Myfollow.detail = function (id) {
    var index = layer.open({
        type: 2,
        title: '客户详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/customer/customer_update/' + id
    });
    this.layerIndex = index;
};

/**
 * 客户成交申请
 */
Myfollow.successapply = function (id) {
    var index = layer.open({
        type: 2,
        title: '客户成交申请',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cusSuccess/success_apply/' + id
    });
    this.layerIndex = index;
};


/**
 * 客户跟进
 */
Myfollow.follow = function (id) {
    var index = layer.open({
        type: 2,
        title: '跟进记录',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cusFollow/cusfollow_record/' + id
    });
    this.layerIndex = index;
};

/**
 * 查询客户管理列表
 */
Myfollow.search = function () {
    var queryData = {};
    queryData['customername'] = $("#customername").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['idcard'] = $("#idcard").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['customertype'] = $("#customertype").val();
    queryData['customerstatus'] = $("#customerstatus").val();
    queryData['importremark'] = $("#importremark").val();
    queryData['iscustomermanager'] = 1;
    Myfollow.table.refresh({query: queryData});
};

/**
 * 标记意向客户
 */
Myfollow.customerstatus = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/customer/cancelcustomerstatushas", function (data) {
        Feng.success("取消意向标记成功!");
        Myfollow.table.refresh();
    }, function (data) {
        Feng.error("意向标记失败!" + data.responseJSON.message + "!");
    });
    ajax.set("customerId",id);
    ajax.start();
};

$(function () {
    var defaultColunms = Myfollow.initColumn();
    var table = new BSTable(Myfollow.id, "/cusFollow/myfollowlist", defaultColunms);
    var queryData = {};
    queryData['iscustomermanager'] = 1;
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    Myfollow.table = table.init();

    //查询导入备注下拉框
    var ajax = new $ax(Feng.ctxPath + "/customer/selectImportRemarkList", function (data) {
        var strHtml = '<option value="">' + '请选择导入备注' + '</option>';
        $.each(data, function (key, val) {
            strHtml += '<option value="' + val.importremark + '">' + val.importremark + '</option>';
        });
        $("#importremark").html(strHtml);
    }, function (data) {
        Feng.error("页面初始化失败!");
    });
    ajax.start();
});
