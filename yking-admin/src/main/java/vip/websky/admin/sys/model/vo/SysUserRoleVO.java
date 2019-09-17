package vip.websky.admin.sys.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.websky.core.base.model.enums.StateEnum;
import vip.websky.core.base.model.enums.UserSexEnum;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Yong.Yang
 * @since 2019/8/26 22:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRoleVO  implements Serializable {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户名id为空")
    private String userId;

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id为空")
    private String roleId;

    @ApiModelProperty(value = "乐观锁")
    private String revision;

    //所属机构
    private String orgId;
    private String orgName;
    //用户名
    private String userAccount;
    //昵称
    private String nickname;
    //性别
    private UserSexEnum sex;
    //头像
    private String avatar;
    //手机
    private String phone;
    //邮箱
    private String email;
    //备注
    private String remark;
    //状态 0：冻结，1：正常，
    private StateEnum state;

}
