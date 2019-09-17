package vip.websky.admin.sys.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import vip.websky.admin.sys.model.pojo.SysUserRole;
import vip.websky.admin.sys.model.vo.SysUserRoleVO;
import vip.websky.core.base.dao.SuperMapper;

/**
 * 用户-角色(SysUserRole)表数据库访问层
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:52:22
 */
@Repository
public interface SysUserRoleMapper extends SuperMapper<SysUserRole> {

    @Select("SELECT ur.*, u.*, o.org_name FROM y_sys_user_role ur " +
            "inner join y_sys_users u on u.id = ur.user_id inner join y_sys_org o on o.id = u.org_id " +
            "${ew.customSqlSegment}")
    IPage<SysUserRoleVO> selectSysUserRolePage(IPage<SysUserRole> page, @Param(Constants.WRAPPER) Wrapper wrapper);

}
