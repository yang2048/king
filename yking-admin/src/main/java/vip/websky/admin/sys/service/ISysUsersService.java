package vip.websky.admin.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.websky.admin.sys.model.dto.SysUserRoleDTO;
import vip.websky.admin.sys.model.dto.SysUsersDTO;
import vip.websky.admin.sys.model.pojo.SysUsers;
import vip.websky.admin.sys.model.vo.SysUserRoleVO;
import vip.websky.admin.sys.model.vo.SysUsersVO;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.service.IBaseService;

import java.util.Collection;

/**
 * 基础用户(SysUsers)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:37
 */
public interface ISysUsersService extends IBaseService<SysUsers, SysUsersVO, SysUsersDTO> {

    boolean saveUserRole(SysUserRoleDTO addDTO);

    boolean saveUserRoleBatch(Collection<SysUserRoleDTO> entityList);

    boolean removeUserRole(SysUserRoleDTO removeDTO);

    boolean removeUserRoleBatch(Collection<SysUserRoleDTO> entityList);

    Page<SysUserRoleVO> getRoleUsersPageByObjs(SysUserRoleDTO findDTO, RequestDTO requestDTO);
}
