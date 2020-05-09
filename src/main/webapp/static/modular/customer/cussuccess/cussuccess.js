/**
 * 客户成交初始化
 */
var CusSuccess = {
    id: "cusSuccessTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CusSuccess.initColumn = function () {
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
                var pageSize = $('#cusSuccessTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber = $('#cusSuccessTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return pageSize * (pageNumber - 1) + index + 1;
            }
        },
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '客户姓名', width:90, field: 'customername', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号码', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
        {title: '客户类型', width:90, field: 'customertypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '导入备注', width:90, field: 'importremark', visible: true, align: 'center', valign: 'middle'},
        {title: '成交人', width:90, field: 'successuserName', visible: true, align: 'center', valign: 'middle'},
        {title: '成交时间', field: 'successdate', visible: true, align: 'center', valign: 'middle'},
        {title: '贷款银行', field: 'bank', visible: true, align: 'center', valign: 'middle'},
        {title: '贷款金额', width:90, field: 'amount', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', width:80, field: 'statusName', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', field: '', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index, field) {
                return [
                    '<button type="button" onclick="CusSuccess.detail(' + row["id"] + ')" class="btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
                ].join('');
            }
        }
    ];
};

/**
 * 客户详情
 */
CusSuccess.detail = function (id) {
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
 * 查询客户管理列表
 */
CusSuccess.search = function () {
    var queryData = {};
    queryData['customername'] = $("#customername").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['idcard'] = $("#idcard").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['customertype'] = $("#customertype").val();
    queryData['customerstatus'] = $("#customerstatus").val();
    queryData['successuserid'] = $("#successuserid").val();
    queryData['importremark'] = $("#importremark").val();
    CusSuccess.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CusSuccess.initColumn();
    var table = new BSTable(CusSuccess.id, "/cusSuccess/successlist", defaultColunms);
    var queryData = {};
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    CusSuccess.table = table.init();

    //查询导入备注下拉框
    var ajax = new $ax(Feng.ctxPath + "/customer/selectImportRemarkList", function (data) {
        var strHtml = '<option value="">' + '请选择导入备注' + '</option>';
        $.each(data, function (key, val) {
            strHtml += '<option value="' + val.importnumber + '">' + val.importremark + '</option>';
        });
        $("#importremark").html(strHtml);
    }, function (data) {
        Feng.error("页面初始化失败!");
    });
    ajax.start();

    //查询客户经理下拉框
    var ajax = new $ax(Feng.ctxPath + "/cusFollow/selectCustomerManagerList", function (data) {
        var strHtml = '<option value="0">' + '请选择跟进人' + '</option>';
        $.each(data, function (key, val) {
            strHtml += '<option value="' + val.id + '">' + val.name + '</option>';
        });
        $("#successuserid").html(strHtml);
    }, function (data) {
        Feng.error("页面初始化失败!");
    });
    ajax.start();
});
