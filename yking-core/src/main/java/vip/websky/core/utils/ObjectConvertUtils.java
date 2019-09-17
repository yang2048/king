package vip.websky.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import vip.websky.core.config.prompt.StatusCode;
import vip.websky.core.exception.CommonsRuntimeException;

import javax.validation.constraints.NotNull;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

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
//            log.debug("转换之前数据 ==> {}", orgi);
//            log.debug("转换之后数据 <== {}", dest);
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
//            log.debug("转换之前数据 ==> {}", orgi);
//            log.debug("转换之后数据 <== {}", t);
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

    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @param clazz      集合元素类型
     * @return 转换后的树形结构
     */
    public static <T> Collection<T> toTree(@NotNull Collection<T> collection, @NotNull Class<T> clazz) {
        return toTree(collection, null, null, null, clazz);
    }

    /**
     * 集合转树结构
     *
     * @param collection 目标集合
     * @param id         节点编号字段名称
     * @param parent     父节点编号字段名称
     * @param children   子节点集合属性名称
     * @param clazz      集合元素类型
     * @return 转换后的树形结构
     */
    public static <T> Collection<T> toTree(@NotNull Collection<T> collection, String id, String parent, String children, @NotNull Class<T> clazz) {
        TimeInterval timer = DateUtil.timer();
        timer.start();
        try {
            if (collection == null || collection.isEmpty()) return null;// 如果目标集合为空,直接返回一个空树
            if (StringUtils.isEmpty(id)) id = "id";                     // 如果被依赖字段名称为空则默认为id
            if (StringUtils.isEmpty(parent)) parent = "parentId";         // 如果依赖字段为空则默认为parent
            if (StringUtils.isEmpty(children)) children = "children";   // 如果子节点集合属性名称为空则默认为children

            // 初始化根节点集合, 支持 Set 和 List
            Collection<T> roots;
            if (collection.getClass().isAssignableFrom(Set.class)) {
                roots = new HashSet<>();
            } else {
                roots = new ArrayList<>();
            }

            // 获取 id 字段, 从当前对象或其父类
            Field idField;
            try {
                idField = clazz.getDeclaredField(id);
            } catch (NoSuchFieldException e1) {
                idField = clazz.getSuperclass().getDeclaredField(id);
            }

            // 获取 parentId 字段, 从当前对象或其父类
            Field parentField;
            try {
                parentField = clazz.getDeclaredField(parent);
            } catch (NoSuchFieldException e1) {
                parentField = clazz.getSuperclass().getDeclaredField(parent);
            }

            // 获取 children 字段, 从当前对象或其父类
            Field childrenField;
            try {
                childrenField = clazz.getDeclaredField(children);
            } catch (NoSuchFieldException e1) {
                childrenField = clazz.getSuperclass().getDeclaredField(children);
            }

            // 设置为可访问
            idField.setAccessible(true);
            parentField.setAccessible(true);
            childrenField.setAccessible(true);

            Map<Object, T> idKeyMap = new HashMap<>();
            for (T c : collection) {
                Object idKey = idField.get(c);
                idKeyMap.put(idKey, c);
            }

            for (T c : collection) {
                Object parentId = parentField.get(c);
                if (idKeyMap.get(parentId) == null) {
                    parentId = null;
                }
                if (isRootNode(parentId)) {
                    roots.add(c);
                }
            }
            // 从目标集合移除所有根节点
            collection.removeAll(roots);

            // 遍历根节点, 依次添加子节点
            for (T root : roots) {
                addChild(root, collection, idField, parentField, childrenField);
            }

            // 关闭可访问
            idField.setAccessible(false);
            parentField.setAccessible(false);
            childrenField.setAccessible(false);
            System.out.println("==========树形数据转换结束 " + timer.intervalRestart());
            return roots;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 为目标节点添加孩子节点
     *
     * @param node          目标节点
     * @param collection    目标集合
     * @param idField       ID 字段
     * @param parentField   父节点字段
     * @param childrenField 字节点字段
     */
    private static <T> void addChild(@NotNull T node, @NotNull Collection<T> collection, @NotNull Field idField,
                                     @NotNull Field parentField, @NotNull Field childrenField) throws IllegalAccessException {
        Object id = idField.get(node);
        Collection<T> children = (Collection<T>) childrenField.get(node);
        // 如果子节点的集合为 null, 初始化孩子集合
        if (children == null) {
            if (collection.getClass().isAssignableFrom(Set.class)) {
                children = new HashSet<>();
            } else children = new ArrayList<>();
        }

        for (T t : collection) {
            Object o = parentField.get(t);
            if (id.equals(o)) {
                // 将当前节点添加到目标节点的孩子节点
                children.add(t);
                // 重设目标节点的孩子节点集合,这里必须重设,因为如果目标节点的孩子节点是null的话,这样是没有地址的,就会造成数据丢失,所以必须重设,如果目标节点所在类的孩子节点初始化为一个空集合,而不是null,则可以不需要这一步,因为java一切皆指针
                childrenField.set(node, children);
                // 递归添加孩子节点
                addChild(t, collection, idField, parentField, childrenField);
            }
        }
    }

    /**
     * 判断是否是根节点, 判断方式为: 父节点编号为空或为 0, 则认为是根节点. 此处的判断应根据自己的业务数据而定.
     *
     * @param parentId 父节点编号
     * @return 是否是根节点
     */
    private static boolean isRootNode(Object parentId) {
        boolean flag = false;
        if (parentId == null) {
            flag = true;
        } else if (parentId instanceof String && (StringUtils.isEmpty(parentId) || parentId.equals("0"))) {
            flag = true;
        } else if (parentId instanceof Integer && Integer.valueOf(0).equals(parentId)) {
            flag = true;
        }
        return flag;
    }

    /**
     * List<Map>转树形结构
     *
     * @param sourceList
     * @param idKey
     * @param parentKey
     * @param childrenStr
     * @return
     */
    public static List<Map> listToTree(@NotNull List<Map<String, Object>> sourceList, @NotNull String idKey,
                                       @NotNull String parentKey, @NotNull String childrenStr) {
        Map<Object, Map> idKeyMap = new HashMap<>();
        for (Map itemMap : sourceList) {
            idKeyMap.put(itemMap.get(idKey), itemMap);
        }

        List<Map> resultTreeList = new ArrayList<>();
        for (Map itemMap : sourceList) {
            // 没有父节点，表明是一级节点
            Object pid = itemMap.get(parentKey);
            itemMap.remove(parentKey);
            //不能注释
            if (idKeyMap.get(pid) == null) {
                pid = null;
            }

            if (pid == null || pid.equals(itemMap.get(idKey))) {
                resultTreeList.add(itemMap);
            } else {
                Map parant = idKeyMap.get(pid);
                if (parant == null) {
                    parant = new HashMap();
                    parant.put(idKey, pid);
                    idKeyMap.put(pid, parant);
                    resultTreeList.add(parant);
                }
                if (parant.get(childrenStr) == null) {
                    parant.put(childrenStr, new ArrayList());
                }
                List children = (List) parant.get(childrenStr);
                children.add(itemMap);
            }
        }
        return resultTreeList;
    }

    /**
     * 集合转List<Map<String, Object>>
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> parse(List list) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object o : list) {
            resultList.add(transBean2Map(o));
        }
        return resultList;
    }

    /**
     * 对象转Map
     *
     * @param obj
     * @return
     */
    private static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
}


