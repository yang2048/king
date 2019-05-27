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
 * 系统配置
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("y_sys_conf")
@ApiModel(value = "SysConf对象", description = "系统配置 ")
public class SysConf implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配置键")
    @TableId(value = "conf_key", type = IdType.ID_WORKER_STR)
    private String confKey;

    @ApiModelProperty(value = "配置值")
    @TableField("conf_value")
    private String confValue;

    @ApiModelProperty(value = "配置说明")
    @TableField("conf_remark")
    private String confRemark;

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
