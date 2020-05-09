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
        {field: 'selectItem', radio: true, visible: false},
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
            {title: '客户姓名', width:100, field: 'customername', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', width:100, field: 'mobile', visible: true, align: 'center', valign: 'middle'},
            {title: '身份证号码', field: 'idcard', visible: true, align: 'center', valign: 'middle'},
            {title: '客户类型', width:110, field: 'customertypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '客户状态', width:90, field: 'customerstatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', width:140, field: 'createdate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据来源', width:100, field: 'datasourcesName', visible: true, align: 'center', valign: 'middle'},
            {title: '导入备注', width:140, field: 'importremark', visible: true, align: 'center', valign: 'middle'},
            {
                title: '操作', field: '', visible: true, align: 'center', valign: 'middle',
                formatter: function (value, row, index, field) {
                    if (row["mobile"]!=null&&row["mobile"].length>0) {
                        return [
                            '<button type="button" onclick="Customer.detail(' + row["id"] + ')" class="btn btn-primary btn-xs" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
                            ,'<button type="button" onclick="Customer.follow(' + row["id"] + ')" class="btn btn-primary btn-xs" style="margin-right:15px;    margin-bottom: 0px;">意向</button>'
                        ].join('');
                    }else {
                        return [
                            '<button type="button" onclick="Customer.detail(' + row["id"] + ')" class="btn btn-primary btn-xs" style="margin-right:15px;    margin-bottom: 0px;">详情</button>'
                            ,'<button type="button" onclick="Customer.follow(' + row["id"] + ')" class="btn btn-primary btn-xs" style="margin-right:15px;    margin-bottom: 0px;">意向</button>'
                            ,'<button type="button" onclick="Customer.delete(' + row["id"] + ')" class="btn btn-primary btn-xs" style="margin-right:15px;    margin-bottom: 0px;">删除</button>'
                        ].join('');
                    }

                }
            }
    ];
};

/**
 * 客户详情
 */
Customer.detail = function (id) {
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
Customer.follow = function (id) {
    var index = layer.open({
        type: 2,
        title: '客户意向标记',
        area: ['90%', '90%'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/customer/customer_follow/' + id
    });
    this.layerIndex = index;
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
 * 删除客户管理
 */
Customer.delete = function (id) {
    Feng.confirm("删除将无法恢复,是否确认删除?",function() {
        var ajax = new $ax(Feng.ctxPath + "/customer/delete", function (data) {
            Feng.success("删除成功!");
            Customer.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("customerIds", id);
        ajax.start();
    })
};

/**
 * 检查是否选中
 */
Customer.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Customer.seItem = selected[0];
        return true;
    }
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
    queryData['iscustomermanager'] = 1;
    Customer.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Customer.initColumn();
    var table = new BSTable(Customer.id, "/customer/managerlist", defaultColunms);
    var queryData = {};
    queryData['iscustomermanager'] = 1;
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    Customer.table = table.init();

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
});
