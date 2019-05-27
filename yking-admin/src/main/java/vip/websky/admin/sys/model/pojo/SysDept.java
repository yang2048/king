package vip.websky.admin.sys.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 机构信息
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("y_sys_Dept")
@ApiModel(value = "SysDept对象", description = "机构信息 ")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构标识")
    @TableId(value = "org_id", type = IdType.ID_WORKER_STR)
    private String orgId;

    @ApiModelProperty(value = "机构父标识")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "机构编码")
    @TableField("org_code")
    private String orgCode;

    @ApiModelProperty(value = "机构名称")
    @TableField("org_name")
    private String orgName;

    @ApiModelProperty(value = "机构图标")
    @TableField("org_logo")
    private String orgLogo;

    @ApiModelProperty(value = "创建人")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新人")
    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "是否删除 0:正常，1:删除")
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private String deleted;
}
