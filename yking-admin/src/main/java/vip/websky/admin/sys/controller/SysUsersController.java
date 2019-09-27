package vip.websky.admin.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysUserRoleDTO;
import vip.websky.admin.sys.model.dto.SysUsersDTO;
import vip.websky.admin.sys.model.pojo.SysUsers;
import vip.websky.admin.sys.model.vo.SysUsersVO;
import vip.websky.admin.sys.service.ISysUsersService;
import vip.websky.core.base.action.BaseAction;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.model.dto.ResponseDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 基础用户  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/users")
public class SysUsersController implements BaseAction<SysUsers, SysUsersVO, SysUsersDTO,ISysUsersService> {
    @Autowired
    private ISysUsersService usersService;

    @Override
    public ISysUsersService baseService() {
        return usersService;
    }

    @RequestMapping(value = "/saveUserRole", method = {RequestMethod.POST})
    public ResponseDTO saveUserRoleBatch(@RequestBody @Validated List<SysUserRoleDTO> sysUserRoleDTOList) {
        //List<SysUserRoleDTO> sysUserRoleDTOList = new ArrayList<>();
        return ResponseDTO.success(baseService().saveUserRoleBatch(sysUserRoleDTOList));
    }

    @RequestMapping(value = "/removeUserRole", method = {RequestMethod.DELETE})
    public ResponseDTO removeUserRole(@RequestBody @Validated List<SysUserRoleDTO> sysUserRoleDTOList) {
        boolean result = baseService().removeUserRoleBatch(sysUserRoleDTOList);
        if (!result){
            return ResponseDTO.error("000003","该数据异常,请重试");
        }
        return ResponseDTO.success(true);
    }

    @RequestMapping(value = "/getRoleUsersPage", method = {RequestMethod.GET})
    public ResponseDTO<Page> getRoleUsersPage(Map<String, Object> searchValue, SysUserRoleDTO findDTO, RequestDTO requestDTO) {
        return ResponseDTO.success(baseService().getUserRolePageByObjs(findDTO, requestDTO));
    }
}
