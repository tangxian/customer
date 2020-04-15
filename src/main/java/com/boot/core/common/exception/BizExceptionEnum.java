/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.boot.core.common.exception;


import com.boot.core.kernel_model.exception.AbstractBaseExceptionEnum;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum implements AbstractBaseExceptionEnum {
    /**
     * 客户管理
     */
    ALREADYFOLLOW(400, "该客户已经被其他客户经理跟进，无法再次跟进"),

    /**
     * 字典
     */
    DICT_EXISTED(400, "字典已经存在"),
    ERROR_CREATE_DICT(500, "创建字典失败"),
    ERROR_WRAPPER_FIELD(500, "包装字典属性失败"),
    ERROR_CODE_EMPTY(500, "字典类型不能为空"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),
    UPLOAD_ERROR(500, "上传图片出错"),

    /**
     * 权限和数据问题
     */
    DB_RESOURCE_NULL(400, "数据库中没有该资源"),
    NO_PERMITION(405, "权限异常"),
    REQUEST_INVALIDATE(400, "请求数据格式不正确"),
    INVALID_KAPTCHA(400, "验证码不正确"),
    GET_AUTHCODE_ERRO(400, "验证码获取失败"),
    CANT_DELETE_ADMIN(600, "不能删除超级管理员"),
    CANT_DELETE_LEADER(600, "不能删除公司管理层"),
    CANT_DELETE_DATABASE(600, "不能删除数据管理员"),
    CANT_DELETE_CUSTOMERMANAGER(600, "不能删除客户经理"),
    CANT_DELETE_EDIT(600, "不能删除编辑"),
    CANT_FREEZE_ADMIN(600, "不能冻结超级管理员"),
    CANT_CHANGE_ADMIN(600, "不能修改超级管理员角色"),

    /**
     * 账户问题
     */

    USER_ALREADY_REG(401, "该用户名已经注册"),
    EMAIL_ALREADY_REG(401, "该邮箱已经注册"),
    MOBLIE_ALREADY_REG(401,"该手机号已被注册"),
    NO_THIS_USER(400, "没有此用户"),
    USER_NOT_EXISTED(400, "没有此用户"),
    ACCOUNT_FREEZED(401, "账号被冻结"),
    OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
    TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),
    ACCOUNT_ALREADY_EXIST(401,"用户名或密码已被注册"),

    /**
     * 错误的请求
     */
    MENU_PCODE_COINCIDENCE(400, "菜单编号和副编号不能一致"),
    EXISTED_THE_MENU(400, "菜单编号重复，不能添加"),
    DICT_MUST_BE_NUMBER(400, "字典的值必须为数字"),
    REQUEST_NULL(400, "请求有错误"),
    SESSION_TIMEOUT(400, "会话超时"),
    SERVER_ERROR(500, "服务器异常"),

    /**
     * token异常
     */
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(700, "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(400, "账号密码错误"),
    VERIFICATION_CODE_ERROR(400, "验证码错误"),
    VERIFICATION_CODE_AUTH_ERROR(400, "验证码验证失败"),
    PASSWORD_REST_ERRO(400,"用户不存在"),
    MARK_ERRO(400,"选定的日期范围内无未结算订单!!"),
    UNKNOWN_ERRO(400,"操作失败,请稍后重试"),
    PAYPLATFORM_REEO(400,"该平台收款信息已存在!!"),
    PASSWORD_CHANGE_ERRO(400,"密码修改失败"),


    /**
     * 支付
     */
    recharge_erro(400,"充值失败!!"),
    withdraw_erro(400,"提现失败!!");





    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
