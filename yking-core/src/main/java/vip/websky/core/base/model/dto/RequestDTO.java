package vip.websky.core.base.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2018/7/1 0:54
 * @Description:
 */
@Data
public class RequestDTO implements Serializable {
    /**
     * 当前页		默认是1
     */
    protected long pageNumber = 1;

    /**
     * 每页大小	    默认是10
     */
    protected long pageSize = 10;

    /**
     * 搜索关键字
     */
    private String searchValue;
    private String channel;
    private String version;
    private String interCode;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 请求 token
     */
    private String token;
}
