package vip.websky.admin.sys.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.websky.admin.sys.model.enums.PowerTypeEnum;
import vip.websky.admin.sys.model.enums.StatusEnum;
import vip.websky.core.base.model.dto.RequestDTO;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 权限菜单(SysPower) 表vo实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:10:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysPower对象", description = "权限菜单")
public class SysPowerDTO extends RequestDTO implements Serializable {
    //标识号
    private String powerId;
    //父id
    private String parentId;
    //权限编码
    private String powerCode;
    //名称
    @NotBlank(message = "权限名称不能为空")
    private String powerName;
    //类型
    @NotBlank(message = "权限类型不能为空")
    private PowerTypeEnum powerType;
    //链接
    private String powerUrl;
    //打开方式
    private String powerTarget;
    //图标
    private String powerIcon;
    //颜色
    private String powerColor;
    //排序
    private String powerSort;
    //状态
    private StatusEnum powerStatus;
}