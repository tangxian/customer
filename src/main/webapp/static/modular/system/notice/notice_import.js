/**
 * 初始化通知详情对话框
 */
var NoticeImportDlg = {

};


/**
 * 提交添加
 */
NoticeImportDlg.addSubmit = function () {


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/notice/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Notice.table.refresh();
        NoticeInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.errorMsg + "!");
    });
    ajax.set(this.noticeInfoData);
    ajax.start();
}

$(function () {
    Feng.initValidator("noticeInfoForm", NoticeInfoDlg.validateFields);

    //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#contentVal").val());
    NoticeInfoDlg.editor = editor;
});
