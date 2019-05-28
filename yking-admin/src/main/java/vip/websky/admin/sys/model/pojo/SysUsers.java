package vip.websky.admin.sys.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.websky.admin.sys.model.enums.StatusEnum;
import vip.websky.admin.sys.model.enums.UserSexEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class SysUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户识别号")
    @TableId(value = "user_id", type = IdType.ID_WORKER_STR)
    private String userId;

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

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "状态 0：冻结，1：正常，")
    @TableField("state")
    private StatusEnum state;

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
