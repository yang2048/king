package vip.websky.core.base.action;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vip.websky.core.base.model.dto.RequestDTO;
import vip.websky.core.base.model.dto.ResponseDTO;
import vip.websky.core.base.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * @author yang2048@qq.com @Y.Yang
 * @since 2019/9/30 10:29
 */
public interface CrudAction<E, VO, DTO, S extends IBaseService<E, VO, DTO>> {

    S baseService();

    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    default ResponseDTO save(@RequestBody @Validated DTO addDTO) {
        return ResponseDTO.success(baseService().saveBase(addDTO));
    }

    @RequestMapping(value = "/update", method = {RequestMethod.PATCH})
    default ResponseDTO update(@RequestBody @Validated DTO updateDTO) {
        return ResponseDTO.success(baseService().updateBase(updateDTO));
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE})
    default ResponseDTO remove(@RequestBody @ApiParam("ID集合") List<String> id) {
        boolean result = baseService().removeBase(id);
        if (!result){
            return ResponseDTO.error("000003","该数据异常,请重试");
        }
        return ResponseDTO.success(true);
    }

    @RequestMapping(value = "/get/{id}", method = {RequestMethod.GET})
    default ResponseDTO get(@PathVariable("id") String id) {
        return ResponseDTO.success(baseService().getBase(id));
    }

    @RequestMapping(value = "/getList", method = {RequestMethod.GET})
    default ResponseDTO<List> getList(Map<String, Object> searchValue, DTO findDTO) {
        return ResponseDTO.success(baseService().getListByObjs(findDTO));
    }

    @RequestMapping(value = "/getPage", method = {RequestMethod.GET})
    default ResponseDTO<Page> getPage(Map<String, Object> searchValue, DTO findDTO, RequestDTO requestDTO) {
        return ResponseDTO.success(baseService().getPageByObjs(findDTO, requestDTO));
    }

}
