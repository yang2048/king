package vip.websky.core.base.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.websky.core.base.model.dto.RequestDTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface IBaseService<E, VO, DTO> extends IService<E> {

    VO saveBase(DTO addDTO);

    boolean removeBase(Collection<? extends Serializable> idList);

    boolean updateBase(DTO updateDTO);

    VO getBase(Serializable id);

    List<VO> getListByObjs(DTO findDTO);

    Page<VO> getPageByObjs(DTO findDTO, RequestDTO requestDTO);
}
