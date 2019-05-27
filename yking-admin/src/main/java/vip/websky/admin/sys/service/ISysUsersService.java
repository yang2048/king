package vip.websky.admin.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.dto.SysUsersDTO;
import vip.websky.admin.sys.model.pojo.SysUsers;
import vip.websky.admin.sys.model.vo.SysUsersVO;

import java.util.List;

/**
 * 基础用户(SysUsers)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:37
 */
public interface ISysUsersService extends IService<SysUsers> {

    SysUsersVO saveSysUsers(SysUsersDTO sysUsersDTO);

    SysUsersVO updateSysUsers(SysUsersDTO sysUsersDTO);

    Page<SysUsersVO> getSysUsersPage(SysUsersDTO sysUsersDTO);
    
    void removeSysUsers(List<String> idList);

    List<SysUsersVO> getSysUsersByObjs(SysUsersDTO sysUsersDTO);

    void addRole(SysUsersDTO sysUsersDTO, SysRoleDTO sysRoleDTO);
}