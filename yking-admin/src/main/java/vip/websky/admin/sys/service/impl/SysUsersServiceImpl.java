package vip.websky.admin.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.websky.admin.sys.constants.DbConstants;
import vip.websky.admin.sys.dao.SysUsersMapper;
import vip.websky.admin.sys.model.dto.SysRoleDTO;
import vip.websky.admin.sys.model.dto.SysUsersDTO;
import vip.websky.admin.sys.model.enums.StatusEnum;
import vip.websky.admin.sys.model.pojo.SysUsers;
import vip.websky.admin.sys.model.vo.SysUsersVO;
import vip.websky.admin.sys.service.ISysUsersService;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;
import vip.websky.core.utils.ObjectConvertUtils;

import java.util.List;

/**
 * 基础用户(SysUsers)表服务实现类
 *
 * @author Yong.Yang
 * @since 2019-05-11 17:53:37
 */
@Service
public class SysUsersServiceImpl extends ServiceImpl<SysUsersMapper, SysUsers> implements ISysUsersService {

    @Override
    public SysUsersVO saveSysUsers(SysUsersDTO sysUsersDTO) {
        Integer count = baseMapper.selectCount(
                new QueryWrapper<SysUsers>().lambda().eq(SysUsers::getUserAccount, sysUsersDTO.getUserAccount()));
        if (count > 0) {
            throw new CommonsRuntimeException(StatusCode.RTN_NOT_NULL, "该用户名已被注册");
        }
        SysUsers user = ObjectConvertUtils.copyToDest(sysUsersDTO, SysUsers.class);
        if (StrUtil.isEmpty(user.getNickname())){
            user.setNickname("用户"+user.getUserAccount());
        }
        user.setOrgId(DbConstants.ORG_DEFAULT);
        user.setCipher("123456");
        user.setCreatedBy("admin");
        user.setState(StatusEnum.normal);
        int result = baseMapper.insert(user);
        return result > 0 ? ObjectConvertUtils.copyToDest(user, SysUsersVO.class) : null;
    }

    @Override
    public SysUsersVO updateSysUsers(SysUsersDTO sysUsersDTO) {
        SysUsers user = ObjectConvertUtils.copyToDest(sysUsersDTO, SysUsers.class);
        int result = baseMapper.updateById(user);
        return result > 0 ? ObjectConvertUtils.copyToDest(user, SysUsersVO.class) : null;
    }

    @Override
    public Page<SysUsersVO> getSysUsersPage(SysUsersDTO sysUsersDTO) {
        Page<SysUsersVO> userVOIPage = new Page<>();
        QueryWrapper<SysUsers> qw = new QueryWrapper<>();
        if (sysUsersDTO.getSex() != null) {
            qw.lambda().eq(SysUsers::getSex, sysUsersDTO.getSex());
        }
        if (StrUtil.isNotEmpty(sysUsersDTO.getState())) {
            qw.lambda().eq(SysUsers::getState, sysUsersDTO.getState());
        }
        if (StrUtil.isNotEmpty(sysUsersDTO.getSearchValue())) {
            qw.lambda().like(SysUsers::getUserAccount, sysUsersDTO.getSearchValue())
                    .or().like(SysUsers::getEmail, sysUsersDTO.getSearchValue())
                    .or().like(SysUsers::getPhone, sysUsersDTO.getSearchValue());
        }
        qw.lambda().orderByDesc(SysUsers::getState).orderByDesc(SysUsers::getCreatedTime);
        Page<SysUsers> page = new Page<>(sysUsersDTO.getPageNumber(), sysUsersDTO.getPageSize());
        IPage<SysUsers> userPage = baseMapper.selectPage(page, qw);
        return ObjectConvertUtils.copyToPage(userPage, SysUsersVO.class, userVOIPage);
    }

    @Override
    public void removeSysUsers(List<String> idList) {
        int result = baseMapper.deleteBatchIds(idList);
        if (result < idList.size()){
            throw new CommonsRuntimeException(StatusCode.RTN_CODE_UNKNOW_ERROR, "删除异常");
        }
    }

    @Override
    public List<SysUsersVO> getSysUsersByObjs(SysUsersDTO sysUsersDTO) {
        QueryWrapper<SysUsers> qw = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(sysUsersDTO.getUserId())) {
            qw.lambda().eq(SysUsers::getUserId, sysUsersDTO.getUserId());
        }
        if (StrUtil.isNotEmpty(sysUsersDTO.getUserAccount())) {
            qw.lambda().eq(SysUsers::getUserAccount, sysUsersDTO.getUserAccount());
        }
        if (StrUtil.isNotEmpty(sysUsersDTO.getEmail())) {
            qw.lambda().eq(SysUsers::getEmail, sysUsersDTO.getEmail());
        }
        if (StrUtil.isNotEmpty(sysUsersDTO.getPhone())) {
            qw.lambda().eq(SysUsers::getPhone, sysUsersDTO.getPhone());
        }
        if (StrUtil.isNotEmpty(sysUsersDTO.getOrgId())) {
            qw.lambda().eq(SysUsers::getOrgId, sysUsersDTO.getOrgId());
        }
        List<SysUsers> usersList = baseMapper.selectList(qw);
        return ObjectConvertUtils.copyToList(usersList, SysUsersVO.class);
    }

    @Override
    public void addRole(SysUsersDTO sysUsersDTO, SysRoleDTO sysRoleDTO) {

    }
}