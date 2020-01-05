/**
 * 客户管理管理初始化
 */
var Cusfollow = {
    id: "cusfollowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Cusfollow.initColumn = function () {
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
                    var pageSize = $('#cusfollowTable').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#cusfollowTable').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '客户姓名', width:100, field: 'customername', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', width:90, field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号码', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
            {title: '客户类型', width:90, field: 'customertypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '客户状态', width:90, field: 'customerstatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据来源', width:90, field: 'datasourcesName', visible: true, align: 'center', valign: 'middle'},
            {title: '导入备注', width:100, field: 'importremark', visible: true, align: 'center', valign: 'middle'},
            {title: '跟进状态', width:90, field: 'flowcount', visible: true, align: 'center', valign: 'middle',
                formatter: function(value, item, index) {
                    if (value==0) {
                        return '未跟进';
                    }
                    else if (value>0) {
                        return '已跟进';
                    }
                }
            },
            {
                title: '操作', field: '', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index, field) {
                    if (row["flowcount"]>0) {
                        return [
                            '<button type="button" onclick="Cusfollow.detail(' + row["id"] + ')" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
                            ,'<button type="button" onclick="Cusfollow.follow(' + row["id"] + ')" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">跟进记录</button>'
                        ].join('');
                    }else if (row["flowcount"]==0) {
                        return [
                            '<button type="button" onclick="Cusfollow.detail(' + row["id"] + ')" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
                        ].join('');
                    }
                }
            }
        ];
};

/**
 * 客户详情
 */
Cusfollow.detail = function (id) {
    var index = layer.open({
        type: 2,
        title: '客户详情',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/customer/customer_detail/' + id
    });
    this.layerIndex = index;
};

/**
 * 客户跟进
 */
Cusfollow.follow = function (id) {
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
 * 查询客户跟进列表
 */
Cusfollow.search = function () {
    var queryData = {};
    queryData['customername'] = $("#customername").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['idcard'] = $("#idcard").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['customertype'] = $("#customertype").val();
    queryData['customerstatus'] = $("#customerstatus").val();
    queryData['datasources'] = $("#datasources").val();
    queryData['iscustomermanager'] = 3;
    queryData['followuserid'] = $("#followuserid").val();
    queryData['importremark'] = $("#importremark").val();
    Cusfollow.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Cusfollow.initColumn();
    var table = new BSTable(Cusfollow.id, "/cusFollow/followlist", defaultColunms);
    var queryData = {};
    queryData['iscustomermanager'] = 3;
    queryData['followuserid'] = 0;
    queryData['customerstatus'] = 0;
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    Cusfollow.table = table.init();

    //查询客户经理下拉框
    var ajax = new $ax(Feng.ctxPath + "/cusFollow/selectCustomerManagerList", function (data) {
        var strHtml = '<option value="0">' + '请选择跟进人' + '</option>';
        $.each(data, function (key, val) {
            strHtml += '<option value="' + val.id + '">' + val.name + '</option>';
        });
        $("#followuserid").html(strHtml);
    }, function (data) {
        Feng.error("页面初始化失败!");
    });
    ajax.start();
});
