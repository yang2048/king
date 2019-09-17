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

/**
 * <p>
 * 机构-角色
 * </p>
 *
 * @author Yang.Yong
 * @since 2019-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("y_sys_org_role")
@ApiModel(value = "SysOrgRole对象", description = "机构-角色 ")
public class SysOrgRole extends BaseModel implements Serializable {

    @ApiModelProperty(value = "机构id")
    @TableField("org_id")
    private String orgId;

    @ApiModelProperty(value = "角色id")
    @TableField("role_id")
    private String roleId;
}
