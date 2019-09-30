package vip.websky.admin.sys.controller;

import org.springframework.web.bind.annotation.PostMapping;
import vip.websky.core.base.action.BaseAction;

/**
 * @author yang2048@qq.com @Y.Yang
 * @since 2019/9/30 10:18
 */
public class IndexController extends BaseAction {

    @PostMapping("login")
    public void login(String username, String password){

    }

}
