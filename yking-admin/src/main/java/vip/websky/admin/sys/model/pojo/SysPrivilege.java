package vip.websky.admin.sys.model.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.websky.core.base.model.pojo.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 权限菜单
 * </p>
 *
 * @author Yang.Yong
 * @since 2019-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("y_sys_privilege")
@ApiModel(value = "SysPrivilege对象", description = "权限菜单 ")
public class SysPrivilege extends BaseModel implements Serializable {

    @ApiModelProperty(value = "父id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "权限编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "名称")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "组件")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "链接")
    @TableField("url")
    private String url;

    @ApiModelProperty(value = "打开方式")
    @TableField("target")
    private String target;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "颜色")
    @TableField("privilege_color")
    private String privilegeColor;

    @ApiModelProperty(value = "排序")
    @TableField("orders")
    private BigDecimal orders;

    @ApiModelProperty(value = "是否显示菜单")
    @TableField("hideMenu")
    private String hideMenu;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private String status;
}
