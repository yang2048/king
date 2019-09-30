package vip.websky.admin.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.websky.admin.sys.model.dto.SysConfDTO;
import vip.websky.admin.sys.model.pojo.SysConf;
import vip.websky.admin.sys.model.vo.SysConfVO;
import vip.websky.admin.sys.service.ISysConfService;
import vip.websky.core.base.action.CrudAction;

/**
 * <p>
 * 系统配置  前端控制器
 * </p>
 *
 * @author Yong.Yang
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/sys/conf")
public class SysConfController implements CrudAction<SysConf, SysConfVO, SysConfDTO, ISysConfService> {

    private final ISysConfService sysConfService;

    public SysConfController(ISysConfService sysConfService) {
        this.sysConfService = sysConfService;
    }

    @Override
    public ISysConfService baseService() {
        return sysConfService;
    }
}
