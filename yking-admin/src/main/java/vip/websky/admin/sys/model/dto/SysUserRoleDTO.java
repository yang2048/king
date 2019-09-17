package vip.websky.admin.sys.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Yong.Yang
 * @since 2019/8/25 17:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUsers对象", description = "基础用户")
public class SysUserRoleDTO implements Serializable {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户名id为空")
    private String userId;

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id为空")
    private String roleId;

    @ApiModelProperty(value = "乐观锁")
    private String revision;

    /**
     * 搜索关键字
     */
    private String searchValue;
}
