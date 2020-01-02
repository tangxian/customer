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
package com.boot.core.common.constant.cache;

/**
 * 缓存标识前缀集合,常用在ConstantFactory类中
 */
public interface CacheKey {

    /**
     * 用户truename
     */
    String TRUENAME_USER_ID = "truename_user_id_";

    /**
     * 用户loginid
     */
    String LOGINID_USER_ID = "loginid_user_id_";

    /**
     * 角色名称(多个)
     */
    String ROLES_NAME = "roles_name_";

    /**
     * 角色名称(单个)
     */
    String SINGLE_ROLE_NAME = "single_role_name_";

    /**
     * 角色英文名称
     */
    String SINGLE_ROLE_TIP = "single_role_tip_";

    /**
     * 部门名称
     */
    String DEPT_NAME = "dept_name_";
    
    /**
     * 手机号
     */
    String PHONE_NUM = "phone_num_";
    /**
     * developerid
     */
    String DEVELOPER_ID="developer_id";
    /**
     * 用户中心userId
     */
    String USERCENTERUSER_ID="usercenteruser_id";
}
