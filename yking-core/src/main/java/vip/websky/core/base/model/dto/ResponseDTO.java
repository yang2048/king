package vip.websky.core.base.model.dto;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import vip.websky.core.config.prompt.StatusCode;

import java.io.Serializable;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2018/7/1 0:54
 */
@Slf4j
@Data
public class ResponseDTO<T> implements Serializable {

    private boolean success;
    private String rspCode;
    private String rspMsg;
    private T result;
    private String debuggerMsg;

    public void setResult(T result) {
        this.success = true;
        this.rspCode = StatusCode.RTN_CODE_SUCCESS;
        this.rspMsg = "OK";
        this.result = result;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
        this.rspMsg = getRspMsg(rspCode);
    }

    private String getRspMsg(String rspCode) {
        if (StrUtil.isEmpty(rspMsg)) {
            this.rspMsg = StatusCode.getKeyValue(rspCode);
        }
        return rspMsg;
    }

    private ResponseDTO() {
        this.success = true;
        this.rspCode = StatusCode.RTN_CODE_SUCCESS;
        this.rspMsg = "OK";
    }

    private ResponseDTO(String rspCode) {
        this.success = false;
        this.rspCode = rspCode;
        this.rspMsg = getRspMsg(rspCode);
    }

    private ResponseDTO(T result) {
        this.success = true;
        this.rspCode = StatusCode.RTN_CODE_SUCCESS;
        this.rspMsg = "OK";
        this.result = result;
    }

    private ResponseDTO(String rspCode, String rspMsg) {
        this.success = false;
        this.rspCode = rspCode;
        this.rspMsg = StrUtil.isNotEmpty(rspMsg) ? rspMsg : getRspMsg(rspCode);
    }

    private ResponseDTO(String rspCode, String rspMsg, String debuggerMsg) {
        this.success = false;
        this.rspCode = rspCode;
        this.rspMsg = StrUtil.isNotEmpty(rspMsg) ? rspMsg : getRspMsg(rspCode);
        this.debuggerMsg = debuggerMsg;
    }

    private ResponseDTO(String rspCode, T result) {
        this.success = false;
        this.rspCode = rspCode;
        this.rspMsg = getRspMsg(rspCode);
        this.result = result;
    }

    private ResponseDTO(String rspCode, String rspMsg, T result) {
        this.success = false;
        this.rspCode = rspCode;
        this.rspMsg = StrUtil.isNotEmpty(rspMsg) ? rspMsg : StatusCode.getKeyValue(rspCode);
        this.result = result;
    }

    public static ResponseDTO success() {
        return new ResponseDTO();
    }

    public static <T> ResponseDTO<T> success(T result) {
        return new ResponseDTO<>(result);
    }

    public static <T> ResponseDTO<T> error(String rspCode) {
        return new ResponseDTO<>(rspCode);
    }

    public static <T> ResponseDTO<T> error(String rspCode, String rspMsg) {
        return new ResponseDTO<>(rspCode, rspMsg);
    }

    public static <T> ResponseDTO<T> error(String rspCode, String rspMsg, String debuggerMsg) {
        if (log.isDebugEnabled()) {
            return new ResponseDTO<>(rspCode, rspMsg, debuggerMsg);
        }
        return new ResponseDTO<>(rspCode, rspMsg);
    }

}
