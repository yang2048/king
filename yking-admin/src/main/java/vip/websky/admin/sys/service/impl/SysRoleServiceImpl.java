package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.websky.admin.sys.dao.SysRoleMapper;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.enums.StatusEnum;
import vip.websky.admin.sys.model.pojo.SysRole;
import vip.websky.admin.sys.model.vo.SysRoleVO;
import vip.websky.admin.sys.service.ISysRoleService;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.util.List;

/**
 * 角色信息(SysRole)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-11 18:00:23
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public SysRoleVO saveSysRole(SysRoleDTO sysRoleDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysRole>().lambda().eq(SysRole::getRoleName, sysRoleDTO.getRoleName()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该角色名已被创建");
        }
        SysRole sysRole = ObjectConvertUtils.copyToDest(sysRoleDTO, SysRole.class);
        sysRole.setRoleStatus(StatusEnum.normal);
        int result = baseMapper.insert(sysRole);
        return result > 0 ? ObjectConvertUtils.copyToDest(sysRole, SysRoleVO.class) : null;
    }

    @Override
    public SysRoleVO updateSysRole(SysRoleDTO sysRoleDTO) {
        SysRole role = ObjectConvertUtils.copyToDest(sysRoleDTO, SysRole.class);
        int result = baseMapper.updateById(role);
        return result > 0 ? ObjectConvertUtils.copyToDest(role, SysRoleVO.class) : null;
    }

    @Override
    public Page<SysRoleVO> getSysRolePage(SysRoleDTO sysRoleDTO) {
        Page<SysRoleVO> roleVOPage = new Page<>();
        QueryWrapper<SysRole> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysRoleDTO.getSearchValue())) {
            qw.lambda().like(SysRole::getRoleName, sysRoleDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysRole::getRoleStatus).orderByDesc(SysRole::getCreatedTime);
        Page<SysRole> page = new Page<>(sysRoleDTO.getPageNumber(), sysRoleDTO.getPageSize(), false);
        IPage<SysRole> rolePage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(rolePage, SysRoleVO.class, roleVOPage);
    }

    @Override
    public void removeSysRole(List<String> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result < idList.size()){
            throw new CommonsRuntimeException(StatusCode.RTN_CODE_UNKNOW_ERROR, "删除异常");
        }
    }

    @Override
    public List<SysRoleVO> getSysRoleByObjs(SysRoleDTO sysRoleDTO) {
        QueryWrapper<SysRole> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysRoleDTO.getRoleId())) {
            qw.lambda().eq(SysRole::getRoleId, sysRoleDTO.getRoleId());
        }
        if (StrUtil.isNotEmpty(sysRoleDTO.getRoleName())) {
            qw.lambda().eq(SysRole::getRoleName, sysRoleDTO.getRoleName());
        }
        List<SysRole> roleList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(roleList, SysRoleVO.class);
    }
}