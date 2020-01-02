/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
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
 *//*

package com.boot.core.kernel_core.exception;

import com.boot.modular.api.util.NetworkResult;
import com.boot.core.kernel_core.reqres.response.ResponseData;
import ApiServiceException;
import RequestEmptyException;
import ServiceException;
import CoreExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static AopSortConstants.DEFAULT_EXCEPTION_HANDLER_SORT;

*/
/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 *//*

@ControllerAdvice
@Order(DEFAULT_EXCEPTION_HANDLER_SORT)
public class DefaultExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    */
/**
     * 拦截各个服务的具体异常
     *//*

    @ExceptionHandler(ApiServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData apiService(ApiServiceException e) {
        log.error("服务具体异常:", e);
        NetworkResult NetworkResult = new NetworkResult(e.getCode(), e.getErrorMessage());
        NetworkResult.setExceptionClazz(e.getExceptionClassName());
        return NetworkResult;
    }

    */
/**
     * 拦截请求为空的异常
     *//*

    @ExceptionHandler(RequestEmptyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData emptyRequest(RequestEmptyException e) {
        return new NetworkResult(e.getCode(), e.getErrorMessage());
    }

    */
/**
     * 拦截业务异常
     *//*

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData notFount(ServiceException e) {
        log.info("业务异常:", e);
        return new NetworkResult(e.getCode(), e.getErrorMessage());
    }

    */
/**
     * 拦截未知的运行时异常
     *//*

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData serverError(Exception e) {
        log.error("运行时异常:", e);
        return new NetworkResult(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage());
    }

}
*/
