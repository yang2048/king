package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vip.websky.admin.sys.dao.SysPrivilegeMapper;
import vip.websky.admin.sys.model.dto.SysPrivilegeDTO;
import vip.websky.admin.sys.model.pojo.SysPrivilege;
import vip.websky.admin.sys.model.vo.SysPrivilegeVO;
import vip.websky.admin.sys.service.ISysPrivilegeService;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 权限菜单(SysPrivilege)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-16 22:27:03
 */
@Service
@Transactional(readOnly = true)
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeMapper, SysPrivilege> implements ISysPrivilegeService {

    @Override
    @Transactional
    @CacheEvict(value="sysPrivilegeCache")
    public SysPrivilegeVO saveBase(SysPrivilegeDTO addDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysPrivilege>().lambda().eq(SysPrivilege::getCode, addDTO.getCode()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该权限码已被注册");
        }
        SysPrivilege privilege = ObjectConvertUtils.copyToDest(addDTO, SysPrivilege.class);
        baseMapper.insert(privilege);
        return ObjectConvertUtils.copyToDest(privilege, SysPrivilegeVO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value="sysPrivilegeCache")
    public boolean removeBase(Collection<? extends Serializable> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result != idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result == idList.size();
    }

    @Override
    @Transactional
    @CacheEvict(value="sysPrivilegeCache")
    public boolean updateBase(SysPrivilegeDTO updateDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysPrivilege>().lambda().eq(SysPrivilege::getCode, updateDTO.getCode()));
        if (count == 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "原数据不正确");
        }
        SysPrivilege privilege = ObjectConvertUtils.copyToDest(updateDTO, SysPrivilege.class);
        return baseMapper.updateById(privilege) > 0;
    }

    @Override
    @Cacheable(value = "sysPrivilegeCache", key = "method.name+'_'+#p0")
    public SysPrivilegeVO getBase(Serializable id) {
        SysPrivilege privilege = baseMapper.selectById(id);
        return ObjectConvertUtils.copyToDest(privilege, SysPrivilegeVO.class);
    }

    @Override
    @Cacheable(value = "sysPrivilegeCache", key = "method.name+'_'+#p0")
    public List<SysPrivilegeVO> getListByObjs(SysPrivilegeDTO findDTO) {
        QueryWrapper<SysPrivilege> qw = new QueryWrapper<>();
        if (findDTO.getId()!= null) {
            qw.lambda().eq(SysPrivilege::getId, findDTO.getId());
        }
        if (findDTO.getParentId() !=null) {
            qw.lambda().eq(SysPrivilege::getParentId, findDTO.getParentId());
        }
        if (StrUtil.isNotEmpty(findDTO.getCode())) {
            qw.lambda().eq(SysPrivilege::getCode, findDTO.getCode());
        }
        if (findDTO.getType() != null) {
            qw.lambda().eq(SysPrivilege::getType, findDTO.getType());
        }
        qw.lambda().orderByAsc(SysPrivilege::getOrders)
                .orderByDesc(SysPrivilege::getStatus)
                .orderByDesc(SysPrivilege::getType);
        List<SysPrivilege> powerList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(powerList, SysPrivilegeVO.class);
    }

    @Override
    @Cacheable(value = "sysPrivilegeCache", key = "method.name+'_'+#p0")
    public Page<SysPrivilegeVO> getPageByObjs(SysPrivilegeDTO findDTO, RequestDTO requestDTO) {
        QueryWrapper<SysPrivilege> qw = new QueryWrapper<>();
        if (findDTO.getType() != null) {
            qw.lambda().eq(SysPrivilege::getType, findDTO.getType());
        }
        if (StrUtil.isNotEmpty(requestDTO.getSearchValue())) {
            qw.lambda().like(SysPrivilege::getCode, requestDTO.getSearchValue())
                    .or().like(SysPrivilege::getParentId, requestDTO.getSearchValue())
                    .or().like(SysPrivilege::getTitle, requestDTO.getSearchValue());
        }
        qw.lambda().orderByAsc(SysPrivilege::getOrders)
                .orderByDesc(SysPrivilege::getStatus)
                .orderByDesc(SysPrivilege::getType);
        Page<SysPrivilege> page = new Page<>(requestDTO.getPageNumber(), requestDTO.getPageSize());
        IPage<SysPrivilege> powerPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(powerPage, SysPrivilegeVO.class, new Page<>());
    }

    @Override
    @Cacheable(value = "sysPrivilegeCache", key = "method.name+'_'+#p0")
    public List<SysPrivilegeVO> getMenu(String userId) {
        LambdaQueryWrapper<SysPrivilege> qw = new LambdaQueryWrapper<>();
//        if (findDTO.getId()!= null) {
//            qw.lambda().eq(SysPrivilege::getId, findDTO.getId());
//        }
//        qw.in(SysPrivilege::getType, "1","2");
        qw.orderByAsc(SysPrivilege::getOrders)
                .orderByDesc(SysPrivilege::getStatus)
                .orderByDesc(SysPrivilege::getType);
        List<SysPrivilege> powerList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(powerList, SysPrivilegeVO.class);
    }
}
