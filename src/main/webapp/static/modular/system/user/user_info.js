/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
        userInfoData: {},
        validateFields: {
            loginId: {
                validators: {
                    notEmpty: {
                        message: '账户不能为空'
                    }
                }
            },
            trueName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            citySel: {
                validators: {
                    notEmpty: {
                        message: '部门不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'rePassword',
                        message: '两次密码不一致'
                    },
                }
            },
            rePassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '两次密码不一致'
                    },
                }
            },
            linkman: {
                validators: {
                    notEmpty: {
                        message: '联系人不能为空'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    }
                }
            },
            mobile: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    }
                }
            }
        }
    }
;

/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, value) {
    if (typeof value == "undefined") {
        if (typeof $("#" + key).val() == "undefined") {
            var str = "";
            var ids = "";
            $("input[name='" + key + "']:checkbox").each(function () {
                if (true == $(this).is(':checked')) {
                    str += $(this).val() + ",";
                }
            });
            if (str) {
                if (str.substr(str.length - 1) == ',') {
                    ids = str.substr(0, str.length - 1);
                }
            } else {
                $("input[name='" + key + "']:radio").each(function () {
                    if (true == $(this).is(':checked')) {
                        ids = $(this).val()
                    }
                });
            }
            this.userInfoData[key] = ids;
        } else {
            this.userInfoData[key] = $("#" + key).val();
        }
    }

    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
UserInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
UserInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
UserInfoDlg.showInfoDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityPosition = $("#citySel").position();
    $("#menuContent").css({
        left: cityPosition.left + "px",
        top: cityPosition.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
UserInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set('id').set('loginId').set('sex').set('photo').set("roleid").set("linkman")
        .set('email').set('trueName').set('birthday').set('deptid').set('mobile');
};


/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
UserInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/add", function (data) {
        Feng.success("添加成功!");
        window.parent.MgrUser.table.refresh();
        UserInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.errorMsg + "!");
    });
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    var password = encrypt.encrypt($('#password').val().trim());
    ajax.set(this.userInfoData);
    ajax.set("password",password);
    ajax.start();
};

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/edit", function (data) {
        Feng.success("修改成功!");
        if (window.parent.MgrUser != undefined) {
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.errorMsg + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 修改密码
 */
UserInfoDlg.chPwd = function () {
    if ($('#oldPwd').val().trim()==0){
        Feng.error("原密码不能为空");
        return;
    }
    if ($('#newPwd').val().trim()==0){
        Feng.error("新密码不能为空");
        return;
    }
    if ($('#rePwd').val().trim()==0){
        Feng.error("新密码验证不能为空");
        return;
    }
    if ($('#rePwd').val().trim()!= $('#newPwd').val().trim()){
        Feng.error("两次密码不一致");
        return;
    }
    var ajax = new $ax(Feng.ctxPath + "/mgr/changePwd", function (data) {
        Feng.success("修改成功!");
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.errorMsg + "!");
    });
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    var newPwd = encrypt.encrypt($('#newPwd').val().trim());
    ajax.set("oldPwd");
    ajax.set("newPwd",newPwd);
    ajax.start();

};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
        event.target).parents("#menuContent").length > 0)) {
        UserInfoDlg.hideDeptSelectTree();
    }
}

/**
 * 获取验证码
 */
UserInfoDlg.getVerificationCode = function () {
    var mobile = $("#mobile").val();
    if (mobile.length == 0) {
        Feng.error("请输入手机号");
        return false;
    }
    var regphone = /^(1[0-9][0-9])\d{8}$/;
    if (!regphone.test(mobile)) {
        Feng.error("请输入正确格式手机号");
        return false;
    }
    $("#btn_verificationcode").attr("disabled", true);
    $("#btn_verificationcode").html("获取验证码 60s");
    setTimeout("fun(60)", 1000)
    //获取验证码
    var ajax = new $ax(Feng.ctxPath + "/mgr/generateVerificationCode", function (data) {
        if (data.errorCode != 200) {
            Feng.error("验证码发送失败!" + data.errorMsg);
        }
    }, function (data) {
        Feng.error("获取短信验证码失败!");
    });
    ajax.set("mobile", mobile);
    ajax.start();

}

function fun(n) {
    if (n > 0) {
        n--;
        $("#btn_verificationcode").html("获取验证码 " + n + "s");
        $("#btn_verificationcode").attr("disabled", true);
        setTimeout("fun(" + n + ")", 1000)
    } else {
        $("#btn_verificationcode").html("获取验证码");
        $("#btn_verificationcode").attr("disabled", false);
    }
}


$(function () {
    Feng.initValidator("userInfoForm", UserInfoDlg.validateFields);

    //初始化性别选项
    if ($("#sexValue").val() != "" && typeof($("#sexValue").val()) != "undefined") {
        $("#sex").val($("#sexValue").val());
    }
    var ztree = new $ZTree("treeDemo", "/dept/tree");
    ztree.bindOnClick(UserInfoDlg.onClickDept);
    ztree.init();
    instance = ztree;

    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

    //查询角色下拉框
    var ajax = new $ax(Feng.ctxPath + "/role/getRoleList", function (data) {

        var strHtml = '';
        $.each(data, function (key, val) {
            strHtml += '<option value="' + val.id + '">' + val.name + '</option>';
        });
        $("#roleid").html(strHtml);
    }, function (data) {
        Feng.error("页面初始化失败!");
    });
    ajax.set("code", "apptype");
    ajax.start();
    //初始化角色
    if ($("#defaultId").val() != "" && typeof($("#defaultId").val()) != "undefined"&&$("#defaultId").val() != "0") {
        $("#roleid").val($("#defaultId").val());
    }
});
