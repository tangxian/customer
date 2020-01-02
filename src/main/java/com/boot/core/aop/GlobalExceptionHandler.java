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
package com.boot.core.aop;

import com.boot.core.common.exception.BizExceptionEnum;
import com.boot.core.common.exception.InvalidKaptchaException;
import com.boot.core.util.NetworkResult;
import com.boot.core.log.LogManager;
import com.boot.core.log.factory.LogTaskFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;

import static com.boot.core.kernel_core.util.HttpContext.getIp;
import static com.boot.core.kernel_core.util.HttpContext.getRequest;


/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常 不需要存日志
     */
   /* @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public NetworkResult bussiness(ServiceException e) {
        getRequest().setAttribute("tip", e.getMessage());
        log.error("业务异常:", e);
        return new NetworkResult(e.getCode(), e.getMessage());
    }*/

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e);
        return "/login.html";
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(DisabledAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String accountLocked(DisabledAccountException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
        model.addAttribute("tips", "账号被冻结");
        return "/login.html";
    }

    /**
     * 账号密码错误异常
     */
  /*  @ExceptionHandler({CredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(CredentialsException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, e.getMessage(), getIp()));
        model.addAttribute("tips", e.getMessage());
        return "/login.html";
    }*/

    /**
     * 远程对接服务器异常
     */
    /*@ExceptionHandler({ServerErrorException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(ServerErrorException e, Model model) {
        String username = getRequest().getParameter("username");
        String message = e.getMessage();
        Map<String,String> map = JSON.parseObject(message, Map.class);
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "服务器异常", getIp()));
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(null, map));
        log.error("服务器异常", e);
        model.addAttribute("tips", "服务器异常");
        return "/login.html";
    }*/

    /**
     * 验证码错误异常
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String credentials(InvalidKaptchaException e, Model model) {
        String username = getRequest().getParameter("username");
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "验证码错误", getIp()));
        model.addAttribute("tips", "验证码错误");
        return "/login.html";
    }

    /**
     * 无权访问该资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public NetworkResult credentials(UndeclaredThrowableException e) {
        getRequest().setAttribute("tip", "权限异常");
        log.error("权限异常!", e);
        return new NetworkResult(BizExceptionEnum.NO_PERMITION.getCode()+"", BizExceptionEnum.NO_PERMITION.getMessage());
    }

    /**
     * 拦截所有过后异常
     */
    /*@ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public NetworkResult notFount(RuntimeException e) {
        String message = e.getMessage();
        Map<String,String> map = JSON.parseObject(message, Map.class);
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser()==null?0:ShiroKit.getUser().getId(), map));
        getRequest().setAttribute("tip", "服务器未知运行时异常");
        log.error("运行时异常:", e);
        return new NetworkResult(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
    }*/
}
