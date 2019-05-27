package vip.websky.admin.sys.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysPowerDTO;
import vip.websky.admin.sys.model.vo.SysPowerVO;
import vip.websky.admin.sys.service.ISysPowerService;
import vip.websky.core.base.action.BaseAction;
import vip.websky.core.base.model.dto.ResponseDTO;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 权限菜单  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/power")
public class SysPowerController extends BaseAction {
    @Autowired
    private ISysPowerService powerService;

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public ResponseDTO<SysPowerVO> saveUser(@Validated SysPowerDTO sysPowerDTO) {
        return ResponseDTO.success(powerService.saveSysPower(sysPowerDTO));
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PATCH})
    public ResponseDTO<SysPowerVO> updateDept(@Validated SysPowerDTO sysPowerDTO) {
        return ResponseDTO.success(powerService.updateSysPower(sysPowerDTO));
    }

    @RequestMapping(value = "/getUsersPage", method = {RequestMethod.GET})
    public ResponseDTO<Page> getSysUsersPage(SysPowerDTO sysPowerDTO) {
        return ResponseDTO.success(powerService.getSysPowerPage(sysPowerDTO));
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE})
    public ResponseDTO removeUsers(@NotNull String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        powerService.removeSysPower(idList);
        return ResponseDTO.success();
    }
}
