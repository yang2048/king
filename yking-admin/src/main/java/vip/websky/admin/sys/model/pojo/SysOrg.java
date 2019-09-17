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
 * 机构信息
 * </p>
 *
 * @author Yang.Yong
 * @since 2019-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("y_sys_org")
@ApiModel(value = "SysOrg对象", description = "机构信息 ")
public class SysOrg extends BaseModel implements Serializable {

    @ApiModelProperty(value = "机构父标识")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "机构编码")
    @TableField("org_code")
    private String orgCode;

    @ApiModelProperty(value = "机构名称")
    @TableField("org_name")
    private String orgName;

    @ApiModelProperty(value = "机构图标")
    @TableField("org_logo")
    private String orgLogo;

    @ApiModelProperty(value = "机构排序")
    @TableField("orders")
    private BigDecimal orders;
}
