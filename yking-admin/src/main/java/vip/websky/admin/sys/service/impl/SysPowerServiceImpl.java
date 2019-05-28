package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.websky.admin.sys.dao.SysPowerMapper;
import vip.websky.admin.sys.model.dto.SysPowerDTO;
import vip.websky.admin.sys.model.pojo.SysPower;
import vip.websky.admin.sys.model.vo.SysPowerVO;
import vip.websky.admin.sys.service.ISysPowerService;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.util.List;

/**
 * 权限菜单(SysPower)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-16 22:27:03
 */
@Service
public class SysPowerServiceImpl extends ServiceImpl<SysPowerMapper, SysPower> implements ISysPowerService {

    @Override
    public SysPowerVO saveSysPower(SysPowerDTO sysPowerDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysPower>().lambda().eq(SysPower::getPowerCode, sysPowerDTO.getPowerCode()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该权限码已被注册");
        }
        SysPower power = ObjectConvertUtils.copyToDest(sysPowerDTO, SysPower.class);
        int result = baseMapper.insert(power);
        return result > 0 ? ObjectConvertUtils.copyToDest(power, SysPowerVO.class) : null;
    }

    @Override
    public SysPowerVO updateSysPower(SysPowerDTO sysPowerDTO) {
        SysPower sysPower = ObjectConvertUtils.copyToDest(sysPowerDTO, SysPower.class);
        int result = baseMapper.updateById(sysPower);
        return result > 0 ? ObjectConvertUtils.copyToDest(sysPower, SysPowerVO.class) : null;
    }

    @Override
    public Page<SysPowerVO> getSysPowerPage(SysPowerDTO sysPowerDTO) {
        Page<SysPowerVO> powerVOPage = new Page<>();
        QueryWrapper<SysPower> qw = new QueryWrapper<>();
        if (sysPowerDTO.getPowerType() != null) {
            qw.lambda().eq(SysPower::getPowerType, sysPowerDTO.getPowerType());
        }
        if (StrUtil.isNotEmpty(sysPowerDTO.getSearchValue())) {
            qw.lambda().like(SysPower::getPowerCode, sysPowerDTO.getSearchValue())
                    .or().like(SysPower::getParentId, sysPowerDTO.getSearchValue())
                    .or().like(SysPower::getPowerName, sysPowerDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysPower::getPowerStatus)
                .orderByDesc(SysPower::getPowerType)
                .orderByDesc(SysPower::getPowerSort);
        Page<SysPower> page = new Page<>(sysPowerDTO.getPageNumber(), sysPowerDTO.getPageSize(), false);
        IPage<SysPower> powerPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(powerPage, SysPowerVO.class, powerVOPage);
    }

    @Override
    public void removeSysPower(List<String> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result < idList.size()) {
            throw new CommonsRuntimeException(StatusCode.RTN_CODE_UNKNOW_ERROR, "删除异常");
        }
    }

    @Override
    public List<SysPowerVO> getSysPowerByObjs(SysPowerDTO sysPowerDTO) {
        QueryWrapper<SysPower> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysPowerDTO.getPowerId())) {
            qw.lambda().eq(SysPower::getPowerId, sysPowerDTO.getPowerId());
        }
        if (StrUtil.isNotEmpty(sysPowerDTO.getParentId())) {
            qw.lambda().eq(SysPower::getParentId, sysPowerDTO.getParentId());
        }
        if (StrUtil.isNotEmpty(sysPowerDTO.getPowerCode())) {
            qw.lambda().eq(SysPower::getPowerCode, sysPowerDTO.getPowerCode());
        }
        if (sysPowerDTO.getPowerType() != null) {
            qw.lambda().eq(SysPower::getPowerType, sysPowerDTO.getPowerType());
        }
        List<SysPower> powerList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(powerList, SysPowerVO.class);
    }
}