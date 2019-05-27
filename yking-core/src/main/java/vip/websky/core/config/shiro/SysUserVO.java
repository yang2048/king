package vip.websky.core.config.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Company
 * @Author: YangYong
 * @Date: 2018/7/21 22:25
 * @Description:
 */
@Data
public class SysUserVO implements Serializable {
    /**
     * 用户名
     */
    private String userId;

    /**
     * 昵称
     */
    private String userName;

}
