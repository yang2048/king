package vip.websky.admin.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.vo.SysRoleVO;
import vip.websky.admin.sys.service.ISysRoleService;
import vip.websky.core.base.action.BaseAction;
import vip.websky.core.base.model.dto.ResponseDTO;

import java.util.Arrays;
import java.util.List;

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
public class SysRoleController extends BaseAction {
    @Autowired
    private ISysRoleService roleService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseDTO<SysRoleVO> saveUser(@Validated SysRoleDTO sysRoleDTO) {
        return ResponseDTO.success(roleService.saveSysRole(sysRoleDTO));
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PATCH})
    public ResponseDTO<SysRoleVO> updateRole(@Validated SysRoleDTO sysRoleDTO) {
        return ResponseDTO.success(roleService.updateSysRole(sysRoleDTO));
    }

    @RequestMapping(value = "/getRolePage", method = {RequestMethod.GET})
    public ResponseDTO<Page> getRolePage(SysRoleDTO sysRoleDTO) {
        return ResponseDTO.success(roleService.getSysRolePage(sysRoleDTO));
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE})
    public ResponseDTO remove(@NotNull String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        roleService.removeSysRole(idList);
        return ResponseDTO.success();
    }
}
