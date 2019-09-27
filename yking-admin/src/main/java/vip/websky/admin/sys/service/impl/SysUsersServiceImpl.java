package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vip.websky.admin.sys.dao.SysUserRoleMapper;
import vip.websky.admin.sys.dao.SysUsersMapper;
import vip.websky.admin.sys.model.dto.SysUserRoleDTO;
import vip.websky.admin.sys.model.dto.SysUsersDTO;
import vip.websky.admin.sys.model.pojo.SysUserRole;
import vip.websky.admin.sys.model.pojo.SysUsers;
import vip.websky.admin.sys.model.vo.SysUserRoleVO;
import vip.websky.admin.sys.model.vo.SysUsersVO;
import vip.websky.admin.sys.service.ISysUsersService;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.model.enums.StateEnum;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 基础用户(SysUsers)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:37
 */
@Service
@Transactional(readOnly = true)
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements ISysUsersService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Transactional
    @CacheEvict(value = "sysUserCache")
    public SysUsersVO saveBase(SysUsersDTO addDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysUsers>().lambda().eq(SysUsers::getUserAccount, addDTO.getUserAccount()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该用户名已被注册");
        }
        SysUsers user = ObjectConvertUtils.copyToDest(addDTO, SysUsers.class);
        if (StrUtil.isEmpty(user.getNickname())) {
            user.setNickname("用户" + user.getUserAccount());
        }
        user.setCipher("123456");
        user.setState(StateEnum.normal);
        baseMapper.insert(user);
        return ObjectConvertUtils.copyToDest(user, SysUsersVO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = "sysUserCache")
    public boolean removeBase(Collection<? extends Serializable> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result != idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result == idList.size();
    }

    @Override
    @Transactional
    @CacheEvict(value = "sysUserCache")
    public boolean updateBase(SysUsersDTO updateDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysUsers>().lambda().eq(SysUsers::getUserAccount, updateDTO.getUserAccount()));
        if (count == 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "原数据不正确");
        }
        SysUsers sysUsers = ObjectConvertUtils.copyToDest(updateDTO, SysUsers.class);
        return baseMapper.updateById(sysUsers) > 0;
    }

    @Override
    @Cacheable(value = "sysUserCache", key = "method.name+'_'+#p0")
    public SysUsersVO getBase(Serializable id) {
        SysUsers sysUsers = baseMapper.selectById(id);
        return ObjectConvertUtils.copyToDest(sysUsers, SysUsersVO.class);
    }

    @Override
    @Cacheable(value = "sysUserCache", key = "method.name+'_'+#p0")
    public List<SysUsersVO> getListByObjs(SysUsersDTO findDTO) {
        QueryWrapper<SysUsers> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(findDTO.getUserAccount())) {
            qw.lambda().eq(SysUsers::getUserAccount, findDTO.getUserAccount());
        }
        if (StrUtil.isNotEmpty(findDTO.getUserAccount())) {
            qw.lambda().eq(SysUsers::getUserAccount, findDTO.getUserAccount());
        }
        if (StrUtil.isNotEmpty(findDTO.getEmail())) {
            qw.lambda().eq(SysUsers::getEmail, findDTO.getEmail());
        }
        if (StrUtil.isNotEmpty(findDTO.getPhone())) {
            qw.lambda().eq(SysUsers::getPhone, findDTO.getPhone());
        }
        if (StrUtil.isNotEmpty(findDTO.getOrgId())) {
            qw.lambda().eq(SysUsers::getOrgId, findDTO.getOrgId());
        }
        List<SysUsers> usersList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(usersList, SysUsersVO.class);
    }

    @Override
    @Cacheable(value = "sysUserCache", key = "method.name+'_'+#p0")
    public Page<SysUsersVO> getPageByObjs(SysUsersDTO findDTO, RequestDTO requestDTO) {
        QueryWrapper<SysUsers> qw = new QueryWrapper<>();
        if (findDTO.getSex() != null) {
            qw.lambda().eq(SysUsers::getSex, findDTO.getSex());
        }
        if (findDTO.getState() != null) {
            qw.lambda().eq(SysUsers::getState, findDTO.getState());
        }
        if (StrUtil.isNotEmpty(findDTO.getOrgId()) && !"1".equals(findDTO.getOrgId())) {
            qw.lambda().eq(SysUsers::getOrgId, findDTO.getOrgId());
        }
        if (StrUtil.isNotEmpty(requestDTO.getSearchValue())) {
            qw.lambda().like(SysUsers::getUserAccount, requestDTO.getSearchValue())
                    .or().like(SysUsers::getNickname, requestDTO.getSearchValue())
                    .or().like(SysUsers::getEmail, requestDTO.getSearchValue())
                    .or().like(SysUsers::getPhone, requestDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysUsers::getState).orderByDesc(SysUsers::getCreatedTime);
        Page<SysUsers> page = new Page<>(requestDTO.getPageNumber(), requestDTO.getPageSize());
        IPage<SysUsers> userPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(userPage, SysUsersVO.class, new Page<>());
    }

    @Override
    @Transactional
    public boolean saveUserRole(SysUserRoleDTO addDTO) {
        SysUserRole userRole = ObjectConvertUtils.copyToDest(addDTO, SysUserRole.class);
        return retBool(sysUserRoleMapper.insert(userRole));
    }

    @Override
    @Transactional
    public boolean saveUserRoleBatch(Collection<SysUserRoleDTO> entityList) {
        entityList.forEach(this::saveUserRole);
        return true;
    }

    @Override
    @Transactional
    public boolean removeUserRole(SysUserRoleDTO removeDTO) {
        LambdaQueryWrapper<SysUserRole> qw = new LambdaQueryWrapper<>();
        qw.eq(SysUserRole::getRoleId, removeDTO.getRoleId());
        qw.eq(SysUserRole::getUserId, removeDTO.getUserId());
        return retBool(sysUserRoleMapper.delete(qw));
    }

    @Override
    @Transactional
    public boolean removeUserRoleBatch(Collection<SysUserRoleDTO> entityList) {
        entityList.forEach(this::removeUserRole);
        return true;
    }

    @Override
    public Page<SysUserRoleVO> getUserRolePageByObjs(SysUserRoleDTO findDTO, RequestDTO requestDTO) {
        LambdaQueryWrapper<SysUserRole> qw = new LambdaQueryWrapper<>();
        qw.eq(SysUserRole::getRoleId, findDTO.getRoleId());
        qw.apply(StrUtil.isNotEmpty(requestDTO.getSearchValue()),"user_account like '%"+requestDTO.getSearchValue()+"%'");
        qw.apply("ur.deleted = {0}", 0);
        Page<SysUserRole> page = new Page<>(requestDTO.getPageNumber(), requestDTO.getPageSize());
        IPage<SysUserRoleVO> userPage = sysUserRoleMapper.selectSysUserRolePage(page, qw);
        return ObjectConvertUtils.copyToPage(userPage, SysUserRoleVO.class, new Page<>());
    }
}
