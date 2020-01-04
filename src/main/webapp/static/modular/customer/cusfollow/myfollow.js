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
        {title: '客户姓名', field: 'customername', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '身份证号码', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
        {title: '客户类型', field: 'customertypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '客户状态', field: 'customerstatusName', visible: true, align: 'center', valign: 'middle'},
        {title: '跟进时间', field: 'followdate', visible: true, align: 'center', valign: 'middle'},
        {title: '跟进内容', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', field: '', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index, field) {
                return [
                    '<button type="button" onclick="Myfollow.detail(' + row["id"] + ')" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
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
        content: Feng.ctxPath + '/customer/customer_detail/' + id
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
    Myfollow.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Myfollow.initColumn();
    var table = new BSTable(Myfollow.id, "/cusFollow/myfollowlist", defaultColunms);
    var queryData = {};
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    Myfollow.table = table.init();
});
