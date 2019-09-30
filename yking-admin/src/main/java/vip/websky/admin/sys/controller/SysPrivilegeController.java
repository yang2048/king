package vip.websky.admin.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysPrivilegeDTO;
import vip.websky.admin.sys.model.dto.SysUserRoleDTO;
import vip.websky.admin.sys.model.pojo.SysPrivilege;
import vip.websky.admin.sys.model.vo.SysPrivilegeVO;
import vip.websky.admin.sys.service.ISysPrivilegeService;
import vip.websky.core.base.action.CrudAction;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.model.dto.ResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限菜单  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/privilege")
public class SysPrivilegeController implements CrudAction<SysPrivilege, SysPrivilegeVO, SysPrivilegeDTO, ISysPrivilegeService> {
    @Autowired
    private ISysPrivilegeService sysPrivilegeService;

    @Override
    public ISysPrivilegeService baseService() {
        return sysPrivilegeService;
    }

    @RequestMapping(value = "/getMenu", method = {RequestMethod.GET})
    public ResponseDTO<List> getRoleUsersPage(Map<String, Object> searchValue, SysUserRoleDTO findDTO, RequestDTO requestDTO) {
        return ResponseDTO.success(baseService().getMenu("1"));
    }
}
