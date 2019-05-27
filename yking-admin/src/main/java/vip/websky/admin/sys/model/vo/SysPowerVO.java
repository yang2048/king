package vip.websky.admin.sys.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 权限菜单(SysPower) 表dto实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:10:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysPower对象", description = "权限菜单")
public class SysPowerVO implements Serializable {
    //标识号
    private String powerId;
    //父id
    private String parentId;
    //权限编码
    private String powerCode;
    //名称
    private String powerName;
    //类型
    private String powerType;
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
    private String powerStatus;
    //创建人
    private String createdBy;
    //创建时间
    private LocalDateTime createdTime;
    //更新人
    private String updatedBy;
    //更新时间
    private LocalDateTime updatedTime;
}