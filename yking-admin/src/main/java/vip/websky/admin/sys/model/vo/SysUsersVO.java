package vip.websky.admin.sys.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.websky.admin.sys.model.enums.UserSexEnum;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 基础用户(SysUsers) 表dto实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUsers对象", description = "基础用户")
public class SysUsersVO implements Serializable {
    //用户识别号
    private String userId;
    //所属机构
    private String orgId;
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
    private String state;
    //创建人
    private String createdBy;
    //创建时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    //更新人
    private String updatedBy;
    //更新时间
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedTime;
}