package io.github.rothschil.common.response.handler;


import io.github.rothschil.common.constant.Constants;
import io.github.rothschil.common.response.Result;
import io.github.rothschil.common.response.interceptor.ResponseBodyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;


/**
 * 统一处理响应的消息内容，默认引入的包路径是 xyz.wongs，编写过程中需要格外注意。在需要响应的内容做标记，
 * 继而在 {@link ResponseBodyInterceptor} 基础上捕获该标记，再次处理
 * 默认处理 {@link Controller},RestController.class,
 *
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @version 1.0.0
 */
@RestControllerAdvice(basePackages = "cn.ffcs.up.ivr.controller")
@Primary
@Slf4j
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否要执行 beforeBodyWrite 方法
     *
     * @param returnType    返回类型
     * @param converterType 转换类型
     * @return boolean true:执行，false:不执行，有注解标记的时候处理返回值
     */
    @Override
    public boolean supports(@Nullable MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(sra, "The ServletRequestAttributes must not be null");
        Assert.notNull(returnType, "The MethodParameter must not be null");
        // 先将 Controller 中含有 的 ResponseBody 注解优先甄别出来
        if (returnType.hasMethodAnnotation(ResponseBody.class)) {
            return true;
        }
        HttpServletRequest request = sra.getRequest();
        RestController cller = (RestController) request.getAttribute(Constants.RESPONSE_RESULT_ANN);
        return cller != null;
    }

    /**
     * 如果响应内容已经是 定义 {@link Result} 和 {@link Result} ，就不需要再额外处理，否则需要再包装一层
     *
     * @param body                响应消息
     * @param returnType          响应类型
     * @param selectContentType   可选内容类型
     * @param selectConverterType 选择覆盖类型
     * @param request             Request请求
     * @param response            Response响应
     * @return Object 响应内容
     * @author <a href="https://github.com/rothschil">Sam</a>
     **/
    @Override
    public Object beforeBodyWrite(Object body, @Nullable MethodParameter returnType, @Nullable MediaType selectContentType, @Nullable Class<? extends HttpMessageConverter<?>> selectConverterType, @Nullable ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        if (vail(body)) {
            return body;
        }
        return Result.success(body);
    }

    /**
     * 判断返回的 body 是哪一种类型，可以按照定义被排除的部分
     *
     * @param body 响应消息
     * @return boolean
     * @author <a href="https://github.com/rothschil">Sam</a>
     **/
    private boolean vail(Object body) {
        return (body instanceof Result);
    }
}
