package vip.websky.admin.sys.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色信息(SysRole) 表dto实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysRole对象", description = "角色信息")
public class SysRoleVO implements Serializable {
    //角色标识
    private String roleId;
    //角色类型
    private String roleType;
    //角色名称
    private String roleName;
    //状态
    private String roleStatus;
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