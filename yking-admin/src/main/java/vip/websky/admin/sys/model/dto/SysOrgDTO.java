package vip.websky.admin.sys.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 机构信息(SysDept) 表vo实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysDept对象", description = "机构信息")
public class SysOrgDTO implements Serializable {
    private String id;
    //机构父标识
    private String parentId;
    //机构编码
    // @Length(min = 1, max = 10, message = "机构编码长度必须在1-10之间")
    private String orgCode;
    //机构名称
    @NotBlank(message = "机构名称不能为空")
    private String orgName;
    //机构图标
    private String orgLogo;
    //机构排序
    private BigDecimal orders;
}
