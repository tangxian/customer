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

import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.common.exception.InvalidKaptchaException;
import com.boot.core.common.exception.ServerErrorException;
import com.boot.core.common.node.MenuNode;
import com.boot.core.log.LogManager;
import com.boot.core.log.factory.LogTaskFactory;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.shiro.ShiroUser;
import com.boot.core.util.ApiMenuFilter;
import com.boot.core.util.KaptchaUtil;
import com.boot.core.util.RSAUtils;
import com.boot.modular.system.model.User;
import com.boot.modular.system.service.IMenuService;
import com.boot.modular.system.service.IUserService;
import com.boot.core.kernel_core.base.controller.BaseController;
import com.boot.core.kernel_core.util.ToolUtil;
import com.boot.core.kernel_core.util.HttpContext;
import com.google.code.kaptcha.Constants;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        //获取菜单列表
        List<Integer> roleList = ShiroKit.getUser().getRoleList();
        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return "/login.html";
        }

        List<MenuNode> menus = menuService.getMenusByRoleIds(roleList);


        List<MenuNode> titles = MenuNode.buildTitle(menus);
        titles = ApiMenuFilter.build(titles);
        model.addAttribute("titles", titles);

        //获取用户头像
        Integer id = ShiroKit.getUser().getId();
        User user = userService.selectById(id);
        String avatar = user.getPhoto();
        model.addAttribute("avatar", avatar);

        return "/index.html";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            model.addAttribute("publicKey", RSAUtils.generateBase64PublicKey());
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali(Model model) {
        String username = "";
        String password = "";
        String remember = "";
        try {
            username =super.getPara("username").trim();
            password = RSAUtils.decryptBase64(super.getPara("password").trim());
            //password = new Md5Hash(password).toString();
            remember = super.getPara("remember");
            //验证验证码是否正确
            if (KaptchaUtil.getKaptchaOnOff()) {
                String kaptcha = super.getPara("kaptcha").trim();
                String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
                if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                    throw new InvalidKaptchaException();
                }
            }

            Subject currentUser = ShiroKit.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);///111111

            if ("on".equals(remember)) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            try {
                currentUser.login(token);
            }catch (LockedAccountException e){
                //用户冻结
                model.addAttribute("tips", BizExceptionEnum.ACCOUNT_FREEZED.getMessage());
                model.addAttribute("publicKey", RSAUtils.generateBase64PublicKey());
                throw new CredentialsException(BizExceptionEnum.ACCOUNT_FREEZED.getMessage());
            }
            ShiroUser shiroUser = ShiroKit.getUser();
            super.getSession().setAttribute("shiroUser", shiroUser);
            super.getSession().setAttribute("username", shiroUser.getAccount());

            LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), HttpContext.getIp()));


            ShiroKit.getSession().setAttribute("sessionFlag", true);
        } catch (CredentialsException e) {
            model.addAttribute("tips", "用户名密码错误");
            model.addAttribute("publicKey", RSAUtils.generateBase64PublicKey());
            throw new CredentialsException(e.getMessage());
        } catch (Exception e) {
            model.addAttribute("tips", "服务器异常");
            model.addAttribute("publicKey", RSAUtils.generateBase64PublicKey());
            throw new ServerErrorException(e);
        }


        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), HttpContext.getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();
        return REDIRECT + "/login";
    }
}
