package vip.websky.admin.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.pojo.SysRole;
import vip.websky.admin.sys.model.vo.SysRoleVO;

import java.util.List;

/**
 * 角色信息(SysRole)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:00:23
 */
public interface ISysRoleService extends IService<SysRole> {

    SysRoleVO saveSysRole(SysRoleDTO sysRoleDTO);

    SysRoleVO updateSysRole(SysRoleDTO sysRoleDTO);

    Page<SysRoleVO> getSysRolePage(SysRoleDTO sysRoleDTO);
    
    void removeSysRole(List<String> idList);

    List<SysRoleVO> getSysRoleByObjs(SysRoleDTO sysRoleDTO);
}