package vip.websky.admin.sys.dao;

import org.springframework.stereotype.Repository;
import vip.websky.admin.sys.model.pojo.SysRolePrivilege;
import vip.websky.core.base.dao.SuperMapper;

import javax.annotation.Resource;

/**
 * 角色-菜单(SysRolePower)表数据库访问层
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:52:22
 */
@Repository
public interface SysRolePrivilegeMapper extends SuperMapper<SysRolePrivilege> {

}
