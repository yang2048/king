package vip.websky.admin.sys.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.websky.core.base.model.pojo.BaseModel;

import java.io.Serializable;

/**
 * <p>
 * 角色-菜单
 * </p>
 *
 * @author Yang.Yong
 * @since 2019-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("y_sys_role_privilege")
@ApiModel(value = "SysRolePrivilege对象", description = "角色-菜单 ")
public class SysRolePrivilege extends BaseModel implements Serializable {

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    @TableField("privilege_id")
    private String privilegeId;

}
