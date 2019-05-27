package vip.websky.admin.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.websky.admin.sys.model.dto.SysPowerDTO;
import vip.websky.admin.sys.model.pojo.SysPower;
import vip.websky.admin.sys.model.vo.SysPowerVO;

import java.util.List;

/**
 * 权限菜单(SysPower)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-16 22:25:44
 */
public interface ISysPowerService extends IService<SysPower> {

    SysPowerVO saveSysPower(SysPowerDTO sysPowerDTO);

    SysPowerVO updateSysPower(SysPowerDTO sysPowerDTO);

    Page<SysPowerVO> getSysPowerPage(SysPowerDTO sysPowerDTO);
    
    void removeSysPower(List<String> idList);
    
    List<SysPowerVO> getSysPowerByObjs(SysPowerDTO sysPowerDTO);
}