package vip.websky.admin.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysOrgDTO;
import vip.websky.admin.sys.model.pojo.SysOrg;
import vip.websky.admin.sys.model.vo.SysOrgVO;
import vip.websky.admin.sys.service.ISysOrgService;
import vip.websky.core.base.action.BaseAction;

/**
 * <p>
 * 机构信息  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/org")
public class SysOrgController implements BaseAction<SysOrg, SysOrgVO, SysOrgDTO, ISysOrgService> {

    private final ISysOrgService sysOrgService;
    public SysOrgController(ISysOrgService sysOrgService) {
        this.sysOrgService = sysOrgService;
    }

    @Override
    public ISysOrgService baseService() {
        return sysOrgService;
    }

}
