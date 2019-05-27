package vip.websky.admin.sys.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import vip.websky.admin.sys.model.enums.UserSexEnum;
import vip.websky.core.base.model.dto.RequestDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 基础用户(SysUsers) 表vo实体类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:12:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysUsers对象", description = "基础用户")
public class SysUsersDTO extends RequestDTO implements Serializable {
    //用户识别号
    private String userId;
    //所属机构
    private String orgId;
    //用户名
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^\\w+$", message = "用户名只能由英文,数字,下划线组成")
    @Length(min = 3, max = 25, message = "用户名长度必须在3-25之间")
    private String userAccount;
    //昵称
    private String nickname;
    //性别
    private UserSexEnum sex;
    //头像
    private String avatar;
    //手机
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166</p>
     * <p>电信：133、153、173、177、180、181、189、199</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    @Pattern(regexp = "^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$", message = "手机号码格式错误")
    private String phone;
    //邮箱
    @Email(message = "邮箱格式不正确")
    private String email;
    //密码
    @Length(min = 6, max = 30, message = "密码长度必须在6-30之间")
    private String cipher;
    //备注
    private String remark;
    //状态 0：冻结，1：正常，
    private String state;
}