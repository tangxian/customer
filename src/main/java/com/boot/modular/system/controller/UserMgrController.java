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
package com.boot.modular.system.controller;

import com.boot.config.properties.GunsProperties;
import com.boot.core.common.annotion.BussinessLog;
import com.boot.core.common.annotion.Permission;
import com.boot.core.common.constant.Const;
import com.boot.core.common.constant.dictmap.UserDict;
import com.boot.core.common.constant.factory.ConstantFactory;
import com.boot.core.common.constant.state.ManagerStatus;
import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.kernel_core.datascope.DataScope;
import com.boot.core.kernel_model.exception.ServiceException;
import com.boot.core.log.LogObjectHolder;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.core.util.DateHelper;
import com.boot.core.util.RSAUtils;
import com.boot.core.util.NetworkResult;
import com.boot.modular.system.factory.UserFactory;
import com.boot.modular.system.model.User;
import com.boot.modular.system.service.IUserService;
import com.boot.modular.system.transfer.UserDto;
import com.boot.modular.system.warpper.UserWarpper;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_core.util.ToolUtil;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    private static String PREFIX = "/system/user/";

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到管理员添加用户页面
     */
    @RequestMapping("/user_add")
    public String addView(Model model) {
        model.addAttribute("publicKey", RSAUtils.generateBase64PublicKey());
        return PREFIX + "user_add.html";
    }


    /**
     * 跳转到角色分配页面
     */
    //@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
    @Permission
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectOne(new EntityWrapper<User>().eq("id", userId));
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getLoginId());
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @Permission
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * 跳转到查看用户详情页面
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return PREFIX + "user_view.html";
    }

    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd(Model model) {
        model.addAttribute("publicKey", RSAUtils.generateBase64PublicKey());
        return PREFIX + "user_chpwd.html";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    @Transactional
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd) {
        newPwd = RSAUtils.decryptBase64(newPwd.trim());
        Integer userId = ShiroKit.getUser().getId();
        User user = userService.selectById(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            user.updateById();
            return SUCCESS_TIP;
        } else {
            throw new ServiceException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid) {
        if (ShiroKit.isAdmin()) {
            List<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptid);
            return new UserWarpper(users).wrap();
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            List<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptid);
            return new UserWarpper(users).wrap();
        }
    }

    /*
     *查询出版社管理员列表
     * */


    /**
     * 添加用户
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加用户", key = "account", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME, Const.LEADER})
    @ResponseBody
    @Transactional
    public NetworkResult add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        user.setPassword(RSAUtils.decryptBase64(user.getPassword().trim()));
        // 判断账号是否重复
        User theUser = userService.getByAccount(user.getLoginId());
        if (theUser != null) {
            throw new ServiceException(BizExceptionEnum.USER_ALREADY_REG);
        }
        //判断邮箱是否重复
        User email = userService.getByEmail(user.getEmail());
        if (email != null) {
            throw new ServiceException(BizExceptionEnum.EMAIL_ALREADY_REG);
        }
        //判断手机号是否重复
        User mobile = userService.getByMobile(user.getMobile());
        if (mobile != null) {
            throw new ServiceException(BizExceptionEnum.MOBLIE_ALREADY_REG);
        }
        // 完善账号信息
        user.setUsercenteruserid("111");
        user.setRoleid(user.getRoleid());
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatedate(new Date());
        user.setUserid(ShiroKit.getUser().id);
        this.userService.insert(UserFactory.createUser(user));
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "loginId", dict = UserDict.class)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public NetworkResult edit(@Valid UserDto user, BindingResult result) throws NoPermissionException {

        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }

        User oldUser = userService.selectById(user.getId());

        //判断用户账号是否存在
        int account = userService.selectCount(new EntityWrapper<User>().eq("login_id", user.getLoginId()));
        if (!oldUser.getLoginId().equals(user.getLoginId())) { //用户修改用户名
            if (account > 0) {
                throw new ServiceException(BizExceptionEnum.USER_ALREADY_REG);
            }
        }
        //判断用户邮箱是否重复
        int email = userService.selectCount(new EntityWrapper<User>().eq("email", user.getEmail()));
        if (!oldUser.getEmail().equals(user.getEmail())) {   //用户修改邮箱
            if (email > 0) {
                throw new ServiceException(BizExceptionEnum.EMAIL_ALREADY_REG);
            }
        }
        //清除缓存
        ConstantFactory.me().removeAllUserCache();
        if (ShiroKit.hasRole(Const.ADMIN_NAME) || ShiroKit.hasRole(Const.LEADER)) {
            try {
                if (oldUser.getEmail().equals(user.getEmail())) { //如果没有修改邮箱
                    this.userService.updateById(UserFactory.editUser(user, oldUser));
                } else {

                    this.userService.updateById(UserFactory.editUser(user, oldUser));
                }
                return SUCCESS_TIP;
            } catch (Exception e) {
                throw new ServiceException(BizExceptionEnum.UNKNOWN_ERRO);
            }
        } else {
            assertAuth(user.getId());
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser.getId().equals(user.getId())) {
                try {
                    if (oldUser.getEmail().equals(user.getEmail())) { //如果没有修改邮箱
                        this.userService.updateById(UserFactory.editUser(user, oldUser));
                    } else {

                        this.userService.updateById(UserFactory.editUser(user, oldUser));
                    }
                    return SUCCESS_TIP;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            } else {
                throw new ServiceException(BizExceptionEnum.NO_PERMITION);
            }
        }
    }

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @Permission({Const.LEADER, Const.ADMIN_NAME})
    @ResponseBody
    public NetworkResult delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME, Const.LEADER})
    @ResponseBody
    @Transactional
    public NetworkResult reset(@RequestParam Integer userId) {
        //随机密码盐ShiroKit.getRandomSalt(5)
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId); //判断用户权限

        User user = this.userService.selectById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        this.userService.updateById(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME, Const.LEADER})
    @ResponseBody
    public NetworkResult freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME, Const.LEADER})
    @ResponseBody
    public NetworkResult unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @Permission({Const.ADMIN_NAME,Const.LEADER})
    @ResponseBody
    public NetworkResult setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

    /**
     * 上传图片
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath() + DateHelper.format(new Date(), "yyyyMM") + "\\";
            File file = new File(fileSavePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return DateHelper.format(new Date(), "yyyyMM") + "/" + pictureName;
    }


    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        if (ShiroKit.isGeneralAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userService.selectById(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new ServiceException(BizExceptionEnum.NO_PERMITION);
        }

    }

    /**
     * 上传文件
     */
    @RequestMapping(method = RequestMethod.POST, path = "/uploadfile")
    @ResponseBody
    public String uploadfile(@RequestPart("file") MultipartFile uploadfile) {
        String pictureName = DateHelper.getNumberForPK() + "." + ToolUtil.getFileSuffix(uploadfile.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath() + DateHelper.format(new Date(), "yyyyMM") + "\\";
            File file = new File(fileSavePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            uploadfile.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new ServiceException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return DateHelper.format(new Date(), "yyyyMM") + "/" + pictureName;
    }
}
