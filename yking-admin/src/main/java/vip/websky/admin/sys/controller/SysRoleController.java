package vip.websky.admin.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.dto.SysRolePrivilegeDTO;
import vip.websky.admin.sys.model.pojo.SysRole;
import vip.websky.admin.sys.model.vo.SysRoleVO;
import vip.websky.admin.sys.service.ISysRoleService;
import vip.websky.core.base.action.BaseAction;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.model.dto.ResponseDTO;

import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/saveRolePrivilege", method = {RequestMethod.POST})
    public ResponseDTO saveUserRoleBatch(@RequestBody @Validated List<SysRolePrivilegeDTO> sysRolePrivilegeDTOS) {
        return ResponseDTO.success(baseService().saveRolePrivilegeBatch(sysRolePrivilegeDTOS));
    }

    @RequestMapping(value = "/removeRolePrivilege", method = {RequestMethod.DELETE})
    public ResponseDTO removeUserRoleBatch(@RequestBody @Validated List<SysRolePrivilegeDTO> sysRolePrivilegeDTOS) {
        boolean result = baseService().removeRolePrivilegeBatch(sysRolePrivilegeDTOS);
        if (!result){
            return ResponseDTO.error("000003","该数据异常,请重试");
        }
        return ResponseDTO.success(true);
    }

    @RequestMapping(value = "/getRolePrivilegePage", method = {RequestMethod.GET})
    public ResponseDTO<Page> getRoleUsersPage(Map<String, Object> searchValue, SysRolePrivilegeDTO findDTO, RequestDTO requestDTO) {
        return ResponseDTO.success(baseService().getRolePrivilegePageByObjs(findDTO, requestDTO));
    }
}
