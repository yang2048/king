package vip.websky.admin.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.pojo.SysRole;
import vip.websky.admin.sys.model.vo.SysRoleVO;
import vip.websky.admin.sys.service.ISysRoleService;
import vip.websky.core.base.action.BaseAction;

/**
 * <p>
 * 角色信息  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController implements BaseAction<SysRole, SysRoleVO, SysRoleDTO, ISysRoleService> {
    @Autowired
    private ISysRoleService roleService;

    @Override
    public ISysRoleService baseService() {
        return roleService;
    }

}
