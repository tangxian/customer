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
package com.boot.core.common.constant.factory;

import com.boot.modular.system.model.Dict;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 常量生产工厂的接口
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     */
    String getUserNameById(Integer userId);

    /**
     * 根据用户id获取用户账号
     */
    String getUserAccountById(Integer userId);

    /**
     * 删除所有的用户缓存
     **/
    public void removeAllUserCache();

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Integer roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Integer roleId);

    /**
     * 删除所有的权限缓存
     **/
    void removeAllRoleCache();

    /**
     * 获取部门名称
     */
    String getDeptName(Integer deptId);

    /**
     * 删除所有的部门缓存
     **/
    public void removeAllDeptCache();

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(Integer dictId);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Integer dictId);

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    String getDictsByName(String name, Integer val);

    /**
     * 根据字典名称和字典中的值删除缓存中的记录
     */
    public void delDictsByName(String name, Integer val);

    /**
     * 根据父类编码获取词典列表
     */
    Object getDictsByParentCode(String code);

    /**
     * 根据父类编码删除词典列表
     */
    void delDictsByParentCode(String code);

    /**
     * 获取性别名称
     */
    String getSexName(Integer sex);

    /**
     * 获取资源类型名称
     */
    public String getResourceitemName(String resourceitem);

    /**
     * 解决简介乱码
     */
    public String getIntroName(String intro) throws UnsupportedEncodingException;

    /**
     * 获取用户登录状态
     */
    String getStatusName(Integer status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(Integer status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Integer id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Integer> getSubDeptId(Integer deptid);

    /**
     * 获取所有父部门id
     */
    List<Integer> getParentDeptIds(Integer deptid);

    /**
     * 通过手机号生成验证码
     */
    String generateVerifyCode(String phonenum);

    /**
     * 通过手机号获取验证码
     */
    String getVerifyCode(String phonenum);


    /**
     * 通过usercenteruserid存入token
     *
     * @param usercenteruserid
     * @param token
     * @return
     */
    public String setUsercenterToken(String usercenteruserid, String token);

    /**
     * 通过usercenteruserid获取token
     *
     * @param usercenteruserid
     * @return
     */
    public String getUsercenterToken(String usercenteruserid);
}
