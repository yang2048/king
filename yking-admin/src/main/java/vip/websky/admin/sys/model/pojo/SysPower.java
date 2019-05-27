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
 * 权限菜单
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("y_sys_power")
@ApiModel(value = "SysPower对象", description = "权限菜单 ")
public class SysPower implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标识号")
    @TableId(value = "power_id", type = IdType.ID_WORKER_STR)
    private String powerId;

    @ApiModelProperty(value = "父id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "权限编码")
    @TableField("power_code")
    private String powerCode;

    @ApiModelProperty(value = "名称")
    @TableField("power_name")
    private String powerName;

    @ApiModelProperty(value = "类型")
    @TableField("power_type")
    private String powerType;

    @ApiModelProperty(value = "链接")
    @TableField("power_url")
    private String powerUrl;

    @ApiModelProperty(value = "打开方式")
    @TableField("power_target")
    private String powerTarget;

    @ApiModelProperty(value = "图标")
    @TableField("power_icon")
    private String powerIcon;

    @ApiModelProperty(value = "颜色")
    @TableField("power_color")
    private String powerColor;

    @ApiModelProperty(value = "排序")
    @TableField("power_sort")
    private String powerSort;

    @ApiModelProperty(value = "状态")
    @TableField("power_status")
    private String powerStatus;

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
