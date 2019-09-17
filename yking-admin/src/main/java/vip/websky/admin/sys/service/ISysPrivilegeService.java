package vip.websky.admin.sys.service;

import vip.websky.admin.sys.model.dto.SysPrivilegeDTO;
import vip.websky.admin.sys.model.pojo.SysPrivilege;
import vip.websky.admin.sys.model.vo.SysPrivilegeVO;
import vip.websky.core.base.service.IBaseService;

import java.util.List;

/**
 * 权限菜单(SysPrivilege)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-16 22:25:44
 */
public interface ISysPrivilegeService  extends IBaseService<SysPrivilege, SysPrivilegeVO, SysPrivilegeDTO> {

    List<SysPrivilegeVO> getMenu(String userId);
}
