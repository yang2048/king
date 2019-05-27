package vip.websky.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright yang2048@qq.com @沐之永
 * @Author: Yong.Yang
 * @Date: 2018/7/21 22:47
 */
@Slf4j
public class ObjectConvertUtils {

    /**
     * 1.对象属性复制
     *
     * @param dest 目标对象
     * @param orgi 源对象
     * @throws CommonsRuntimeException 转换异常
     */
    public static void convert(Object orgi, Object dest) {

        try {
            BeanUtils.copyProperties(orgi, dest);
            log.debug("转换之前数据 ==> {}", orgi);
            log.debug("转换之后数据 <== {}", dest);
        } catch (Exception e) {
            throw new CommonsRuntimeException(StatusCode.OBJECT_CONVERT_ERROR);
        }
    }


    /**
     * 2.对象转化,生成需要的对象
     *
     * @param orgi   源对象
     * @param tClass 目标对象
     * @param <T>    目标对象 类型
     * @return 目标对象实体
     * @throws CommonsRuntimeException 转换异常
     */
    public static <T> T copyToDest(Object orgi, Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            BeanUtils.copyProperties(orgi, t);
            log.debug("转换之前数据 ==> {}", orgi);
            log.debug("转换之后数据 <== {}", t);
            return t;
        } catch (Exception e) {
            throw new CommonsRuntimeException(StatusCode.OBJECT_CONVERT_ERROR);
        }
    }

    /**
     * 3.列表转换
     *
     * @param orgList 源列表
     * @param tClass  目标列表元素类型
     * @param <T>     目标列表元素类型
     * @return 目标列表
     * @throws CommonsRuntimeException
     */
    public static <T> List<T> copyToList(List orgList, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        orgList.forEach(object -> list.add(copyToDest(object, tClass)));
        return list;
    }

    /**
     * 4.分页数据转换
     *
     * @param orgList
     * @param tClass
     * @param page
     * @param <T>
     * @return
     */
    public static <T> Page<T> copyToPage(IPage orgList, Class<T> tClass, Page<T> page) {
        convert(orgList, page);
        List<T> list = copyToList(orgList.getRecords(), tClass);
        page.setRecords(list);
        return page;
    }

}


