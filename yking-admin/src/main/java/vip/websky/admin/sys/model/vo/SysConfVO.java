package vip.websky.admin.sys.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 系统配置(SysConf) 表dto实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysConf对象", description = "系统配置")
public class SysConfVO implements Serializable {
    //配置键
    private String confKey;
    //配置值
    private String confValue;
    //配置说明
    private String confRemark;
    //创建人
    private String createdBy;
    //创建时间
    private LocalDateTime createdTime;
    //更新人
    private String updatedBy;
    //更新时间
    private LocalDateTime updatedTime;
}