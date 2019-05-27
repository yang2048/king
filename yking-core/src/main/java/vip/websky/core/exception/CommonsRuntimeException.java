package vip.websky.core.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import vip.websky.core.config.prompt.StatusCode;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2018/7/22 11:59
 * @Description:
 */
@Slf4j
@Getter
public class CommonsRuntimeException extends RuntimeException implements Serializable {
    private String code;
    private String message;

    /**
     * 错误信息模板
     */
    private final static String MSG_TEMPLATE = "\n 状态码:{0}\n 异常信息:{1}\n 描述:{2}";

    public CommonsRuntimeException(String code) {
        this.code = code;
        this.message = StatusCode.getKeyValue(code);
    }

    public CommonsRuntimeException(String code, String message) {
        this(code, message, null);
    }

    public CommonsRuntimeException(String code, String message, Throwable cause) {
        super(getExceptionMessage(code, message, cause), cause);
        this.code = code;
        this.message = message;
    }

    private static String getExceptionMessage(String code, String message, Throwable cause) {
        String extraMessage = "";
        if (null != cause && StrUtil.isNotBlank(cause.getMessage())) {
            extraMessage = cause.getMessage();
        }
        return MessageFormat.format(MSG_TEMPLATE, code, message, extraMessage);
    }
}
