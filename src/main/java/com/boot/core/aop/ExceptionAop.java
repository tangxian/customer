package com.boot.core.aop;

import com.boot.core.common.exception.ServerErrorException;
import com.boot.core.kernel_core.util.ToolUtil;
import com.boot.core.kernel_model.exception.ServiceException;
import com.boot.core.log.LogManager;
import com.boot.core.log.factory.LogTaskFactory;
import com.boot.core.shiro.ShiroKit;
import com.boot.core.util.NetworkResult;
import org.apache.shiro.authc.CredentialsException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.boot.core.kernel_core.util.HttpContext.getIp;
import static com.boot.core.kernel_core.util.HttpContext.getRequest;


/**
 * 异常捕获AOP
 */
@Aspect
@Component
@ResponseBody
public class ExceptionAop {
    @Autowired(required = false)
    private HttpServletResponse response;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.boot.*..controller.*.*(..))")
    public void exceptionPoint() {
    }

    @Around("exceptionPoint()")
    public Object exceptionIntercept(ProceedingJoinPoint point) throws Throwable {
        Map map = new HashMap();
        try {
            Signature signature = point.getSignature();
            String declaringTypeName = signature.getDeclaringTypeName();//LoginController
            String methodName = signature.getName();//login
            map.put("declaringTypeName", declaringTypeName);
            map.put("methodName", methodName);
            return point.proceed();
        } catch (CredentialsException e) {//账号密码错误异常
            String username = getRequest().getParameter("username");
            LogManager.me().executeLog(LogTaskFactory.loginLog(username, e.getMessage(), getIp()));
            return "/login.html";
        } catch (ServerErrorException e) {//远程对接服务器异常
            Throwable cause = e.getCause();
            //备注
            String exceptionMsg = "";
            if (cause != null) {
                exceptionMsg = ToolUtil.getExceptionMsg(cause);
            } else {
                exceptionMsg = ToolUtil.getExceptionMsg(e);
            }
            String message = e.getMessage();//日志名称
            map.put("exceptionMsg", exceptionMsg);
            map.put("bussinessName", message);
            String username = getRequest().getParameter("username");
            LogManager.me().executeLog(LogTaskFactory.loginLog(username, "服务器异常", getIp()));
            LogManager.me().executeLog(LogTaskFactory.exceptionLog(null, map));
            log.error("服务器异常", e.getCause());
            return "/login.html";
        } catch (ServiceException e) {//拦截业务异常 不需要存日志
            getRequest().setAttribute("tip", e.getMessage());
            if (e.getCause() != null) {
                log.error("业务异常:", e.getCause());
            } else {
                log.error("业务异常:", e);
            }
            return getResponseData(e.getCode(), e.getErrorMessage());
        } catch (Exception e) {//拦截所有过滤后异常
            Throwable cause = e.getCause();
            String exceptionMsg = "";
            if (cause != null) {
                exceptionMsg = ToolUtil.getExceptionMsg(cause);
            } else {
                exceptionMsg = ToolUtil.getExceptionMsg(e);
            }
            String message = e.getMessage();//日志类型
            map.put("exceptionMsg", exceptionMsg);
            map.put("bussinessName", message);
            LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser() == null ? 0 : ShiroKit.getUser().getId(), map));
            getRequest().setAttribute("tip", "服务器未知运行时异常");
            log.error("运行时异常:", e.getCause());
            return getResponseData();
        }
    }

    public Object getResponseData() {
        response.setStatus(500);
        return new NetworkResult("500", "服务器异常");
    }

    public Object getResponseData(Integer code, String message) {
        response.setStatus(code);
        return new NetworkResult(code+"", message);
    }

}
