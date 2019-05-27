package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vip.websky.admin.sys.dao.SysDeptMapper;
import vip.websky.admin.sys.model.dto.SysDeptDTO;
import vip.websky.admin.sys.model.pojo.SysDept;
import vip.websky.admin.sys.model.vo.SysDeptVO;
import vip.websky.admin.sys.service.ISysDeptService;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.util.List;

/**
 * 机构信息(SysDept)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:36
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Override
    public SysDeptVO saveSysDept(SysDeptDTO sysDeptDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysDept>().lambda().eq(SysDept::getOrgId, sysDeptDTO.getOrgId()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该部门已存在");
        }
        SysDept sysDept = ObjectConvertUtils.copyToDest(sysDeptDTO, SysDept.class);
        int result = baseMapper.insert(sysDept);
        return result > 0 ? ObjectConvertUtils.copyToDest(sysDept, SysDeptVO.class) : null;
    }

    @Override
    public SysDeptVO updateSysDept(SysDeptDTO sysDeptDTO) {
        SysDept sysDept = ObjectConvertUtils.copyToDest(sysDeptDTO, SysDept.class);
        int result = baseMapper.updateById(sysDept);
        return result > 0 ? ObjectConvertUtils.copyToDest(sysDept, SysDeptVO.class) : null;
    }

    @Override
    public Page<SysDeptVO> getSysDeptPage(SysDeptDTO sysDeptDTO) {
        Page<SysDeptVO> deptVOPage = new Page<>();
        QueryWrapper<SysDept> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysDeptDTO.getSearchValue())) {
            qw.lambda().eq(SysDept::getOrgId, sysDeptDTO.getSearchValue())
                    .or().like(SysDept::getOrgName, sysDeptDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysDept::getCreatedTime);
        Page<SysDept> page = new Page<>(sysDeptDTO.getPageNumber(), sysDeptDTO.getPageSize(), false);
        IPage<SysDept> deptPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(deptPage, SysDeptVO.class, deptVOPage);
    }

    @CacheEvict(value="users")
    @Override
    public void removeSysDept(List<String> idList) {
        int result =  baseMapper.deleteBatchIds(idList);
        if (result < idList.size()){
            throw new CommonsRuntimeException(StatusCode.RTN_CODE_UNKNOW_ERROR, "删除异常");
        }
    }

    @Cacheable(value = "userCache", key = "#root.method.name+sysDeptDTO.orgId")
    @Override
    public List<SysDeptVO> getSysDeptByObjs(SysDeptDTO sysDeptDTO) {
        QueryWrapper<SysDept> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysDeptDTO.getOrgId())) {
            qw.lambda().eq(SysDept::getOrgId, sysDeptDTO.getOrgId());
        }
        if (StrUtil.isNotEmpty(sysDeptDTO.getParentId())) {
            qw.lambda().eq(SysDept::getParentId, sysDeptDTO.getParentId());
        }
        if (StrUtil.isNotEmpty(sysDeptDTO.getOrgName())) {
            qw.lambda().eq(SysDept::getOrgName, sysDeptDTO.getOrgName());
        }
        List<SysDept> deptList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(deptList, SysDeptVO.class);
    }
}