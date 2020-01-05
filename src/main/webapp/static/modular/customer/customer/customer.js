/**
 * 客户管理管理初始化
 */
var Customer = {
    id: "customerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Customer.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {
                field: 'SerialNumber',
                title: '序号',
                sortable: false,
                align: "center",
                width: 45,
                formatter: function (value, row, index) {
                    //获取每页显示的数量
                    var pageSize = $('#customerTable').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#customerTable').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '客户姓名', width: 90, field: 'customername', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号码', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
            {title: '客户类型', field: 'customertypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '客户状态', width: 90 , field: 'customerstatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据来源', width: 90, field: 'datasourcesName', visible: true, align: 'center', valign: 'middle'},
            {title: '导入备注', field: 'importremark', visible: true, align: 'center', valign: 'middle'},
            {title: '跟进状态', width: 90, field: 'flowcount', visible: true, align: 'center', valign: 'middle',
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
                if (row["customerstatus"]==1) {
                    return [
                        '<button type="button" onclick="Customer.customerstatus(' + row["id"] + ')" class="RoleOfedit btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">意向标记</button>'
                    ].join('');
                }else if (row["customerstatus"]>0) {
                    //return ;
                }
            }
        }
    ];
};

/**
 * 检查是否选中
 */
Customer.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else if(selected.length > 1){
        Feng.info("只能选择一条记录进行修改！");
        return false;
    }else{
        Customer.seItem = selected[0];
        return true;
    }
};

/**
 * 获取多选按钮选中值
 */
Customer.checkbox = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    var ids = new Array();
    for (var i = 0; i < selected.length; i++) {
        ids[i] = selected[i].id;
    }
    return ids;
}
/**
 * 点击添加客户管理
 */
Customer.openAddcustomer = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户管理',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/customer/customer_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看客户管理详情
 */
Customer.opencustomerDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '客户管理详情',
            area: ['90%', '90%'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/customer/customer_update/' + Customer.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除客户管理
 */
Customer.delete = function () {
    var ids = this.checkbox();
    console.log(ids.join(","));
    if(ids.length==0){
        Feng.info("请先选中表格中的某一记录！");
        return;
    }else{
        var ajax = new $ax(Feng.ctxPath + "/customer/delete", function (data) {
            Feng.success("删除成功!");
            Customer.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("customerIds",ids.join(","));
        ajax.start();
    }
};

/**
 * 标记意向客户
 */
Customer.customerstatus = function (id) {
    var ajax = new $ax(Feng.ctxPath + "/customer/customerstatushas", function (data) {
        Feng.success("意向标记成功!");
        Customer.table.refresh();
    }, function (data) {
        Feng.error("意向标记失败!" + data.responseJSON.message + "!");
    });
    ajax.set("customerId",id);
    ajax.start();
};

/**
 * 查询客户管理列表
 */
Customer.search = function () {
    var queryData = {};
    queryData['customername'] = $("#customername").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['idcard'] = $("#idcard").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['customertype'] = $("#customertype").val();
    queryData['customerstatus'] = $("#customerstatus").val();
    queryData['datasources'] = $("#datasources").val();
    queryData['importremark'] = $("#importremark").val();
    queryData['iscustomermanager'] = 0;
    Customer.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Customer.initColumn();
    var table = new BSTable(Customer.id, "/customer/list", defaultColunms);
    var queryData = {};
    queryData['iscustomermanager'] = 0;
    queryData['customerstatus'] = 0;
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    Customer.table = table.init();
});
