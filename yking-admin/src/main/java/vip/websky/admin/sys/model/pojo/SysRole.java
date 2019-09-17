package vip.websky.admin.sys.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.websky.core.base.model.enums.StateEnum;
import vip.websky.core.base.model.pojo.BaseModel;

import java.io.Serializable;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author Yang.Yong
 * @since 2019-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("y_sys_role")
@ApiModel(value = "SysRole对象", description = "角色信息 ")
public class SysRole extends BaseModel implements Serializable {

    @ApiModelProperty(value = "角色类型")
    @TableField("role_type")
    private String roleType;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "简介")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    private StateEnum state;
}
