package vip.websky.admin.sys.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.websky.core.base.model.enums.StateEnum;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 角色信息(SysRole) 表vo实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysRole对象", description = "角色信息")
public class SysRoleDTO implements Serializable {
    //角色标识
    private String id;
    //角色类型
    private String roleType;
    //角色名称
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    //描述
    private String description;
    //状态
    private StateEnum state;
}
