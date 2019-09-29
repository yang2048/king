package vip.websky.admin.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.dto.SysRolePrivilegeDTO;
import vip.websky.admin.sys.model.pojo.SysRole;
import vip.websky.admin.sys.model.vo.SysRolePrivilegeVO;
import vip.websky.admin.sys.model.vo.SysRoleVO;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.service.IBaseService;

import java.util.List;

/**
 * 角色信息(SysRole)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:00:23
 */
public interface ISysRoleService extends IBaseService<SysRole, SysRoleVO, SysRoleDTO> {

    boolean saveRolePrivilege(SysRolePrivilegeDTO addDTO);

    boolean saveRolePrivilegeBatch(List<SysRolePrivilegeDTO> entityList);

    boolean removeRolePrivilege(SysRolePrivilegeDTO removeDTO);

    boolean removeRolePrivilegeBatch(List<SysRolePrivilegeDTO> entityList);

    Page<SysRolePrivilegeVO> getRolePrivilegePageByObjs(SysRolePrivilegeDTO findDTO, RequestDTO requestDTO);
}
