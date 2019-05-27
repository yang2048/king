package vip.websky.admin.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysUsersDTO;
import vip.websky.admin.sys.model.vo.SysUsersVO;
import vip.websky.admin.sys.service.ISysUsersService;
import vip.websky.core.base.action.BaseAction;
import vip.websky.core.base.model.dto.ResponseDTO;

import java.util.Arrays;
import java.util.List;

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
public class SysUsersController extends BaseAction {
    @Autowired
    private ISysUsersService usersService;

    /**
     * 添加用户
     *
     * @param sysUsersDTO
     * @return ResponseDTO
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseDTO<SysUsersVO> saveUser(@Validated SysUsersDTO sysUsersDTO) {
        return ResponseDTO.success(usersService.saveSysUsers(sysUsersDTO));
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PATCH})
    public ResponseDTO<SysUsersVO> updateDept(@Validated SysUsersDTO sysUsersDTO) {
        return ResponseDTO.success(usersService.updateSysUsers(sysUsersDTO));
    }

    /**
     * 查询用户列表
     *
     * @param sysUsersDTO
     * @return ResponseDTO
     */
    @ApiOperation(value = "查询用户列表", notes = "根据用户id修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "searchValue", value = "查询关键字", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "gender", value = "性别", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "status", value = "状态", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNumber", value = "页码", required = true, dataType = "String", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页容量", required = true, dataType = "String", defaultValue = "10"),
    })
    @RequestMapping(value = "/getUsersPage", method = {RequestMethod.GET})
    public ResponseDTO<Page> getSysUsersPage(SysUsersDTO sysUsersDTO) {
        return ResponseDTO.success(usersService.getSysUsersPage(sysUsersDTO));
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE})
    public ResponseDTO removeUsers(@NotNull String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        usersService.removeSysUsers(idList);
        return ResponseDTO.success();
    }

    @RequestMapping(value = "/getUser", method = {RequestMethod.GET})
    public ResponseDTO<List> getSysUser(SysUsersDTO sysUsersDTO) {
        List<SysUsersVO> result = usersService.getSysUsersByObjs(sysUsersDTO);
        return ResponseDTO.success(result);
    }


}
