package vip.websky.admin.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysDeptDTO;
import vip.websky.admin.sys.model.vo.SysDeptVO;
import vip.websky.admin.sys.service.ISysDeptService;
import vip.websky.core.base.action.BaseAction;
import vip.websky.core.base.model.dto.ResponseDTO;
import vip.websky.core.utils.Json2bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 机构信息  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends BaseAction {

    @Autowired
    private ISysDeptService deptService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseDTO<SysDeptVO> addDept(@Validated SysDeptDTO sysDeptDTO) {
        return ResponseDTO.success(deptService.saveSysDept(sysDeptDTO));
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PATCH})
    public ResponseDTO<SysDeptVO> updateDept(@Validated SysDeptDTO sysDeptDTO) {
        return ResponseDTO.success(deptService.updateSysDept(sysDeptDTO));
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    public ResponseDTO<Collection> getDept(SysDeptDTO sysDeptDTO) {
        List<SysDeptVO> menuList = deptService.getSysDeptByObjs(sysDeptDTO);
        Collection<SysDeptVO> menus = Json2bean.toTree(menuList, "orgId", "parentId", "children", SysDeptVO.class);
        return ResponseDTO.success(menus);
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE})
    public ResponseDTO removeDept(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        deptService.removeSysDept(idList);
        return ResponseDTO.success();
    }
}
