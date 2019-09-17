package vip.websky.admin.sys.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.websky.core.base.model.enums.StateEnum;
import vip.websky.core.base.model.enums.UserSexEnum;
import vip.websky.core.base.model.pojo.BaseModel;

import java.io.Serializable;

/**
 * <p>
 * 基础用户
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("y_sys_users")
@ApiModel(value = "SysUsers对象", description = "基础用户 ")
public class SysUsers extends BaseModel implements Serializable {

    @ApiModelProperty(value = "所属机构")
    @TableField("org_id")
    private String orgId;

    @ApiModelProperty(value = "用户名")
    @TableField("user_account")
    private String userAccount;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "性别")
    @TableField("sex")
    private UserSexEnum sex;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "手机")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "密码")
    @TableField("cipher")
    private String cipher;

    @ApiModelProperty(value = "密码盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "状态")
    @TableField("state")
    private StateEnum state;
}
