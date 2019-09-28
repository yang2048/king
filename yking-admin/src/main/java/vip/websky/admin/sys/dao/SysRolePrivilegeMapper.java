package vip.websky.admin.sys.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import vip.websky.admin.sys.model.pojo.SysRolePrivilege;
import vip.websky.admin.sys.model.vo.SysRolePrivilegeVO;
import vip.websky.core.base.dao.SuperMapper;

/**
 * 角色-菜单(SysRolePower)表数据库访问层
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:52:22
 */
@Repository
public interface SysRolePrivilegeMapper extends SuperMapper<SysRolePrivilege> {

    @Select("SELECT " +
            "FROM " +
            "${ew.customSqlSegment}")
    IPage<SysRolePrivilegeVO> selectSysUserRolePage(Page<SysRolePrivilege> page, LambdaQueryWrapper<SysRolePrivilege> qw);
}
