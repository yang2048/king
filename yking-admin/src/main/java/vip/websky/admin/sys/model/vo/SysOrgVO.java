package vip.websky.admin.sys.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 机构信息(SysDept) 表dto实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysOrgVO对象", description = "机构信息")
public class SysOrgVO implements Serializable {
    //机构标识
    private String id;
    //机构父标识
    private String parentId;
    //机构编码
    private String orgCode;
    //机构名称
    private String orgName;
    //机构图标
    private String orgLogo;
    // 排序
    private BigDecimal orders;
    //创建人
    private String createdBy;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    //更新人
    private String updatedBy;
    //更新时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
    //private List<SysOrgVO> children;
}
