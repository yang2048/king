package vip.websky.admin.sys.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 权限菜单(SysPower) 表dto实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:10:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysPrivilegeVO对象", description = "权限菜单")
public class SysPrivilegeVO implements Serializable {
    //标识号
    private String id;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "权限编码")
    private String code;

    @ApiModelProperty(value = "名称")
    private String title;

    @ApiModelProperty(value = "路由名称")
    private String name;

    @ApiModelProperty(value = "重定向地址")
    private String redirect;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "路由地址")
    private String path;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "打开方式")
    private String target;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "颜色")
    private String privilegeColor;

    @ApiModelProperty(value = "排序")
    private BigDecimal orders;

    @ApiModelProperty(value = "是否显示菜单")
    private String hideMenu;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "状态")
    private String status;
}
