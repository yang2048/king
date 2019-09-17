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
import vip.websky.admin.sys.dao.SysConfMapper;
import vip.websky.admin.sys.model.dto.SysConfDTO;
import vip.websky.admin.sys.model.pojo.SysConf;
import vip.websky.admin.sys.model.vo.SysConfVO;
import vip.websky.admin.sys.service.ISysConfService;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Yong.Yang
 * @since 2019/8/9 23:39
 */
@Service
@Transactional(readOnly = true)
public class SysConfServiceImpl extends ServiceImpl<SysConfMapper, SysConf> implements ISysConfService {

    @Override
    @Transactional
    @CacheEvict(value="confCache")
    public SysConfVO saveBase(SysConfDTO addDTO) {
        Integer count = baseMapper.selectCount(
                new LambdaQueryWrapper<SysConf>().eq(SysConf::getConfRemark, addDTO.getConfKey()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该键名已被注册");
        }
        SysConf sysConf = ObjectConvertUtils.copyToDest(addDTO, SysConf.class);
        baseMapper.insert(sysConf);
        return ObjectConvertUtils.copyToDest(sysConf, SysConfVO.class);
    }

    @Override
    @Transactional
    @CacheEvict(value="confCache")
    public boolean removeBase(Collection<? extends Serializable> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result != idList.size()) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return result == idList.size();
    }

    @Override
    @Transactional
    @CacheEvict(value="confCache")
    public boolean updateBase(SysConfDTO updateDTO) {
        Integer count = baseMapper.selectCount(new LambdaQueryWrapper<SysConf>()
                .eq(SysConf::getId, updateDTO.getId())
                .eq(SysConf::getConfKey, updateDTO.getConfKey()));
        if (count == 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "原数据不正确");
        }
        SysConf sysConf = ObjectConvertUtils.copyToDest(updateDTO, SysConf.class);
        return baseMapper.updateById(sysConf) > 0;
    }

    @Override
    @Cacheable(value = "confCache", key = "method.name+'_'+#p0")
    public SysConfVO getBase(Serializable id) {
        SysConf sysConf = baseMapper.selectById(id);
        return ObjectConvertUtils.copyToDest(sysConf, SysConfVO.class);
    }

    @Override
    @Cacheable(value = "confCache", key = "method.name+'_'+#p0")
    public List<SysConfVO> getListByObjs(SysConfDTO findDTO) {
        QueryWrapper<SysConf> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(findDTO.getConfKey())) {
            qw.lambda().eq(SysConf::getConfKey, findDTO.getConfKey());
        }
        if (StrUtil.isNotEmpty(findDTO.getConfValue())) {
            qw.lambda().eq(SysConf::getConfValue, findDTO.getConfValue());
        }
        List<SysConf> confList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(confList, SysConfVO.class);
    }

    @Override
    @Cacheable(value = "confCache", key = "method.name+'_'+#p0")
    public Page<SysConfVO> getPageByObjs(SysConfDTO findDTO, RequestDTO requestDTO) {

        QueryWrapper<SysConf> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(findDTO.getConfKey())) {
            qw.lambda().eq(SysConf::getConfKey, findDTO.getConfKey());
        }
        if (StrUtil.isNotEmpty(findDTO.getConfValue())) {
            qw.lambda().eq(SysConf::getConfValue, findDTO.getConfValue());
        }
        Page<SysConf> page = new Page<>(requestDTO.getPageNumber(), requestDTO.getPageSize());
        IPage<SysConf> confIPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(confIPage, SysConfVO.class, new Page<>());
    }
}
