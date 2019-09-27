package vip.websky.admin.sys.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yang2048@qq.com @Y.Yang
 * @since 2019/9/27 17:05
 */
@Data
public class SysRolePrivilegeDTO implements Serializable {

    @NotBlank(message = "角色id为空")
    private String roleId;

    private String privilegeId;

    @ApiModelProperty(value = "乐观锁")
    private String revision;
}
