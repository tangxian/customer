/**
 * 客户成交初始化
 */
var Cusimport = {
    id: "cusImportTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Cusimport.initColumn = function () {
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
                var pageSize = $('#cusImportTable').bootstrapTable('getOptions').pageSize;
                //获取当前是第几页
                var pageNumber = $('#cusImportTable').bootstrapTable('getOptions').pageNumber;
                //返回序号，注意index是从0开始的，所以要加上1
                return pageSize * (pageNumber - 1) + index + 1;
            }
        },
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '导入数据备注', field: 'importremark', visible: true, align: 'center', valign: 'middle'},
        {title: '导入数据流水号', field: 'importnumber', visible: true, align: 'center', valign: 'middle'},
        {title: '导入时间', field: 'importdate', visible: true, align: 'center', valign: 'middle'},
        {
            title: '操作', field: '', visible: true, align: 'center', valign: 'middle',
            formatter: function (value, row, index, field) {
                return [
                    '<button type="button" onclick="Cusimport.delete(' + row["id"] + ')" class="btn btn-primary  btn-sm" style="margin-right:15px;    margin-bottom: 0px;">删除</button>'
                ].join('');
            }
        }
    ];
};

/**
 * 删除导入客户数据
 */
Cusimport.delete = function (id) {
    Feng.confirm("删除将清空该导入批次所有客户信息无法恢复,是否确认删除?",function() {
        var ajax = new $ax(Feng.ctxPath + "/cusImport/delete", function (data) {
            Feng.success("删除成功!");
            Cusimport.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", id);
        ajax.start();
    })
};

/**
 * 查询客户管理列表
 */
Cusimport.search = function () {
    var queryData = {};
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['importremark'] = $("#importremark").val();
    Cusimport.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Cusimport.initColumn();
    var table = new BSTable(Cusimport.id, "/cusImport/cusimportlist", defaultColunms);
    var queryData = {};
    table.setQueryParams(queryData);
    table.setPaginationType("server");
    table.setHeight(624);
    Cusimport.table = table.init();

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
