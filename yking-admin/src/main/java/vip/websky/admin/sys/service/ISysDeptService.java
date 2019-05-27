package vip.websky.admin.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.websky.admin.sys.model.dto.SysDeptDTO;
import vip.websky.admin.sys.model.pojo.SysDept;
import vip.websky.admin.sys.model.vo.SysDeptVO;

import java.util.List;

/**
 * 机构信息(SysDept)表服务接口
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:36
 */
public interface ISysDeptService extends IService<SysDept> {

    SysDeptVO saveSysDept(SysDeptDTO sysDeptDTO);

    SysDeptVO updateSysDept(SysDeptDTO sysDeptDTO);

    Page<SysDeptVO> getSysDeptPage(SysDeptDTO sysDeptDTO);

    void removeSysDept(List<String> idList);

    List<SysDeptVO> getSysDeptByObjs(SysDeptDTO sysDeptDTO);
}