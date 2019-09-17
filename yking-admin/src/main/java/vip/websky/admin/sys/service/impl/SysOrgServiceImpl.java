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
import vip.websky.admin.sys.dao.SysOrgMapper;
import vip.websky.admin.sys.dao.SysUsersMapper;
import vip.websky.admin.sys.model.dto.SysOrgDTO;
import vip.websky.admin.sys.model.pojo.SysOrg;
import vip.websky.admin.sys.model.pojo.SysUsers;
import vip.websky.admin.sys.model.vo.SysOrgVO;
import vip.websky.admin.sys.service.ISysOrgService;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static vip.websky.core.config.prompt.StatusCode.UNEXPECTED_ERROR;

/**
 * 机构信息(SysOrg)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:36
 */
@Service
@Transactional(readOnly = true)
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements ISysOrgService {

    @Autowired
    private SysUsersMapper sysUsersMapper;

    @Override
    @Transactional
    @CacheEvict(value="sysOrgCache")
    public SysOrgVO saveBase(SysOrgDTO addDTO) {
        Integer count = baseMapper.selectCount(
                new LambdaQueryWrapper<SysOrg>().eq(SysOrg::getId, addDTO.getOrgCode()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该部门编号已被注册");
        }
        SysOrg sysOrg = ObjectConvertUtils.copyToDest(addDTO, SysOrg.class);
        baseMapper.insert(sysOrg);
        return ObjectConvertUtils.copyToDest(sysOrg, SysOrgVO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value="sysOrgCache")
    public boolean removeBase(Collection<? extends Serializable> idList) {
        AtomicReference<Integer> count = new AtomicReference<>(0);
        idList.forEach(id -> {
            count.set(sysUsersMapper.selectCount(new LambdaQueryWrapper<SysUsers>().eq(SysUsers::getOrgId, id)));
        });
        if (count.get() > 0) {
            throw new CommonsRuntimeException(UNEXPECTED_ERROR,"已选机构下存在成员数据，不能删除！");
        }
        int result = baseMapper.deleteBatchIds(idList);
        if (result != idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result == idList.size();
    }

    @Override
    @Transactional
    @CacheEvict(value="sysOrgCache")
    public boolean updateBase(SysOrgDTO updateDTO) {
        Integer count = baseMapper.selectCount(
                new LambdaQueryWrapper<SysOrg>().eq(SysOrg::getId, updateDTO.getId()));
        if (count == 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "原数据不正确");
        }
        SysOrg sysOrg = ObjectConvertUtils.copyToDest(updateDTO, SysOrg.class);
        return baseMapper.updateById(sysOrg) > 0;
    }

    @Override
    @Cacheable(value = "sysOrgCache", key = "method.name+'_'+#p0")
    public SysOrgVO getBase(Serializable id) {
        SysOrg sysOrg = baseMapper.selectById(id);
        return ObjectConvertUtils.copyToDest(sysOrg, SysOrgVO.class);
    }

    @Override
    @Cacheable(value = "sysOrgCache", key = "method.name+'_'+#p0")
    public List<SysOrgVO> getListByObjs(SysOrgDTO findDTO) {
        QueryWrapper<SysOrg> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(findDTO.getOrgCode())) {
            qw.lambda().eq(SysOrg::getOrgCode, findDTO.getOrgCode());
        }
        if (StrUtil.isNotEmpty(findDTO.getParentId())) {
            qw.lambda().eq(SysOrg::getParentId, findDTO.getParentId());
        }
        if (StrUtil.isNotEmpty(findDTO.getOrgName())) {
            qw.lambda().eq(SysOrg::getOrgName, findDTO.getOrgName());
        }
        List<SysOrg> deptList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(deptList, SysOrgVO.class);
    }

    @Override
    @Cacheable(value = "sysOrgCache", key = "method.name+'_'+#p0")
    public Page<SysOrgVO> getPageByObjs(SysOrgDTO findDTO, RequestDTO requestDTO) {
        QueryWrapper<SysOrg> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(requestDTO.getSearchValue())) {
            qw.lambda().eq(SysOrg::getOrgCode, requestDTO.getSearchValue())
                    .or().like(SysOrg::getOrgName, requestDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysOrg::getCreatedTime);

        Page<SysOrg> page = new Page<>(requestDTO.getPageNumber(), requestDTO.getPageSize());
        IPage<SysOrg> deptPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(deptPage, SysOrgVO.class, new Page<>());
    }
}
