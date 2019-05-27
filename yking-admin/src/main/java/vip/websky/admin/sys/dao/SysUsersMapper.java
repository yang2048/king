package vip.websky.admin.sys.dao;

import org.springframework.stereotype.Repository;
import vip.websky.core.base.dao.SuperMapper;
import vip.websky.admin.sys.model.pojo.SysUsers;

/**
 * 基础用户(SysUsers)表数据库访问层
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:52:22
 */
@Repository
public interface SysUsersMapper extends SuperMapper<SysUsers> {

}