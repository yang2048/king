package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vip.websky.admin.sys.dao.SysRoleMapper;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.pojo.SysRole;
import vip.websky.admin.sys.model.vo.SysRoleVO;
import vip.websky.admin.sys.service.ISysRoleService;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 角色信息(SysRole)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:00:23
 */
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    @Transactional
    @CacheEvict(value="sysRoleCache")
    public SysRoleVO saveBase(SysRoleDTO addDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleName, addDTO.getRoleName()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该角色名已被创建");
        }
        SysRole sysRole = ObjectConvertUtils.copyToDest(addDTO, SysRole.class);
        //sysRole.setRoleType(StateEnum.fa.getTitle());
        baseMapper.insert(sysRole);
        return ObjectConvertUtils.copyToDest(sysRole, SysRoleVO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value="sysRoleCache")
    public boolean removeBase(Collection<? extends Serializable> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result != idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result == idList.size();
    }

    @Override
    @Transactional
    @CacheEvict(value="sysRoleCache")
    public boolean updateBase(SysRoleDTO updateDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleName, updateDTO.getRoleName()));
        if (count == 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "原数据不正确");
        }
        SysRole sysRole = ObjectConvertUtils.copyToDest(updateDTO, SysRole.class);
        return baseMapper.updateById(sysRole) > 0;
    }

    @Override
    @Cacheable(value = "sysRoleCache", key = "method.name+'_'+#p0")
    public SysRoleVO getBase(Serializable id) {
        SysRole sysRole = baseMapper.selectById(id);
        return ObjectConvertUtils.copyToDest(sysRole, SysRoleVO.class);
    }

    @Override
    @Cacheable(value = "sysRoleCache", key = "method.name+'_'+#p0")
    public List<SysRoleVO> getListByObjs(SysRoleDTO findDTO) {
        QueryWrapper<SysRole> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(findDTO.getRoleType())) {
            qw.lambda().eq(SysRole::getRoleType, findDTO.getRoleType());
        }
        if (StrUtil.isNotEmpty(findDTO.getRoleName())) {
            qw.lambda().eq(SysRole::getRoleName, findDTO.getRoleName());
        }
        List<SysRole> roleList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(roleList, SysRoleVO.class);
    }

    @Override
    @Cacheable(value = "sysRoleCache", key = "method.name+'_'+#p0")
    public Page<SysRoleVO> getPageByObjs(SysRoleDTO findDTO, RequestDTO requestDTO) {
        QueryWrapper<SysRole> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(requestDTO.getSearchValue())) {
            qw.lambda().like(SysRole::getRoleName, requestDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysRole::getState).orderByDesc(SysRole::getCreatedTime);
        Page<SysRole> page = new Page<>(requestDTO.getPageNumber(), requestDTO.getPageSize());
        IPage<SysRole> rolePage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(rolePage, SysRoleVO.class, new Page<>());
    }
}
