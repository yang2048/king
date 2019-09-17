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
 * 系统配置
 * </p>
 *
 * @author Yang.Yong
 * @since 2019-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("y_sys_conf")
@ApiModel(value = "SysConf对象", description = "系统配置 ")
public class SysConf extends BaseModel implements Serializable {
    @ApiModelProperty(value = "配置键")
    @TableField("conf_key")
    private String confKey;

    @ApiModelProperty(value = "配置值")
    @TableField("conf_value")
    private String confValue;

    @ApiModelProperty(value = "配置说明")
    @TableField("conf_remark")
    private String confRemark;
}
